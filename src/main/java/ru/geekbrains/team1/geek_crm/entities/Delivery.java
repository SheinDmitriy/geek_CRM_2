package ru.geekbrains.team1.geek_crm.entities;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "deliveries")
@Data
@Builder
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Long orderId;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "delivery_address_id")
    private Address deliveryAddress;

    @Column(name = "delivery_cost")
    private BigDecimal deliveryCost;

    @Column(name = "delivery_expected_at")
    private LocalDateTime deliveryExpectedAt;

    @Column(name = "delivered_at")
    private LocalDateTime deliveredAt;

    @Override
    public String toString() {
        return "Delivery{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", deliveryAddress=" + deliveryAddress +
                ", deliveryCost=" + deliveryCost +
                ", deliveryExpectedAt=" + deliveryExpectedAt +
                ", deliveredAt=" + deliveredAt +
                '}';
    }
}
