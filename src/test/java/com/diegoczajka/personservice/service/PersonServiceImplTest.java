package com.diegoczajka.personservice.service;

import com.diegoczajka.personservice.entity.Address;
import com.diegoczajka.personservice.entity.Person;
import com.diegoczajka.personservice.model.Address.AddressRegisterData;
import com.diegoczajka.personservice.model.Person.PersonRegisterData;
import com.diegoczajka.personservice.repository.AddressRepository;
import com.diegoczajka.personservice.repository.PersonRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.mockito.ArgumentMatchers.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PersonServiceImplTest {

    @Autowired
    PersonService service;
    @MockBean
    PersonRepository personRepository;

    @MockBean
    AddressRepository addressRepository;
    private Address address;
    private Person person;

    @BeforeEach
    void setup() {
        address = new Address();
        address.setPerson(new Person());
        address.setId(1L);
        address.setCity("Foz");
        address.setHouseNumber(115);

        person = new Person();
        person.setId(1L);
        person.setName("diego");
    }


    @Test
    @DisplayName("should be able to register person")
    void registerPerson() {

        Mockito.when(personRepository.save(any(Person.class))).thenReturn(person);
        Mockito.when(addressRepository.findAByPersonIdAndIsMain(anyLong(), anyBoolean())).thenReturn(address);
        List<AddressRegisterData> main = new ArrayList<>();
        PersonRegisterData personRegister = new PersonRegisterData("diego", LocalDate.now(), main);

        var result = service.registerPerson(personRegister);

        Assert.assertEquals(Long.valueOf(1), result.id());

    }

    @Test
    void editPerson() {
    }

    @Test
    void listPerson() throws Exception {
        Page<Person> persons = Mockito.mock(Page.class);


        Mockito.when(personRepository.findAll(any(Pageable.class))).thenReturn(persons);
        Pageable pagination = PageRequest.of(0, 10);
        service.listPerson(pagination);
        assertThatNoException();


    }

    @Test
    void findOne() {
        Mockito.when(personRepository.getReferenceById(anyLong())).thenReturn(person);
        var result = service.findOne(1L);
        assertThat(result.id()).isEqualTo(1L);
    }

    @Test
    void deletePerson() {
        Mockito.doNothing().when(personRepository).deleteById(anyLong());
        service.deletePerson(1L);
        assertThatNoException();
    }
}