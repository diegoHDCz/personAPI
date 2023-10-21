package com.diegoczajka.personservice.service;

import com.diegoczajka.personservice.model.Address.AddressDetailData;
import com.diegoczajka.personservice.model.Address.AddressRegisterData;
import com.diegoczajka.personservice.model.Address.UpdateAddressData;
import com.diegoczajka.personservice.model.Person.PersonRegisterData;
import com.diegoczajka.personservice.model.Person.UpdatePersonData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AddressService {
    AddressDetailData register(AddressRegisterData person);

    AddressDetailData edit(UpdateAddressData person);

    Page<AddressDetailData> list();

    AddressDetailData findIsMain(Long id);

    void delete(Long id);
}
