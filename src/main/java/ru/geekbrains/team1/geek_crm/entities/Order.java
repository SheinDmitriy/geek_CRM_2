package ru.geekbrains.team1.geek_crm.entities;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_status")
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderItem> orderItems;

    @Column(name = "total_items_costs")
    private BigDecimal totalItemsCosts;

    @Column(name = "total_costs")
    private BigDecimal totalCosts;

    @Column(name = "store")
    private String store;

    @OneToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @CreationTimestamp
    private LocalDateTime updatedAt;

//    public Order(String orderStatus, Customer Customer, List<OrderItem> orderItems, BigDecimal totalItemsCosts,
//                 BigDecimal totalCosts, String store, Delivery delivery, LocalDateTime createdAt, LocalDateTime updatedAt) {
//        this.orderStatus = orderStatus;
//        this.Customer = Customer;
//        this.orderItems = orderItems;
//        this.totalItemsCosts = totalItemsCosts;
//        this.totalCosts = totalCosts;
//        this.store = store;
//        this.delivery = delivery;
//        this.createdAt = createdAt;
//        this.updatedAt = updatedAt;
//    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderStatus=" + orderStatus +
                ", userId=" + customer.toString() +
                ", orderItems=" + orderItems +
                ", totalItemsCosts=" + totalItemsCosts +
                ", totalCosts=" + totalCosts +
                ", delivery=" + delivery +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", store=" + store +
                '}';
    }

//    public static class builder {
//    }

//    public class builder extends Order {
//        builder(Long id, String orderStatus, Customer Customer, List<OrderItem> orderItems, BigDecimal totalItemsCosts, BigDecimal totalCosts, String store, Delivery delivery, LocalDateTime createdAt, LocalDateTime updatedAt) {
//            super(id, orderStatus, Customer, orderItems, totalItemsCosts, totalCosts, store, delivery, createdAt, updatedAt);
//        }
//    }
}
