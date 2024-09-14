package com.example.E_Commerce.Services.Services;

import com.example.E_Commerce.Enums.CardType;
import com.example.E_Commerce.RequestDto.CardRequestDto;
import com.example.E_Commerce.ResponseDto.CardResponseDto;
import com.example.E_Commerce.ResponseDto.CustomerResponseDto;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface CardService {

    String addCard(CardRequestDto cardRequestDto) throws Exception;

    String delCard(int cardId, int customerId) throws Exception;

    List<CardResponseDto> getAllCardsByCustomerId(int customerId);

    List<CardResponseDto> getAllCards();

    String updateExpiryDate(int id, Date expiryDate);

    List<String> getByCardType(CardType cardType);
}
