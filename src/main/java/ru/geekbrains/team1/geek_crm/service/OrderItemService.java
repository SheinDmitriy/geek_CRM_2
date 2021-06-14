package ru.geekbrains.team1.geek_crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.team1.geek_crm.entities.OrderItem;
import ru.geekbrains.team1.geek_crm.repository.OrderItemRepository;

@Service
public class OrderItemService {
    private OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }


    public void save(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }


}
