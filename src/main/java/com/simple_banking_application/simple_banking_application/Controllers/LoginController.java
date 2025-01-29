package com.simple_banking_application.simple_banking_application.Controllers;

import com.simple_banking_application.simple_banking_application.Models.Model;
import com.simple_banking_application.simple_banking_application.Views.AccountType;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public ChoiceBox<AccountType> acc_selector;
    public Button login_btn;
    public Button About_us_btn;
    public Button Contact_us_btn;
    public Button FAQs_btn;
    public Button Exit_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        acc_selector.setItems(FXCollections.observableArrayList(AccountType.ADMIN, AccountType.CLIENT));
        acc_selector.setValue(Model.getInstance().getViewsfactory().getLoginAccountType());
        acc_selector.valueProperty().addListener(observable -> Model.getInstance().getViewsfactory().setLoginAccountType(acc_selector.getValue()));
        login_btn.setOnAction(actionEvent -> onLogin());
        Exit_btn.setOnAction(actionEvent -> onExit());
    }

    private void onLogin() {
        Stage stage = (Stage) login_btn.getScene().getWindow();
        //System.out.println("Closing stage"+stage.getTitle());
        Model.getInstance().getViewsfactory().closeWindow(stage);
        //Model.getInstance().getViewsfactory().showClientWindow();
        if (Model.getInstance().getViewsfactory().getLoginAccountType() == AccountType.CLIENT) {
            Model.getInstance().getViewsfactory().showClientLoginWindow();
        } else {
            Model.getInstance().getViewsfactory().showAdminLoginWindow();
        }
    }

    private void onExit(){
        Stage stage=(Stage) Exit_btn.getScene().getWindow();
        Model.getInstance().getViewsfactory().closeWindow(stage);
    }
}
