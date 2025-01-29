package com.simple_banking_application.simple_banking_application.Models;

import com.simple_banking_application.simple_banking_application.Controllers.Admin.ClientController;
import com.simple_banking_application.simple_banking_application.Views.AccountType;
import com.simple_banking_application.simple_banking_application.Views.Viewsfactory;
import eu.hansolo.tilesfx.tools.DoubleExponentialSmoothingForLinearSeries;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Model {
    private static Model model;
    private final Viewsfactory viewsfactory;
    private final DatabaseConnection databaseConnection;
    private AccountType loginAccountType = AccountType.CLIENT;

    //Client Data Section
    //private final Client client;
    private Client client;
    public int clientID;
    public String fname;
    public String lname;
    public String Phone_number;
    public String Account_number;
    private boolean clientLoginSuccessFlag;
    private boolean clientExists;

    //Admin Data Section
    private boolean adminLoginSuccessFlag;
    private final ClientController clientController;

    /*private Model(Viewsfactory viewsfactory,DatabaseConnection
                  databaseConnection) {*/
    private Model() {
        this.viewsfactory = new Viewsfactory();
        this.databaseConnection = new DatabaseConnection();
        //this.viewsfactory=viewsfactory;
        //this.databaseConnection=databaseConnection;

        //Client Section
        this.clientLoginSuccessFlag = false;
        //this.client = new Client("", "", null, "", null);
        //this.client=new Client("","","");
        //client=new Client();

        //Admin Section
        this.adminLoginSuccessFlag = false;
        this.clientController = new ClientController();
    }

    /*public static synchronized Model getInstance(Viewsfactory viewsfactory,DatabaseConnection databaseConnection) {
        if (model == null) {
            model = new Model(viewsfactory,databaseConnection);
        }
        return model;
    }*/
    public static synchronized Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public Viewsfactory getViewsfactory() {
        return viewsfactory;
    }

    public DatabaseConnection getDatabaseConnection() {
        return databaseConnection;
    }

    public AccountType getLoginAccountType() {
        return loginAccountType;
    }

    public void setLoginAccountType(AccountType loginAccountType) {
        this.loginAccountType = loginAccountType;
    }

    //Client Section
    public boolean getClientLoginSuccessFlag() {
        return this.clientLoginSuccessFlag;
    }

    public void setClientLoginSuccessFlag(boolean flag) {
        this.clientLoginSuccessFlag = flag;
    }

    /*public void setClientExists(boolean clientExists) {
        this.clientExists = clientExists;
    }*/

    public boolean getClientExists() {
        return this.clientExists;
    }

    public Client getClient() {
        System.out.println("Client fetched from model is: " + client);
        return client;
    }

    public void setClient(Client client) {
        System.out.println("Client set in model is: " + client);
        this.client = client;
    }

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean checkHashedPasswords(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

    public void evaluateClientCredentials(String Email_address, String Password) {
        ResultSet resultSet = databaseConnection.getClientData(Email_address);
        try {
            if (resultSet.isBeforeFirst()) {
                resultSet.next();
                System.out.println("Fetched client data for: " + Email_address);
                String hashedPassword = resultSet.getString("password");
                boolean passwordsMatch = BCrypt.checkpw(Password, hashedPassword);
                System.out.println("Passwords match: " + passwordsMatch);
                if (passwordsMatch) {
                    Client loggedInClient = new Client(clientID, fname, lname, Phone_number, Email_address, Account_number);
                    loggedInClient.clientIDProperty().set(resultSet.getInt("Client_ID"));
                    loggedInClient.fnameProperty().set(resultSet.getString("First_name"));
                    loggedInClient.lnameProperty().set(resultSet.getString("Last_name"));
                    //loggedInClient.accBalanceProperty().set(resultSet.getDouble("Balance"));
                    //this.client.accountNumberProperty().set(resultSet.getString("Account_number"));
                    loggedInClient.phoneNumberProperty().set(resultSet.getString("Phone_Number"));
                    loggedInClient.emailAddressProperty().set(resultSet.getString("Email_address"));
                    loggedInClient.getAccountNumber().set(resultSet.getString("Account_number"));
                    loggedInClient.getBalance().set(resultSet.getDouble("Balance"));
                    Account respectiveClientAccount = new Account();
                    respectiveClientAccount.accountNumberProperty().set(resultSet.getString("Account_number"));
                    //respectiveClientAccount.balanceProperty().set(resultSet.getDouble("Balance"));
                    Model.getInstance().setClient(loggedInClient);
                    System.out.println("Logged in client is: " + loggedInClient);
                    this.clientLoginSuccessFlag = true;
                } else {
                    System.out.println("Incorrect password for: " + Email_address);
                    this.clientLoginSuccessFlag = false;
                }
            } else {
                System.out.println("No client found with email: " + Email_address);
                this.clientLoginSuccessFlag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*public boolean checkIfClientExists(String Email_address){
        ResultSet resultSet= databaseConnection.checkIfClientExists(Email_address);
        try{
            if(resultSet.isBeforeFirst()) {
                resultSet.next();
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }*/

    public void checkIfClientExists(String Email_address) {
        ResultSet resultSet = databaseConnection.checkIfClientExists(Email_address);
        try {
            if (resultSet.isBeforeFirst()) {
                resultSet.next();
                this.clientExists = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //Admin section
    public boolean getAdminLoginSuccessFlag() {
        return adminLoginSuccessFlag;
    }


    public void setAdminLoginSuccessFlag(boolean adminLoginSuccessFlag) {
        this.adminLoginSuccessFlag = adminLoginSuccessFlag;
    }

    public void evaluateAdminCredentials(String Username, String Password) {
        ResultSet resultSet = databaseConnection.getAdmindata(Username, Password);
        try {
            if (resultSet.isBeforeFirst()) {
                resultSet.next();
                this.adminLoginSuccessFlag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet displayClientBySearchDetails(String Fname) {
        ResultSet resultSet = databaseConnection.searchForClient(Fname);
        try {
            if (resultSet.isBeforeFirst()) {
                resultSet.next();
            } else {
                System.out.println("No such client exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    /*public void showRegisteredClients() {
        ResultSet resultSet = databaseConnection.loadClients();
        try {
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

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/
}
