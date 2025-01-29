package com.simple_banking_application.simple_banking_application;

import com.simple_banking_application.simple_banking_application.Models.Model;
import com.simple_banking_application.simple_banking_application.Views.Viewsfactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {


    @Override
    public void start(Stage stage) {
        Model.getInstance().getViewsfactory().showLoginwindow();
    }
}
