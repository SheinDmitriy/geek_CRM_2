package ru.geekbrains.team1.geek_crm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.team1.geek_crm.entities.Delivery;
import ru.geekbrains.team1.geek_crm.repository.DeliveryRepository;

@Service
@RequiredArgsConstructor
public class DeliveryService {
    private DeliveryRepository deliveryRepository;

    @Autowired
    public void setRepository(DeliveryRepository repository) {
        this.deliveryRepository = repository;
    }

    public void save(Delivery delivery) {
        deliveryRepository.save(delivery);
    }

}
