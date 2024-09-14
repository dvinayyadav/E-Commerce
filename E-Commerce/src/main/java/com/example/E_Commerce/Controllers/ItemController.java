package com.example.E_Commerce.Controllers;

import com.example.E_Commerce.ResponseDto.ItemResponseDto;
import com.example.E_Commerce.Services.Services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    ItemService itemService;


    @GetMapping("/getAllItems")
    public List<ItemResponseDto> getAll(){
       return  itemService.getAll();
    }

    @PutMapping("/DeletingAllItemsFromAParticularCustomer")
    public String delAllItemsInCartUsingCustomerId(@RequestParam("customerId")int id){
        return itemService.delAllItemsInCartByCustomerId(id);
    }

    @DeleteMapping("/deleteAllItems")
    public String deleteAllItems(){
        return itemService.deletAllItems();
    }

}
