package com.simple_banking_application.simple_banking_application.Views;

import com.simple_banking_application.simple_banking_application.Controllers.Admin.ClientCellController;
import com.simple_banking_application.simple_banking_application.Models.Client;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class ClientCellFactory extends ListCell<Client> {
    @Override
    protected void updateItem(Client client, boolean b) {
        super.updateItem(client, b);
        if(b){
            setText(null);
            setGraphic(null);
        }else{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/Fxml/Admin/ClientCell.fxml"));
            ClientCellController controller=new ClientCellController(client);
            loader.setController(controller);
            setText(null);
            try{
                setGraphic(loader.load());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
