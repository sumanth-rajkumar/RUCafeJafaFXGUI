package com.example.rucafe;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;


/**
 * This class is what controls all the actions for each button using the inputs given by the
 * user in the orderingDonuts, orderingCoffee, orderingBasket GUI
 * @author Sumanth Rajkumar, Shantanu Jain
 */
public class StoreOrdersController {
    private StoreOrders store = new StoreOrders();

    @FXML
    private ListView<MenuItem> orders;
    @FXML
    private ComboBox<Integer> ordersDropDown;
    @FXML
    private TextField total;

    /**
     * It will add the order items with its specific order number to ListView in store GUI based on user input
     * @param order - order item to be placed in store ListView
     */
    public void placeOrder(Order order)
    {
        store.add(order);
        int orderNumber = order.getOrderNumber();
        ordersDropDown.getItems().add(orderNumber);
        ordersDropDown.setValue(orderNumber);
        onOrderSelection();
    }

    /**
     * This method corresponds to the order number combobox of the GUI
     * It will update the ListView of order items, and it's total cost based on user selection
     */
    @FXML
    public void onOrderSelection()
    {
        Integer orderNumber = ordersDropDown.getValue();
        orders.getItems().clear();
        total.setText("");
        Order order = null;
        if(orderNumber!=null) {
            order = store.getOrder(orderNumber);
            if(order!=null){
                orders.getItems().addAll(order.getOrderItems());
                total.setText(String.format("%1$.2f", order.getTotal()));
            }
        }

    }

    /**
     * This method corresponds to the Cancel Orders button of the GUI
     * It will remove order items from ListView, and update order number combobox
     */
    @FXML
    public void cancelOrder()
    {
        if(orders.getItems().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Cancel order");
            alert.setTitle("Error");
            alert.setContentText("Can't cancel empty order.");
            alert.showAndWait();
            return;
        }
        int orderNumber = ordersDropDown.getValue();
        ordersDropDown.getItems().remove((Object)orderNumber);
        store.removeOrder(orderNumber);
        ordersDropDown.getSelectionModel().selectFirst();
        onOrderSelection();
    }

    /**
     * This method corresponds to the Export Orders button of the GUI
     * It will write all info of each order for each order number to a txt file
     * @throws IOException - exception
     */
    @FXML
    public void exportToFile() throws IOException
    {
        if(orders.getItems().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Export order");
            alert.setTitle("Error");
            alert.setContentText("Can't export empty order.");
            alert.showAndWait();
            return;
        }
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Target File for the Export");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"),
            new FileChooser.ExtensionFilter("All Files", "*.*"));
        Stage stage = new Stage();
        File targetFile = chooser.showSaveDialog(stage);
        store.exportToFile(targetFile);
    }

}
