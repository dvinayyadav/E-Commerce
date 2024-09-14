package com.example.E_Commerce.ResponseDto;

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
public class CardResponseDto {

    int id;

    String cardNo;

    Date expiryDate;

    CardType cardType;

    int custumerId;
}
