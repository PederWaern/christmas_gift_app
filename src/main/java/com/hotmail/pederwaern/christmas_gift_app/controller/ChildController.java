package com.hotmail.pederwaern.christmas_gift_app.controller;

import com.hotmail.pederwaern.christmas_gift_app.domain.Child;
import com.hotmail.pederwaern.christmas_gift_app.service.ChildService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController(value = "Child")
@RequestMapping(value = "/child/")

public class ChildController {

    private final ChildService service;

    @Autowired
    public ChildController(ChildService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Child> getAllChildren() {
        return service.getAllChildren();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Child getChildById(@PathVariable Integer id)  {
        return service.getChildById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Child saveChild(@RequestBody Child child) {
        service.saveChild(child);
        return child;
    }



}

