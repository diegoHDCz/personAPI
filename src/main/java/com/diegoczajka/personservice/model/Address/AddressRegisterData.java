package com.diegoczajka.personservice.model.Address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record AddressRegisterData(


        @NotBlank
        String street,
        @NotNull
        int houseNumber,
        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String postalCode,
        @NotBlank
        String city,

        boolean isMain
) {
}
