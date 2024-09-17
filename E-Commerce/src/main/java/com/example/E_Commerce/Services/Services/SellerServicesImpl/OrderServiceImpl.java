package com.example.E_Commerce.Services.Services.SellerServicesImpl;

import com.example.E_Commerce.Entities.Card;
import com.example.E_Commerce.Entities.Customer;
import com.example.E_Commerce.Entities.Item;
import com.example.E_Commerce.Entities.Ordered;
import com.example.E_Commerce.Exceptions.ExceedQuantity;
import com.example.E_Commerce.Repositories.OrderRepository;
import com.example.E_Commerce.ResponseDto.OrderResponseDto;
import com.example.E_Commerce.Services.Services.OrderService;
import com.example.E_Commerce.Services.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductService productService;
    @Override
    public Ordered addOrder(Customer customer, Card card) throws ExceedQuantity {
        Ordered ordered=new Ordered();
        ordered.setOrderedNo(UUID.randomUUID().toString());
        String cardNo=card.getCardNo();
        String muskedNumber="";
        for(int i=0;i<cardNo.length()-4;i++){
            muskedNumber+='X';
        }
        muskedNumber+=(cardNo.substring(cardNo.length()-4));
      ordered.setCardUsedForPayment(muskedNumber);
      ordered.setCustomer(customer);
      ordered.setTotalCost(customer.getCart().getCartTotal());
      List<Item> orderedItems=new ArrayList<>();
      for(Item item:customer.getCart().getItems()){
          try {
              productService.decreaseQuantity(item);
             orderedItems.add(item);
          } catch (ExceedQuantity e) {
              throw new ExceedQuantity("OUT OF STOCK");
          }
      }
        ordered.setOrderedItems(orderedItems);
      ordered.setTotalCost(customer.getCart().getCartTotal());
      orderRepository.save(ordered);
      return ordered;
    }
}
