package com.example.E_Commerce.ResponseDto;

import com.example.E_Commerce.Entities.Card;
import com.example.E_Commerce.Entities.Cart;
import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerResponseDto {
    String name;

    int age;


    String email;


    String mobNo;

    String address;

    List<CardResponseDto> cardList;


}
