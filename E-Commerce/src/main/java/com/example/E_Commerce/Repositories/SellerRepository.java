package com.example.E_Commerce.Repositories;

import com.example.E_Commerce.Entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Integer> {
    Seller getByPanNo(String panNo);

    Seller getByEmail(String email);

    void deleteByEmail(String email);
}
