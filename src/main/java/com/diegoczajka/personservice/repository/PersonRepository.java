package com.diegoczajka.personservice.repository;

import com.diegoczajka.personservice.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long> {
}
