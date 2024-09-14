package com.example.E_Commerce.Services.Services.SellerServicesImpl;

import com.example.E_Commerce.Converters.CardConverters;
import com.example.E_Commerce.Converters.CustomerConverters;
import com.example.E_Commerce.Entities.Card;
import com.example.E_Commerce.Entities.Customer;
import com.example.E_Commerce.Enums.CardType;
import com.example.E_Commerce.Exceptions.InvalidCustomerId;
import com.example.E_Commerce.Repositories.CardRepository;
import com.example.E_Commerce.Repositories.CustomerRepository;
import com.example.E_Commerce.RequestDto.CardRequestDto;
import com.example.E_Commerce.RequestDto.CustomerRequestDto;
import com.example.E_Commerce.ResponseDto.CardResponseDto;
import com.example.E_Commerce.ResponseDto.CustomerResponseDto;
import com.example.E_Commerce.Services.Services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CardServicesImpl implements CardService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CardRepository cardRepository;

    @Override
    public String addCard(CardRequestDto cardRequestDto) throws Exception {
        Customer customer;
        try {
            customer = customerRepository.findById(cardRequestDto.getCustomerId()).get();
        } catch (Exception e) {
            throw new Exception("Please check CustomerId and enter again");
        }
        Card card = CardConverters.cardRequestDtoToCard(cardRequestDto);
        card.setCustomer(customer);

        customer.getCards().add(card);
        customerRepository.save(customer);
        return "CARD ADDED AND UPDATED IN CUSTUMER LIST";
    }

    @Override
    public String delCard(int cardId, int customerId) throws Exception {
        Card card = cardRepository.findById(cardId).get();
        if (card == null) {
            throw new Exception("Invalid CardId");
        }
        Customer customer = customerRepository.findById(customerId).get();
        if (customer == null) {
            throw new Exception("Invalid customerID");
        }
        cardRepository.deleteById(cardId);
        List<Card> cardList = customerRepository.findById(customerId).get().getCards();
        cardList.remove(card);
        customerRepository.save(customer);
        return "SUCCESSFULLY REMOVED FROM CARD FROM CARD TABLE AND CUSTOMER LIST";
    }

    @Override
    public List<CardResponseDto> getAllCardsByCustomerId(int customerId) {
        Customer customer = customerRepository.findById(customerId).get();
        List<Card> cardList = customer.getCards();
        List<CardResponseDto> cardResponseDtoList = new ArrayList<>();
        for (Card card : cardList) {
            cardResponseDtoList.add(CardConverters.cardToCardResponseDto(card));
        }
        return cardResponseDtoList;
    }

    @Override
    public List<CardResponseDto> getAllCards() {
        List<Card> cardList = cardRepository.findAll();
        List<CardResponseDto> cardResponseDtoList = new ArrayList<>();
        for (Card card : cardList) {
            cardResponseDtoList.add(CardConverters.cardToCardResponseDto(card));
        }
        return cardResponseDtoList;
    }

    @Override
    public String updateExpiryDate(int id, Date expiryDate) {
        Card card = cardRepository.findById(id).get();
        card.setExpiryDate(expiryDate);
        cardRepository.save(card);
        return "Updated Successfully";
    }


    public List<String> getByCardType(CardType cardType) {
       List<String> customerNames=new ArrayList<>();
        List<Card> cardList=cardRepository.getByCardType(cardType);
        List<CustomerResponseDto> customerResponseDtoList = new ArrayList<>();
        for (Card card : cardList) {
             customerNames.add(card.getCustomer().getName());
        }
        return customerNames ;
    }






}
