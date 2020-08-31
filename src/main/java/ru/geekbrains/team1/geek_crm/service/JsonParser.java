package ru.geekbrains.team1.geek_crm.service;

import org.json.JSONException;
import org.json.JSONObject;
import ru.geekbrains.team1.geek_crm.entities.User;

public class JsonParser {
    private String response;
    private JSONObject objJson;

    public JsonParser(String response) throws JSONException {
        this.response = response;
         this.objJson = new JSONObject(response);
        switch(objJson.getString("entity")){
            case "OutOrder":
                getOrder(response);
                break;
            case "OutProduct":
                getProduct(response);
                break;
            case "OutUser":
                getUser(response);
        }
    }

    private void getUser(String response) throws JSONException {
        long id = objJson.getLong("id");
        String userName = objJson.getString("userName");
        String firstName = objJson.getString("firstName");
        String lastName = objJson.getString("lastName");
        String email = objJson.getString("email");
        String phoneNumber = objJson.getString("phoneNumber");

        User user = new User(id, userName, firstName, lastName, email, phoneNumber);
        System.out.println(user.toString());
    }

    private void getProduct(String response) {
        System.out.println("product");

    }

    private void getOrder(String response) {
        System.out.println("order");

    }
}
