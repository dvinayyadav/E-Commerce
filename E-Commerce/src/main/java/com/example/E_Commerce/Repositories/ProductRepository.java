package com.example.E_Commerce.Repositories;

import com.example.E_Commerce.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
