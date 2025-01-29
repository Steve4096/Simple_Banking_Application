package com.simple_banking_application.simple_banking_application.Controllers.Admin;

import com.simple_banking_application.simple_banking_application.Models.Client;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientCellController implements Initializable {
    public Label ClientName_lbl;
    public Label AccountNo_lbl;
    public Label PhoneNo_lbl;
    public Label DateCreated_lbl;
    public Button Delete_btn;

    private final Client client;

    public ClientCellController(Client client){
        this.client=client;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
