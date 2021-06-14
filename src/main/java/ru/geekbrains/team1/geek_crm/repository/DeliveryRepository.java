package ru.geekbrains.team1.geek_crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.geekbrains.team1.geek_crm.entities.Delivery;
import ru.geekbrains.team1.geek_crm.entities.Order;

@Repository
public interface DeliveryRepository extends
        JpaRepository<Delivery, Long>, JpaSpecificationExecutor<Delivery> {

    Delivery findByOrder(Order order);

    void deleteByOrderId(Long orderId);
}
