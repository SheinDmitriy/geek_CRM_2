package ru.geekbrains.team1.geek_crm.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONException;
import org.json.JSONObject;
import ru.geekbrains.team1.geek_crm.entities.User;

public class JsonParser {
    private String response;


    public JsonParser(String response) throws JSONException, JsonProcessingException {
        this.response = response;
        JSONObject objJson = new JSONObject(response);

        switch(objJson.getString("entity")){
            case "OutOrder":
                getOrder(objJson);
                break;
            case "OutProduct":
                getProduct(objJson);
                break;
            case "OutUser":
                getUser(objJson);
                break;

        }
    }

    private User getUser(JSONObject userJson) throws JSONException {
        long id = userJson.getLong("id");
        String userName = userJson.getString("userName");
        String firstName = userJson.getString("firstName");
        String lastName = userJson.getString("lastName");
        String email = userJson.getString("email");
        String phoneNumber = userJson.getString("phoneNumber");

        User user = new User(id, userName, firstName, lastName, email, phoneNumber);

        System.out.println(user.toString());

        return user;
    }

    private void getProduct(JSONObject prodJson) {
        System.out.println("product");
    }

    private void getOrder(JSONObject orderJson) throws JSONException, JsonProcessingException {
        long id = orderJson.getLong("id");
        String orderStatus = orderJson.getString("orderStatusTitle");
        User user = getUser( orderJson.getJSONObject("outUser"));
    }

    private void getEvent(JSONObject orderJson) throws JSONException, JsonProcessingException {
        long id = orderJson.getLong("id");
        String orderStatus = orderJson.getString("orderStatusTitle");
        User user = getUser( orderJson.getJSONObject("outUser"));
//        List orderItem = getUser( orderJson.getJSONObject("outUser"));



    }
}
