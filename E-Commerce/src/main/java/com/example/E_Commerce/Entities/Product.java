package com.example.E_Commerce.Entities;

import com.example.E_Commerce.Enums.ProductCategory;
import com.example.E_Commerce.Enums.ProductStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name="product")
public class Product implements Comparable<Product> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    private String productName;

    private int price;

    private int quantity;

    @Enumerated(EnumType.STRING)
    ProductCategory productCategory;

    @Enumerated(EnumType.STRING)
    ProductStatus productStatus;

    @ManyToOne
    @JoinColumn
    Seller seller;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    List<Item> item=new ArrayList<>();


    @Override
    public int compareTo(Product o) {
        return Double.compare(this.price, o.price);
    }
}
