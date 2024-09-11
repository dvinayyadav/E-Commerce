package com.example.E_Commerce.Controllers;

import com.example.E_Commerce.Exceptions.InvalidEmail;
import com.example.E_Commerce.Exceptions.InvalidPanNo;
import com.example.E_Commerce.RequestDto.SellerRequestDto;
import com.example.E_Commerce.ResponseDto.SellerResponseDto;
import com.example.E_Commerce.Services.Services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    SellerService sellerService;

    @PostMapping("/add")
    public String addSeller(@RequestBody SellerRequestDto sellerRequestDto){

        return sellerService.addSeller(sellerRequestDto);

    }

    @GetMapping("/getAllSellers")
    public List<SellerResponseDto> getAll(){
        return sellerService.getAll();
    }

    @GetMapping("/getById/{id}")
    public SellerResponseDto getById(@PathVariable("id")int id) {
        return sellerService.getById(id);
    }

    @GetMapping("/getByPan/{panNo}")
    public SellerResponseDto getByPan(@PathVariable("panNo")String panNo)throws InvalidPanNo {
        return sellerService.getByPan(panNo);
    }

    @GetMapping("/getByEmail/{email}")
    public SellerResponseDto getByEmail(@PathVariable("email")String email)throws InvalidEmail {
        return sellerService.getByEmail(email);
    }

    @PutMapping("/UpdateUsingEmail")
    public String updateByMobileEmail(@RequestParam("email")String email,@RequestParam("mobileNo")String mobileNo){
        return sellerService.updateMobileNoByEmail(email,mobileNo);
    }

    @DeleteMapping("/deleteSellerById")
    public String deleteSellerById(@RequestParam("id")int id){
        return sellerService.deleteSellerById(id);
    }

    @DeleteMapping("/deleteSellerByEmail")
    public String deleteSellerByEmail(@RequestParam("email")String email){
        return sellerService.deleteSellerByEmail(email);
    }


}
