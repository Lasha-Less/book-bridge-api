package com.lb.book_bridge_api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class BookItem {

    @JacksonXmlProperty(localName = "volumeInfo")
    @JsonProperty("volumeInfo")
    private VolumeInfo volumeInfo;

    @JacksonXmlProperty(localName = "accessInfo")
    @JsonProperty("accessInfo")
    private AccessInfo accessInfo;

    // Getters and Setters
    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(VolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }

    public AccessInfo getAccessInfo() {
        return accessInfo;
    }

    public void setAccessInfo(AccessInfo accessInfo) {
        this.accessInfo = accessInfo;
    }

}
