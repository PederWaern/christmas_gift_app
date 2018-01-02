package com.hotmail.pederwaern.christmas_gift_app.domain;

import java.util.Set;

public class WishMailWrapper {

    private int id;
    private String name;
    private String description;
    private Integer price;
    private boolean bought;
    private Child child;
    private Set<Adult> adults;

    public WishMailWrapper(Wish wish) {
        this.id = wish.getId();
        this.name = wish.getName();
        this.description = wish.getDescription();
        this.price = wish.getPrice();
        this.bought = wish.isBought();
        this.child = wish.getChild();
        this.adults = wish.getChild().getAdults();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPrice() {
        return price;
    }

    public boolean isBought() {
        return bought;
    }

    public Child getChild() {
        return child;
    }

    public Set<Adult> getAdults() {
        return adults;
    }
}
