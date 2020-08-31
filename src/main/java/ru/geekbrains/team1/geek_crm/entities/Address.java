package ru.geekbrains.team1.geek_crm.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "delivery_addresses")
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "address")
    private String address;


}
