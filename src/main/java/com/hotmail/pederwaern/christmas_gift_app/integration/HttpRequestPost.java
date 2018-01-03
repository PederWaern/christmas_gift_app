package com.hotmail.pederwaern.christmas_gift_app.integration;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

public class HttpRequestPost {

    private String payload;
    private String URI;

    public HttpRequestPost(String payload, String URI) {
        this.payload = payload;
        this.URI = URI;
    }

    public void sendHttpRequest() {
        StringEntity entity = new StringEntity(payload, ContentType.APPLICATION_JSON);
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(URI);
        request.setEntity(entity);

        HttpResponse response = null;
        try {
            response = httpClient.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
