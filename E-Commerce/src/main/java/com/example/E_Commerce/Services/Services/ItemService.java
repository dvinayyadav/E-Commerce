package com.example.E_Commerce.Services.Services;

import com.example.E_Commerce.Entities.Item;
import com.example.E_Commerce.Exceptions.ExceedQuantity;
import com.example.E_Commerce.Exceptions.InvalidCustomerId;
import com.example.E_Commerce.Exceptions.InvalidProuductId;
import com.example.E_Commerce.RequestDto.ItemRequestDto;
import com.example.E_Commerce.ResponseDto.ItemResponseDto;
import jakarta.mail.FetchProfile;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public interface ItemService {

//    public Item addItem(ItemRequestDto itemRequestDto) throws InvalidCustomerId, InvalidProuductId, ExceedQuantity;

    List<ItemResponseDto> getAll();

    String delAllItemsInCartByCustomerId(int id);

    String deletAllItems();

    Item createItem(ItemRequestDto itemRequestDto) throws Exception;
}
