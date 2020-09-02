package ru.geekbrains.team1.geek_crm.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "type", unique = true)
    private String type;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "entity")
    private Object entity;

    @Column(name = "delivery_status")
    private String deliveryStatus;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "delivered_at")
    private LocalDateTime deliveredAt;

}
