package com.simple_banking_application.simple_banking_application.Models;

import com.simple_banking_application.simple_banking_application.Controllers.Admin.ClientController;
import javafx.scene.control.Alert;

import java.sql.*;

public class DatabaseConnection {
    private Connection conn;
    AccountCreation accountCreation = new AccountCreation();
    ClientController clientController=new ClientController();

    public DatabaseConnection() {
        try {
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/simple_banking_application", "root", "");
            if (this.conn != null) {
                System.out.println("Connection is Successful");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to establish the connection");
        }
    }

    //Client Section
    public void insertClientandAccountData(Integer IDNo, String fname, String lname, String Email_address, String Phone_number, String Password) {
        String accountNumber = accountCreation.createAccountNumber(Phone_number);
        //Inserting Client data into Clients table
        try {
            String insertClient = "INSERT INTO Clients(Client_ID,First_name,Last_name,Email_address,Phone_number,Password,Account_number) values(?,?,?,?,?,?,?)";
            PreparedStatement clientStatement;
            clientStatement = this.conn.prepareStatement(insertClient);
            clientStatement.setInt(1, IDNo);
            clientStatement.setString(2, fname);
            clientStatement.setString(3, lname);
            clientStatement.setString(4, Email_address);
            clientStatement.setString(5, Phone_number);
            clientStatement.setString(6, Password);
            clientStatement.setString(7, accountNumber);
            clientStatement.executeUpdate();

            //Inserting Account creation details into Accounts table
            String insertAccount = "INSERT INTO Accounts(Account_number,Balance,Date_created,Client_ID)values(?,?,NOW(),?)";
            PreparedStatement accountStatement;
            accountStatement = conn.prepareStatement(insertAccount);
            accountStatement.setString(1, accountNumber);
            accountStatement.setDouble(2, 0.0);
            accountStatement.setInt(3, IDNo);
            accountStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*public boolean checkIfClientExists(String Email_address){
        PreparedStatement preparedStatement3;
        ResultSet rs;
        try {
            String check="SELECT COUNT(*) FROM Clients WHERE Email_address=?";
            preparedStatement3=conn.prepareStatement(check);
            preparedStatement3.setString(1,Email_address);
            rs= preparedStatement3.executeQuery();
            if (rs.next()){
                return rs.getInt(1)>0;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }*/

    public ResultSet checkIfClientExists(String Email_address) {
        PreparedStatement preparedStatement;
        ResultSet resultSet = null;
        try {
            String check = "SELECT * FROM Clients WHERE Email_address=?";
            preparedStatement = conn.prepareStatement(check);
            preparedStatement.setString(1, Email_address);
            resultSet = preparedStatement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getClientData(String Email_address) {
        PreparedStatement preparedStatement1;
        ResultSet resultSet = null;
        try {
            String clientData = "SELECT c.Client_ID,c.First_name,c.Last_name,c.Account_number,c.Email_address,c.Phone_Number,c.password,a.Balance FROM Clients c INNER JOIN Accounts a ON c.Client_ID=a.Client_ID WHERE Email_address=?";
            preparedStatement1 = this.conn.prepareStatement(clientData);
            preparedStatement1.setString(1, Email_address);
            resultSet = preparedStatement1.executeQuery();
            /*resultSet.close();
            preparedStatement1.close();
            conn.close();*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

   /* public String getHashedPassword(String Email_address) {
        String hashedPassword = null;
        String query = "SELECT Password FROM Clients WHERE Email_address=?";
        try (
            PreparedStatement preparedStatement = conn.prepareStatement(query)){;
            preparedStatement.setString(1, Email_address);
            try (
                ResultSet resultSet= preparedStatement.executeQuery()){;
                if(resultSet.next()){
                    hashedPassword=resultSet.getString("password");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return hashedPassword;
    }*/

    public void deposit(Double balance, String Account_number) {
        PreparedStatement preparedStatement;
        try {
            String deposit = "UPDATE accounts SET balance=balance+? WHERE Account_number=?";
            preparedStatement = conn.prepareStatement(deposit);
            preparedStatement.setDouble(1, balance);
            preparedStatement.setString(2, Account_number);
            preparedStatement.executeUpdate();

            String updateTransaction = "INSERT INTO Transactions(Account_from,Account_to,Date_created,Amount)values(NULL,?,NOW(),?)";
            PreparedStatement transaction = conn.prepareStatement(updateTransaction);
            transaction.setString(1, Account_number);
            transaction.setDouble(2, balance);
            transaction.executeUpdate();
            System.out.println("DEPOSITED SUCCESSFULLY");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public synchronized void withdraw(Double amount, String Account_number) {
        PreparedStatement preparedStatement;
        try {
            String withdraw = "UPDATE accounts SET balance=balance-? WHERE Account_number=?";
            preparedStatement = conn.prepareStatement(withdraw);
            preparedStatement.setDouble(1, amount);
            preparedStatement.setString(2, Account_number);
            preparedStatement.executeUpdate();

            String updateTransaction = "INSERT INTO Transactions(Account_from,Account_to,Date_created,Amount)values(?,NULL,NOW(),?)";
            PreparedStatement transaction = conn.prepareStatement(updateTransaction);
            transaction.setString(1, Account_number);
            transaction.setDouble(2, amount);
            transaction.executeUpdate();
            System.out.println("WITHDRAWAL IS SUCCESSFULL");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //Admin Section
    public ResultSet getAdmindata(String Username, String Password) {
        PreparedStatement preparedStatement;
        ResultSet resultSet = null;
        try {
            String select = "SELECT * FROM Admin WHERE Username=? AND Password=?";
            preparedStatement = this.conn.prepareStatement(select);
            preparedStatement.setString(1, Username);
            preparedStatement.setString(2, Password);
            resultSet = preparedStatement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet searchForClient(String Fname) {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement;
        try {
            String search = "SELECT * FROM Clients WHERE First_name=?";
            preparedStatement = this.conn.prepareStatement(search);
            preparedStatement.setString(1, Fname);
            // preparedStatement.setString(2,Fname);
            //preparedStatement.setString(3,Lname);
            //preparedStatement.setString(4,Phone_No);
            resultSet = preparedStatement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public void loadClients() {
        PreparedStatement preparedStatement;
        ResultSet resultSet=null;
        try {
            String loadClients = "SELECT Client_ID,First_name,Last_name,Email_address,Phone_number,Account_number,Date_created FROM clients";
            preparedStatement = conn.prepareStatement(loadClients);
            resultSet= preparedStatement.executeQuery();
            while (resultSet.next()) {
                clientController.clientsList.add(new Client(
                        resultSet.getInt("Client_ID"),
                        resultSet.getString("First_name"),
                        resultSet.getString("Last_name"),
                        resultSet.getString("Email_address"),
                        resultSet.getString("Phone_number"),
                        resultSet.getString("Account_number")
                ));
                System.out.println("Registered clients are being checked");
                System.out.println("Clientlist is as follows: "+clientController.clientsList);
                //clientController.Clients_table.setItems(clientController.clientsList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database error");
            alert.setHeaderText("Error loading clients");
            alert.setContentText("An error occurred while loading clients from the database. Please check the logs.");
            alert.showAndWait();
        }
        //return resultSet;
    }
}

    //Utility methods(used by both)






