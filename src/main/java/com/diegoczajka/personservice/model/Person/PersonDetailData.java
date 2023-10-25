package com.diegoczajka.personservice.model.Person;

import com.diegoczajka.personservice.entity.Address;
import com.diegoczajka.personservice.entity.Person;

import java.time.LocalDate;

public record PersonDetailData(Long id, String name, LocalDate birthdate,Address mainAddress) {

    public PersonDetailData(Person person, Address mainAddress) {
        this(person.getId(), person.getName(), person.getBirthdate(), mainAddress);
    }


}
