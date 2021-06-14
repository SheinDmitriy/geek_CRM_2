package ru.geekbrains.team1.geek_crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.team1.geek_crm.entities.Address;
import ru.geekbrains.team1.geek_crm.repository.AddressRepository;

@Service
public class AddressService {
    private AddressRepository addressRepository;

    @Autowired
    public void setRepository(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public void save(Address address) {
        addressRepository.save(address);
    }

}
