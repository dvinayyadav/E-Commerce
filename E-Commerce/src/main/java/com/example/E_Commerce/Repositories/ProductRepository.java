package com.example.E_Commerce.Repositories;

import com.example.E_Commerce.Entities.Product;
import com.example.E_Commerce.ResponseDto.ProductResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    @Query(value="select * from product  where price>=:price and product_category=:category",nativeQuery = true)
    List<Product> getProductByPriceAndCategory(int price, String category);
}
