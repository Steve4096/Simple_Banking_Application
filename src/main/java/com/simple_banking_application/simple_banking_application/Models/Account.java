package com.simple_banking_application.simple_banking_application.Models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Account {
    private final StringProperty owner;
    private final StringProperty accountNumber;
    private final DoubleProperty balance;

    /*public Account(String owner,String accountNumber,Double balance){
        this.owner=new SimpleStringProperty(this,"owner",owner);
        this.accountNumber=new SimpleStringProperty(this,"accountNumber",accountNumber);
        this.balance=new SimpleDoubleProperty(this,"balance",balance);
    }*/

    public Account(){
        this.owner=new SimpleStringProperty("");
        this.accountNumber=new SimpleStringProperty("");
        this.balance=new SimpleDoubleProperty();
    }

    public StringProperty ownerProperty(){return owner;}

    public StringProperty accountNumberProperty(){return accountNumber;}

    public DoubleProperty balanceProperty(){return balance;}
}
