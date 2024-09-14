package com.example.E_Commerce.Converters;

import com.example.E_Commerce.Entities.Item;
import com.example.E_Commerce.Repositories.CustomerRepository;
import com.example.E_Commerce.Repositories.ProductRepository;
import com.example.E_Commerce.RequestDto.ItemRequestDto;
import com.example.E_Commerce.ResponseDto.ItemResponseDto;
import org.springframework.beans.factory.annotation.Autowired;

public class ItemConverters {

    @Autowired
    static CustomerRepository customerRepository;
    @Autowired
    static ProductRepository productRepository;

    public static Item ItemRequestDtoToItem(ItemRequestDto itemRequestDto){
        return Item.builder()
                .requiredQuantity(itemRequestDto.getRequiredQuantity())
                .build();
    }

    public static ItemResponseDto itemResponseDtoToItem(Item item){
        return ItemResponseDto.builder()
                .productName(item.getProduct().getProductName())
                .priceofOneItem(item.getProduct().getPrice())
                .quantity(item.getProduct().getQuantity())
                .build();
    }
}
