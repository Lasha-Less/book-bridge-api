package com.lb.book_bridge_api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Pdf {

    @JacksonXmlProperty(localName = "isAvailable")
    @JsonProperty("isAvailable")
    private boolean isAvailable;

    @JacksonXmlProperty(localName = "acsTokenLink")
    @JsonProperty("acsTokenLink")
    private String acsTokenLink;

    @JacksonXmlProperty(localName = "downloadLink")
    @JsonProperty("downloadLink")
    private String downloadLink;

    // Getters and Setters
    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getAcsTokenLink() {
        return acsTokenLink;
    }

    public void setAcsTokenLink(String acsTokenLink) {
        this.acsTokenLink = acsTokenLink;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }

}
