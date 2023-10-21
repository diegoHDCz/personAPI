package com.diegoczajka.personservice.model.Address;

import com.diegoczajka.personservice.entity.Person;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private Long id;
    private String street;
    private String postalCode;
    private int houseNumber;
    private String city;
    private boolean isMain;




}
