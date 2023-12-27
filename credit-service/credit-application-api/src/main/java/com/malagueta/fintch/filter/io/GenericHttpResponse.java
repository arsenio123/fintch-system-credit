package com.malagueta.fintch.filter.io;

public class GenericHttpResponse {
    int httpErrorCode;
    String httpBody;
    String errorMessage;

    public int getHttpErrorCode() {
        return httpErrorCode;
    }

    public GenericHttpResponse setHttpErrorCode(int httpErrorCode) {
        this.httpErrorCode = httpErrorCode;
        return this;
    }

    public String getHttpBody() {
        return httpBody;
    }

    public GenericHttpResponse setHttpBody(String httpBody) {
        this.httpBody = httpBody;
        return this;
    }
}