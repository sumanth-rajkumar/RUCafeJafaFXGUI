package com.example.rucafe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OrderingDonutsController implements Initializable {

    @FXML
    private Button addToFlavor;
    @FXML
    private Button addToOrder;
    @FXML
    private Button add;
    @FXML
    private ListView<String> donutFlavors;
    @FXML
    private ListView<String> orderFlavors;
    @FXML
    private ComboBox<String> donutTypes;
    @FXML
    private ImageView donutImage;
    @FXML
    private ComboBox<String> quantity;

    public ObservableList<String> listOfFlavorsForYeast(){
    ObservableList<String> flavorList;
    flavorList = FXCollections.observableArrayList("jelly","glazed","chocolate frosted", "strawberry frosted", "sugar", "lemon filled");
    return flavorList;
    }

    public ObservableList<String> listOfFlavorsForCake(){
        ObservableList<String> flavorList;
        flavorList = FXCollections.observableArrayList("cinnamon sugar","old fashion","blueberry");
        return flavorList;
    }

    public ObservableList<String> listOfFlavorsForHoles(){
        ObservableList<String> flavorList;
        flavorList = FXCollections.observableArrayList("glazed holes","jelly holes","cinnamon sugar holes");
        return flavorList;
    }
    public ObservableList<String> donutList(){

        ObservableList<String> donuts;
        donuts = FXCollections.observableArrayList("yeast donut","cake donut", "donut hole");
        return donuts;
    }
    public ObservableList<String> quantityList()
    {
        ObservableList<String> quantity;
        quantity = FXCollections.observableArrayList("1","2","3","4","5");
        return quantity;
    }
    public ObservableList<String> orderList()
    {
        ArrayList<String> order = new ArrayList<>();
        ObservableList<String> orderList = FXCollections.observableArrayList(order);
        return orderList;
    }
    @FXML
    void addToList(ActionEvent event) {

    }
    @FXML
    void selectType(ActionEvent event) {

        if(donutTypes.getSelectionModel().getSelectedItem().equalsIgnoreCase("yeast donut"))
        {
            donutFlavors.setItems(listOfFlavorsForYeast());
            Image image = new Image("yeast.jpg");
            donutImage.setImage(image);
        }
        if(donutTypes.getSelectionModel().getSelectedItem().equalsIgnoreCase("cake donut"))
        {
            donutFlavors.setItems(listOfFlavorsForCake());
            Image image = new Image("cake.jpg");
            donutImage.setImage(image);
        }
        if(donutTypes.getSelectionModel().getSelectedItem().equalsIgnoreCase("donut hole"))
        {
            donutFlavors.setItems(listOfFlavorsForHoles());
            Image image = new Image("holes.jpg");
            donutImage.setImage(image);
        }
    }
    @FXML
    void selectQuantity(ActionEvent event) {

    }
    @FXML
    void addToChoice(ActionEvent event) {

    }
    @FXML
    void addToOrderFlavor(ActionEvent event) {
        if(donutTypes.getSelectionModel().getSelectedItem().equalsIgnoreCase("yeast donut")) {
            String selected = donutFlavors.getSelectionModel().getSelectedItem();
            listOfFlavorsForYeast().remove(selected);
            orderList().add(selected);
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        donutTypes.setItems(donutList());
        quantity.setItems(quantityList());
    }
}
