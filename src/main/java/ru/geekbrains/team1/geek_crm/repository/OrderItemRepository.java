package ru.geekbrains.team1.geek_crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.geekbrains.team1.geek_crm.entities.Order;
import ru.geekbrains.team1.geek_crm.entities.OrderItem;

import java.util.List;

@Repository
public interface OrderItemRepository extends
        JpaRepository<OrderItem, Long>, JpaSpecificationExecutor<OrderItem> {

    List<OrderItem> findAllOrderItemsByOrder(Order order);

    void deleteOrderItemsByOrderId(Long id);
}
