package com.diegoczajka.personservice.service;

import com.diegoczajka.personservice.entity.Person;
import com.diegoczajka.personservice.model.Person.PersonDetailData;
import com.diegoczajka.personservice.model.Person.PersonListData;
import com.diegoczajka.personservice.model.Person.PersonRegisterData;
import com.diegoczajka.personservice.model.Person.UpdatePersonData;
import com.diegoczajka.personservice.repository.AddressRepository;
import com.diegoczajka.personservice.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public PersonDetailData registerPerson(PersonRegisterData personData) {
        var person = new Person(personData);

        personRepository.save(person);

        var address = addressRepository.findMainAddressByPersonId(person.getId());
        return new PersonDetailData(person, address);

    }

    @Override
    public PersonDetailData editPerson(UpdatePersonData person) {
        return null;
    }

    @Override
    public Page<PersonListData> listPerson(Pageable pagination) {
        return personRepository.findAll(pagination).map(PersonListData::new);
    }

    @Override
    public PersonDetailData findOne(Long id) {
        var person = personRepository.getReferenceById(id);
        var address = addressRepository.findMainAddressByPersonId(id);
        return new PersonDetailData(person, address);
    }

    @Override
    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

}
