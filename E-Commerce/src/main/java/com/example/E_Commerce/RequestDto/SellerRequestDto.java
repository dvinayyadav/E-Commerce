package com.example.E_Commerce.RequestDto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SellerRequestDto {

    private String name;

    private String email;

    private String mobileNo;

    private String panNo;
}
