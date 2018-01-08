package com.hotmail.pederwaern.christmas_gift_app.controller;

import com.hotmail.pederwaern.christmas_gift_app.domain.Wish;
import com.hotmail.pederwaern.christmas_gift_app.service.WishService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/wishes/")
public class WishController {

    private final WishService service;

    @Autowired
    public WishController(WishService service) {
        this.service = service;
    }

    @ApiOperation(value = "Get all wishes")
    @RequestMapping(method = RequestMethod.GET)
    public List<Wish> getAllWishes() {
        return service.getAllWishes();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{id}/toggleWishBought")
    public Wish toggleWishBought(@PathVariable Integer id) {
        Wish wish = service.getWishById(id);
        wish.setBought(!wish.isBought());
        service.saveWish(wish);
        return wish;
    }

    @RequestMapping(method = RequestMethod.DELETE, value="{id}")
    public String deleteWish(@PathVariable Integer id) {
        return service.deleteWish(id);
    }

}
