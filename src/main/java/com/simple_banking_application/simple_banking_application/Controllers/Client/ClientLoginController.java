package com.simple_banking_application.simple_banking_application.Controllers.Client;

import com.simple_banking_application.simple_banking_application.Models.Client;
import com.simple_banking_application.simple_banking_application.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientLoginController implements Initializable {
    public Button Log_in_btn;
    public TextField Email_address_field;
    public TextField Password_field;
    public Label Invalid_credentials_txt;
    public Hyperlink Sign_up_link;
    public Button Forgot_pwd_btn;

    private Client client;

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Log_in_btn.setOnAction(actionEvent -> loggingIn());
        Sign_up_link.setOnAction(actionEvent -> setSign_up_link());
    }

private void loggingIn(){
    String email= Email_address_field.getText();
    String password=Password_field.getText();


    Model.getInstance().evaluateClientCredentials(email,password);
    if(Model.getInstance().getClientLoginSuccessFlag()){
        proceedToLogIn();
    }else {
        showInvalidLoginCredentials();
    }
}

private void proceedToLogIn(){
        Stage stage=(Stage) Log_in_btn.getScene().getWindow();
        Model.getInstance().getViewsfactory().closeWindow(stage);
        Model.getInstance().getViewsfactory().showClientWindow();
        //Model.getInstance().getViewsfactory().getDashboardView();
}

private void showInvalidLoginCredentials(){
        Email_address_field.clear();
        Password_field.clear();
        Invalid_credentials_txt.setText("INVALID CREDENTIALS");
}



private void setSign_up_link(){
        Stage stage=(Stage) Sign_up_link.getScene().getWindow();
        Model.getInstance().getViewsfactory().showNewClientWindow();
        Model.getInstance().getViewsfactory().closeWindow(stage);
}
}
