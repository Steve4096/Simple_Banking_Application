package com.simple_banking_application.simple_banking_application.Models;

import javafx.beans.property.*;

import java.time.LocalDateTime;

public class Transaction {

  private final StringProperty Account_from;
  private final StringProperty Account_to;
  private final DoubleProperty amount;
  private final ObjectProperty<LocalDateTime> dateTransactionCreated;

  /*public Transaction(String Account_from, String Account_to, Double amount, LocalDateTime dateTime){
      this.Account_from= new SimpleStringProperty(this,"sender",sender);
      this.Account_to=new SimpleStringProperty(this,"sender",receiver);
      this.amount=new SimpleDoubleProperty(this,"Amount",amount);
      this.date=new SimpleObjectProperty<>(this,"Date",dateTime);
  }*/

    public Transaction(String Account_from,String Account_to,Double amount,ObjectProperty<LocalDateTime> dateTransactionCreated){
        this.Account_from=new SimpleStringProperty();
        this.Account_to=new SimpleStringProperty();
        this.amount=new SimpleDoubleProperty();
        this.dateTransactionCreated=new SimpleObjectProperty<>();
    }

  public StringProperty Account_fromProperty (){return this.Account_from;}

    public StringProperty Account_toProperty (){return this.Account_to;}

    public DoubleProperty amountProperty(){return this.amount;}

    public ObjectProperty<LocalDateTime> dateTimeProperty(){return this.dateTimeProperty();}

}
