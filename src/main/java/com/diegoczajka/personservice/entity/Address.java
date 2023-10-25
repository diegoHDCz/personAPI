package com.diegoczajka.personservice.entity;

import com.diegoczajka.personservice.model.Address.AddressRegisterData;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "address")
@Table(name = "address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String postalCode;
    private int houseNumber;
    private String city;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;

    private boolean isMain;

    public Address(String street, String postalCode, int houseNumber, String city, Person person, boolean isMain) {
        this.street = street;
        this.postalCode = postalCode;
        this.houseNumber = houseNumber;
        this.city = city;
        this.person = person;
        this.isMain = isMain;
    }


    public static Address toAddress(AddressRegisterData data, Person person) {
        return new Address(data.street(), data.postalCode(), data.houseNumber(), data.city(), person, data.isMain());
    }

    public void updateInfo(Address address) {
        this.isMain = address.isMain;
        if (address.city != null) {
            this.city = address.getCity();
        }
        if (address.houseNumber != 0) {
            this.houseNumber = address.getHouseNumber();
        }
        if (address.postalCode != null) {
            this.postalCode = address.getPostalCode();
        }
        if (address.street != null) {
            this.street = address.getStreet();
        }
    }
}
