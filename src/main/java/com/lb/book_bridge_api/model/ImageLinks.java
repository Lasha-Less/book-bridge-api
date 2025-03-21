package com.lb.book_bridge_api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class ImageLinks {

    @JacksonXmlProperty(localName = "smallThumbnail")
    @JsonProperty("smallThumbnail")
    private String smallThumbnail;

    @JacksonXmlProperty(localName = "thumbnail")
    @JsonProperty("thumbnail")
    private String thumbnail;

    // Getters and Setters
    public String getSmallThumbnail() {
        return smallThumbnail;
    }

    public void setSmallThumbnail(String smallThumbnail) {
        this.smallThumbnail = smallThumbnail;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

}
