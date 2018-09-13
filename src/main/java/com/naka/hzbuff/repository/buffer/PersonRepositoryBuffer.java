package com.naka.hzbuff.repository.buffer;

import com.naka.hzbuff.model.Person;
import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepositoryBuffer extends KeyValueRepository<Person, Integer>
{

}
