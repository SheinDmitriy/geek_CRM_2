package ru.geekbrains.team1.geek_crm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import ru.geekbrains.team1.geek_crm.entities.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JsonConnector {

    public JsonConnector() {
        JsonParser jsonParser = new JsonParser();

        String requestUrl = "https://gb-spring-amin-ishop-heroku.herokuapp.com/api/v1/order/2/id";

        URL url = null;
        try {
            url = new URL(requestUrl);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.connect();

            BufferedReader in;

            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            ObjectMapper mapper = new ObjectMapper();
            Object json = mapper.readValue(response.toString(), Object.class);
            String indented = mapper.writeValueAsString(json);
            User user = jsonParser.getUser(indented);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
