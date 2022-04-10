package com.example.rucafe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.Optional;

/**
 * This class is what controls all the actions for each button using the inputs given by the
 * user in the orderingCoffee GUI
 * @author Sumanth Rajkumar, Shantanu Jain
 */
public class OrderingCoffeeController  {

    @FXML
    private ComboBox<String> sizes;
    @FXML
    private ComboBox<String> quantity;
    @FXML
    private CheckBox cream;
    @FXML
    private CheckBox syrup;
    @FXML
    private CheckBox whippedCream;
    @FXML
    private CheckBox milk;
    @FXML
    private CheckBox caramel;
    @FXML
    private TextField subTotal;

    private CheckBox[] addIns;

    private Coffee coffee;

    /**
     * Gives the observable list of size types
     * @return - ObservableList with all the size types
     */
    public ObservableList<String> sizesList()
    {

        ObservableList<String> sizes;
        sizes = FXCollections.observableArrayList(Coffee.SIZES);
        return sizes;
    }

    /**
     * Gives the observable list of quantity numbers
     * @return - ObservableList with all the quantity numbers
     */
    public ObservableList<String> quantityList()
    {
        ObservableList<String> quantity;
        quantity = FXCollections.observableArrayList("1","2","3","4","5");
        return quantity;
    }

    /**
     * This method sets the UI controls of the GUI immediately after it's opened
     */
    @FXML
    public void initialize()
    {
        coffee = new Coffee();
        sizes.setItems(sizesList());
        sizes.setValue(Coffee.TALL);
        quantity.setItems(quantityList());
        quantity.setValue("1");
        addIns = new CheckBox[]{cream,syrup,whippedCream,milk,caramel};
        subTotal.setText(coffee.getItemCostText());
        cream.setSelected(false);
        caramel.setSelected(false);
        milk.setSelected(false);
        syrup.setSelected(false);
        whippedCream.setSelected(false);
    }

    /**
     * This method corresponds to the Add order button of the GUI
     * It will add the order to ListView in basket GUI based on user input
     */
    @FXML
    void addToList()
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Add order");
        alert.setTitle("Confirmation");
        alert.setContentText("Coffee Order Added.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK)
        {
            basketController.addToOrder(new Coffee(coffee));
        }
    }

    /**
     * This method corresponds to the size type combobox of the GUI
     * It will update sub-total text based on user input
     */
    @FXML
    void selectSize()
    {
        if(sizes.getValue()==null) return;
          coffee.setSize(sizes.getSelectionModel().getSelectedItem());
          subTotal.setText(coffee.getItemCostText());
    }

    /**
     * This method corresponds to the quantity combobox of the GUI
     * It will update sub-total text based on user input
     */
    @FXML
    void selectQuantity()
    {
        if(quantity.getValue()==null) return;

        coffee.setQuantity(Integer.parseInt(quantity.getValue()));
        subTotal.setText(coffee.getItemCostText());
    }

    /**
     * This method corresponds to each addIn checkbox of the GUI
     * It will update sub-total text based on user input
     */
    @FXML
    void refreshAddIns()
    {
        for (CheckBox addIn : this.addIns)
        {
            if(addIn.isSelected())
            {
                coffee.add(addIn.getText());
            }
            else
            {
                coffee.remove(addIn.getText());
            }
        }
        subTotal.setText(coffee.getItemCostText());
    }

    private OrderingBasketController basketController;

    /**
     * Gets the reference of the OrderingBasketController object
     * to call the public methods to reference the instance variables
     * declared in OrderingBasketController
     * @param basketController - OrderingBasketController class that's set this class's OrderingBasketController
     */
    public void setBasketController(OrderingBasketController basketController)
    {
        this.basketController= basketController;
    }
}
