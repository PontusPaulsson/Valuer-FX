package sample;

import javafx.application.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.scene.control.*;
import java.util.ArrayList;
import java.util.*;
import java.lang.*;

public class Main extends Application {
    Stage window;
    BorderPane layout;
    Label valuablesLbl;
    ListView<String> listView = new ListView<>();
    ArrayList<Valuables> valuablesArrayList = new ArrayList<>();
    ChoiceBox<String> choiceBox;
    Scene introScene, deviceScene, jeweleryScene, stockScene;

    @Override
    public void start(Stage primaryStage) throws Exception{

        //Set window to Primarystage for better naming.
        window = primaryStage;
        window.setTitle("Valuables");

        //Valuables label
        valuablesLbl = new Label("Valuables");
        BorderPane.setAlignment(valuablesLbl, Pos.CENTER);
        //Show button
        Button showBtn = new Button("Show");
        showBtn.setOnAction(e-> showValuables());
        //Crash button
        Button craschBtn = new Button("Crash");
        craschBtn.setOnAction(e-> marketCrasch());

        //Choicebox
        choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("Stock", "Jewelery", "Device");
        choiceBox.setOnAction(e -> addValuables(choiceBox.getValue()));

        //Radiobuttons and sort-label
        RadioButton nameRadioBtn = new RadioButton("Name");
        nameRadioBtn.setOnAction(e-> sort("Name"));
        RadioButton worthRadioBtn = new RadioButton("Worth");
        worthRadioBtn.setOnAction(e-> sort("Worth"));
        Label sortLbl = new Label("Sort");
        ToggleGroup btnGroup = new ToggleGroup();
        nameRadioBtn.setToggleGroup(btnGroup);
        worthRadioBtn.setToggleGroup(btnGroup);
        VBox radioBtnHbox = new VBox(sortLbl, nameRadioBtn, worthRadioBtn);
        radioBtnHbox.setAlignment(Pos.CENTER);

        //VBox for bottom buttons and choicebox
        HBox bottomVbox = new HBox();
        bottomVbox.getChildren().addAll(choiceBox, showBtn, craschBtn);
        bottomVbox.setAlignment(Pos.CENTER);
        //Layout
        layout = new BorderPane();
        layout.setTop(valuablesLbl);
        layout.setCenter(listView);
        layout.setBottom(bottomVbox);
        layout.setRight(radioBtnHbox);
        //Scene
        introScene = new Scene(layout);
        window.setScene(introScene);
        window.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
    public void addValuables(String typeOfValuable){
        switch (typeOfValuable){
            case "Stock":
                createStock();
                return;
            case "Jewelery":
                createJewelery();
                return;
            case "Device":
                createDevice();
                return;
        }
    }
    public void createDevice(){
        //Borderpane
        BorderPane borderPane = new BorderPane();
        //Textfield for name
        TextField nameField = new TextField("Name");
        //Textfield for price
        TextField priceField = new TextField("Price");
        //Textfield for condition
        TextField conditionField = new TextField("Condition");
        VBox vBox = new VBox(nameField, conditionField, priceField); //Adding textfield to Vertical BOX
        //Create button
        Button createBtn = new Button("Create");
        createBtn.setOnAction(e ->{
            valuablesArrayList.add(new Device(nameField.getText(), Double.parseDouble(priceField.getText()), Integer.parseInt(conditionField.getText())));
            showValuables();
            window.setScene(introScene);
        });
        //Cancel button
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> {
            window.setScene(introScene);
        });
        HBox hBox = new HBox(createBtn, cancelButton); //Adding buttons to horizontal box
        hBox.setAlignment(Pos.BOTTOM_CENTER);
        //-------------------------
        borderPane.setBottom(hBox);
        borderPane.setCenter(vBox);
        //-------------------------
        deviceScene = new Scene(borderPane, 300,300);
        window.setScene(deviceScene);

    }
    public void createStock(){
        //Borderpane
        BorderPane borderPane = new BorderPane();
        //Textfield for name
        TextField nameField = new TextField("Name");
        //Textfield for Stockquote
        TextField quoteField = new TextField("Quote");
        //Textfield for price
        TextField priceField = new TextField("Price");
        //Textfield for condition
        TextField amountField = new TextField("Amount");
        VBox vBox = new VBox(nameField, quoteField, priceField, amountField); //Adding textfield to Vertical BOX
        //Create button
        Button createBtn = new Button("Create");
        createBtn.setOnAction(e ->{
            valuablesArrayList.add(new Stock(nameField.getText(), quoteField.getText(), Integer.parseInt(amountField.getText()), Double.parseDouble(priceField.getText())));
            showValuables();
            window.setScene(introScene);
        });
        //Cancel button
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> {
            window.setScene(introScene);
        });
        HBox hBox = new HBox(createBtn, cancelButton); //Adding buttons to horizontal box
        hBox.setAlignment(Pos.BOTTOM_CENTER);
        //-------------------------
        borderPane.setBottom(hBox);
        borderPane.setCenter(vBox);
        //-------------------------
        deviceScene = new Scene(borderPane, 300,300);
        window.setScene(deviceScene);
    }
    public void createJewelery(){
        //Borderpane
        BorderPane borderPane = new BorderPane();
        //Textfield for name
        TextField nameField = new TextField("Name");
        //Textfield for price
        TextField gemField = new TextField("Number of gems");
        //Checkbox for isGold
        CheckBox isGoldChkBox = new CheckBox("Gold?");
        VBox vBox = new VBox(nameField, gemField, isGoldChkBox); //Adding textfield and checkbox to Vertical BOX
        //Create button
        Button createBtn = new Button("Create");
        createBtn.setOnAction(e ->{
            boolean isGold = false;
            if(isGoldChkBox.isSelected())
                isGold = true;
            valuablesArrayList.add(new Jewelery(nameField.getText(), isGold, Integer.parseInt(gemField.getText())));
            showValuables();
            window.setScene(introScene);
        });
        //Cancel button
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> {
            window.setScene(introScene);
        });
        HBox hBox = new HBox(createBtn, cancelButton); //Adding buttons to horizontal box
        hBox.setAlignment(Pos.BOTTOM_CENTER);
        //-------------------------
        borderPane.setBottom(hBox);
        borderPane.setCenter(vBox);
        //-------------------------
        deviceScene = new Scene(borderPane, 300,300);
        window.setScene(deviceScene);
    }
    public void showValuables(){
        listView.getItems().clear();
        for(Valuables v : valuablesArrayList){
           if(v instanceof Stock){
               listView.getItems().add("Stock: " + v.getName() + " Quote: " + ((Stock) v).getStockQuote() + " Amount: " + ((Stock) v).getAmount() + " Worth: " + v.getWorth());
           }
           else if(v instanceof Jewelery){
               listView.getItems().add("Jewelry: " + v.getName() + " Worth: " + v.getWorth() + " Gemstones: " + ((Jewelery) v).getGemStones() + " Type: " + ((Jewelery) v).getType());
           }
           else if(v instanceof Device)
           {
               listView.getItems().add("Device: " + v.getName() + " Worth " + v.getWorth() + " Condition: " + ((Device) v).getCondition() + " Price when bought: " + ((Device) v).getPriceWhenBought());
           }
        }
    }
    public void sort(String sort) {
        if(sort.equals("Name"))
        {
            valuablesArrayList.sort(Comparator.comparing(Valuables::getName));
        }
        else
        {
            valuablesArrayList.sort(Comparator.comparing(Valuables::getWorth).reversed());
        }
        showValuables();
    }
    public void marketCrasch(){
        for(Valuables v : valuablesArrayList){
            if(v instanceof Stock){
                v.setWorth(0);
            }
        }
        showValuables();
    }
}
