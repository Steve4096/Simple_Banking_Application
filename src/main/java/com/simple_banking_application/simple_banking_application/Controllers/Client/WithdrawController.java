package com.simple_banking_application.simple_banking_application.Controllers.Client;

import com.simple_banking_application.simple_banking_application.Models.Client;
import com.simple_banking_application.simple_banking_application.Models.Model;
import javafx.beans.binding.Bindings;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class WithdrawController implements Initializable {
    public Label Acc_no_lbl;
    public TextField Withdrawal_amount_txtfield;
    public Button Withdraw_btn;
    public Button Cancel_btn;
    public Label Balance_lbl;
    public Label Minimum_withdrawal_amount_lbl;
    public Label Withdrawalamount_isGreaterthan_Balance_lbl;

    private Client client;

    public void setClient(Client client){
        this.client=client;
        populateClientDetails();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Withdraw_btn.setOnAction(actionEvent -> withdraw());
    }



    public void populateClientDetails(){
        Acc_no_lbl.setText(client.getAccountNumber().get());
        Balance_lbl.textProperty().bind(Bindings.format("%2f",client.getBalance()));
    }

    private void withdraw(){
        String Account_number=client.getAccountNumber().get();
        Double maximumWithdrawalAmount=client.getBalance().get();
        String Amount=Withdrawal_amount_txtfield.getText();
        Double amount=Double.parseDouble(Amount);
        if(amount<=99){
            Minimum_withdrawal_amount_lbl.setText("MINIMUM WITHDRAWAL AMOUNT IS KSH.100");
            resetFields();
        } else if (amount>maximumWithdrawalAmount) {
            //System.out.println("THE AMOUNT ENTERED IS GREATER THAN THE BALANCE AVAILABLE");
            resetFields();
            Withdrawalamount_isGreaterthan_Balance_lbl.setText("THE AMOUNT ENTERED IS GREATER THAN THE BALANCE AVAILABLE");
        }else {
            withdrawalSuccessBox();
            Model.getInstance().getDatabaseConnection().withdraw(amount,Account_number);
            resetFields();
        }
    }

    private void resetFields(){
        Withdrawal_amount_txtfield.setText("");
    }

    private void withdrawalSuccessBox(){
        String Amount=Withdrawal_amount_txtfield.getText();
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("WITHDRAWAL SUCCESS");
        alert.setHeaderText(null);
        alert.setContentText("WITHDRAWAL OF" + " " +Amount+ " " + "IS SUCCESSFULL");
        alert.show();
    }
}
