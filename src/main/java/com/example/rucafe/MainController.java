package com.example.rucafe;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


/**
 * This class is the Main GUI that has the buttons that lead to all the other GUIs
 * @author Sumanth Rajkumar, Shantanu Jain
 */
public class MainController {



    /**
     * This method corresponds to the Order coffee button of the GUI
     * It will open up the orderingCoffee GUI
     */
    @FXML
    void goToCoffeeWindow()
    {
        coffeeStage.show();
        basketStage.hide();
        storeStage.hide();
        donutsStage.hide();

    }

    /**
     * This method corresponds to the Order donuts button of the GUI
     * It will open up the orderingDonuts GUI
     */
    @FXML
    void goToDonutWindow()
    {
        donutsStage.show();
        basketStage.hide();
        storeStage.hide();
        coffeeStage.hide();

    }

    /**
     * This method corresponds to the store orders button of the GUI
     * It will open up the storeOrders GUI
     */
    @FXML
    void goToStoreOrderWindow()
    {
        storeStage.show();
        basketStage.hide();
        coffeeStage.hide();
        donutsStage.hide();
    }

    /**
     * This method corresponds to the your orders button of the GUI
     * It will open up the orderingBasket GUI
     */
    @FXML
    void goToYourOrderWindow()
    {
        basketStage.show();
        coffeeStage.hide();
        donutsStage.hide();
        storeStage.hide();
    }

    FXMLLoader donutLoader = new FXMLLoader(getClass().getResource("OrderingDonutsView.fxml"));
    FXMLLoader basketLoader = new FXMLLoader(getClass().getResource("OrderingBasketView.fxml"));
    FXMLLoader coffeeLoader = new FXMLLoader(getClass().getResource("OrderingCoffeeView.fxml"));
    FXMLLoader storeLoader = new FXMLLoader(getClass().getResource("StoreOrdersView.fxml"));


    Stage donutsStage;
    Stage basketStage;
    Stage coffeeStage;
    Stage storeStage;


    OrderingDonutsController donutsController;
    OrderingBasketController basketController;
    OrderingCoffeeController coffeeController;
    StoreOrdersController storeController;

    /**
     * Sets the stage with scene that takes in FXMLLoader
     * @throws IOException - exception
     * @param loader - location
     * @return - Stage of each window
     */
    private Stage initializeStage(FXMLLoader loader) throws IOException
    {
        Parent root1 = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        return stage;
    }


    /**
     * Shares data between all GUI controllers
     * @throws IOException - exception
     */
    @FXML
    public void initialize() throws IOException
    {

        donutsStage = initializeStage(donutLoader);

        donutsController = donutLoader.getController();

        basketStage = initializeStage(basketLoader);
        basketController = basketLoader.getController();

        coffeeStage = initializeStage(coffeeLoader);
        coffeeController = coffeeLoader.getController();


        storeStage = initializeStage(storeLoader);
        storeController = storeLoader.getController();

        donutsController.setBasketController(basketController);
        coffeeController.setBasketController(basketController);
        basketController.setStoreOrdersController(storeController, donutsController, coffeeController);
    }

}
