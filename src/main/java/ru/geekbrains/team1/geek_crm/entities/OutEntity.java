package ru.geekbrains.team1.geek_crm.entities;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class OutEntity {

    private String store = "gb-spring-amin-ishop-heroku";

    private String entityClassSimpleName;

    private Map<String, Object> body;

    public OutEntity(String entityClassSimpleName) {
        this.entityClassSimpleName = entityClassSimpleName;
        body = new HashMap<>();
    }

}
