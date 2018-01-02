package com.hotmail.pederwaern.christmas_gift_app.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotmail.pederwaern.christmas_gift_app.domain.Wish;
import com.hotmail.pederwaern.christmas_gift_app.domain.WishMailWrapper;
import com.hotmail.pederwaern.christmas_gift_app.exception.ControllerExceptionHandler;
import com.hotmail.pederwaern.christmas_gift_app.repository.WishRepository;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class WishService {

    private final WishRepository repository;
    private final AdultService adultService;

    @Autowired
    public WishService(WishRepository repository, AdultService adultService) {
        this.repository = repository;
        this.adultService = adultService;
    }

    public List<Wish> getAllWishes() {
        List<Wish> wishes = new ArrayList<>();
        repository.findAll().forEach(wishes::add);
        return wishes;
    }

    public Wish getWishById(Integer id) {
        final Wish wish = repository.findOne(id);
        if (wish == null) {
            throw new ControllerExceptionHandler.WishResourceNotFound();
        }
        return wish;
    }

    public void saveWish(Wish wish) {
        if(wish.isBought()) {
            sendEmailToAdults(wish);
        }
        repository.save(wish);
    }

    private void sendEmailToAdults(Wish wish) {
        String json = parseWishToJson(wish);
        send_HTTP_Request(json);
    }

    private String parseWishToJson(Wish wish) {
        ObjectMapper mapper = new ObjectMapper();
        WishMailWrapper wishDecorator = new WishMailWrapper(wish);
        String payload = "";
        try {
            payload = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(wishDecorator);
            System.out.println(payload);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return payload;
    }

    private void send_HTTP_Request(String payload) {
        StringEntity entity = new StringEntity(payload,
                ContentType.APPLICATION_JSON);

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost("http://localhost:5000/sendMail");
        request.setEntity(entity);

        HttpResponse response = null;
        try {
            response = httpClient.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(response.getStatusLine().getStatusCode());
    }

    //TODO Flytta ut logiken till en egen klass?
}
