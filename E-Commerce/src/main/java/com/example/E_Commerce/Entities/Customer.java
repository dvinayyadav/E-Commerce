package com.example.E_Commerce.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="customer")
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    private String name;

    private int age;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String mobNo;

    private String address;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    List<Card> cards = new ArrayList<>();

    @OneToOne(mappedBy = "customer",cascade = CascadeType.ALL)
    Cart cart;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    List<Ordered> orders = new ArrayList<>();
}
