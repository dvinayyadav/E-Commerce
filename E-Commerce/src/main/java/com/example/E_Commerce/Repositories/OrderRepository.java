package com.example.E_Commerce.Repositories;

import com.example.E_Commerce.Entities.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Ordered,Integer> {

        }
