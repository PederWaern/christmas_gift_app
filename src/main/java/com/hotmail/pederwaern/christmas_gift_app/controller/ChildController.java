package com.hotmail.pederwaern.christmas_gift_app.controller;

import com.hotmail.pederwaern.christmas_gift_app.domain.Child;
import com.hotmail.pederwaern.christmas_gift_app.domain.Wish;
import com.hotmail.pederwaern.christmas_gift_app.service.ChildService;
import com.hotmail.pederwaern.christmas_gift_app.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "Child")
@RequestMapping(value = "/child/")

public class ChildController {

    private final ChildService service;
    private final WishService wish_service;

    @Autowired
    public ChildController(ChildService service, WishService wish_service) {
        this.service = service;
        this.wish_service = wish_service;
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

    @RequestMapping(method = RequestMethod.POST, value = "/{id}/addWishToChild")
    public Child addWishToChild(@PathVariable Integer id, @RequestBody Wish wish) {
        Child child = service.getChildById(id);
        wish.setChild(child);
        child.getWishes().add(wish);
        service.saveChild(child);
        return child;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public String deleteChild(@PathVariable Integer id) {
        return service.deleteChild(id);
    }





}

