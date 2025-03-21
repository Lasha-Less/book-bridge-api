package com.lb.book_bridge_api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "BookResponse")
public class BookResponse {

    private String kind;
    private int totalItems;
    private List<BookItem> items;

    @JacksonXmlProperty(localName = "kind")
    @JsonProperty("kind")  // Needed for JSON parsing
    public String getKind() {
        return kind;
    }

    @JacksonXmlProperty(localName = "totalItems")
    @JsonProperty("totalItems")
    public int getTotalItems() {
        return totalItems;
    }

    @JacksonXmlProperty(localName = "items")
    @JsonProperty("items")
    public List<BookItem> getItems() {
        return items;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public void setItems(List<BookItem> items) {
        this.items = items;
    }

}
