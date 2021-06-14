package ru.geekbrains.team1.geek_crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.geekbrains.team1.geek_crm.entities.ActionType;


import java.util.Optional;

@Repository
public interface ActionTypeRepository extends
        JpaRepository<ActionType, Short>, JpaSpecificationExecutor<ActionType> {

    Optional<ActionType> getActionTypeByTitleEquals(String actionTypeTitle);

}
