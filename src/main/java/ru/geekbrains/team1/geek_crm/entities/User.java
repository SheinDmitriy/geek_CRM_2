package ru.geekbrains.team1.geek_crm.entities;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "users")
@Data
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String userName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "store")
    private String store;


//    @ManyToOne
//    @JoinColumn(name = "delivery_address_id")
//    private Address deliveryAddress;

     public static final Map<String, String> COLUMN_MAPPINGS = new HashMap<>();

    static {
        COLUMN_MAPPINGS.put("id", "id");
        COLUMN_MAPPINGS.put("username", "userName");
        COLUMN_MAPPINGS.put("password", "password");
        COLUMN_MAPPINGS.put("first_name", "firstName");
        COLUMN_MAPPINGS.put("last_name", "lastName");
        COLUMN_MAPPINGS.put("email", "email");
    }


    public User(Long id, String userName, String firstName, String lastName, String email, String phoneNumber, String store) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.store = store;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", store='" + store + '\'' +
                '}';
    }
}