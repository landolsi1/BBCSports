package com.appsolute.rami.bbcsports.Entities;

import java.io.Serializable;

/**
 * Created by Rami on 1/4/2018.
 */

public class Sport implements Serializable{

    private static final long serialVersionUID = 1L;

    String source,title,author,url,description,publishedAt;
    String urlToImage;


    public Sport() {
    }

    public Sport(String source, String title, String author, String url, String urlToImage, String description, String publishedAt) {
        this.source = source;
        this.title = title;
        this.author = author;
        this.url = url;
        this.urlToImage = urlToImage;
        this.description = description;
        this.publishedAt = publishedAt;
    }

    public Sport(String source, String title, String author, String url, String urlToImage, String description, String publishedAt, int id) {
        this.source = source;
        this.title = title;
        this.author = author;
        this.url = url;
        this.urlToImage = urlToImage;
        this.description = description;
        this.publishedAt = publishedAt;
        this.id = id;
    }

    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }
}
