/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.personal;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.DirectoryStream;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author MadMax
 */
public class UserInterface extends Application {

    private final ObservableList<POI> data
            = FXCollections.observableArrayList();
    Scanner scanner = new Scanner(System.in);

    POIFactory poiFactory = new POIFactory();
    HashMap<String, POI> POIMap = new HashMap<>();
    POIs POIList = new POIs();
    TableView table;

    @Override
    public void start(Stage stage) {


        VBox tableVBox = new VBox();
        Scene scene = new Scene(new Group());

        stage.setTitle(
                "POI Parser to Google Map Directions");


        ChoiceBox<String> choiceBox = new ChoiceBox();
        choiceBox.getItems().addAll("Latitude", "Longitude", "Station Name", "City");
        choiceBox.setValue("Station Name");

        Label fileLabel = new Label();
        Label linkLabel = new Label();
        linkLabel.setText("Link will take to Google Maps and give you directions to: ");
        FileChooser.ExtensionFilter fileExtensions = new FileChooser.ExtensionFilter("CSV", "*csv");
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(fileExtensions);

        Button fileBtn = new Button("Select File");


        fileBtn.setOnAction((event) -> {

            // get the file selected
//            File file = fileChooser.showOpenDialog(stage);
            File file = new File("Sample Data Spreadsheet.csv");

            if (file != null) {
                fileLabel.setText(file.getAbsolutePath()
                        + "  selected");
            }
            POIList = poiFactory.createPOIs(file);
            POIMap = POIList.getPOIs();
            initData(POIMap);
        });


        FilteredList<POI> flPOI = new FilteredList(data, p -> true);//Pass the data to a filtered list
        Table tableObj = new Table(POIList);


        table = tableObj.getTable(stage);

        final Label label = new Label("POI Data");
        label.setFont(new Font("Arial", 20));
        Hyperlink link = new Hyperlink();
          link.setText("http://example.com");

        table = createCollumns(table, flPOI);
        table.setEditable(true);
        table.setPrefWidth(750);
        TextField textField = new TextField();
        textField.setPrefWidth(450);
        textField.setPromptText("Choose field from drop down and search here");
        textField.textProperty().addListener((obs, oldValue, newValue) -> {
            switch (choiceBox.getValue())//Switch on choiceBox value
            {
                case "Lattitude":
                    flPOI.setPredicate(p -> p.getLatitude().toLowerCase().contains(newValue.toLowerCase().trim()));
                    break;
                case "Longitude":
                    flPOI.setPredicate(p -> p.getLongitude().toLowerCase().contains(newValue.toLowerCase().trim()));
                    break;
                case "Station Name":
                    flPOI.setPredicate(p -> p.getSubName().toLowerCase().contains(newValue.toLowerCase().trim()));
                    break;

            }
        });

        choiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal)
                -> {//reset table and textfield when new choice is selected
            if (newVal != null) {
                textField.setText("");
            }
        });

        table.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue observableValue, Number number, Number t1) {
                System.out.println("ObservableValue: " + observableValue.getValue() + " Number: " + number + "Number " + flPOI.get(t1.intValue()).getLongitude());
                linkLabel.setText("Link below will take to Google Maps and give you directions to: "+flPOI.get(t1.intValue()).getSubName());
                link.set
            }

            public void changed(ObservableValue ov, Number new_value) {

            }

        });


        HBox hBox = new HBox(fileBtn, fileLabel);
        HBox hBox2 = new HBox(choiceBox, textField);//Add choiceBox and textFieldm to hBox
        HBox hBox3 = new HBox(linkLabel);
        HBox hBox4 = new HBox(link);
        hBox2.setAlignment(Pos.CENTER_LEFT);
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox4.setAlignment(Pos.CENTER_LEFT);
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, hBox2, hBox, hBox3, hBox4);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        stage.setTitle("GPS Parser");
        stage.setWidth(785);
        stage.setHeight(570);
        stage.setScene(scene);
        stage.show();
    }

    private void initData(HashMap<String, POI> POIList) {
        POIList.values().stream().forEach(poi -> data.add(poi));
    }

    private TableView createCollumns(TableView table, FilteredList flPOI) {
        table.setItems(flPOI);//Set the table's items using the filtered list

        TableColumn stationCol = new TableColumn("Station Name");
        stationCol.setMinWidth(200);
        stationCol.setCellValueFactory(
                new PropertyValueFactory<POI, String>("subName"));

        TableColumn latitudeCol = new TableColumn("Latitude");
        latitudeCol.setMinWidth(100);
        latitudeCol.setCellValueFactory(
                new PropertyValueFactory<POI, String>("latitude"));

        TableColumn longitudeCol = new TableColumn("Longitude");
        longitudeCol.setMinWidth(100);
        longitudeCol.setCellValueFactory(
                new PropertyValueFactory<POI, String>("longitude"));


        table.getColumns().addAll(stationCol, latitudeCol, longitudeCol);

        return table;
    }

    public static void main(String[] args) {
        launch(UserInterface.class);
    }

}
