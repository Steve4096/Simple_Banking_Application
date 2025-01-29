package com.simple_banking_application.simple_banking_application.Controllers.Admin;

import com.simple_banking_application.simple_banking_application.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminLoginController implements Initializable {
    public Button Log_in_btn;
    public Label error_lbl;
    public TextField username_fld;
    public TextField pwd_field;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Log_in_btn.setOnAction(actionEvent ->adminLogin() );
    }

    public void adminLogin(){
        Stage stage=(Stage) Log_in_btn.getScene().getWindow();
        Model.getInstance().evaluateAdminCredentials(username_fld.getText(), pwd_field.getText());
        if(Model.getInstance().getAdminLoginSuccessFlag()){
            Model.getInstance().getViewsfactory().showAdminWindow();
            Model.getInstance().getViewsfactory().closeWindow(stage);
        }else {
            username_fld.setText("");
            pwd_field.setText("");
            error_lbl.setText("INVALID CREDENTIALS");
        }

    }
}
