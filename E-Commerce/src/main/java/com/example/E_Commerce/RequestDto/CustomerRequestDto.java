package com.example.E_Commerce.RequestDto;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerRequestDto {

     String name;

     int age;

      String email;


     String mobNo;

     String address;

}
