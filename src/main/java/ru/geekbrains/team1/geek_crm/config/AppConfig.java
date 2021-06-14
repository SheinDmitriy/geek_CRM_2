package ru.geekbrains.team1.geek_crm.config;

import org.springframework.context.annotation.Bean;
import ru.geekbrains.team1.geek_crm.rest.JsonConnector;

public class AppConfig {

    private String eventURL = "http://localhost:8080/api/v1/event/1/eventId";
    private String eventURL2 = "http://localhost:8080/api/v1/event/serverUnaccepted/first";

    private String requestUrl = "https://gb-spring-amin-ishop-heroku.herokuapp.com/api/v1/order/2/id";

    @Bean
    public JsonConnector jsonConnector(){

        JsonConnector jsonConnector = new JsonConnector();
        jsonConnector.get(eventURL2);
        return jsonConnector;
    }
}
