package com.simple_banking_application.simple_banking_application.Models;

import java.util.Random;

public class AccountCreation {
    private String generateRandomDigits(int length){
        Random random=new Random();
        StringBuilder randomDigits=new StringBuilder();
        for (int i=0;i<length;i++){
            randomDigits.append(random.nextInt(10));
        }
        return randomDigits.toString();
    }

    public String createAccountNumber(String phoneNumber){
        String baseAccount=phoneNumber.substring(0,5);
        String randomPart=generateRandomDigits(11);
        String accountNumber=baseAccount+randomPart;
        return accountNumber;
    }

}
