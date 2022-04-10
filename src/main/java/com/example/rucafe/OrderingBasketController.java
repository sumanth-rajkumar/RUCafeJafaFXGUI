package com.example.rucafe;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.Collection;
import java.util.Optional;

/**
 * This class is what controls all the actions for each button using the inputs given by the
 * user in the orderingDonuts and orderingCoffee GUI
 * @author Sumanth Rajkumar, Shantanu Jain
 */
public class OrderingBasketController {

    private Order order = new Order();

    @FXML
    private TextField subTotal;
    @FXML
    private TextField salesTax;
    @FXML
    private TextField total;
    @FXML
    private ListView<MenuItem> items;

    @FXML
    private Button removeButton;

    /**
     * This method corresponds to the selection of an item in items ListView of GUI
     * It will see if item has been selected
     */
    @FXML
    public void initialize()
    {
        items.getSelectionModel().selectedItemProperty().addListener(this::onItemSelectionChange);
    }

    /**
     * Adds to items ListView based on passed in Collection of MenuItems
     * @param orderItems - Collection of MenuItems
     */
    public void addToOrder(Collection<? extends MenuItem> orderItems)
    {
        addToOrder(orderItems.toArray(MenuItem[]::new));
    }

    /**
     * Adds to items ListView based on passed in MenuItems array
     * @param orderItems - Array of MenuItems
     */
    public void addToOrder(MenuItem... orderItems)
    {
        for (MenuItem orderItem : orderItems)
        {
            order.add(orderItem);
            items.getItems().add(orderItem);
        }
        updateOrderCosts();
    }

    /**
     * Shows the combined subtotal amount, sales tax amount, total amount of all orders in ListView
     */
    private void updateOrderCosts()
    {
        subTotal.setText(String.format("%1$.2f", order.getOrderSubTotal()));
        salesTax.setText(String.format("%1$.2f", order.getSalesTax()));
        total.setText(String.format("%1$.2f", order.getTotal()));
    }

    /**
     * Removes the passed in MenuItem from ListView
     * @param item - MenuItem to be removed
     */
    public void removeFromOrder(MenuItem item)
    {
        order.remove(item);
        items.getItems().remove(item);
        updateOrderCosts();
    }


    /**
     * Disables the remove button in the GUI if item is selected in ListView
     * @param ov - Observable MenuItem value
     * @param old - old MenuItem value
     * @param newV - new MenuItem value
     */
    public void onItemSelectionChange(ObservableValue<? extends MenuItem> ov, MenuItem old, MenuItem newV)
    {
        removeButton.setDisable(items.getSelectionModel().getSelectedItem() == null);
    }

    /**
     * This method corresponds to the Remove Selected Item button of the GUI
     * It will remove the order item from the ListView based on user selection
     */
    @FXML
    void removeFromOrder()
    {
        removeFromOrder(items.getSelectionModel().getSelectedItem());
        donutsController.initialize();
        coffeeController.initialize();
    }

    private StoreOrdersController storeOrdersController;
    private OrderingDonutsController donutsController;
    private OrderingCoffeeController coffeeController;

    /**
     * Gets the reference of the StoreOrdersController object
     * to call the public methods to reference the instance variables
     * declared in StoreOrdersController, OrderingDonutsController, and OrderingCoffeeController
     * @param storeOrdersController - StoreOrdersController class that's set this class's StoreOrdersController
     * @param coffeeController - OrderingCoffeeController class that's set this class's OrderingCoffeeController
     * @param donutsController - OrderingDonutsController class that's set this class's OrderingDonutsController
     */
    public void setStoreOrdersController(StoreOrdersController storeOrdersController, OrderingDonutsController donutsController,
                                         OrderingCoffeeController coffeeController)
    {
        this.storeOrdersController = storeOrdersController;
        this.donutsController = donutsController;
        this.coffeeController = coffeeController;
    }

    /**
     * Clears all user input fields after order has been placed
     */
    public void resetOrder()
    {
        order = new Order();
        items.getItems().clear();
        updateOrderCosts();
        donutsController.initialize();
        coffeeController.initialize();
    }

    /**
     * This method corresponds to the Place Order button of the GUI
     * It will add the order items with its specific order number to ListView in store GUI based on user input
     */
    @FXML
    public void placeOrder()
    {

        if(items.getItems().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Place order");
            alert.setTitle("Error");
            alert.setContentText("Can't place empty order.");
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Place order");
        alert.setTitle("Confirmation");
        alert.setContentText("Order placed.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK)
        {
            Order existingOrder = order;
            items.getSelectionModel().selectFirst();
            storeOrdersController.placeOrder(existingOrder);
            resetOrder();
        }
    }
}
