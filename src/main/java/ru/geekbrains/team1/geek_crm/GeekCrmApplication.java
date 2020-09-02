package ru.geekbrains.team1.geek_crm;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.geekbrains.team1.geek_crm.rest.JsonConnector;

@SpringBootApplication
public class GeekCrmApplication {

    public static void main(String[] args) {
//        SpringApplication.run(GeekCrmApplication.class, args);
        new JsonConnector();
    }
}
