package com.naka.hzbuff.repository.buffer;

import com.naka.hzbuff.model.Person;
import org.springframework.data.hazelcast.repository.HazelcastRepository;
import org.springframework.stereotype.Repository;

@Repository("prb")
public interface PersonRepositoryBuffer extends HazelcastRepository<Person, Integer>
{

}
