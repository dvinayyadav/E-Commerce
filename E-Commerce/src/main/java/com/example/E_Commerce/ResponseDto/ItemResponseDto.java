package com.example.E_Commerce.ResponseDto;

import com.example.E_Commerce.Entities.Product;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemResponseDto {

    String productName;

    int priceofOneItem;


    int quantity;



}
