package com.hotmail.pederwaern.christmas_gift_app.service;

import com.hotmail.pederwaern.christmas_gift_app.domain.Adult;
import com.hotmail.pederwaern.christmas_gift_app.exception.ControllerExceptionHandler;
import com.hotmail.pederwaern.christmas_gift_app.repository.AdultRepository;
import com.hotmail.pederwaern.christmas_gift_app.utils.JSONReturnMessage;
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
            throw new ControllerExceptionHandler.InvalidRequestBody();
        }
        repository.save(adult);
    }

    public Adult getAdultById(Integer id) {
       if (repository.findOne(id) == null) {
           throw new ControllerExceptionHandler.AdultResourceNotFound();
       }
       return repository.findOne(id);
    }

    public String deleteAdult(Integer id) {
        if(id == null || repository.findOne(id) == null) {
            throw new ControllerExceptionHandler.AdultResourceNotFound();
        }
        repository.delete(id);
        return new JSONReturnMessage(id, "Adult").format();
    }
}



