package com.example.rucafe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class is what controls all the actions for each button using the inputs given by the
 * user in the orderingDonuts GUI
 * @author Sumanth Rajkumar, Shantanu Jain
 */
public class OrderingDonutsController {

    @FXML
    private TextField subTotal;
    @FXML
    private ListView<String> donutFlavors;
    @FXML
    private ListView<Donut> orderFlavors;
    @FXML
    private ComboBox<String> donutTypes;
    @FXML
    private ImageView donutImage;
    @FXML
    private ComboBox<String> quantity;


    private static final String[] yeastFlavors = new String[] {"jelly","glazed","chocolate frosted", "strawberry frosted", "sugar", "lemon filled"};

    /**
     * Gives the observable list of flavors for yeast donut
     * @return - ObservableList with all the flavors for a yeast donut
     */
    public ObservableList<String> listOfFlavorsForYeast()
    {
        ObservableList<String> flavorList;
        Set<String> selectedYeastFlavors = orderFlavors.getItems().stream()
            .filter(d -> d.getDonutType().equals(Donut.YEAST_TYPE))
            .map(Donut::getDonutFlavor).collect(Collectors.toSet());

        List<String> flavors = Arrays.stream(yeastFlavors).filter(f -> !selectedYeastFlavors.contains(f)).collect(Collectors.toList());

        flavorList = FXCollections.observableList(flavors);
        return flavorList;
    }


    private static final String[] cakeFlavors = new String[] {"cinnamon sugar","old fashion","blueberry"};

    /**
     * Gives the observable list of flavors for cake donut
     * @return - ObservableList with all the flavors for a cake donut
     */
    public ObservableList<String> listOfFlavorsForCake()
    {
        ObservableList<String> flavorList;
        Set<String> selectedCakeFlavors = orderFlavors.getItems().stream()
            .filter(d -> d.getDonutType().equals(Donut.CAKE_TYPE))
            .map(Donut::getDonutFlavor).collect(Collectors.toSet());

        List<String> flavors = Arrays.stream(cakeFlavors).filter(f -> !selectedCakeFlavors.contains(f)).collect(Collectors.toList());

        flavorList = FXCollections.observableList(flavors);
        return flavorList;
    }

    private static final String[] holesFlavors = new String[] {"glazed holes","jelly holes","cinnamon sugar holes"};

    /**
     * Gives the observable list of flavors for donut holes
     * @return - ObservableList with all the flavors for a donut hole
     */
    public ObservableList<String> listOfFlavorsForHoles()
    {
        ObservableList<String> flavorList;
        Set<String> selectedHolesFlavors = orderFlavors.getItems().stream()
            .filter(d -> d.getDonutType().equals(Donut.HOLES_TYPE))
            .map(Donut::getDonutFlavor).collect(Collectors.toSet());

        List<String> flavors = Arrays.stream(holesFlavors).filter(f -> !selectedHolesFlavors.contains(f)).collect(Collectors.toList());

        flavorList = FXCollections.observableList(flavors);
        return flavorList;
    }

    /**
     * Gives the observable list of donut types
     * @return - ObservableList with all the donut types
     */
    public ObservableList<String> donutList()
    {

        ObservableList<String> donuts;
        donuts = FXCollections.observableArrayList(Donut.DONUT_TYPES);
        return donuts;
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
     * Gives the Donut object that has been set with info from user input
     * @return - Donut object set with user input
     */
    private Donut getSelectedMenuItem()
    {
        if( this.donutFlavors.getSelectionModel().isEmpty())
        {
            return null;
        }

        Donut donut = new Donut(
            Integer.parseInt(quantity.getSelectionModel().getSelectedItem()),
            this.donutTypes.getSelectionModel().getSelectedItem(),
            this.donutFlavors.getSelectionModel().getSelectedItem()
        );
        return donut;
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
        this.basketController = basketController;
    }

    /**
     * This method corresponds to the Add order button of the GUI
     * It will add the order to ListView in basket GUI based on user input
     */
    @FXML
    void addToList()
    {
        if(orderFlavors.getItems().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Add order");
            alert.setTitle("Error");
            alert.setContentText("No items selected.");
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Add order");
        alert.setTitle("Confirmation");
        alert.setContentText("Donut Order Added.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK)
        {
            ObservableList<? extends MenuItem> orderItems = orderFlavors.getItems();
            basketController.addToOrder(orderItems);
        }
    }


    /**
     * This method corresponds to the donut type combobox in the GUI
     * It will update the list of flavors and the image of donut based on user selection
     */
    @FXML
    void selectType()
    {

        String selectedDonutType = donutTypes.getSelectionModel().getSelectedItem();
        if(selectedDonutType == null) return;
           switch (selectedDonutType)
           {
           case Donut.CAKE_TYPE:
               donutFlavors.setItems(listOfFlavorsForCake());
               Image image = new Image("cake.jpg");
               donutImage.setImage(image);
               return;
           case Donut.YEAST_TYPE:
               donutFlavors.setItems(listOfFlavorsForYeast());
               image = new Image("yeast.jpg");
               donutImage.setImage(image);
               return;
           case Donut.HOLES_TYPE:
               donutFlavors.setItems(listOfFlavorsForHoles());
               image = new Image("holes.jpg");
               donutImage.setImage(image);
               return;
           default: throw new RuntimeException("unknown type " + selectedDonutType);
           }


    }

    /**
     * Updates the sub-total after each time the user
     * either adds to or remove from his list of choices
     */
    private void refreshOrderTotal()
    {
        double totalPrice =  orderFlavors.getItems().stream().mapToDouble(MenuItem::getItemCost).sum();
        subTotal.setText(String.format("%1$.2f",totalPrice));
    }

    /**
     * This method corresponds to the left arrows button in the GUI
     * Adds user's selected flavor from their list of items back to the list of flavor choices
     */
    @FXML
    void addToChoice()
    {

        Donut removeItem = orderFlavors.getSelectionModel().getSelectedItem();
        if(removeItem==null) return;
        orderFlavors.getItems().remove(removeItem);
        String currentDonutType = donutTypes.getValue();
        if(removeItem.getDonutType().equals(currentDonutType)) {
            donutFlavors.getItems().add(removeItem.getDonutFlavor());
        }
        refreshOrderTotal();
    }

    /**
     * This method corresponds to the right pointing arrows button in the GUI
     * Adds user's selected flavor from list of flavor choices to their list of items
     */
    @FXML
    void addToOrderFlavor()
    {

        Donut donut = getSelectedMenuItem();
        if(donut==null) return;

        donutFlavors.getItems().remove(donut.getDonutFlavor());
        orderFlavors.getItems().add(donut);
        refreshOrderTotal();

    }

    /**
     * This method sets the UI controls of the GUI immediately after it's opened
     */
    @FXML
    public void initialize()
    {
        orderFlavors.getItems().clear();
        donutTypes.setItems(donutList());
        donutTypes.setValue("yeast donut");
        donutFlavors.setItems(listOfFlavorsForYeast());
        //donutFlavors.getSelectionModel().selectFirst();
        Image image = new Image("yeast.jpg");
        donutImage.setImage(image);
        quantity.setItems(quantityList());
        quantity.setValue("1");

        this.subTotal.setText("");
    }




}
