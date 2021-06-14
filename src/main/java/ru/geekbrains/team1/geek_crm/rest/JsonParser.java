package ru.geekbrains.team1.geek_crm.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.team1.geek_crm.entities.*;
import ru.geekbrains.team1.geek_crm.repository.EventRepository;
import ru.geekbrains.team1.geek_crm.repository.OrderRepository;
import ru.geekbrains.team1.geek_crm.service.EventService;
import ru.geekbrains.team1.geek_crm.service.OrderService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static ru.geekbrains.team1.geek_crm.Util.TimeUtils.localDateConvert;

@Service
public class JsonParser {
    private EventService eventService;
    private OrderService orderService;

//    @Autowired
//    public JsonParser(EventService eventService, OrderService orderService) {
//        this.eventService = eventService;
//        this.orderService = orderService;
//    }

    JsonConnector connector = new JsonConnector();
    String url = "https://dev-amin-ishop-heroku.herokuapp.com/api/v1/";
    String url2 = "http://localhost:8080/api/v1/";

    public void parse(String response) throws JSONException, JsonProcessingException {
        JSONObject objJson = new JSONObject(response);
        System.out.println(Objects.requireNonNull(getEntity(objJson), "объект на входе. что-то не то"));
        getEntity(objJson);

//        connector.getEvent(url2, objJson);

    }

    private Object getEntity(JSONObject entity) throws JSONException, JsonProcessingException {
        String eventClassName = entity.getString("entityType");
        JSONObject classBody = entity.getJSONObject("entityFields");

        if(eventClassName.equals("Event")) {
            return getEvent(classBody);
        } else if(eventClassName.equals("Order")) {
            return getOrder(classBody);
        } else if(eventClassName.equals("OrderStatus") ) {
            return getOrderStatus(classBody);
        } else if(eventClassName.equals("User") ) {
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
        } else if(eventClassName.equals("ActionType") ) {
            return getActionType(classBody);
        }
        return null;
    }

    private ActionType getActionType(JSONObject actionTypeJson) {
        ActionType actionType = new Gson().fromJson(actionTypeJson.toString(), ActionType.class);
        return actionType;
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
        System.out.println(productJson);
        Product product = Product.builder()
                .id(productJson.getLong("id"))
                .category((Category) getEntity(productJson.getJSONObject("category")))
                .vendorCode(productJson.getString("vendorCode"))
                .title(productJson.getString("title"))
//                .price(BigDecimal.valueOf(productJson.getLong("itemPrice")))
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

    private Event getEvent(JSONObject eventJson) throws JSONException, JsonProcessingException {
        String requestUrl;
        Event event = Event.builder()
                 .id(eventJson.getLong("id"))
                 .actionType((ActionType) getEntity(eventJson.getJSONObject("actionType")))
                 .entityType(eventJson.getString("entityType"))
                 .entityId(eventJson.getLong("id"))
                 .createdAt(localDateConvert(eventJson.getString("createdAt")))
                 .serverAcceptedAt(localDateConvert(eventJson.getString("serverAcceptedAt")))
                 .build();
//         getEntity(eventJson.getJSONObject(eventJson.getString("actionType")));
//         eventService.save(event);
        System.out.println(event);

        requestUrl = url2 + event.getEntityType().toLowerCase() + "/" + event.getId() + "/" + event.getEntityType().toLowerCase() + "Id";
        connector.get(requestUrl);
        return event;

    }

    private Order getOrder(JSONObject orderJson) throws JSONException, JsonProcessingException {
         Order order = Order.builder()
                .id(orderJson.getLong("id"))
                .orderStatus((OrderStatus) getEntity(orderJson.getJSONObject("orderStatus")))
                .customer((Customer) getEntity(orderJson.getJSONObject("user")))
                .orderItems(getOrderItems(orderJson.getJSONArray("orderItems")))
                .totalItemsCosts(BigDecimal.valueOf(orderJson.getLong("totalItemsCosts")))
                .totalCosts(BigDecimal.valueOf(orderJson.getLong("totalCosts")))
//                .store(orderJson.getString("store"))
                .delivery((Delivery) getEntity(orderJson.getJSONObject("delivery")))
                .createdAt(localDateConvert(orderJson.getString("createdAt")))
                .updatedAt(localDateConvert(orderJson.getString("updatedAt")))
                .build();
        System.out.println(order);
//        orderService.saveOrder(order);
        return order;
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
        for (int i = 0; i < len; i++){
            list.add((OrderItem) getEntity(new JSONObject(classBody.get(i).toString())));
        }
        return list;
    }
}
