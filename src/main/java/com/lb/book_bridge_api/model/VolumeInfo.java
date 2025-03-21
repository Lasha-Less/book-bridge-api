package com.lb.book_bridge_api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public class VolumeInfo {

    @JacksonXmlProperty(localName = "title")
    @JsonProperty("title")
    private String title;

    @JacksonXmlProperty(localName = "authors")
    @JsonProperty("authors")
    private List<String> authors;

    @JacksonXmlProperty(localName = "publisher")
    @JsonProperty("publisher")
    private String publisher;

    @JacksonXmlProperty(localName = "publishedDate")
    @JsonProperty("publishedDate")
    private String publishedDate;

    @JacksonXmlProperty(localName = "description")
    @JsonProperty("description")
    private String description;

    @JacksonXmlProperty(localName = "categories")
    @JsonProperty("categories")
    private List<String> categories;

    @JacksonXmlProperty(localName = "imageLinks")
    @JsonProperty("imageLinks")
    private ImageLinks imageLinks;

    @JacksonXmlProperty(localName = "language")
    @JsonProperty("language")
    private String language;

    @JacksonXmlProperty(localName = "infoLink")
    @JsonProperty("infoLink")
    private String infoLink;

    @JacksonXmlProperty(localName = "canonicalVolumeLink")
    @JsonProperty("canonicalVolumeLink")
    private String canonicalVolumeLink;

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public ImageLinks getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(ImageLinks imageLinks) {
        this.imageLinks = imageLinks;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getInfoLink() {
        return infoLink;
    }

    public void setInfoLink(String infoLink) {
        this.infoLink = infoLink;
    }

    public String getCanonicalVolumeLink() {
        return canonicalVolumeLink;
    }

    public void setCanonicalVolumeLink(String canonicalVolumeLink) {
        this.canonicalVolumeLink = canonicalVolumeLink;
    }

}
