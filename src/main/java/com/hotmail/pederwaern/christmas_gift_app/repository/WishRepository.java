package com.hotmail.pederwaern.christmas_gift_app.repository;

import com.hotmail.pederwaern.christmas_gift_app.domain.Wish;
import org.springframework.data.repository.CrudRepository;

public interface WishRepository extends CrudRepository<Wish, Integer> {

}
