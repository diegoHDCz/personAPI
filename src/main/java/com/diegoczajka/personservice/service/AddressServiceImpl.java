package com.diegoczajka.personservice.service;

import com.diegoczajka.personservice.model.Address.AddressDetailData;
import com.diegoczajka.personservice.model.Address.AddressRegisterData;
import com.diegoczajka.personservice.model.Address.UpdateAddressData;
import org.springframework.data.domain.Page;

public class AddressServiceImpl implements AddressService{
    @Override
    public AddressDetailData register(AddressRegisterData person) {
        return null;
    }

    @Override
    public AddressDetailData edit(UpdateAddressData person) {
        return null;
    }

    @Override
    public Page<AddressDetailData> list() {
        return null;
    }

    @Override
    public AddressDetailData findIsMain(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
