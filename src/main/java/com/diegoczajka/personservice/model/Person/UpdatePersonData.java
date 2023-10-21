package com.diegoczajka.personservice.model.Person;

import com.diegoczajka.personservice.entity.Address;
import com.diegoczajka.personservice.model.Address.AddressRegisterData;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public record UpdatePersonData(
        @NotNull
        Long id,
        String name,
        LocalDate birthdate,

        @Valid List<AddressRegisterData> addresses
) {
}
