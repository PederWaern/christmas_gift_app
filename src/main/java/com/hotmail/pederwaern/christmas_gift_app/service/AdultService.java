package com.hotmail.pederwaern.christmas_gift_app.service;

import com.hotmail.pederwaern.christmas_gift_app.domain.Adult;
import com.hotmail.pederwaern.christmas_gift_app.exception.InvalidRequestBody;
import com.hotmail.pederwaern.christmas_gift_app.repository.AdultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdultService {

    private final AdultRepository repository;

    @Autowired
    public AdultService(AdultRepository repository) {
        this.repository = repository;
    }

    public List<Adult> getAllAdults() {
        List<Adult> adults = new ArrayList<>();
        repository.findAll().forEach(adults::add);
        return adults;
    }

    public void saveAdult(Adult adult) {
        if (adult.getFirstName() == null || adult.getLastName() == null) {
            throw new InvalidRequestBody();
        }
        repository.save(adult);
    }

    public Adult getAdultById(Integer id) {
       return repository.findOne(id);
    }
}



