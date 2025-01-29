package com.simple_banking_application.simple_banking_application.Controllers.Admin;

import com.simple_banking_application.simple_banking_application.Models.Model;
import com.simple_banking_application.simple_banking_application.Views.AdminMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminMenuController implements Initializable {
    public Button Clients_btn;
    public Button Transactions_btn;
    public Button Logs_btn;
    public Button Logout_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }
private void addListeners(){
        Clients_btn.setOnAction(actionEvent -> onClients());
        Transactions_btn.setOnAction(actionEvent -> onTransactions());
       Logs_btn.setOnAction(actionEvent -> onLogs());
       Logout_btn.setOnAction(actionEvent -> onLogout());
}

    private void onClients(){
        Model.getInstance().getViewsfactory().getAdminSelectedMenuItem().set(AdminMenuOptions.Clients);
    }

    private void onTransactions(){
        Model.getInstance().getViewsfactory().getAdminSelectedMenuItem().set(AdminMenuOptions.Transactions);
    }
    private void onLogs(){
        Model.getInstance().getViewsfactory().getAdminSelectedMenuItem().set(AdminMenuOptions.Logs);
    }
    private void onLogout(){
        Stage stage=(Stage) Logout_btn.getScene().getWindow();
        Model.getInstance().setAdminLoginSuccessFlag(false);
        Model.getInstance().getViewsfactory().closeWindow(stage);
        Model.getInstance().getViewsfactory().showLoginwindow();
    }


}
