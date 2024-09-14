package com.example.E_Commerce.Converters;

import com.example.E_Commerce.Entities.Product;
import com.example.E_Commerce.RequestDto.ProductRequestDto;
import com.example.E_Commerce.ResponseDto.ProductResponseDto;

public class ProductConverters {

    public static Product ProductRequestDtoToProduct(ProductRequestDto productRequestDto){
        return Product.builder()
                .productName(productRequestDto.getProductName())
                .price(productRequestDto.getPrice())
                .stockAvailable(productRequestDto.getStockAvailable())
                .productCategory(productRequestDto.getProductCategory())
                .productStatus(productRequestDto.getProductStatus())
                .build();
    }

    public static ProductResponseDto productToProductResponseDto(Product product){
        return ProductResponseDto.builder()
                .productName(product.getProductName())
                .price(product.getPrice())
                .productCategory(product.getProductCategory())
                .quantity(product.getStockAvailable())
                .sellerId(product.getSeller().getId())
                .productStatus(product.getProductStatus())
                .build();
    }
}
