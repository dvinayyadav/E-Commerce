package com.example.E_Commerce.Converters;

import com.example.E_Commerce.Entities.Card;
import com.example.E_Commerce.RequestDto.CardRequestDto;
import com.example.E_Commerce.ResponseDto.CardResponseDto;

public class CardConverters {
    public static Card cardRequestDtoToCard(CardRequestDto cardRequestDto){
        return Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .cardType(cardRequestDto.getCardType())
                .cvv(cardRequestDto.getCvv())
                .expiryDate(cardRequestDto.getExpiryDate())
                .build();
    }

    public static CardResponseDto cardToCardResponseDto(Card card){
       return CardResponseDto.builder()
               .id(card.getId())
                .cardNo(card.getCardNo())
                .cardType(card.getCardType())
               .expiryDate(card.getExpiryDate())
               .custumerId(card.getCustomer().getId())

                .build();
    }
}
