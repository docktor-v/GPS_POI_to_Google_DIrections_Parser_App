package org.personal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import java.util.HashMap;

public class Table {
    POIFactory poiFactory = new POIFactory();
    HashMap<String, POI> POIMap = new HashMap<>();
    POIs POIList = new POIs();

    public Table(POIs POIList) {
        this.POIList = POIList;
    }

    private TableView<POI> table = new TableView<POI>();

    private final ObservableList<POI> data
            = FXCollections.observableArrayList();

    private void initData(HashMap<String, POI> POIList) {

        POIList.values().stream().forEach(poi -> data.add(poi));
    }

    public TableView getTable(Stage stage) {
        initData(POIList.getPOIs());

        return table;
    }

}