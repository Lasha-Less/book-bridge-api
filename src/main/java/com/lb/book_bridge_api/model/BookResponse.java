package com.lb.book_bridge_api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "BookResponse")
public class BookResponse {

    @JacksonXmlProperty(localName = "kind")
    @JsonProperty("kind")
    private String kind;

    @JacksonXmlProperty(localName = "totalItems")
    @JsonProperty("totalItems")
    private int totalItems;

    @JacksonXmlProperty(localName = "items")
    @JsonProperty("items")
    private List<BookItem> items;


    public String getKind() {
        return kind;
    }


    public int getTotalItems() {
        return totalItems;
    }

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
