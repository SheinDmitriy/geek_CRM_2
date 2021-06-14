package ru.geekbrains.team1.geek_crm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.team1.geek_crm.entities.Customer;
import ru.geekbrains.team1.geek_crm.repository.CustomerRepository;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Transactional
    public boolean save(Customer systemUser) {
        Customer customer = Customer.builder().build();

        if (findByUserName(systemUser.getUserName())) {
            return false;
        }

        customer.setUserName(systemUser.getUserName());
        customer.setFirstName(systemUser.getFirstName());
        customer.setLastName(systemUser.getLastName());
        customer.setPhoneNumber(systemUser.getPhoneNumber());
        customer.setEmail(systemUser.getEmail());
        customerRepository.save(customer);
        return true;
    }

    private boolean findByUserName(String userName) {
        return customerRepository.findOneByUserName(userName);
    }

}

