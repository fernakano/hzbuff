package com.naka.hzbuff.model;

import lombok.Data;
import org.springframework.data.keyvalue.annotation.KeySpace;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@KeySpace("person")
@Table(name = "person")
public class Person implements Serializable {

    @javax.persistence.Id
    @org.springframework.data.annotation.Id
    Integer id;

    String name;

    public Person(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    private Person() {
    }
}
