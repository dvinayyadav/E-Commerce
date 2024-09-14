package com.example.E_Commerce.Services.Services;

import com.example.E_Commerce.Entities.Item;
import com.example.E_Commerce.RequestDto.ItemRequestDto;
import com.example.E_Commerce.ResponseDto.CartResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {
    List<CartResponseDto> getAll();


    String addItemToCart(ItemRequestDto itemRequestDto);



    CartResponseDto getCartByCustomerId(int id);


    CartResponseDto addToCart(Item item, ItemRequestDto itemRequestDto);
}
