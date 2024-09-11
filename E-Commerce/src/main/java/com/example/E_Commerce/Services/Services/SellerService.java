package com.example.E_Commerce.Services.Services;

import com.example.E_Commerce.Exceptions.InvalidEmail;
import com.example.E_Commerce.Exceptions.InvalidPanNo;
import com.example.E_Commerce.RequestDto.SellerRequestDto;
import com.example.E_Commerce.ResponseDto.SellerResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SellerService {

     String addSeller(SellerRequestDto sellerRequestDto) ;

     List<SellerResponseDto> getAll();

    SellerResponseDto getByPan(String panNo) throws InvalidPanNo;

    SellerResponseDto getByEmail(String email) throws InvalidEmail;

    String deleteSellerById(int id);

    String updateMobileNoByEmail(String email, String mobileNo);

    String deleteSellerByEmail(String email);

    SellerResponseDto getById(int id);
}
