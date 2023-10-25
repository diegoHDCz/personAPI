package com.diegoczajka.personservice.entity;

import com.diegoczajka.personservice.model.Address.AddressRegisterData;
import com.diegoczajka.personservice.model.Person.PersonRegisterData;
import com.diegoczajka.personservice.model.Person.UpdatePersonData;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "person")
@Table(name = "person")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(exclude = {"addresses"})
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate birthdate;

    @JsonManagedReference
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<>();

    public Person(PersonRegisterData data) {


        this.name = data.name();
        this.birthdate = data.birthdate();
        this.addresses = data.addresses().stream().map(
                (AddressRegisterData ad) -> Address.toAddress(ad, this)
        ).collect(Collectors.toList());

    }

    public void updatePersonInfo(UpdatePersonData data) {
        if (data.name() != null) {
            this.name = data.name();
        }
        if (data.birthdate() != null) {
            this.birthdate = data.birthdate();
        }
        if (data.addresses() != null) {
            this.addresses.stream().map(address -> {
                address.updateInfo(address);
                return address;
            }).collect(Collectors.toList());
        }

    }
}
