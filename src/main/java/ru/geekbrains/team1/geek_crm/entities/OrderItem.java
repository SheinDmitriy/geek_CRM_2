package ru.geekbrains.team1.geek_crm.entities;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_item")
@Data
@Builder
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "item_price")
    private BigDecimal itemPrice;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "item_costs")
    private BigDecimal itemCosts;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Long order_id;

    @Column(name = "store")
    private String store;

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", product=" + product.toString() +
                ", itemPrice=" + itemPrice +
                ", quantity=" + quantity +
                ", itemCosts=" + itemCosts +
                ", order_id=" + order_id +
                ", store=" + store +
                '}';
    }
}
