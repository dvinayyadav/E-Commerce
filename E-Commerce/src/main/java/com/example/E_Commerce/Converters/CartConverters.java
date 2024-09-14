package com.example.E_Commerce.Converters;

import com.example.E_Commerce.Entities.Cart;
import com.example.E_Commerce.Entities.Item;
import com.example.E_Commerce.ResponseDto.CartResponseDto;
import com.example.E_Commerce.ResponseDto.ItemResponseDto;

import java.util.ArrayList;
import java.util.List;

public class CartConverters {

    public static CartResponseDto cartToCartResponseDto(Cart cart){

        CartResponseDto cartResponseDto=CartResponseDto.builder()
                .cartTotal(cart.getCartTotal())
                .customerName(cart.getCustomer().getName())
                .numberOfItems(cart.getNumberOfItems())
                .build();
        List<Item> itemList=cart.getItems();
        List<ItemResponseDto> DtoList=new ArrayList<>();
        for(Item item:itemList){
            DtoList.add(ItemConverters.itemResponseDtoToItem(item));
        }
       cartResponseDto.setItemList(DtoList);
        return cartResponseDto;
    }
}
