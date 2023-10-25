package com.diegoczajka.personservice.repository;

import com.diegoczajka.personservice.entity.Address;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {


    Address findAByPersonIdAndIsMain(Long person_id, boolean isMain);

    List<Address> findAllByPersonId(Long person_id);
}
