package com.example.E_Commerce.Services.Services;

import com.example.E_Commerce.RequestDto.CustomerRequestDto;
import com.example.E_Commerce.ResponseDto.CustomerResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    String add(CustomerRequestDto customerRequestDto) throws Exception;

    List<CustomerResponseDto> getAll();

    CustomerResponseDto getById(int id);

    CustomerResponseDto getByEmail(String email);

    CustomerResponseDto getByMobNo(String mobNo);

    CustomerResponseDto getCustomerByMobileNoOrEmail(String mobileOrEmail);

    List<CustomerResponseDto> getAllCustomersByAge(int age);

    List<CustomerResponseDto> getAllCustomersInBetweenAge(int lowest, int highest);


}
