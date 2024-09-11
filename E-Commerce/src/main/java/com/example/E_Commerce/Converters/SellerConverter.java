package com.example.E_Commerce.Converters;

import com.example.E_Commerce.Entities.Seller;
import com.example.E_Commerce.Repositories.SellerRepository;
import com.example.E_Commerce.RequestDto.SellerRequestDto;
import com.example.E_Commerce.ResponseDto.SellerResponseDto;
import lombok.experimental.UtilityClass;
import org.springframework.web.bind.annotation.RequestBody;

@UtilityClass
public class SellerConverter {

    public static Seller sellerRequestDtoToSeller(SellerRequestDto sellerRequestDto) {
        return Seller.builder()
                .name(sellerRequestDto.getName())
                .email(sellerRequestDto.getEmail())
                .mobileNo(sellerRequestDto.getMobileNo())
                .panNo(sellerRequestDto.getPanNo())
                .build();
    }

    public static SellerResponseDto sellerToSellerResponseDto(Seller seller) {

        return SellerResponseDto.builder()
                .name(seller.getName())
                .email(seller.getEmail())
                .panNo(seller.getPanNo())
                .mobileNo(seller.getMobileNo())
                .build();
    }
}