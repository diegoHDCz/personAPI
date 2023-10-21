package com.diegoczajka.personservice.repository;

import com.diegoczajka.personservice.entity.Address;
import com.diegoczajka.personservice.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address,Long> {
    @Query("SELECT a FROM Address a WHERE a.person = :person AND a.isMain = true")
    Address findMainAddressByPersonId(Long person_id);
}
