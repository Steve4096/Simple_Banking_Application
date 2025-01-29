package com.simple_banking_application.simple_banking_application.Models;

import javafx.beans.InvalidationListener;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.time.LocalDateTime;

public class Client {

    private final IntegerProperty clientID;
    private final StringProperty fname;
    private final StringProperty lname;
    private final Account account;
    private final StringProperty phoneNumber;
    private final ObjectProperty<LocalDateTime> dateCreated;
    private final StringProperty emailAddress;

    /*public Client(String fname, String lname, Account accountNumber,String phoneNumber, LocalDateTime dateCreated) {
        this.fname = new SimpleStringProperty(this, "fname", fname);
        this.lname=new SimpleStringProperty(this,"lname",lname);
        this.accountNumber= new SimpleStringProperty<>(this,"accountNumber",accountNumber);
        this.phoneNumber=new SimpleStringProperty(this,"phoneNumber",phoneNumber);
        this.dateCreated=new SimpleObjectProperty<>(this,"dateCreated",dateCreated);
    }*/

    /*public Client(){
        this.clientID=new SimpleIntegerProperty();
        this.fname=new SimpleStringProperty("");
        this.lname=new SimpleStringProperty("");
        this.phoneNumber=new SimpleStringProperty("");
        this.dateCreated=new SimpleObjectProperty(null);
        this.emailAddress=new SimpleStringProperty("");
        this.account=new Account();
    }*/

    public Client(Integer clientID,String fname,String lname,String phoneNumber,String emailAddress,String account){
        this.clientID=new SimpleIntegerProperty(clientID);
        this.fname=new SimpleStringProperty(fname);
        this.lname=new SimpleStringProperty(lname);
        this.phoneNumber=new SimpleStringProperty(phoneNumber);
        this.dateCreated=new SimpleObjectProperty(null);
        this.emailAddress=new SimpleStringProperty(emailAddress);
        this.account=new Account();
    }


    public IntegerProperty clientIDProperty(){return clientID;}

    public StringProperty fnameProperty(){return fname;}

    public StringProperty lnameProperty(){return lname;}

    public StringProperty phoneNumberProperty(){return phoneNumber;}

    public ObjectProperty dateTimeProperty(){return dateCreated;}

    public StringProperty emailAddressProperty(){return emailAddress;}

    public Account getAccount(){return account;}

    public StringProperty getAccountNumber(){
        return account.accountNumberProperty();
    }

    public DoubleProperty getBalance(){
        return account.balanceProperty();
    }

    /*public String getFname(){
        return fname;
    }

    public String getLname(){
        return lname;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public String setFname(String fname){
        return fname;
    }

    public String setLname(){
        return lname;
    }*/
}