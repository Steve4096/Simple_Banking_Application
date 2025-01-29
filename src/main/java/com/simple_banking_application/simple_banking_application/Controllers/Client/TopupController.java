package com.simple_banking_application.simple_banking_application.Controllers.Client;

import com.simple_banking_application.simple_banking_application.Models.Account;
import com.simple_banking_application.simple_banking_application.Models.Client;
import com.simple_banking_application.simple_banking_application.Models.DatabaseConnection;
import com.simple_banking_application.simple_banking_application.Models.Model;
import com.simple_banking_application.simple_banking_application.Views.Viewsfactory;
import javafx.beans.binding.Bindings;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class TopupController implements Initializable {
    public Label acc_no_lbl;
    public Label balance_lbl;
    public TextField Topup_amount_txtfield;
    public Button Top_up_btn;
    public Button Cancel_btn;
    public Label Minimum_topup_amount;

    private Client client;


    public void setClient(Client client) {
        this.client = client;
        populateClientDetails();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Top_up_btn.setOnAction(actionEvent -> topUp());
    }

    public void populateClientDetails(){
        acc_no_lbl.textProperty().bind(client.getAccountNumber());
        balance_lbl.textProperty().bind(Bindings.format("%2f",client.getBalance()));
    }

    private synchronized void topUp(){
        TextField textField=new TextField();
        String Account_number=client.getAccountNumber().get();
        String Amount=Topup_amount_txtfield.getText();
        Double amount=Double.parseDouble(Amount);
        if(amount<=99){
            Minimum_topup_amount.setText("MINIMUM TOP UP AMOUNT IS 100");
        }else {
            topupSuccessBox();
            Model.getInstance().getDatabaseConnection().deposit(amount,Account_number);
        }
    }

    public void topupSuccessBox(){
        String amount=Topup_amount_txtfield.getText();
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("TOP UP SUCCESS");
        alert.setHeaderText(null);
        alert.setContentText("TOP UP OF" +" " +amount+ " "+ "IS SUCCESSFULL");
        alert.showAndWait();
    }
}
