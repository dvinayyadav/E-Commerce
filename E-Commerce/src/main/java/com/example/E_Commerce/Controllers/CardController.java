package com.example.E_Commerce.Controllers;

import com.example.E_Commerce.Enums.CardType;
import com.example.E_Commerce.RequestDto.CardRequestDto;
import com.example.E_Commerce.ResponseDto.CardResponseDto;
import com.example.E_Commerce.ResponseDto.CustomerResponseDto;
import com.example.E_Commerce.Services.Services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    CardService cardService;

    @PostMapping("/add")
    public String addCard(@RequestBody CardRequestDto cardRequestDto)throws Exception{
        return cardService.addCard(cardRequestDto);
    }

    @PutMapping("/UpdateExpiryDate")
    public String updateExpiryDate(@RequestParam("id") int id,@RequestParam("expiryDate") Date expiryDate){
        return cardService.updateExpiryDate(id,expiryDate);
    }

    @DeleteMapping("/del")
    public String delCard(@RequestParam("cardId")int cardId,@RequestParam("customerId")int customerId)throws Exception{
        return cardService.delCard(cardId,customerId);
    }

    @GetMapping("/getAllCardByCustomerId/{id}")
    public List<CardResponseDto> getCustomerAllCards(@PathVariable("id")int customerId){
        return cardService.getAllCardsByCustomerId(customerId);
    }

    @GetMapping("/getAllCards")
    public List<CardResponseDto> getAllCards(){
        return cardService.getAllCards();
    }

    @GetMapping("/getAllCustomerUsingParticularCardType/{cardType}")
    public List<String> getAllCustomersUsingParticularCardType(@PathVariable("cardType") CardType cardType){
        return cardService.getByCardType(cardType);
    }
}
