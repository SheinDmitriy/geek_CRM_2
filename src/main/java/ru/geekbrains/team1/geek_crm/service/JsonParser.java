package ru.geekbrains.team1.geek_crm.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.geekbrains.team1.geek_crm.entities.OrderItem;
import ru.geekbrains.team1.geek_crm.entities.Product;
import ru.geekbrains.team1.geek_crm.entities.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
        String store = userJson.getString("store");

        User user = new User(id, userName, firstName, lastName, email, phoneNumber, store);

        System.out.println(user.toString());

        return user;
    }

    private Product getProduct(JSONObject prodJson) throws JSONException {
        Product product = new Product();
        product.setId(prodJson.getLong("id"));
        product.setStore(prodJson.getString("store"));
        product.setVendorCode(prodJson.getString("vendorCode"));
        product.setTitle(prodJson.getString("title"));
        product.setShortDescription(prodJson.getString("shortDescription"));
        product.setFullDescription(prodJson.getString("fullDescription"));
        product.setPrice(BigDecimal.valueOf(Long.parseLong(prodJson.getString("price"))));
        return product;
    }

    private void getOrder(JSONObject orderJson) throws JSONException, JsonProcessingException {
        long id = orderJson.getLong("id");
        String store = orderJson.getString("store");
        String orderStatus = orderJson.getString("orderStatusTitle");
        User user = getUser( orderJson.getJSONObject("outUser"));
        List<OrderItem> orderItems = getOrderItems(orderJson.getJSONArray("outOrderItems"));
    }

    private List<OrderItem> getOrderItems(JSONArray outOrderItems) throws JSONException {
        List<OrderItem> list = new ArrayList<>();

        if (outOrderItems != null) {
            int len = outOrderItems.length();
            for (int i=0;i<len;i++){
                list.add(getOrderItem(new JSONObject(outOrderItems.get(i).toString())));
            }
        }
        return list;
    }

    private OrderItem getOrderItem(JSONObject jsonObject) {
        OrderItem orderItem = new OrderItem();

        return orderItem;

    }

    private void getEvent(JSONObject orderJson) throws JSONException, JsonProcessingException {
        long id = orderJson.getLong("id");
        String orderStatus = orderJson.getString("orderStatusTitle");
        User user = getUser( orderJson.getJSONObject("outUser"));
//        List orderItem = getUser( orderJson.getJSONObject("outUser"));
    }
}
