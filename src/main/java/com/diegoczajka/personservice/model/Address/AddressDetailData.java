package com.diegoczajka.personservice.model.Address;

import jakarta.validation.constraints.NotNull;

public record AddressDetailData(
        @NotNull
        Long id,
        String street,
        String postalCode,
        int houseNumber,
        String city,
        boolean isMain
) {
}
