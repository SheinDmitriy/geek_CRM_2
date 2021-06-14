package ru.geekbrains.team1.geek_crm.service;

import org.springframework.stereotype.Service;
import ru.geekbrains.team1.geek_crm.entities.OrderStatus;
import ru.geekbrains.team1.geek_crm.repository.OrderStatusRepository;

@Service
public class OrderStatusService {
    private OrderStatusRepository orderStatusRepository;

    public OrderStatusService(OrderStatusRepository orderStatusRepository) {
        this.orderStatusRepository = orderStatusRepository;
    }

    public OrderStatus findByTitle(String title){
        return orderStatusRepository.findByTitle(title);
    }
}
