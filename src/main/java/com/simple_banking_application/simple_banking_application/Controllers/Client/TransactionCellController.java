package com.simple_banking_application.simple_banking_application.Controllers.Client;

import com.simple_banking_application.simple_banking_application.Models.Transaction;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionCellController implements Initializable {
    public Label Deposit_lbl;
    public Label Withdrawal_lbl;
    public Label Amount_lbl;
    public Label DateTime_lbl;

    private final Transaction transaction;

    public TransactionCellController(Transaction transaction){
        this.transaction=transaction;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
