package com.example.E_Commerce.Services.Services;

import com.example.E_Commerce.Entities.Card;
import com.example.E_Commerce.Entities.Customer;
import com.example.E_Commerce.Entities.Ordered;
import com.example.E_Commerce.Exceptions.ExceedQuantity;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    Ordered addOrder(Customer customer, Card card) throws ExceedQuantity;
}
