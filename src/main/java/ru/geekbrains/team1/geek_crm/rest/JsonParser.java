package ru.geekbrains.team1.geek_crm.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.geekbrains.team1.geek_crm.entities.*;
import ru.geekbrains.team1.geek_crm.entities.Order.OrderBuilder;
import ru.geekbrains.team1.geek_crm.entities.OutEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonParser {
    private String response;


    public JsonParser(String response) throws JSONException, JsonProcessingException {
        this.response = response;
        JSONObject objJson = new JSONObject(response);
        getEntity(objJson);
    }

    private void getEntity(JSONObject entity) throws JSONException, JsonProcessingException {
        String eventClassName = entity.getString("entityClassSimpleName");
        JSONObject classBody = entity.getJSONObject("body");

        if(eventClassName.equals("Event")) {
            getEvent(classBody);
        } else if(eventClassName.equals("Order")) {
            getOrder(classBody);
        } else if(eventClassName.equals("OrderStatus") ) {
            getOrderStatus(classBody);
        } else if(eventClassName.equals("User") ) {
            getUser(classBody);
        } else if(eventClassName.equals("OrderItem") ) {
            getOrderItems(entity.getJSONArray("body"));
        } else if(eventClassName.equals("Product") ) {
            getProduct(classBody);
        } else if(eventClassName.equals("Category") ) {
            getCategory(classBody);
        } else if(eventClassName.equals("Delivery") ) {
            getDelivery(classBody);
        } else if(eventClassName.equals("Address") ) {
            getAddress(classBody);
        }
    }

    private Address getAddress(JSONObject addressJson) throws JSONException {
        Address address = new Gson().fromJson(addressJson.toString(), Address.class);

        //        Address address = Address.builder()
//                .id(addressJson.getLong("id"))
//                .country(addressJson.getString("country"))
//                .city(addressJson.getString("city"))
//                .address(addressJson.getString("address"))
//                .build();
        return address;

    }

    private Category getCategory(JSONObject catJson) {
        Category category = new Gson().fromJson(catJson.toString(), Category.class);
        return category;

    }

    private void getEvent(JSONObject body) {

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

    private Order getOrder(JSONObject orderJson) throws JSONException {
        Order order = Order.builder()
                .id(orderJson.getLong("id"))
                .orderStatus(getOrderStatus(orderJson.getJSONObject("orderStatus")))
                .user(getUser(orderJson.getJSONObject("user")))
                .orderItems(getOrderItems(orderJson.getJSONArray("orderItems")))
                .totalItemsCosts(BigDecimal.valueOf(Long.parseLong(orderJson.getString("totalItemsCosts"))))
                .totalCosts(BigDecimal.valueOf(Long.parseLong(orderJson.getString("totalCosts"))))
                .store(orderJson.getString("store"))
                .delivery(getDelivery(orderJson.getJSONObject("delivery")))
                .createdAt(localDateConvert(orderJson.getString("createdAt")))
                .updatedAt(localDateConvert(orderJson.getString("updatedAt")))
                .build();

        System.out.println(order.toString());
        return order;
    }

    private LocalDateTime localDateConvert(String str) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime date = LocalDateTime.parse(str, formatter);
        return date;
    }

    private Delivery getDelivery(JSONObject deliveryJson) throws JSONException {
        Delivery delivery = Delivery.builder()
                .id(deliveryJson.getLong("id"))
                .orderId(deliveryJson.getLong("order"))
                .phoneNumber(deliveryJson.getString("phoneNumber"))
                .deliveryAddress(getAddress(deliveryJson.getJSONObject("deliveryAddress").getJSONObject("body")))
                .build();

        return delivery;
    }

    private OrderStatus getOrderStatus(JSONObject orderJson) {
        return null;
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
}
