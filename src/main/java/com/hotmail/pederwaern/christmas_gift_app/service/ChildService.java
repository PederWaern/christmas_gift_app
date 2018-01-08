package com.hotmail.pederwaern.christmas_gift_app.service;

import com.hotmail.pederwaern.christmas_gift_app.domain.Adult;
import com.hotmail.pederwaern.christmas_gift_app.domain.Child;
import com.hotmail.pederwaern.christmas_gift_app.exception.ControllerExceptionHandler;
import com.hotmail.pederwaern.christmas_gift_app.repository.ChildRepository;
import com.hotmail.pederwaern.christmas_gift_app.utils.JSONReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChildService {

    private final ChildRepository repository;

    @Autowired
    public ChildService(ChildRepository repository) {
        this.repository = repository;
    }

    public List<Child> getAllChildren() {
        List<Child> children = new ArrayList<>();
        repository.findAll().forEach(children::add);
        return children;
    }

    public Child getChildById(Integer id) {
        Child child = repository.findOne(id);
        if (child == null) {
            throw new ControllerExceptionHandler.ChildResourceNotFound();
        }
        return child;
    }

    public void addAdults(List<Adult> adults, Child child) {
        for (Adult adult: adults) {
            child.getAdults().add(adult);
        }
        repository.save(child);
    }


    public void saveChild(Child child) {
        child.setId(0);
        repository.save(child);
    }

    public String deleteChild(Integer id) {
        if(id == null || repository.findOne(id) == null) {
            throw new ControllerExceptionHandler.ChildResourceNotFound();
        }
        repository.delete(id);
        return new JSONReturnMessage(id, "Child").format();
    }
}
