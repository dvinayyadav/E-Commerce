package com.example.E_Commerce.RequestDto;


import com.example.E_Commerce.Enums.CardType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardRequestDto {

    int customerId;

    Date expiryDate;

    String cardNo;

    int cvv;


    CardType cardType;
}
