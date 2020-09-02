package ru.geekbrains.team1.geek_crm.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "address")
    private String address;

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
