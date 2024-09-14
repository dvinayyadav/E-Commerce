package com.example.E_Commerce.Entities;

import com.example.E_Commerce.Enums.CardType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String cardNo;

    private int cvv;

    private Date expiryDate;

    @Enumerated(EnumType.STRING)
    private CardType cardType;




    @ManyToOne
    @JoinColumn
    Customer customer;
}