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
import java.util.List;

public class JsonParser {
//    private String response;
//
//    public JsonParser(String response) throws JSONException, JsonProcessingException {
//        this.response = response;
//
//    }

    public void parse(String response) throws JSONException, JsonProcessingException {
        JSONObject objJson = new JSONObject(response);
        System.out.println(getEntity(objJson).toString());
    }

    private Object getEntity(JSONObject entity) throws JSONException, JsonProcessingException {
        String eventClassName = entity.getString("entityClassSimpleName");
        JSONObject classBody = entity.getJSONObject("body");

        if(eventClassName.equals("Event")) {
            return getEvent(classBody);
        } else if(eventClassName.equals("Order")) {
            return getOrder(classBody);
        } else if(eventClassName.equals("OrderStatus") ) {
            return getOrderStatus(classBody);
        } else if(eventClassName.equals("Customer") ) {
            return getCustomer(classBody);
        } else if(eventClassName.equals("OrderItem") ) {
            return getOrderItem(classBody);
        } else if(eventClassName.equals("Product") ) {
            return getProduct(classBody);
        } else if(eventClassName.equals("Category") ) {
            return getCategory(classBody);
        } else if(eventClassName.equals("Delivery") ) {
            return getDelivery(classBody);
        } else if(eventClassName.equals("Address") ) {
            return getAddress(classBody);
        }
        return null;
    }

    private Customer getCustomer(JSONObject customerJson) throws JSONException, JsonProcessingException {
        Customer customer = Customer.builder()
                .id(customerJson.getLong("id"))
                .userName(customerJson.getString("userName"))
                .firstName(customerJson.getString("firstName"))
                .lastName(customerJson.getString("lastName"))
                .email(customerJson.getString("email"))
                .phoneNumber(customerJson.getString("phoneNumber"))
//                .store(userJson.getString("store"))
                .deliveryAddress((Address) getEntity(customerJson.getJSONObject("deliveryAddress")))
                .build();
        return customer;
    }

    private Product getProduct(JSONObject productJson) throws JSONException, JsonProcessingException {
        Product product = Product.builder()
                .id(productJson.getLong("id"))
                .category((Category) getEntity(productJson.getJSONObject("category")))
                .vendorCode(productJson.getString("vendorCode"))
                .title(productJson.getString("title"))
                .price(BigDecimal.valueOf(productJson.getLong("price")))
                .shortDescription(productJson.getString("shortDescription"))
//                .fullDescription(productJson.getString("fullDescription"))
//                .createdAt(localDateConvert(productJson.getString("createdAt")))
//                .updatedAt(localDateConvert(productJson.getString("updatedAt")))
//                .store(productJson.getString("store"))
                .build();
        return product;
    }

    private OrderStatus getOrderStatus(JSONObject orderStatusJson) {
         OrderStatus orderStatus = new Gson().fromJson(orderStatusJson.toString(), OrderStatus.class);
         return orderStatus;
    }

    private OrderItem getOrderItem(JSONObject orderItemJson) throws JSONException, JsonProcessingException {
       OrderItem orderItem = OrderItem.builder()
               .id(orderItemJson.getLong("id"))
               .product((Product) getEntity(orderItemJson.getJSONObject("product")))
               .itemPrice(BigDecimal.valueOf(orderItemJson.getLong("itemPrice")))
               .quantity(orderItemJson.getInt("quantity"))
               .itemCosts(BigDecimal.valueOf(orderItemJson.getLong("itemCosts")))
               .orderId(orderItemJson.getLong("order"))
//               .store(orderItemJson.getString("store"))
               .build();
       return orderItem;
    }

    private Address getAddress(JSONObject addressJson) {
        Address address = new Gson().fromJson(addressJson.toString(), Address.class);
        return address;
    }

    private Category getCategory(JSONObject catJson) {
        Category  category = new Gson().fromJson(catJson.toString(), Category.class);
        return category;
    }

    private Event getEvent(JSONObject eventJson) throws JSONException {
         Event event = Event.builder()
                 .id(eventJson.getLong("id"))
                 .actionType(eventJson.getString("actionType"))
                 .title(eventJson.getString("title"))
                 .description(eventJson.getString("description"))
                 .entityType(eventJson.getString("entityType"))
                 .entityId(eventJson.getLong("entityId"))
                 .createdAt(localDateConvert(eventJson.getString("createdAt")))
                 .serverAcceptedAt(localDateConvert(eventJson.getString("serverAcceptedAt")))
                 .build();
         return event;
    }

    private Order getOrder(JSONObject orderJson) throws JSONException, JsonProcessingException {
         Order order = Order.builder()
                .id(orderJson.getLong("id"))
                .orderStatus((OrderStatus) getEntity(orderJson.getJSONObject("orderStatus")))
                .customer((Customer) getEntity(orderJson.getJSONObject("Customer")))
                .orderItems(getOrderItems(orderJson.getJSONArray("orderItems")))
                .totalItemsCosts(BigDecimal.valueOf(orderJson.getLong("totalItemsCosts")))
                .totalCosts(BigDecimal.valueOf(orderJson.getLong("totalCosts")))
//                .store(orderJson.getString("store"))
                .delivery((Delivery) getEntity(orderJson.getJSONObject("delivery")))
                .createdAt(localDateConvert(orderJson.getString("createdAt")))
                .updatedAt(localDateConvert(orderJson.getString("updatedAt")))
                .build();
        return order;
    }

    private LocalDateTime localDateConvert(String dateTime) {
        if (!dateTime.equals("null")) {
            dateTime = dateTime.replace("T", " ");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime date = LocalDateTime.parse(dateTime, formatter);

            return date;
        } else return null;

    }

    private Delivery getDelivery(JSONObject deliveryJson) throws JSONException, JsonProcessingException {
         Delivery delivery = Delivery.builder()
                 .id(deliveryJson.getLong("id"))
                 .orderId(deliveryJson.getLong("order"))
                 .phoneNumber(deliveryJson.getString("phoneNumber"))
                 .deliveryAddress((Address)getEntity(deliveryJson.getJSONObject("deliveryAddress")))
                 .deliveryCost(BigDecimal.valueOf(deliveryJson.getLong("deliveryCost")))
                 .deliveryExpectedAt(localDateConvert(deliveryJson.getString("deliveryExpectedAt")))
                 .deliveredAt(localDateConvert(deliveryJson.getString("deliveredAt")))
                 .build();
         return delivery;
    }

    private List<OrderItem> getOrderItems(JSONArray classBody) throws JSONException, JsonProcessingException {
        List<OrderItem> list = new ArrayList<>();
        int len = classBody.length();
        for (int i=0;i<len;i++){
            list.add((OrderItem) getEntity(new JSONObject(classBody.get(i).toString())));
        }
        return list;
    }
}
