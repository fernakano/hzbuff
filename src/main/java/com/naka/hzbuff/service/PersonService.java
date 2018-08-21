package com.naka.hzbuff.service;

import com.naka.hzbuff.model.Person;
import com.naka.hzbuff.repository.dao.PersonRepository;
import com.naka.hzbuff.repository.buffer.PersonRepositoryBuffer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PersonService {

    @Autowired
    PersonRepositoryBuffer personRepositoryBuffer;

    @Autowired
    PersonRepository personRepository;

    PersonService() {
    }


    @EventListener(ApplicationReadyEvent.class)
    public void Init() {
        Iterable<Person> personList;
        personList = personRepositoryBuffer.findAll();
        log.info("Empty:" + personList.toString());
        for (int i = 0; i < 5; i++) {
            personRepositoryBuffer.save(new Person(i, "Naka"));
        }
        personList = personRepositoryBuffer.findAll();
        log.info("Data:" + personList.toString());

//        log.info("Data DB:" + personRepository.findAll().toString());
//        log.info("Data DB2:" + personRepository.findAllId());

    }

}
