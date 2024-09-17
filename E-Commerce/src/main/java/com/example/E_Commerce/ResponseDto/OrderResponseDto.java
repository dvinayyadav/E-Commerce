package com.example.E_Commerce.ResponseDto;


import com.example.E_Commerce.Entities.Customer;
import com.example.E_Commerce.Entities.Item;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponseDto {

   String orderNumber;

     Date orderDate;

     int totalCost;

     String cardUsedForPayment;


    String customerName;


    List<ItemResponseDto> orderedItems = new ArrayList<>();

}
