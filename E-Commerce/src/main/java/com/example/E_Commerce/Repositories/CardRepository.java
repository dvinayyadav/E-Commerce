package com.example.E_Commerce.Repositories;

import com.example.E_Commerce.Entities.Card;
import com.example.E_Commerce.Enums.CardType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {


    List<Card> getByCardType(CardType cardType);

    Card findByCardNo(String cardNumber);
}

