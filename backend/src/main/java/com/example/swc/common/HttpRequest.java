package com.example.swc.common;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.fluent.Request;

import java.io.IOException;

public class HttpRequest<T> {

    private String uri;

    public HttpRequest(String uri) {
        this.uri = uri;
    }

    public T get(Class<T> targetDtoClass) throws IOException {
        return new ObjectMapper()
                .readValue(getAsString(),
                        targetDtoClass);
    }


    public T getAsType(TypeReference<T> targetType) throws IOException {
        return new ObjectMapper()
                .readValue(getAsString(),
                        targetType);
    }

    public String getAsString() throws IOException {
        return Request.Get(uri)
                .execute()
                .returnContent()
                .toString();
    }
}
