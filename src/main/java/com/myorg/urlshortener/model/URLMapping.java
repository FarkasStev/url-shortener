package com.myorg.urlshortener.model;

import jakarta.persistence.*;

@Entity
public class URLMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true, nullable=false)
    private String shortCode;

    @Column(nullable=false)
    private String originalUrl;


    // getters / setters

    public String getOriginalUrl() {
        return this.originalUrl;
    }

    public void setOriginalUrl(String url) {
        this.originalUrl = url;
    }

    public String getShortCode() {
        return this.originalUrl;
    }

    public void setShortCode(String code) {
        this.shortCode = code;
    }

    public String getId() {
        return this.originalUrl;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
