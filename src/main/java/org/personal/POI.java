/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.personal;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author MadMax
 */
public class POI {
    
    private final StringProperty latitude;
    private final StringProperty longitude;
    private final StringProperty subName;


    public POI(String latitude, String longitude, String subName) {
        this.latitude = new SimpleStringProperty(latitude);
        this.longitude = new SimpleStringProperty(longitude);
        this.subName = new SimpleStringProperty(subName);

    }

    public String getLatitude() {return latitude.get();}
    public StringProperty latitudeProperty() { return latitude; }

   public StringProperty longitudeProperty() { return longitude; }
    public String getLongitude() {
        return longitude.get();
    }
   public StringProperty subNameProperty() { return subName; }
    public String getSubName() {
        return subName.get();
    }


}
