package com.example.E_Commerce.Controllers;

import com.example.E_Commerce.Entities.Cart;
import com.example.E_Commerce.Entities.Item;
import com.example.E_Commerce.Exceptions.*;
import com.example.E_Commerce.RequestDto.CheckoutRequestDto;
import com.example.E_Commerce.RequestDto.ItemRequestDto;
import com.example.E_Commerce.ResponseDto.CartResponseDto;
import com.example.E_Commerce.ResponseDto.CustomerResponseDto;
import com.example.E_Commerce.ResponseDto.OrderResponseDto;
import com.example.E_Commerce.Services.Services.CartService;
import com.example.E_Commerce.Services.Services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;

    @Autowired
    ItemService itemService;


    @GetMapping("/getAll")
    public List<CartResponseDto> getAll(){
        return cartService.getAll();
    }

    @PostMapping("/AddItemToCart")
    public ResponseEntity addItemToCart(@RequestBody ItemRequestDto itemRequestDto) throws InvalidProuductId, InvalidCustomerId, ExceedQuantity {
        try{
            Item item = itemService.createItem(itemRequestDto);
            CartResponseDto cartResponseDto = cartService.addToCart(item,itemRequestDto);
            return new ResponseEntity(cartResponseDto,HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/GetCartByCustomerId")
    public CartResponseDto getCartByCustomerId(@RequestParam("customerId")int id){
        return cartService.getCartByCustomerId(id);
    }

    @PostMapping("/orderingItems")
    public OrderResponseDto checkOutCart(@RequestBody CheckoutRequestDto checkoutRequestDto) throws EmptyCartException, InvalidCustomerId, InvalidCardException, ExceedQuantity {

       return cartService.checkOutCart(checkoutRequestDto);
    }

    @DeleteMapping("/deleteItemsIfItisOutOfStockByCustomerId")
    public CartResponseDto deleteItemsInCartIfItisOutOfStock(@RequestParam("id")int id){
       return  cartService.deleteItemsInCartIfItisOutOfStock(id);
    }



}
