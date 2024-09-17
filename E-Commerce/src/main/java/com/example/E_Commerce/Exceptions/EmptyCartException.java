package com.example.E_Commerce.Exceptions;

public class EmptyCartException extends Exception{
    public EmptyCartException(String message){
        super(message);
    }
}
