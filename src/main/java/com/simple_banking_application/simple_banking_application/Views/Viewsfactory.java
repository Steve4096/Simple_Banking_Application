package com.simple_banking_application.simple_banking_application.Views;

import com.simple_banking_application.simple_banking_application.Controllers.Admin.AdminController;
import com.simple_banking_application.simple_banking_application.Controllers.Client.*;
import com.simple_banking_application.simple_banking_application.Models.Client;
import com.simple_banking_application.simple_banking_application.Models.Model;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Viewsfactory {
private AccountType loginAccountType;
    //Client views
    private final ObjectProperty<ClientMenuOptions> clientSelectedMenuItem;
    private AnchorPane dashboardView;
    private AnchorPane transactionsview;
    private AnchorPane profileview;
    private VBox topupview;
    private VBox withdrawview;


    //Admin views;
    private ObjectProperty<AdminMenuOptions> adminSelectedMenuItem;
    private AnchorPane clientview;
    private AnchorPane allTransactionsview;


    public Viewsfactory() {
        this.loginAccountType= AccountType.ADMIN;
        this.clientSelectedMenuItem = new SimpleObjectProperty<>();
        this.adminSelectedMenuItem= new SimpleObjectProperty<>();
    }

    public AccountType getLoginAccountType() {
        return loginAccountType;
    }

    public void setLoginAccountType(AccountType loginAccountType) {
        this.loginAccountType = loginAccountType;
    }

    public ObjectProperty<ClientMenuOptions> getClientSelectedMenuItem() {
        return clientSelectedMenuItem;
    }

    public AnchorPane getDashboardView(Client client) {
        if (dashboardView == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Client/Dashboard.fxml"));
                dashboardView=loader.load();
                DashboardController dashboardController=loader.getController();
                if(client!=null) {
                    dashboardController.setClient(client);
                }else {
                    System.out.println("Client in dashboard view is null");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dashboardView;
    }

    /*public AnchorPane getDashboardView(Client client) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Client/Dashboard.fxml"));
            AnchorPane dashboardView = loader.load();
            DashboardController dashboardController = loader.getController();
            if (client != null) {
                System.out.println("Setting client for dashboard: " + client);
                dashboardController.setClient(client);
            } else {
                System.out.println("Client in dashboard view is null");
            }
            return dashboardView;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }*/


    public AnchorPane getTransactionsview() {
        if (transactionsview == null) {
            try {
                //FXMLLoader loader=new FXMLLoader(getClass().getResource("/Fxml/Client/Transactions.fxml")).load();
                transactionsview = new FXMLLoader(getClass().getResource("/Fxml/Client/Transactions.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return transactionsview;
    }

    /*public AnchorPane getProfileview() {
        if (profileview == null) {
            try {
                profileview = new FXMLLoader(getClass().getResource("/Fxml/Client/Profile.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return profileview;
    }*/

    public AnchorPane getProfileview(Client client) {
        if(profileview==null){
            try{
                FXMLLoader loader=new FXMLLoader(getClass().getResource("/Fxml/Client/Profile.fxml"));
                profileview=loader.load();
                ProfileController profileController=loader.getController();
                profileController.setClient(client);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return profileview;
    }

    /*public AnchorPane getProfileview(Client client) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Client/Profile.fxml"));
            AnchorPane profileView= loader.load();
            ProfileController profileController = loader.getController();
            if (client != null) {
                System.out.println("Setting client for profile: " + client);
                profileController.setClient(client);
            } else {
                System.out.println("Client in profile view is null");
            }
            return profileView;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }*/

    public VBox getTopupwindow(Client client){
            try{
                FXMLLoader loader=new FXMLLoader(getClass().getResource("/Fxml/Client/Topup.fxml"));
                topupview=loader.load();
                TopupController topupController=loader.getController();
                topupController.setClient(client);
            }catch (Exception e){
                e.printStackTrace();
            }
        return topupview;
    }

    public VBox getWithdrawview(Client client) {
        if(withdrawview==null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Client/Withdraw.fxml"));
                withdrawview = loader.load();
                WithdrawController withdrawController = loader.getController();
                withdrawController.setClient(client);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return withdrawview;
    }

    public void showClientWindow(){
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/Fxml/Client/Client.fxml"));
        ClientController clientController=new ClientController();
        loader.setController(clientController);
        CreateStage(loader,null);

    }

   /*public void showClientWindow(){
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/Fxml/Client/Client.fxml"));
        Parent root=loader.load();
        ClientController clientController=loader.getController();
        clientController.setClient(client);
        //Stage currentStage=(Stage)
    }*/

    /*public void showClientWindow(Client client) {
        try {
            // Load the Client.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Client/Client.fxml"));
            Parent root = loader.load();

            // Get the controller and pass the current client
            ClientController clientController = loader.getController();
            clientController.setClient(client); // Set the client in the controller

            // Create a new stage and show the client window
            Stage clientStage = new Stage();
            clientStage.setTitle("Client Dashboard");
            clientStage.setScene(new Scene(root));
            clientStage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/ClientIcon.png"))));
            clientStage.setResizable(false);
            clientStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/


    public void showClientLoginWindow(){
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/Fxml/Client/ClientLogin.fxml"));
        CreateStage(loader,null);
    }


    //Admin views section
    public void showAdminLoginWindow(){
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/Fxml/Admin/AdminLogin.fxml"));
        CreateStage(loader,null);
        /*Stage stage=new Stage();
        stage.setTitle("ADMIN LOGIN FORM");*/
    }

    public void showAdminWindow(){
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/Fxml/Admin/Admin.fxml"));
        AdminController controller=new AdminController();
        loader.setController(controller);
        CreateStage(loader,null);
    }

    public ObjectProperty<AdminMenuOptions> getAdminSelectedMenuItem(){
        return adminSelectedMenuItem;
    }


    public AnchorPane getClientview() {
        if(clientview==null){
            try {
                clientview=new FXMLLoader(getClass().getResource("/Fxml/Admin/Client.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return clientview;
    }

    public AnchorPane getAllTransactionsview(){
        if(allTransactionsview==null){
            try {
                allTransactionsview=new FXMLLoader(getClass().getResource("/Fxml/Admin/Transactions.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return allTransactionsview;
    }


    public void showNewClientWindow(){
      FXMLLoader loader=new FXMLLoader(getClass().getResource("/Fxml/NewClient/NewClient.fxml"));
      CreateStage(loader,null);
      /*Stage stage=new Stage();
      stage.setTitle("NEW CLIENT REGISTRATION");*/
    }

    public void showLoginwindow() {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/LoginController.fxml"));
    CreateStage(loader,null);
}

        /*private void CreateStage(FXMLLoader loader) {
        Scene scene=null;
        try {
            scene=new Scene(loader.load());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.setTitle("Tevin Financial solutions");
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/WhatsApp Image 2024-07-24 at 18.01.01.jpeg"))));
        stage.setResizable(false);
        stage.show();
    }*/

    private void CreateStage(FXMLLoader loader, Stage currentStage) {
        try {
            Scene scene = new Scene(loader.load());
            Stage newStage = new Stage();
            newStage.setScene(scene);
            newStage.setTitle("Tevin Financial Solutions");
            newStage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/WhatsApp Image 2024-07-24 at 18.01.01.jpeg"))));
            newStage.setResizable(false);
            newStage.show();

            // Close the current stage
            if (currentStage != null) {
                currentStage.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void closeWindow(Stage stage){
        stage.close();
    }

    public void resetDashboardView(){
        dashboardView=null;
    }

    public void resetProfileView(){
        profileview=null;
    }

    public void resetTopupView(){
        topupview=null;
    }

    public void resetWithdrawView(){
        withdrawview=null;
    }
}


