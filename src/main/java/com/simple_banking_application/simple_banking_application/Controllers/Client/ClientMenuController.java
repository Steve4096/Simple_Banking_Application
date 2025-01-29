package com.simple_banking_application.simple_banking_application.Controllers.Client;

import com.simple_banking_application.simple_banking_application.Models.Model;
import com.simple_banking_application.simple_banking_application.Views.ClientMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientMenuController implements Initializable {
    public Button dashboard_btn;
    public Button Profile_btn;
    public Button transactions_btn;
    public Button top_up_btn;
    public Button Withdraw_btn;
    public Button settings_btn;
    public Button log_out_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }

    private void addListeners(){
        dashboard_btn.setOnAction(actionEvent -> onDashboard());
        transactions_btn.setOnAction(actionEvent -> onTransaction());
        Profile_btn.setOnAction(actionEvent ->onProfile());
        top_up_btn.setOnAction(actionEvent -> onTopup());
        Withdraw_btn.setOnAction(actionEvent -> onWithdraw());
        log_out_btn.setOnAction(actionEvent -> onLogout());
    }

    private void onDashboard(){
        Model.getInstance().getViewsfactory().getClientSelectedMenuItem().set(ClientMenuOptions.DASHBOARD);
    }

    private void onTransaction(){
        Model.getInstance().getViewsfactory().getClientSelectedMenuItem().set(ClientMenuOptions.TRANSACTIONS);

    }

    private void onProfile(){
        Model.getInstance().getViewsfactory().getClientSelectedMenuItem().set(ClientMenuOptions.PROFILE);
    }

    private void onTopup(){
        Model.getInstance().getViewsfactory().getClientSelectedMenuItem().set(ClientMenuOptions.TOP_UP);
    }

    private void onWithdraw(){
        Model.getInstance().getViewsfactory().getClientSelectedMenuItem().set(ClientMenuOptions.WITHDRAW);
    }

    private void onLogout(){
        Stage stage=(Stage) log_out_btn.getScene().getWindow();
        Model.getInstance().setClientLoginSuccessFlag(false);
        Model.getInstance().setClient(null);
        Model.getInstance().getViewsfactory().resetDashboardView();
        Model.getInstance().getViewsfactory().resetProfileView();
        Model.getInstance().getViewsfactory().resetTopupView();
        Model.getInstance().getViewsfactory().resetWithdrawView();
        Model.getInstance().getViewsfactory().closeWindow(stage);
        Model.getInstance().getViewsfactory().showLoginwindow();
    }
}
