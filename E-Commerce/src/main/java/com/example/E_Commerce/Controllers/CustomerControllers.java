package com.example.E_Commerce.Controllers;

import com.example.E_Commerce.RequestDto.CustomerRequestDto;
import com.example.E_Commerce.ResponseDto.CustomerResponseDto;
import com.example.E_Commerce.Services.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerControllers {

    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public String add(@RequestBody CustomerRequestDto customerRequestDto)throws Exception{
         return customerService.add(customerRequestDto);
    }
    @GetMapping("/getAll")
    public List<CustomerResponseDto> getAll(){
        return customerService.getAll();
    }

    @GetMapping("/getById/{id}")
    public CustomerResponseDto getById(@PathVariable("id") int id){
      return  customerService.getById(id);
    }

    @GetMapping("/getByEmail/{email}")
    public CustomerResponseDto getByEmail(@PathVariable("email") String email){
       return customerService.getByEmail(email);
    }

    @GetMapping("/getCustomersByMobNoOrEmail")
    public CustomerResponseDto getCustomerByMobileNoOrEmail(@RequestParam("MobileNo/Email")String mobileOrEmail){
        return customerService.getCustomerByMobileNoOrEmail(mobileOrEmail);
    }

    @GetMapping("/getByMobNo/{mobNo}")
    public CustomerResponseDto getByMobNo(@PathVariable("mobNo") String mobNo){
        return customerService.getByMobNo(mobNo);
    }

    @GetMapping("/getCustomersByAge")
    public List<CustomerResponseDto> getAllCustomersByAge(@RequestParam("age")int age){
      return  customerService.getAllCustomersByAge(age);
    }

    @GetMapping("/getAllCustomersInBetweenAge")
    public List<CustomerResponseDto> getAllCustomerInBetweenAge(@RequestParam("lowest") int lowest,@RequestParam("highest") int highest){
        return customerService.getAllCustomersInBetweenAge(lowest,highest);
    }


}
