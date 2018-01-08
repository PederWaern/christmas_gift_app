package com.hotmail.pederwaern.christmas_gift_app.repository;

import com.hotmail.pederwaern.christmas_gift_app.domain.Adult;
import org.springframework.data.repository.CrudRepository;

public interface AdultRepository extends CrudRepository<Adult, Integer> {
    Adult findByFirstName(String firstName);
}
