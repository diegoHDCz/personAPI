package com.diegoczajka.personservice.service;

import com.diegoczajka.personservice.entity.Person;
import com.diegoczajka.personservice.model.Person.PersonDetailData;
import com.diegoczajka.personservice.model.Person.PersonListData;
import com.diegoczajka.personservice.model.Person.PersonRegisterData;
import com.diegoczajka.personservice.model.Person.UpdatePersonData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PersonService {
    PersonDetailData registerPerson(PersonRegisterData person);

    PersonDetailData editPerson(UpdatePersonData person);

    Page<PersonListData> listPerson(Pageable pagination);

    PersonDetailData findOne(Long id);

    void deletePerson(Long id);


}
