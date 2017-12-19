package com.hotmail.pederwaern.christmas_gift_app.service;

import com.hotmail.pederwaern.christmas_gift_app.domain.Wish;
import com.hotmail.pederwaern.christmas_gift_app.repository.WishRepository;
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

}
