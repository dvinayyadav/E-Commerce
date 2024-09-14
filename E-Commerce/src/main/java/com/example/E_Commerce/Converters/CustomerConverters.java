package com.example.E_Commerce.Converters;

import com.example.E_Commerce.Entities.Card;
import com.example.E_Commerce.Entities.Customer;
import com.example.E_Commerce.Entities.Item;
import com.example.E_Commerce.RequestDto.CustomerRequestDto;
import com.example.E_Commerce.ResponseDto.CardResponseDto;
import com.example.E_Commerce.ResponseDto.CartResponseDto;
import com.example.E_Commerce.ResponseDto.CustomerResponseDto;
import com.example.E_Commerce.ResponseDto.ItemResponseDto;

import java.util.ArrayList;
import java.util.List;

public class CustomerConverters {

    public static Customer customerRequestDtoTOCustomer(CustomerRequestDto customerRequestDto){
        return Customer.builder()
                .age(customerRequestDto.getAge())
                .name(customerRequestDto.getName())
                .mobNo(customerRequestDto.getMobNo())
                .email(customerRequestDto.getEmail())
                .address(customerRequestDto.getAddress())
                .build();

    }

    public static CustomerResponseDto customerToCustomerResponseDto(Customer customer){
        CustomerResponseDto customer1= CustomerResponseDto.builder()
                .email(customer.getEmail())
                .mobNo(customer.getMobNo())
                .age(customer.getAge())
                .name(customer.getName())
                .address(customer.getAddress())
                .build();
        List<Card> cardList=customer.getCards();
        List<CardResponseDto> cardResponseDtoList=new ArrayList<>();
        for(Card card:cardList){
            cardResponseDtoList.add(CardConverters.cardToCardResponseDto(card));
        }
        customer1.setCardList(cardResponseDtoList);

        return customer1;
    }

    public static List<CustomerResponseDto> customerListToCustomerResponseList(List<Customer> customerList){
        List<CustomerResponseDto> customerResponseDtoList=new ArrayList<>();
        for(Customer customer:customerList){
            customerResponseDtoList.add(CustomerConverters.customerToCustomerResponseDto(customer));
        }
        return customerResponseDtoList;
    }
}
