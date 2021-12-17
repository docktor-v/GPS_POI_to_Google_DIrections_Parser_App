/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.personal;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author MadMax
 */
public class UserInterface extends Application {


    @Override
    public void start(Stage stage) {
        Scanner scanner = new Scanner(System.in);
        POIFactory poiFactory = new POIFactory();
        HashMap<String, POI> POIMap = new HashMap<>();
        POIs POIList = new POIs();

        Table table = new Table(POIList);
        VBox tableVBox = new VBox();
        Scene scene = new Scene(new Group());
//5///aaaa
        BorderPane borderPane = new BorderPane();
        borderPane.setPrefSize(600, 400);

        stage.setTitle(
                "POI Parser to Google Map Directions");
        tableVBox = table.getVBox(stage);
        ((Group) scene.getRoot()).getChildren().addAll(tableVBox);
        stage.show();
    }

    public static void main(String[] args) {
        launch(UserInterface.class);
    }

}
