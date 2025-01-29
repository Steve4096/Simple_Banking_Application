package com.simple_banking_application.simple_banking_application.Controllers.Client;

import com.simple_banking_application.simple_banking_application.Models.Client;
import com.simple_banking_application.simple_banking_application.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
    public Label name_lbl;
    public Label id_lbl;
    public Label DOB_lbl;
    public Label BankAccNo_lbl;
    public Label PhoneNo_lbl;
    public Label Emailaddress_lbl;
    public Button Close_btn;

    private Client client;

    public void setClient(Client client){
        System.out.println("Setting client in ProfileController: "+client);
        this.client=client;
        populateClientDetails();

   }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //populateClientDetails();
    }

    private void populateClientDetails() {
        //Client client= Model.getInstance().getClient();
        //Client client1=Model.getInstance().getClient();
        if (client != null) {
            id_lbl.textProperty().unbind();
            name_lbl.textProperty().unbind();
            PhoneNo_lbl.textProperty().unbind();
            Emailaddress_lbl.textProperty().unbind();

            id_lbl.textProperty().bind(client.clientIDProperty().asString());
            name_lbl.textProperty().bind(client.fnameProperty().concat(" ").concat(client.lnameProperty()));
            PhoneNo_lbl.textProperty().bind(client.phoneNumberProperty());
            Emailaddress_lbl.textProperty().bind(client.emailAddressProperty());
            BankAccNo_lbl.textProperty().bind(client.getAccountNumber());
        }
    }
}
