package org.personal;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.util.HashMap;

import javafx.stage.FileChooser;

public class Table {
    POIFactory poiFactory = new POIFactory();
    HashMap<String, POI> POIMap = new HashMap<>();
    POIs POIList = new POIs();

    public Table(POIs POIList) {
        this.POIList = poiFactory.createPOIs("Duke Directions Spreadsheet.csv");

    }

    //    POIMap = POIList.getPOIs();
    private TableView<POI> table = new TableView<POI>();

    private final ObservableList<POI> data
            = FXCollections.observableArrayList();

    private void initData(HashMap<String, POI> POIList) {

        POIList.values().stream().forEach(poi -> data.add(poi));
    }

    public TableView getTable(Stage stage) {

        initData(POIList.getPOIs());
        Scene scene = new Scene(new Group());
        stage.setTitle("GPS Parser");
        stage.setWidth(450);
        stage.setHeight(550);

//        final Label label = new Label("POI Data");
//        label.setFont(new Font("Arial", 20));

        table.setEditable(true);






//        table.setItems(flPOI);//Set the table's items using the filtered list
//        table.getColumns().addAll(substationCol, latitudeCol, longitudeCol, cityCol);
        return this.table;
    }
//        //Adding ChoiceBox and TextField here!
//        ChoiceBox<String> choiceBox = new ChoiceBox();
//        choiceBox.getItems().addAll("Latitude", "Longitude", "Substation Name", "City");
//        choiceBox.setValue("Substation Name");
////        FileChooser fileChooser = new FileChooser();
////
////        Button button = new Button("Select File");
////        button.setOnAction(e -> {
////            File selectedFile = fileChooser.showOpenDialog(stage);
////        });
//      //  VBox vBox = new VBox(button);
//        TextField textField = new TextField();
//        textField.setPrefWidth(450);
//        textField.setPromptText("Choose field from drop down and search here");
//        textField.textProperty().addListener((obs, oldValue, newValue) -> {
//            switch (choiceBox.getValue())//Switch on choiceBox value
//            {
//                case "Lattitude":
//                    flPOI.setPredicate(p -> p.getLatitude().toLowerCase().contains(newValue.toLowerCase().trim()));//filter table by first name
//                    break;
//                case "Longitude":
//                    flPOI.setPredicate(p -> p.getLongitude().toLowerCase().contains(newValue.toLowerCase().trim()));//filter table by last name
//                    break;
//                case "Substation Name":
//                    flPOI.setPredicate(p -> p.getSubName().toLowerCase().contains(newValue.toLowerCase().trim()));//filter table by email
//                    break;
//                case "City":
//                    flPOI.setPredicate(p -> p.getSubName().toLowerCase().contains(newValue.toLowerCase().trim()));//filter table by email
//                    break;
//            }
//        });
//
//        choiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal)
//                -> {//reset table and textfield when new choice is selected
//            if (newVal != null) {
//                textField.setText("");
//            }
//        });
//
//        HBox hBox = new HBox(choiceBox, textField);//Add choiceBox and textField to hBox
//        hBox.setAlignment(Pos.CENTER);//Center HBox
//        final VBox vbox = new VBox();
//        vbox.setSpacing(5);
//        vbox.setPadding(new Insets(10, 0, 0, 10));
//
//
//     //   ((Group) scene.getRoot()).getChildren().addAll(vbox);
//        return (vbox);
////        stage.setScene(scene);
////        stage.show();
    }

