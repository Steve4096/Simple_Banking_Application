package com.simple_banking_application.simple_banking_application.Controllers.NewClient;

import com.simple_banking_application.simple_banking_application.Models.DatabaseConnection;
import com.simple_banking_application.simple_banking_application.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class NewClientController implements Initializable {
    public TextField First_name_textarea;
    public TextField Last_name_textarea;
    public TextField Email_address_textarea;
    public TextField Phone_No;
    public TextField Pwd;
    public TextField Confirm_pwd;
    public Button Sign_up_btn;
    public Label Pwderror_lbl;
    public Label Allfields_lbl;
    public Label Clientexists_lbl;
    public TextField Id_no_textarea;
    public TextField Phone_Number_textarea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Sign_up_btn.setOnAction(actionEvent -> onRegistration());
    }






    public void onRegistration() {
        TextField textField = new TextField();

        String IDno = Id_no_textarea.getText();
        Integer IDNo = Integer.valueOf(IDno);
        String fname = First_name_textarea.getText();
        String lname = Last_name_textarea.getText();
        String Email_address = Email_address_textarea.getText();
        String Phone_No= Phone_Number_textarea.getText();
        String password = Pwd.getText();
        String hashedPassword=Model.hashPassword(password);
        String confirmPassword = Confirm_pwd.getText();


        boolean allFieldsFilled = checkIfAllFieldsAreFilled();
        boolean passwordsMatch = passwordsMatch();
        //boolean clientExists=Model.getInstance().checkIfClientExists(Email_address_textarea.getText());

        Stage stage = (Stage) Sign_up_btn.getScene().getWindow();
        Model.getInstance().checkIfClientExists(Email_address_textarea.getText());
        /*if(Model.getInstance().getClientExists()){
            showClientexists();
        }else {
            databaseConnection.insertClientandAccountData(IDNo,fname,lname,Email_address,Integer.valueOf(phone_no),password);
            showSuccess();

            Model.getInstance().getViewsfactory().closeWindow(stage);
        }
    }*/
    if(allFieldsFilled&&passwordsMatch&&!Model.getInstance().getClientExists()){
        Model.getInstance().getDatabaseConnection().insertClientandAccountData(IDNo,fname,lname,Email_address,Phone_No,hashedPassword);
        showSuccess();
        Model.getInstance().getViewsfactory().closeWindow(stage);
        }else if(Model.getInstance().getClientExists()){
        showClientexists();
        resetFields();
    }
}


    public boolean checkIfAllFieldsAreFilled(){
        if (First_name_textarea.getText().isEmpty()||Last_name_textarea.getText().isEmpty()||Email_address_textarea.getText().isEmpty()||
                Pwd.getText().isEmpty()||Confirm_pwd.getText().isEmpty()) {
            Allfields_lbl.setText("Please fill all fields before proceeding");
            return false;
        }
        return true;
    }

    private boolean passwordsMatch(){
        if (!Pwd.getText().equals(Confirm_pwd.getText())) {
            Pwderror_lbl.setText("PASSWORDS DO NOT MATCH");
            return false;
        }
        return true;
    }

    private void resetFields(){
        Id_no_textarea.setText("");
        First_name_textarea.setText("");
        Last_name_textarea.setText("");
        Email_address_textarea.setText("");
        Phone_Number_textarea.setText("");
        Pwd.setText("");
        Confirm_pwd.setText("");
    }

    private void showSuccess(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("REGISTRATION IS SUCCESSFUL");
        alert.showAndWait();
    }

    private void showClientexists(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText("CLIENT EXISTS");
        alert.showAndWait();
    }

}
