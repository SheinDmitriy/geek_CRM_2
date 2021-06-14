package ru.geekbrains.team1.geek_crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.geekbrains.team1.geek_crm.entities.OrderStatus;

@Repository
public interface OrderStatusRepository extends
        JpaRepository<OrderStatus, Short>, JpaSpecificationExecutor<OrderStatus> {

    OrderStatus findByTitle(String title);
}
