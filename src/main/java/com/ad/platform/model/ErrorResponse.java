package com.ad.platform.model;

public class ErrorResponse {
    public final String url;
    public final String ex;

    public ErrorResponse(String url, Exception ex) {
        this.url = url;
        this.ex = ex.getLocalizedMessage();
    }
}
