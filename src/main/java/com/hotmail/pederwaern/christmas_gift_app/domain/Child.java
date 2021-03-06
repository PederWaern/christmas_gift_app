package com.hotmail.pederwaern.christmas_gift_app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String firstName;
    private String lastName;


    @JsonIgnore
    @ManyToMany(mappedBy = "children")
    private Set<Adult> adults = new HashSet<>();

    @OneToMany(mappedBy = "child")
    private Set<Wish> wishes;

    @PreRemove
    public void nullifyRelations() {
        System.out.println("Hello from nullifyRelations");
        if(wishes != null) {
            getWishes().forEach(wish -> wish.setChild(null));
        }
        if(adults != null) {
            for (Adult adult: getAdults()) {
                adult.getChildren().remove(this);
            }
        }
    }

    public Set<Adult> getAdults() {
        return adults;
    }

    public void setAdults(Set<Adult> adults) {
        this.adults = adults;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Wish> getWishes() {
        return wishes;
    }

    public void setWishes(Set<Wish> wishes) {
        this.wishes = wishes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Child child = (Child) o;

        if (id != child.id) return false;
        if (firstName != null ? !firstName.equals(child.firstName) : child.firstName != null) return false;
        return lastName != null ? lastName.equals(child.lastName) : child.lastName == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }
}
