package com.example.swc.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.fluent.Request;

import java.io.IOException;

public class HttpClient {
    public static <T> T invokeGet(
            String uri,
            Class<T> targetDtoClass)
            throws IOException {

        String response = Request.Get(uri)
                .execute()
                .returnContent()
                .toString();

        return new ObjectMapper()
                .readValue(
                        response,
                        targetDtoClass);
    }
}
