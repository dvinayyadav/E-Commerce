package com.example.E_Commerce.Services.Services;

import com.example.E_Commerce.Entities.Item;
import com.example.E_Commerce.Entities.Ordered;
import com.example.E_Commerce.Exceptions.ExceedQuantity;
import com.example.E_Commerce.Exceptions.InvalidSeller;
import com.example.E_Commerce.RequestDto.ProductRequestDto;
import com.example.E_Commerce.ResponseDto.ProductResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    String addProduct(ProductRequestDto productRequestDto) throws InvalidSeller;

     ProductResponseDto getById(int id);

    List<ProductResponseDto> getAll();

    List<ProductResponseDto> getAllProductsBySellerEmail(String email);

    String delProcuctIdInSellerId(int productId, int sellerId);

    List<ProductResponseDto> getTopTwoPriceProducts();

    List<ProductResponseDto> getProductByPriceAndCategory(int price, String productCategory);

    void decreaseQuantity(Item item) throws ExceedQuantity;
}
