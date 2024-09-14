package com.example.E_Commerce.ResponseDto;

import com.example.E_Commerce.Entities.Customer;
import com.example.E_Commerce.Entities.Item;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartResponseDto {


   int cartTotal;

   int numberOfItems;

   String customerName;

  List<ItemResponseDto> itemList=new ArrayList<>();


}
