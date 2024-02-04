package com.customers.CustomerManagement;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(String message){
        super(message) ;
    }
}