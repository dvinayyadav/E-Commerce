package com.example.E_Commerce.RequestDto;

import com.example.E_Commerce.Entities.Seller;
import com.example.E_Commerce.Enums.ProductCategory;
import com.example.E_Commerce.Enums.ProductStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequestDto {

    String productName;

    Integer price;

    int quantity;


    ProductCategory productCategory;


    ProductStatus productStatus;


    Seller seller;
}
