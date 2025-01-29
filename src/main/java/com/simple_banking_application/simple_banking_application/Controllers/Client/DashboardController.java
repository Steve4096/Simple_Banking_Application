package com.simple_banking_application.simple_banking_application.Controllers.Client;

import com.simple_banking_application.simple_banking_application.Models.Account;
import com.simple_banking_application.simple_banking_application.Models.Client;
import com.simple_banking_application.simple_banking_application.Models.Model;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    //public Text user_name;
    public Label card_holder_name_lbl;
    public Label validity_lbl;
    public Label acc_balance;
    public Label acc_no;
    public Label acc_summ_sent;
    public Label acc_received_summ;
    public Label acc_summ_bills;
    public ListView transactions_listview;
    public Label date_lbl;
    public Label username_lbl;

    private Client client;
    //private Account account;

    LocalDateTime now=LocalDateTime.now();
    DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("HH-mm-ss");
    String timeNow=now.format(dateTimeFormatter);


    public void setClient(Client client){
        System.out.println("Setting client: "+client);
        this.client=client;
        System.out.println("Client set to: "+client);
        clientSpecificDetails();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //clientSpecificDetails();
    }


    private void clientSpecificDetails(){
        if(client!=null){
            username_lbl.textProperty().unbind();
            card_holder_name_lbl.textProperty().unbind();
            acc_balance.textProperty().unbind();
            acc_no.textProperty().unbind();

            /*username_lbl.textProperty().bind(client.fnameProperty());
            card_holder_name_lbl.textProperty().bind(client.fnameProperty().concat(" ").concat(client.lnameProperty()));
            acc_balance.textProperty().bind(Bindings.format("%.2f",client.accBalanceProperty()));*/

            username_lbl.setText(client.fnameProperty().get());
            card_holder_name_lbl.setText(client.fnameProperty().get().concat(" ").concat(client.lnameProperty().get()));
            acc_no.setText(client.getAccountNumber().get());
            acc_balance.textProperty().bind(Bindings.format("%.2f",client.getBalance()));
            //date_lbl.setText(timeNow);
            Timeline timeline=new Timeline(
                    new KeyFrame(Duration.seconds(1),actionEvent -> date_lbl.setText(timeNow))
            );
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        }else {
            System.out.println("Client does not exist");
        }

    }

}

