package com.simple_banking_application.simple_banking_application.Controllers.Admin;

import com.simple_banking_application.simple_banking_application.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    public BorderPane Admin_parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewsfactory().getAdminSelectedMenuItem().addListener((observableValue, oldVal, newVal) ->{
            switch (newVal){
                case Clients -> Admin_parent.setCenter(Model.getInstance().getViewsfactory().getClientview());
                case Transactions -> Admin_parent.setCenter(Model.getInstance().getViewsfactory().getAllTransactionsview());
                default -> Admin_parent.setCenter(Model.getInstance().getViewsfactory().getClientview());
}
        } );
    }
}
