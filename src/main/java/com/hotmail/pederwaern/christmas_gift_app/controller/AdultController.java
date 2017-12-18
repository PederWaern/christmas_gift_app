package com.hotmail.pederwaern.christmas_gift_app.controller;

import com.hotmail.pederwaern.christmas_gift_app.domain.Adult;
import com.hotmail.pederwaern.christmas_gift_app.domain.Child;
import com.hotmail.pederwaern.christmas_gift_app.service.AdultService;
import com.hotmail.pederwaern.christmas_gift_app.service.ChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adult/")
public class AdultController {

    private final AdultService service;
    private final ChildService child_service;

    @Autowired
    public AdultController(AdultService service, ChildService child_service) {
        this.service = service;
        this.child_service = child_service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Adult> getAllAdults() {
        return service.getAllAdults();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Adult getAdultById(@PathVariable Integer id) {
        Adult returned = service.getAdultById(id);
        return returned;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{id}/addChildren")
    public Adult addChildrenToAdult(@PathVariable Integer id,
                                    @RequestBody List<Child> children) {
        Adult adult = service.getAdultById(id);

        for (Child c: children) {
            //TODO Fixa så att den inte sätter uteblivna variabler till null
            c.getAdults().add(adult);
            adult.getChildren().add(c);
            child_service.saveChild(c);
        }
        return adult;
    }



    @RequestMapping(method = RequestMethod.POST)
    public Adult saveAdult(@RequestBody Adult adult) {
        service.saveAdult(adult);
        return adult;
    }


}
