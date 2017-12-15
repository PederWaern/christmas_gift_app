package com.hotmail.pederwaern.christmas_gift_app.service;

import com.hotmail.pederwaern.christmas_gift_app.domain.Child;
import com.hotmail.pederwaern.christmas_gift_app.repository.ChildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChildService {

    @Autowired
    private ChildRepository repository;

    public List<Child> getAllChildren() {
        List<Child> children = new ArrayList<>();
        repository.findAll().forEach(children::add);
        return children;
    }

    public void saveChild(Child child) {
        repository.save(child);
    }
}
