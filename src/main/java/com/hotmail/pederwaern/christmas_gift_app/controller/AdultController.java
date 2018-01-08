package com.hotmail.pederwaern.christmas_gift_app.controller;

import com.hotmail.pederwaern.christmas_gift_app.domain.Adult;
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
        return service.getAdultById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{id}/addChild/{childId}")
    public Adult addChildToAdult(@PathVariable Integer id,
                                 @PathVariable Integer childId) {
        Adult adult = service.getAdultById(id);
        adult.getChildren().add(child_service.getChildById(childId));
        service.saveAdult(adult);
        return adult;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Adult saveAdult(@RequestBody Adult adult) {
        adult.setId(0);
        service.saveAdult(adult);
        return adult;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public String deleteAdult(@RequestParam Integer id){
        return service.deleteAdult(id);
    }
}
