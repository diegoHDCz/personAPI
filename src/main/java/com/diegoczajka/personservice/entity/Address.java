package com.diegoczajka.personservice.entity;

import com.diegoczajka.personservice.model.Address.AddressRegisterData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "address")
@Table(name = "address")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String postalCode;
    private int houseNumber;
    private String city;

    @ManyToOne
    private Person person;

    private boolean isMain;

    public Address(String street, String postalCode, int houseNumber, String city, boolean isMain) {
        this.street = street;
        this.postalCode = postalCode;
        this.houseNumber = houseNumber;
        this.city = city;
        this.isMain = isMain;
    }

    public static Address toAddress(AddressRegisterData data){
        return new Address(data.street(),data.postalCode(),data.houseNumber(),data.city(),data.isMain());
    }
}
