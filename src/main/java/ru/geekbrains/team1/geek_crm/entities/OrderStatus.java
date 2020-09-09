package ru.geekbrains.team1.geek_crm.entities;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "order_statuses")
@Data
@Builder
public class OrderStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Short id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Override
    public String toString() {
        return "OrderStatus{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
