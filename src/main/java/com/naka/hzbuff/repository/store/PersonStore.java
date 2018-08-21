package com.naka.hzbuff.repository.store;

import com.hazelcast.core.MapStore;
import com.hazelcast.spring.context.SpringAware;
import com.naka.hzbuff.model.Person;
import com.naka.hzbuff.repository.dao.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@SpringAware
public class PersonStore implements MapStore<Integer, Person> {

    @Autowired
    PersonRepository personRepository;

    @Override
    public Person load(Integer key) {
        return personRepository.findById(key).get();
    }

    @Override
    public Map<Integer, Person> loadAll(Collection<Integer> keys) {
        Map<Integer, Person> result = new HashMap<>();
        for (Integer key : keys) {
            Person person = this.load(key);
            if (person != null) {
                result.put(key, person);
            }
        }
        return result;
    }

    @Override
    public Iterable<Integer> loadAllKeys() {
        return personRepository.findAllId();
    }

    @Override
    public void store(Integer integer, Person person) {
        personRepository.save(person);
    }

    @Override
    public void storeAll(Map<Integer, Person> map) {
        personRepository.saveAll((List) map);
    }

    @Override
    public void delete(Integer id) {
        personRepository.deleteById(id);
    }

    @Override
    public void deleteAll(Collection<Integer> personIds) {
        personIds.forEach(personRepository::deleteById);
    }
}
