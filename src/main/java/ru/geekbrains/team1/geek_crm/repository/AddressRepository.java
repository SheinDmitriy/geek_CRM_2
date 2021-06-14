package ru.geekbrains.team1.geek_crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.geekbrains.team1.geek_crm.entities.Address;

@Repository
public interface AddressRepository extends
        JpaRepository<Address, Long>, JpaSpecificationExecutor<Address> {

    Address getAddressByCountryEqualsAndCityEqualsAndAddressEquals(
            String country, String city, String address);
}
