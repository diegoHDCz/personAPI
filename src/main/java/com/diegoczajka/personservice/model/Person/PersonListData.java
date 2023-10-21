package com.diegoczajka.personservice.model.Person;

import com.diegoczajka.personservice.entity.Address;
import com.diegoczajka.personservice.entity.Person;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public record PersonListData(
        Long id,
        String name,
        LocalDate birthdate,
        List<Address> addresses
) {
    public PersonListData(Person person) {
        this(person.getId(), person.getName(), person.getBirthdate(), person.getAddresses());
    }
}
