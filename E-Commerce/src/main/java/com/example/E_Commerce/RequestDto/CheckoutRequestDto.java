package com.example.E_Commerce.RequestDto;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CheckoutRequestDto {

    int customerId;

    String  cardNumber;

    int cvv;
}
