package ru.geekbrains.team1.geek_crm.service;

import org.json.JSONException;
import org.json.JSONObject;
import ru.geekbrains.team1.geek_crm.entities.User;

public class JsonParser {

    public User getUser(String response) throws JSONException {
        JSONObject userJson = new JSONObject(response);
        long id = userJson.getLong("id");
        String userName = userJson.getString("userName");
        String firstName = userJson.getString("firstName");
        String lastName = userJson.getString("lastName");
        String email = userJson.getString("email");
        String phoneNumber = userJson.getString("phoneNumber");

        return new User(id, userName, firstName, lastName, email, phoneNumber);
    }
}
