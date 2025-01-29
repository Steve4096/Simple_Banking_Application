package com.simple_banking_application.simple_banking_application.Views;

import com.simple_banking_application.simple_banking_application.Controllers.Client.TransactionCellController;
import com.simple_banking_application.simple_banking_application.Models.Transaction;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

public class TransactionCellFactory extends ListCell<Transaction> {
    @Override
    protected void updateItem(Transaction transaction, boolean b) {
        super.updateItem(transaction, b);
        if(b){
            setText(null);
            setGraphic(null);
        }else {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/Fxml/Client/TransactionCell.fxml"));
            TransactionCellController controller=new TransactionCellController(transaction);
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
