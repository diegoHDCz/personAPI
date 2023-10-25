package com.diegoczajka.personservice.service;

import com.diegoczajka.personservice.entity.Address;
import com.diegoczajka.personservice.entity.Person;
import com.diegoczajka.personservice.model.Address.AddressRegisterData;
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

import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public PersonDetailData registerPerson(PersonRegisterData personData) {
        var person = new Person(personData);

        var result = personRepository.save(person);

        var mainAddress = addressRepository.findAByPersonIdAndIsMain(result.getId(), true);

        return new PersonDetailData(result, mainAddress);

    }

    @Override
    public PersonDetailData editPerson(UpdatePersonData personData) {
        var person = personRepository.getReferenceById(personData.id());
        person.updatePersonInfo(personData);
        var mainAddress = addressRepository.findAByPersonIdAndIsMain(person.getId(), true);
        return new PersonDetailData(person, mainAddress);
    }

    @Override
    public Page<PersonListData> listPerson(Pageable pagination)  {
            var apqp = personRepository.findAll();
          System.out.println(apqp);
            return personRepository.findAll(pagination).map(PersonListData::new);

    }

    @Override
    public PersonDetailData findOne(Long id) {
        var person = personRepository.getReferenceById(id);
        var address = addressRepository.findAByPersonIdAndIsMain(id, true);
        return new PersonDetailData(person, address);
    }

    @Override
    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

}
