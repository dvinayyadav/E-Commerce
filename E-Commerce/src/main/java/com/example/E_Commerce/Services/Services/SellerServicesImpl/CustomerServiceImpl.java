package com.example.E_Commerce.Services.Services.SellerServicesImpl;

import com.example.E_Commerce.Converters.CardConverters;
import com.example.E_Commerce.Converters.CustomerConverters;
import com.example.E_Commerce.Entities.Card;
import com.example.E_Commerce.Entities.Cart;
import com.example.E_Commerce.Entities.Customer;
import com.example.E_Commerce.Exceptions.InvalidCustomerId;
import com.example.E_Commerce.Repositories.CardRepository;
import com.example.E_Commerce.Repositories.CustomerRepository;
import com.example.E_Commerce.RequestDto.CustomerRequestDto;
import com.example.E_Commerce.ResponseDto.CardResponseDto;
import com.example.E_Commerce.ResponseDto.CustomerResponseDto;
import com.example.E_Commerce.Services.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;



    @Override
    public String add(CustomerRequestDto customerRequestDto) throws InvalidCustomerId {
        Customer customer;
try {
     customer = CustomerConverters.customerRequestDtoTOCustomer(customerRequestDto);
} catch (Exception mobileNumberExisted) {
    throw new InvalidCustomerId("Sry!!! The Mobile Number that u have entered is already Registered");
}

        Cart cart= Cart.builder()
                .cartTotal(0)
                .customer(customer)
                .build();
        customer.setCart(cart);
       customerRepository.save(customer);
        return "Thanks "+customerRequestDto.getName()+" for registering in our e_commerce Application";
    }

    @Override
    public List<CustomerResponseDto> getAll() {
        List<Customer> customerList=customerRepository.findAll();
        List<CustomerResponseDto> customerResponseDtoList=new ArrayList<>();
        for(Customer customer:customerList){
            CustomerResponseDto customerResponseDto=CustomerConverters.customerToCustomerResponseDto(customer);
            List<Card> cardList=customer.getCards();
            List<CardResponseDto> cardResponseDtoList=new ArrayList<>();
            for(Card card:cardList){
                cardResponseDtoList.add(CardConverters.cardToCardResponseDto(card));
            }
          customerResponseDto.setCardList(cardResponseDtoList);
            customerResponseDtoList.add(customerResponseDto);
        }
        return customerResponseDtoList;
    }

    @Override
    public CustomerResponseDto getById(int id) {

        return CustomerConverters.customerToCustomerResponseDto(customerRepository.findById(id).get());
    }

    @Override
    public CustomerResponseDto getByEmail(String email) {
         return CustomerConverters.customerToCustomerResponseDto(customerRepository.getByEmail(email));
    }

    @Override
    public CustomerResponseDto getByMobNo(String mobNo) {

        return CustomerConverters.customerToCustomerResponseDto(customerRepository.getByMobNo(mobNo));
    }

    @Override
    public CustomerResponseDto getCustomerByMobileNoOrEmail(String mobileOrEmail) {
        List<Customer> customerList=customerRepository.findAll();
        CustomerResponseDto customerResponseDto = null;
        for(Customer customer:customerList){
            if(customer.getEmail().equals(mobileOrEmail) || customer.getMobNo().equals(mobileOrEmail)){
                customerResponseDto=CustomerConverters.customerToCustomerResponseDto(customer);
                break;
            }

        }
        return customerResponseDto;
    }

    @Override
    public List<CustomerResponseDto> getAllCustomersByAge(int age) {
        List<Customer> customerList=customerRepository.getAllCustomerByAge(age);

        List<CustomerResponseDto> customerResponseDtoList=new ArrayList<>();
        for(Customer customer:customerList){
            customerResponseDtoList.add(CustomerConverters.customerToCustomerResponseDto(customer));
        }

        return customerResponseDtoList;
    }

    @Override
    public List<CustomerResponseDto> getAllCustomersInBetweenAge(int lowest, int highest) {
        return CustomerConverters.customerListToCustomerResponseList(customerRepository.getAllCustomerInBetweenAge(lowest,highest));
    }


}
