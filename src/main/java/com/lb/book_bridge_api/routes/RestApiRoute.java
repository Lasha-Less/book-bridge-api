package com.lb.book_bridge_api.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class RestApiRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        restConfiguration()
                .component("servlet")
                .bindingMode(RestBindingMode.auto);

        // The REST endpoint: any query parameter supplied will be mapped to a header.
        rest("/api")
                .get("/convert-to-xml")
                .to("direct:jsonToXml");

    }
}
