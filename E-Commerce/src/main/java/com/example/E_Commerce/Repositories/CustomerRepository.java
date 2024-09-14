package com.example.E_Commerce.Repositories;

import com.example.E_Commerce.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Customer getByEmail(String name);

    Customer getByMobNo(String mobNo);

    @Query(value = "select * from customer c where c.age>:age",nativeQuery = true)
    List<Customer> getAllCustomerByAge(int age);

    @Query(value = "select * from customer c where age>:lowest and age<:highest",nativeQuery = true)
    List<Customer> getAllCustomerInBetweenAge(int lowest,int highest);


}
