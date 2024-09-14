package com.example.E_Commerce.Repositories;

import com.example.E_Commerce.Entities.Cart;
import com.example.E_Commerce.Entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
    @Query(value="select * from product p where p.product_status!='OUT_OF_STOCK'",nativeQuery = true)
    List<Item> getByProductStatus(String outOfStock);
}
