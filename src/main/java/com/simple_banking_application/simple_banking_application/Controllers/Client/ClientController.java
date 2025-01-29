package com.simple_banking_application.simple_banking_application.Controllers.Client;

import com.simple_banking_application.simple_banking_application.Models.Client;
import com.simple_banking_application.simple_banking_application.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientController implements Initializable {
    //Client currentClient =Model.getInstance().getClient();


    public BorderPane Client_parent;



    /*@Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }*/

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewsfactory().getClientSelectedMenuItem().addListener((observablevalue, oldVal, newVal) -> {
            switch (newVal) {
                case TRANSACTIONS ->loadTransactionsview();
                case PROFILE -> loadProfileView();
                case TOP_UP -> loadTopupView();
                case WITHDRAW -> loadWithdrawView();
                default -> onDefault();
            }
        });
        //Initially load the dashboard view
        onDefault();
    }


    private void loadTransactionsview() {
        Client_parent.setCenter(Model.getInstance().getViewsfactory().getTransactionsview());
    }

    private void loadProfileView() {
        Client client=Model.getInstance().getClient();
        Client_parent.setCenter(Model.getInstance().getViewsfactory().getProfileview(client));
    }

    private void loadTopupView(){
        Client client=Model.getInstance().getClient();
        Client_parent.setCenter(Model.getInstance().getViewsfactory().getTopupwindow(client));
    }

    private void loadWithdrawView(){
        Client client=Model.getInstance().getClient();
        Client_parent.setCenter(Model.getInstance().getViewsfactory().getWithdrawview(client));
    }

    private void onDefault() {
        Client client=Model.getInstance().getClient();
        Client_parent.setCenter(Model.getInstance().getViewsfactory().getDashboardView(client));
    }
}