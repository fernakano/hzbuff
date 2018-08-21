package com.naka.hzbuff.repository.dao;

import com.naka.hzbuff.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("pr")
public interface PersonRepository extends JpaRepository<Person, Integer> {

    @Query("SELECT id FROM Person")
    Iterable<Integer> findAllId();
}
