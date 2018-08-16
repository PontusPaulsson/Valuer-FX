import javafx.application.*;
import javafx.geometry.Insets;
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
    CheckBox arrayListChkBox;
    CheckBox dataBaseChkBox;
    @Override
    public void start(Stage primaryStage) throws Exception{
        //SQLClient.insertDevice("Testar", 100, "Device", 100, 9);

        //Set window to Primarystage for better naming.
        window = primaryStage;
        window.setTitle("Valuables");

        //Valuables label
        valuablesLbl = new Label("Valuables");
        valuablesLbl.getStyleClass().add("valuables");
        valuablesLbl.setPadding(new Insets(10,10,10,10));
        BorderPane.setAlignment(valuablesLbl, Pos.CENTER);
        //Show button
        Button showBtn = new Button("Show");
        showBtn.setOnAction(e-> showValuables());
        //Crash button
        Button craschBtn = new Button("Crash");
        craschBtn.setOnAction(e-> marketCrasch());

        //MenuButton and items
        MenuButton menuButton = new MenuButton("Select valuable to add.");
        MenuItem stock = new MenuItem("Stock");
        stock.setOnAction( e -> createStock());
        MenuItem device = new MenuItem("Device");
        device.setOnAction( e -> createDevice());
        MenuItem jewelery = new MenuItem("Jewelery");
        jewelery.setOnAction( e -> createJewelery());
        menuButton.getItems().addAll(stock, device, jewelery);

        //Radiobuttons and sort-label
        RadioButton nameRadioBtn = new RadioButton("Name");
        nameRadioBtn.setOnAction(e-> sort("Name"));
        RadioButton worthRadioBtn = new RadioButton("Worth");
        worthRadioBtn.setOnAction(e-> sort("Worth"));
        Label sortLbl = new Label("Sort");
        sortLbl.getStyleClass().add("sort");
        ToggleGroup btnGroup = new ToggleGroup();
        nameRadioBtn.setToggleGroup(btnGroup);
        worthRadioBtn.setToggleGroup(btnGroup);
        VBox radioBtnHbox = new VBox(sortLbl, nameRadioBtn, worthRadioBtn);
        radioBtnHbox.setAlignment(Pos.CENTER);
        radioBtnHbox.setSpacing(5);
        radioBtnHbox.setPadding(new Insets(10,10,10,10));
        //Checkboxes arraylist or database
        dataBaseChkBox = new CheckBox("Database");
        arrayListChkBox = new CheckBox("Arraylist");
        //VBox for bottom buttons and choicebox
        HBox bottomHbox = new HBox();
        bottomHbox.setSpacing(10);
        bottomHbox.getChildren().addAll(dataBaseChkBox, arrayListChkBox,menuButton, showBtn, craschBtn);
        bottomHbox.setAlignment(Pos.CENTER);
        //Layout
        layout = new BorderPane();
        layout.getStyleClass().add("borderpane");
        layout.setTop(valuablesLbl);
        layout.setCenter(listView);
        layout.setBottom(bottomHbox);
        layout.setRight(radioBtnHbox);
        //Scene
        introScene = new Scene(layout, 500,500);
        introScene.getStylesheets().add("/src/style.css");
        window.setScene(introScene);
        window.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
    public void createDevice(){
        //Labels
        Label nameLabel = new Label("Name");
        Label priceLabel = new Label("Price");
        Label conditionLabel = new Label("Condition");
        //Borderpane
        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: #A9B7C6");
        //Textfield for name
        TextField nameField = new TextField();
        //Textfield for price
        TextField priceField = new TextField();
        //Textfield for condition

        //Checkbox for storing in arraylist or database
        arrayListChkBox = new CheckBox("Arraylist");
        dataBaseChkBox = new CheckBox("Database");

        TextField conditionField = new TextField();
        VBox vBox = new VBox(nameLabel, nameField, priceLabel, priceField, conditionLabel, conditionField, arrayListChkBox, dataBaseChkBox); //Adding textfield to Vertical BOX
        vBox.setMaxWidth(200);
        //Create button
        Button createBtn = new Button("Create");
        createBtn.setOnAction(e ->{
            Device d = new Device(nameField.getText(), Double.parseDouble(priceField.getText()), Integer.parseInt(conditionField.getText()));
            if(arrayListChkBox.isSelected()){
                valuablesArrayList.add(d);
            }
            if(dataBaseChkBox.isSelected()){
                SQLClient.insertDevice(d.getName(), d.getWorth(), "Device", d.getPriceWhenBought(), d.getCondition());
            }
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
        deviceScene.getStylesheets().add("/src/style.css");
        window.setScene(deviceScene);

    }
    public void createStock(){
        //Labels
        Label nameLabel = new Label("Name");
        Label quoteLabel = new Label("Quote");
        Label priceLabel = new Label("Price");
        Label amountLabel = new Label("Amount");
        //Borderpane
        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: #A9B7C6");
        //Textfield for name
        TextField nameField = new TextField();
        //Textfield for Stockquote
        TextField quoteField = new TextField();
        //Textfield for price
        TextField priceField = new TextField();
        //Textfield for condition
        //Checkbox for storing in arraylist or database
        arrayListChkBox = new CheckBox("Arraylist");
        dataBaseChkBox = new CheckBox("Database");
        TextField amountField = new TextField();
        VBox vBox = new VBox(nameLabel, nameField, quoteLabel, quoteField, priceLabel, priceField, amountLabel, amountField, arrayListChkBox, dataBaseChkBox); //Adding textfield and labels to Vertical BOX
        vBox.setMaxWidth(200);
        //Create button
        Button createBtn = new Button("Create");
        createBtn.setOnAction(e ->{
            Stock s = new Stock(nameField.getText(), quoteField.getText(), Integer.parseInt(amountField.getText()), Double.parseDouble(priceField.getText()));
            if(arrayListChkBox.isSelected()){
                valuablesArrayList.add(s);
            }
            if(dataBaseChkBox.isSelected()){
                SQLClient.insertStock(s.getName(),s.getWorth() , "Stock", s.getStockQuote(), s.getAmount());
            }
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
        stockScene = new Scene(borderPane, 300,300);
        stockScene.getStylesheets().add("/src/style.css");
        window.setScene(stockScene);
    }
    public void createJewelery(){
        //Labels
        Label nameLabel = new Label("Name");
        Label gemLabel = new Label("Gems?");
        //Borderpane
        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: #A9B7C6");
        //Textfield for name
        TextField nameField = new TextField();
        //Textfield for price
        TextField gemField = new TextField();
        //Checkbox for isGold
        CheckBox isGoldChkBox = new CheckBox("Gold?");
        //Checkbox for storing in arraylist or database
        arrayListChkBox = new CheckBox("Arraylist");
        dataBaseChkBox = new CheckBox("Database");
        VBox vBox = new VBox(nameLabel, nameField, gemLabel, gemField, isGoldChkBox, arrayListChkBox, dataBaseChkBox); //Adding textfield and checkbox to Vertical BOX
        vBox.setMaxWidth(200);
        //Create button
        Button createBtn = new Button("Create");
        createBtn.setOnAction(e ->{
            boolean isGold = false;
            if(isGoldChkBox.isSelected())
                isGold = true;
            Jewelery j = new Jewelery(nameField.getText(), isGold, Integer.parseInt(gemField.getText()));
            if(arrayListChkBox.isSelected()){
                valuablesArrayList.add(j);
            }
            if(dataBaseChkBox.isSelected()){
                SQLClient.insertJewelery(j.getName(), j.getWorth(), "Jewelery", j.getGemStones(), isGold );
            }
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
        jeweleryScene = new Scene(borderPane, 300,300);
        jeweleryScene.getStylesheets().add("/src/style.css");
        window.setScene(jeweleryScene);
    }
    public void showValuables(){
        listView.getItems().clear();
        if(arrayListChkBox.isSelected()){
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
        if(dataBaseChkBox.isSelected()){
            for(String s : SQLClient.selectAll()){
                listView.getItems().add(s);
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
