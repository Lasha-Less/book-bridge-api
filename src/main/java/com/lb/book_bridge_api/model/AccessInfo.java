package com.lb.book_bridge_api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class AccessInfo {

    @JacksonXmlProperty(localName = "epub")
    @JsonProperty("epub")
    private Epub epub;

    @JacksonXmlProperty(localName = "pdf")
    @JsonProperty("pdf")
    private Pdf pdf;

    // Getters and Setters
    public Epub getEpub() {
        return epub;
    }

    public void setEpub(Epub epub) {
        this.epub = epub;
    }

    public Pdf getPdf() {
        return pdf;
    }

    public void setPdf(Pdf pdf) {
        this.pdf = pdf;
    }

}
