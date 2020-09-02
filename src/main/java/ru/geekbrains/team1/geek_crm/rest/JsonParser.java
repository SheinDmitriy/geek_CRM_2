package ru.geekbrains.team1.geek_crm.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.geekbrains.team1.geek_crm.entities.*;
import ru.geekbrains.team1.geek_crm.entities.OutEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonParser {
    private String response;


    public JsonParser(String response) throws JSONException, JsonProcessingException {
        this.response = response;

/**
 * Вариант №1 с ручным парсингм
 */
//        JSONObject objJson = new JSONObject(response);
//        switch(objJson.getString("entity")){
//            case "OutOrder":
//                getOrder(objJson);
//                break;
//            case "OutProduct":
//                getProduct(objJson);
//                break;
//            case "OutUser":
//                getUser(objJson);
//                break;
//
//        }

/**
 * Вариант №2 с GSON
 */
        OutEntity inEvent = new Gson().fromJson(response, OutEntity.class);
        getEvent(inEvent);
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

        return user;
    }

    private Product getProduct(JSONObject prodJson) throws JSONException {
        Product product = new Product();
        product.setId(prodJson.getLong("id"));
        product.setStore(prodJson.getString("store"));
        product.setVendorCode(prodJson.getString("vendorCode"));
        product.setTitle(prodJson.getString("categoryTitle"));
        product.setShortDescription(prodJson.getString("shortDescription"));
        product.setFullDescription(prodJson.getString("fullDescription"));
        product.setPrice(BigDecimal.valueOf(Long.parseLong(prodJson.getString("price"))));
        return product;
    }

    private void getOrder(JSONObject orderJson) throws JSONException, JsonProcessingException {
        Order order = new Order();
        order.setId(orderJson.getLong("id"));
        order.setStore(orderJson.getString("store"));
        order.setOrderStatus(orderJson.getString("orderStatusTitle"));
        order.setUser(getUser( orderJson.getJSONObject("outUser")));
        order.setOrderItems(getOrderItems(orderJson.getJSONArray("outOrderItems")));

        System.out.println(order.toString());
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

    private OrderItem getOrderItem(JSONObject orderItemJson) throws JSONException {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(orderItemJson.getLong("id"));
        orderItem.setProduct(getProduct(orderItemJson.getJSONObject("outProduct")));
        orderItem.setStore(orderItemJson.getString("store"));
        orderItem.setItemPrice(BigDecimal.valueOf(Long.parseLong(orderItemJson.getString("itemPrice"))));
        orderItem.setItemCosts(BigDecimal.valueOf(Long.parseLong(orderItemJson.getString("itemCosts"))));
        orderItem.setQuantity(orderItemJson.getInt("quantity"));
        orderItem.setOrder_id(orderItemJson.getLong("orderId"));
        return orderItem;
    }

    /**
     * Тут начинаются методы к Вариант №2 с GSON
     * @param inEvent
     */

    private OutEntity getEvent(Object inEvent) {
//        OutEntity inEvent = new Gson().fromJson(response, OutEntity.class);
        OutEntity event = new OutEntity(inEvent.getClass().getSimpleName());
        Map<String, Object> entityFields = event.getBody();



        if(inEvent instanceof Event) {
            fillEventEntityFields((Event) entityFields.get("body"), entityFields);
        } else if(inEvent instanceof Order) {
            fillOrderEntityFields((Order)entityFields.get("body"));
        } else if(inEvent instanceof OrderStatus) {
            fillOrderStatusEntityFields((OrderStatus)entityFields.get("body"));
        } else if(inEvent instanceof User) {
            fillUserEntityFields((User)entityFields.get("body"));
        } else if(inEvent instanceof OrderItem) {
            fillOrderItemEntityFields((OrderItem)entityFields.get("body"));
        } else if(inEvent instanceof Product) {
            fillProductEntityFields((Product)entityFields.get("body"));
        } else if(inEvent instanceof Category) {
            fillCategoryEntityFields((Category)entityFields.get("body"));
        } else if(inEvent instanceof Delivery) {
            fillDeliveryEntityFields((Delivery)entityFields.get("body"));
        } else if(inEvent instanceof Address) {
            fillAddressEntityFields((Address)entityFields.get("body"));
        }

        return event;

//        System.out.println(outEntity.toString());
    }

    private void fillAddressEntityFields(Address body) {
    }

    private void fillDeliveryEntityFields(Delivery body) {

    }

    private void fillCategoryEntityFields(Category body) {

    }

    private void fillProductEntityFields(Product body) {

    }

    private void fillOrderItemEntityFields(OrderItem body) {

    }

    private void fillUserEntityFields(User body) {

    }

    private void fillOrderStatusEntityFields(OrderStatus body) {

    }

    private void fillOrderEntityFields(Order body) {
    }

    private void fillEventEntityFields(Event body, Map<String, Object> entityFields) {
        body.setEntity(getEvent((Order)entityFields.get("entity")));
    }


}
