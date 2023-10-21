package com.diegoczajka.personservice.entity;

import com.diegoczajka.personservice.model.Person.PersonRegisterData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "person")
@Table(name = "person")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate birthdate;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

    public Person(PersonRegisterData data) {


        this.name = data.name();
        this.birthdate = data.birthdate();
        this.addresses = data.addresses().stream().map(
                        Address::toAddress)
                .collect(Collectors.toList());
    }
}
