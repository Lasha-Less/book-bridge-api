package com.lb.book_bridge_api.routes;

import com.lb.book_bridge_api.model.BookResponse;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.dataformat.JacksonXMLDataFormat;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

@Component
public class JsonToXmlRoute extends RouteBuilder {

    private static final String DATA_DIRECTORY = "data/";

    @Override
    public void configure() throws Exception {
        JacksonXMLDataFormat xmlDataFormat = new JacksonXMLDataFormat();

        from("direct:jsonToXml")
                .log("Received request for JSON to XML conversion")

                // Select JSON file (either requested file or latest file)
                .process(exchange -> {
                    File selectedFile = getJsonFile(exchange.getIn().getHeader("fileName", String.class));
                    exchange.getIn().setHeader("selectedJsonFile", selectedFile.getAbsolutePath());
                })
                .log("Selected JSON file: ${header.selectedJsonFile}")

                // Read the file content
                .setBody().simple("resource:file:${header.selectedJsonFile}")
                .convertBodyTo(String.class)
                .log("Raw JSON content: ${body}")

                // Ensure file is not empty
                .choice()
                .when(simple("${body} == null || ${body.trim().length()} == 0"))
                .log("ERROR: JSON file is empty")
                .setBody(constant("Error: JSON file is empty"))
                .stop()
                .end()

                // Unmarshal JSON to Java Object (Replace BookResponse.class with the correct model)
                .unmarshal(new JacksonDataFormat(BookResponse.class))
                .log("Converted Java object: ${body}")

                // Convert to XML
                .marshal(xmlDataFormat)
                .log("Converted XML output: ${body}");

        // New body-based route
        from("direct:jsonToXmlFromBody")
                .log("Received in-memory JSON for conversion")
                .unmarshal(new JacksonDataFormat(BookResponse.class))
                .log("Converted to Java object: ${body}")
                .marshal(xmlDataFormat)
                .log("Converted to XML: ${body}");
    }

    /**
     * Retrieves the requested JSON file, or the latest available JSON file if no file is specified.
     */
    private File getJsonFile(String requestedFile) {
        File selectedFile;

        if (requestedFile != null && !requestedFile.isEmpty()) {
            selectedFile = new File(DATA_DIRECTORY + requestedFile);
            if (!selectedFile.exists()) {
                throw new RuntimeException("Requested file not found: " + requestedFile);
            }
        } else {
            // Pick the most recent JSON file
            File directory = new File(DATA_DIRECTORY);
            File[] jsonFiles = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".json"));

            if (jsonFiles == null || jsonFiles.length == 0) {
                throw new RuntimeException("No JSON files found in " + DATA_DIRECTORY);
            }

            selectedFile = Arrays.stream(jsonFiles)
                    .max(Comparator.comparingLong(File::lastModified))
                    .orElseThrow(() -> new RuntimeException("No valid JSON files found"));
        }

        return selectedFile;
    }

}
