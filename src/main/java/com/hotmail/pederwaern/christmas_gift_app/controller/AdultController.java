package com.hotmail.pederwaern.christmas_gift_app.controller;

import com.hotmail.pederwaern.christmas_gift_app.domain.Adult;
import com.hotmail.pederwaern.christmas_gift_app.service.AdultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/adult/")
public class AdultController {

    private final AdultService service;

    @Autowired
    public AdultController(AdultService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Adult> getAllAdults() {
        return service.getAllAdults();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Adult saveAdult(@RequestBody Adult adult) {
        service.saveAdult(adult);
        return adult;
    }


}
