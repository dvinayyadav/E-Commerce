package com.example.E_Commerce.Converters;

import com.example.E_Commerce.Entities.Cart;
import com.example.E_Commerce.ResponseDto.CartResponseDto;

public class CartConverters {

    public static CartResponseDto cartToCartResponseDto(Cart cart){
       return  CartResponseDto.builder()
                .cartTotal(cart.getCartTotal())
                .customerName(cart.getCustomer().getName())
                .numberOfItems(cart.getNumberOfItems())
                .build();
    }
}
