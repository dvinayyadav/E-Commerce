package com.example.E_Commerce.Services.Services;

import com.example.E_Commerce.Entities.Cart;
import com.example.E_Commerce.Entities.Item;
import com.example.E_Commerce.Exceptions.EmptyCartException;
import com.example.E_Commerce.Exceptions.ExceedQuantity;
import com.example.E_Commerce.Exceptions.InvalidCardException;
import com.example.E_Commerce.Exceptions.InvalidCustomerId;
import com.example.E_Commerce.RequestDto.CheckoutRequestDto;
import com.example.E_Commerce.RequestDto.ItemRequestDto;
import com.example.E_Commerce.ResponseDto.CartResponseDto;
import com.example.E_Commerce.ResponseDto.OrderResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {
    List<CartResponseDto> getAll();


    String addItemToCart(ItemRequestDto itemRequestDto);



    CartResponseDto getCartByCustomerId(int id);


    CartResponseDto addToCart(Item item, ItemRequestDto itemRequestDto);

    OrderResponseDto checkOutCart(CheckoutRequestDto checkoutRequestDto) throws InvalidCustomerId, InvalidCardException, EmptyCartException, ExceedQuantity;

    Cart resetCart(Cart cart);

    CartResponseDto deleteItemsInCartIfItisOutOfStock(int id);
}
