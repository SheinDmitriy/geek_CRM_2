package ru.geekbrains.team1.geek_crm.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.sun.deploy.net.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import sun.net.www.http.HttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@Slf4j
public class JsonConnector {

    public void get (String requestUrl){
        String inputLine;

        try {
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            try( BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                ObjectMapper mapper = new ObjectMapper();
                Object json = mapper.readValue(response.toString(), Object.class);
                String indented = mapper.writeValueAsString(json);
                JsonParser jsonParser = new JsonParser();
                jsonParser.parse(indented);
            } catch (Exception e){
                log.error(e.getMessage());
            }
        }  catch ( Exception e) {
            log.error(e.getMessage());
        }
    }

//    public void getEvent (String requestUrl, JSONObject jsonObject) throws JSONException {
//
//        long eventId = jsonObject.getLong("id");
//
//
//
//    }

    public void post(String requestUrl, JSONObject objJson) throws JSONException {
        String eventClassName = objJson.getString("entityType");
        long eventId = objJson.getJSONObject("entityFields").getLong("id");
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd'T'HH:mm:ss.SSS");
        LocalDateTime date = LocalDateTime.now();
//        String time = formatter.format(date);
        String send = new Gson().toJson(date);
//        System.out.println(time);
        JSONObject dateAsObj = new JSONObject();
//        dateAsObj.put("__type", "Date");
        dateAsObj.put("LocalDateTime", date);
        System.out.println(dateAsObj.toString());

//        jsonParam.put("sendTime", dateAsObj);
        String urlParameters = dateAsObj.toString();
        System.out.println(urlParameters);

        requestUrl = requestUrl + eventClassName.toLowerCase() + "/" + eventId + "/" + eventClassName.toLowerCase() + "Id" + "/serverAcceptedAt";
        System.out.println(requestUrl);


        try {
            URL url = new URL(requestUrl);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-Type", "application/json");
//            connection.setConnectTimeout(10000);
            connection.setRequestMethod("PUT");
            connection.setRequestProperty( "charset", "utf-8");
            connection.setDoOutput(true);
//            connection.setDoInput(true);

//            int i = 1;

            OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
            wr.write(send);

            wr.flush();


//            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            StringBuffer response = new StringBuffer();
//            String inputLine;
//            while ((inputLine = in.readLine()) != null) {
//                response.append(inputLine);
//            }

//            System.out.println(response);
            String inputLine;
//            connection.connect();
//            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            StringBuffer response = new StringBuffer();
//            while ((inputLine = in.readLine()) != null) {
//                response.append(inputLine);
//            }
            int httpResult = connection.getResponseCode();
            inputLine = connection.getResponseMessage();
//            connection.get
            System.out.println(httpResult);
            System.out.println(inputLine);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
