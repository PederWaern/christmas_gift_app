package com.hotmail.pederwaern.christmas_gift_app.controller;

import com.hotmail.pederwaern.christmas_gift_app.domain.Child;
import com.hotmail.pederwaern.christmas_gift_app.service.ChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/child")
public class ChildController {

    @Autowired
    private ChildService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<Child> getAllChildren() {
        return service.getAllChildren();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Child saveChild(@RequestBody Child child) {
        service.saveChild(child);
        return child;
    }


}

