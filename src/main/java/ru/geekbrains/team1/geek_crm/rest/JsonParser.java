package ru.geekbrains.team1.geek_crm.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.geekbrains.team1.geek_crm.entities.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JsonParser {
    private String response;


    public JsonParser(String response) throws JSONException, JsonProcessingException {
        this.response = response;
        JSONObject objJson = new JSONObject(response);
        getEntity(objJson);
    }

    private Object getEntity(JSONObject entity) throws JSONException, JsonProcessingException {
        Object object = new Object();
        String eventClassName = entity.getString("entityClassSimpleName");
        JSONObject classBody = entity.getJSONObject("body");

        if(eventClassName.equals("Event")) {
            getEvent(classBody, (Event) object);
        } else if(eventClassName.equals("Order")) {
            getOrder(classBody, (Order) object);
        } else if(eventClassName.equals("OrderStatus") ) {
            getOrderStatus(classBody, (OrderStatus) object);
        } else if(eventClassName.equals("User") ) {
            getUser(classBody, (User) object);
        } else if(eventClassName.equals("OrderItem") ) {
            getOrderItem(classBody, (OrderItem) object);
        } else if(eventClassName.equals("Product") ) {
            getProduct(classBody, (Product) object);
        } else if(eventClassName.equals("Category") ) {
            getCategory(classBody, (Category) object);
        } else if(eventClassName.equals("Delivery") ) {
            getDelivery(classBody, (Delivery) object);
        } else if(eventClassName.equals("Address") ) {
            getAddress(classBody, (Address) object);
        }

        return object;
    }

    private void getProduct(JSONObject productJson, Product product) throws JSONException, JsonProcessingException {
        product = Product.builder()
                .id(productJson.getLong("id"))
                .category((Category) getEntity(productJson.getJSONObject("category")))
                .title(productJson.getString("title"))
                .price(BigDecimal.valueOf(Long.parseLong(productJson.getString("price"))))
                .shortDescription(productJson.getString("shortDescription"))
                .fullDescription(productJson.getString("fullDescription"))
                .createdAt(localDateConvert(productJson.getString("createdAt")))
                .updatedAt(localDateConvert(productJson.getString("updatedAt")))
                .store(productJson.getString("store"))
                .build();
    }

    private void getOrderStatus(JSONObject orderStatusJson, OrderStatus orderStatus) throws JSONException {
//        orderStatus = OrderStatus.builder()
//                .id(orderStatusJson.getS("id"))
//                .build();
        orderStatus = new Gson().fromJson(orderStatusJson.toString(), OrderStatus.class);
    }

    private void getOrderItem(JSONObject orderItemJson, OrderItem orderItem) throws JSONException, JsonProcessingException {
       orderItem = OrderItem.builder()
               .id(orderItemJson.getLong("id"))
               .product((Product) getEntity(orderItemJson.getJSONObject("product")))
               .itemPrice(BigDecimal.valueOf(Long.parseLong(orderItemJson.getString("itemPrice"))))
               .quantity(orderItemJson.getInt("quantity"))
               .itemCosts(BigDecimal.valueOf(Long.parseLong(orderItemJson.getString("itemCosts"))))
               .orderId(orderItemJson.getLong("order"))
               .store(orderItemJson.getString("store"))
               .build();
    }

    private void getAddress(JSONObject addressJson, Address address) throws JSONException {
//        Address
                address = new Gson().fromJson(addressJson.toString(), Address.class);

        //        Address address = Address.builder()
//                .id(addressJson.getLong("id"))
//                .country(addressJson.getString("country"))
//                .city(addressJson.getString("city"))
//                .address(addressJson.getString("address"))
//                .build();
//        return address;

    }

    private void getCategory(JSONObject catJson, Category category) {
        category = new Gson().fromJson(catJson.toString(), Category.class);
//        return category;

    }

    private void getEvent(JSONObject eventJson, Event event) throws JSONException {
         event = Event.builder()
                 .id(eventJson.getLong("id"))
                 .actionType(eventJson.getString("actionType"))
                 .title(eventJson.getString("title"))
                 .description(eventJson.getString("description"))
                 .entityType(eventJson.getString("entityType"))
                 .entityId(eventJson.getLong("entityId"))
                 .createdAt(localDateConvert(eventJson.getString("createdAt")))
                 .serverAcceptedAt(localDateConvert(eventJson.getString("serverAcceptedAt")))
                 .build();
    }

    private void getOrder(JSONObject orderJson, Order order) throws JSONException, JsonProcessingException {
         order = Order.builder()
                .id(orderJson.getLong("id"))
                .orderStatus((OrderStatus) getEntity(orderJson.getJSONObject("orderStatus")))
                .user((User) getEntity(orderJson.getJSONObject("user")))
                .orderItems(getOrderItems(orderJson.getJSONObject("orderItems")))
                .totalItemsCosts(BigDecimal.valueOf(Long.parseLong(orderJson.getString("totalItemsCosts"))))
                .totalCosts(BigDecimal.valueOf(Long.parseLong(orderJson.getString("totalCosts"))))
                .store(orderJson.getString("store"))
                .delivery((Delivery) getEntity(orderJson.getJSONObject("delivery")))
                .createdAt(localDateConvert(orderJson.getString("createdAt")))
                .updatedAt(localDateConvert(orderJson.getString("updatedAt")))
                .build();

        System.out.println(order.toString());
//        return order;
    }

    private LocalDateTime localDateConvert(String str) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime date = LocalDateTime.parse(str, formatter);
        return date;
    }

    private void getDelivery(JSONObject deliveryJson, Delivery delivery) throws JSONException, JsonProcessingException {
         delivery = Delivery.builder()
                 .id(deliveryJson.getLong("id"))
                 .orderId(deliveryJson.getLong("order"))
                 .phoneNumber(deliveryJson.getString("phoneNumber"))
                 .deliveryAddress((Address)getEntity(deliveryJson.getJSONObject("deliveryAddress")))
                 .deliveryCost(BigDecimal.valueOf(Long.parseLong(deliveryJson.getString("totalCosts"))))
                 .deliveryExpectedAt(localDateConvert(deliveryJson.getString("deliveryExpectedAt")))
                 .deliveredAt(localDateConvert(deliveryJson.getString("deliveredAt")))
                 .build();

//        return delivery;
    }

    private List<OrderItem> getOrderItems(JSONObject outOrderItems) throws JSONException, JsonProcessingException {

        Iterator x = outOrderItems.keys();
//        JSONArray jsonArray = new JSONArray();
        List<OrderItem> list = new ArrayList<>();
        while (x.hasNext()){
            String key = (String) x.next();
            list.add((OrderItem) getEntity(new JSONObject(outOrderItems.get(key).toString())));
        }



//        int len = jsonArray.length();
//        for (int i=0;i<len;i++){
//            list.add((OrderItem) getEntity(new JSONObject(jsonArray.get(i).toString())));
//        }

        return list;
    }
}
