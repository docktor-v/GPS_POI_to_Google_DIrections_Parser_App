/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.personal;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.nio.file.DirectoryStream;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author MadMax
 */
public class UserInterface extends Application {

    private final ObservableList<POI> data
            = FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) {
        Scanner scanner = new Scanner(System.in);
        POIFactory poiFactory = new POIFactory();
        HashMap<String, POI> POIMap = new HashMap<>();
        POIs POIList = new POIs();
        TableView table;

        VBox tableVBox = new VBox();
        Scene scene = new Scene(new Group());

        stage.setTitle(
                "POI Parser to Google Map Directions");


        ChoiceBox<String> choiceBox = new ChoiceBox();
        choiceBox.getItems().addAll("Latitude", "Longitude", "Substation Name", "City");
        choiceBox.setValue("Substation Name");
//        FileChooser fileChooser = new FileChooser();
//
//        Button button = new Button("Select File");
//        button.setOnAction(e -> {
//            File selectedFile = fileChooser.showOpenDialog(stage);
//        });
        POIList = poiFactory.createPOIs("Duke Directions Spreadsheet.csv");
        POIMap = POIList.getPOIs();
        initData(POIMap);
        FilteredList<POI> flPOI = new FilteredList(data, p -> true);//Pass the data to a filtered list
        Table tableConst = new Table(POIList);


        table = tableConst.getTable(stage);

        final Label label = new Label("POI Data");
        label.setFont(new Font("Arial", 20));

        table = createCollumns(table, flPOI);

        TextField textField = new TextField();
        textField.setPrefWidth(450);
        textField.setPromptText("Choose field from drop down and search here");
        textField.textProperty().addListener((obs, oldValue, newValue) -> {
            switch (choiceBox.getValue())//Switch on choiceBox value
            {
                case "Lattitude":
                    flPOI.setPredicate(p -> p.getLatitude().toLowerCase().contains(newValue.toLowerCase().trim()));//filter table by first name
                    break;
                case "Longitude":
                    flPOI.setPredicate(p -> p.getLongitude().toLowerCase().contains(newValue.toLowerCase().trim()));//filter table by last name
                    break;
                case "Substation Name":
                    flPOI.setPredicate(p -> p.getSubName().toLowerCase().contains(newValue.toLowerCase().trim()));//filter table by email
                    break;
                case "City":
                    flPOI.setPredicate(p -> p.getSubName().toLowerCase().contains(newValue.toLowerCase().trim()));//filter table by email
                    break;
            }
        });

        choiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal)
                -> {//reset table and textfield when new choice is selected
            if (newVal != null) {
                textField.setText("");
            }
        });

        HBox hBox = new HBox(choiceBox, textField);//Add choiceBox and textField to hBox
        hBox.setAlignment(Pos.CENTER);//Center HBox
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, hBox);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        stage.setScene(scene);
        stage.show();
    }

    private void initData(HashMap<String, POI> POIList) {

        POIList.values().stream().forEach(poi -> data.add(poi));
    }

    private TableView createCollumns(TableView table, FilteredList flPOI) {
        table.setItems(flPOI);//Set the table's items using the filtered list

        TableColumn substationCol = new TableColumn("Substation Name");
        substationCol.setMinWidth(200);
        substationCol.setCellValueFactory(
                new PropertyValueFactory<POI, String>("subName"));

        TableColumn latitudeCol = new TableColumn("Latitude");
        latitudeCol.setMinWidth(100);
        latitudeCol.setCellValueFactory(
                new PropertyValueFactory<POI, String>("latitude"));

        TableColumn longitudeCol = new TableColumn("Longitude");
        longitudeCol.setMinWidth(100);
        longitudeCol.setCellValueFactory(
                new PropertyValueFactory<POI, String>("longitude"));

        TableColumn cityCol = new TableColumn("City");
        cityCol.setMinWidth(100);
        cityCol.setCellValueFactory(
                new PropertyValueFactory<POI, String>("city"));

        table.getColumns().addAll(substationCol, latitudeCol, longitudeCol, cityCol);
        return table;
    }

    public static void main(String[] args) {
        launch(UserInterface.class);
    }

}
