package com.diegoczajka.personservice.model.Person;

import com.diegoczajka.personservice.model.Address.AddressRegisterData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;
import java.util.List;

public record PersonRegisterData(
        @NotBlank
        String name,
        @NotNull
        @Past
        LocalDate birthdate,

        @Valid List<AddressRegisterData> addresses
        ) {
}
