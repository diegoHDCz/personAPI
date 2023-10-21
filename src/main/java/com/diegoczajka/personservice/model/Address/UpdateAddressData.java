package com.diegoczajka.personservice.model.Address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UpdateAddressData(
        @NotNull
        Long id,

        @NotNull
        Long person_id,

        String street,

        int houseNumber,

        @Pattern(regexp = "\\d{8}")
        String postalCode,

        String city,

        boolean isMain
) {
}
