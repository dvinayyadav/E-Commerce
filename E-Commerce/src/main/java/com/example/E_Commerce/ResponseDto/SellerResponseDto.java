package com.example.E_Commerce.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class SellerResponseDto {

    String name;

    String email;

    String panNo;

    String mobileNo;

    List<ProductResponseDto> productResponseDtos;
}
