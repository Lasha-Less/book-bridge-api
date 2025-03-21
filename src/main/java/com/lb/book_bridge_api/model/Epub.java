package com.lb.book_bridge_api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Epub {

    @JacksonXmlProperty(localName = "isAvailable")
    @JsonProperty("isAvailable")
    private boolean isAvailable;

    @JacksonXmlProperty(localName = "downloadLink")
    @JsonProperty("downloadLink")
    private String downloadLink;

    @JacksonXmlProperty(localName = "acsTokenLink")
    @JsonProperty("acsTokenLink")
    private String acsTokenLink;

    // Getters and Setters
    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }

    public String getAcsTokenLink() {
        return acsTokenLink;
    }

    public void setAcsTokenLink(String acsTokenLink) {
        this.acsTokenLink = acsTokenLink;
    }

}
