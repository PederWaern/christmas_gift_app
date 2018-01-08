package com.hotmail.pederwaern.christmas_gift_app.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotmail.pederwaern.christmas_gift_app.domain.Wish;
import com.hotmail.pederwaern.christmas_gift_app.domain.WishMailWrapper;
import com.hotmail.pederwaern.christmas_gift_app.exception.ControllerExceptionHandler;
import com.hotmail.pederwaern.christmas_gift_app.integration.HttpRequestPost;
import com.hotmail.pederwaern.christmas_gift_app.repository.WishRepository;
import com.hotmail.pederwaern.christmas_gift_app.utils.JSONReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishService {

    private final WishRepository repository;

    @Autowired
    public WishService(WishRepository repository) {
        this.repository = repository;
    }

    public List<Wish> getAllWishes() {
        List<Wish> wishes = new ArrayList<>();
        repository.findAll().forEach(wishes::add);
        return wishes;
    }

    public Wish getWishById(Integer id) {
        final Wish wish = repository.findOne(id);
        if(wish == null) {
            throw new ControllerExceptionHandler.WishResourceNotFound();
        }
        return wish;
    }

    public String deleteWish(Integer id) {
        if(repository.findOne(id) == null || id == null) {
            throw new ControllerExceptionHandler.WishResourceNotFound();
        }
        repository.delete(id);
        return new JSONReturnMessage(id, "Wish").format();
    }

    public void saveWish(Wish wish) {
        if(wish.isBought()) {
            sendEmailToAdults(wish);
        }
        wish.setId(0);
        repository.save(wish);
    }

    private void sendEmailToAdults(Wish wish) {
        String json = parseWishToJson(wish);
        new HttpRequestPost(json, "http://mailserver:5000/sendMail")
                .sendHttpRequest();
    }

    private String parseWishToJson(Wish wish) {
        ObjectMapper mapper = new ObjectMapper();
        WishMailWrapper wishDecorator = new WishMailWrapper(wish);
        String payload = "";
        try {
            payload = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(wishDecorator);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return payload;
    }
}
