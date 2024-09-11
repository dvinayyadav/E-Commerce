package com.example.E_Commerce.ResponseDto;

import com.example.E_Commerce.Enums.ProductCategory;
import com.example.E_Commerce.Enums.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponseDto {

  String productName;

    int price;

    int quantity;

    ProductCategory productCategory;


    ProductStatus productStatus;


    int sellerId;
}
