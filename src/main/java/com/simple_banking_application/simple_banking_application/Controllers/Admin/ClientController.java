package com.simple_banking_application.simple_banking_application.Controllers.Admin;

import com.simple_banking_application.simple_banking_application.Models.Client;
import com.simple_banking_application.simple_banking_application.Models.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class ClientController implements Initializable {
    public Button Search_btn;
    public TextField Search_txtField;
    public TableView<Client> Clients_table;
    public TableColumn<Client,String> Client_ID;
    public TableColumn<Client,String> First_name;
    public TableColumn<Client,String> Last_name;
    public TableColumn<Client,String> Email_address;
    public TableColumn<Client,String> Phone_number;
    public TableColumn<Client,String> Account_number;

    public final ObservableList<Client> clientsList= FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Search_btn.setOnAction(actionEvent -> searchForClient());
        Client_ID.setCellValueFactory(new PropertyValueFactory<>("clientID"));
        First_name.setCellValueFactory(new PropertyValueFactory<>("fname"));
        Last_name.setCellValueFactory(new PropertyValueFactory<>("lname"));
        Email_address.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));
        Phone_number.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        //Account_number.setCellValueFactory(new PropertyValueFactory<>(""));

        /*System.out.println(Client_ID.getCellValueFactory());
        System.out.println(First_name.getCellValueFactory());
        System.out.println(Last_name.getCellValueFactory());
        System.out.println(Email_address.getCellValueFactory());
        System.out.println(Phone_number.getCellValueFactory());*/

        //clientsList.add(new Client( 456,"John Doe", "543543", "ACC001","sdgdg","dadssas"));
        //clientsList.add(new Client(124, "Jane Smith", "0987654321", "ACC002","asdsad","asdsadss"));

        Model.getInstance().getDatabaseConnection().loadClients();
        Clients_table.setItems(clientsList);
    }

    private void loadClientsFromDatabase() {
        Task<Void> loadTask = new Task<>() {
            @Override
            protected Void call() {
                Model.getInstance().getDatabaseConnection().loadClients();
                return null;
            }
        };

        loadTask.setOnSucceeded(event -> {
            Clients_table.setItems(clientsList);
            System.out.println("Clients loaded successfully.");
        });

        loadTask.setOnFailed(event -> {
            System.err.println("Error loading clients: " + loadTask.getException().getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Failed to Load Clients");
            alert.setContentText("An error occurred while loading data. Please check the logs.");
            alert.showAndWait();
        });

        new Thread(loadTask).start();
    }


    private void searchForClient(){
        String Fname=Search_txtField.getText();
        Model.getInstance().getDatabaseConnection().searchForClient(Fname);
    }

}

