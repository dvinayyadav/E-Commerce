package com.example.E_Commerce.Converters;

import com.example.E_Commerce.Entities.Item;
import com.example.E_Commerce.Entities.Ordered;
import com.example.E_Commerce.Repositories.OrderRepository;
import com.example.E_Commerce.ResponseDto.ItemResponseDto;
import com.example.E_Commerce.ResponseDto.OrderResponseDto;

import java.util.ArrayList;
import java.util.List;

public class OrderedConverters {

    public static OrderResponseDto OrderToOrderResponseDto(Ordered ordered){
        OrderResponseDto orderResponseDto=OrderResponseDto.builder()
                .orderDate(ordered.getOrderDate())
                .orderNumber(ordered.getOrderedNo())
                .totalCost(ordered.getTotalCost())
                .customerName(ordered.getCustomer().getName())
                .cardUsedForPayment(ordered.getCardUsedForPayment())
                .build();
        List<ItemResponseDto> itemResponseDtoList=new ArrayList<>();
        List<Item> itemList=ordered.getOrderedItems();
        for(Item item:itemList){
            itemResponseDtoList.add(ItemConverters.itemResponseDtoToItem(item));
        }
        orderResponseDto.setOrderedItems(itemResponseDtoList);
        return orderResponseDto;
    }
}
