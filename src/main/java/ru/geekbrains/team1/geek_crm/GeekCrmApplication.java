package ru.geekbrains.team1.geek_crm;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.geekbrains.team1.geek_crm.rest.JsonConnector;

@SpringBootApplication
public class GeekCrmApplication {

    public static void main(String[] args) {
//        SpringApplication.run(GeekCrmApplication.class, args);
        String eventURL = "https://dev-amin-ishop-heroku.herokuapp.com/api/v1/order/1/orderId";
        String requestUrl = "https://gb-spring-amin-ishop-heroku.herokuapp.com/api/v1/order/2/id";
        JsonConnector jsonConnector = new JsonConnector();
        jsonConnector.get(eventURL);

    }
}
