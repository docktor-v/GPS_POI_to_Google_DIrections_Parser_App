/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.personal;

import java.util.HashMap;

/**
 * @author MadMax
 */
public class POIs {

    private HashMap<String, POI> POIs;

    public POIs() {
        this.POIs = new HashMap<>();
    }

    public HashMap<String, POI> getPOIs() {
        return POIs;
    }

    public void addPOI(String subName, POI poi) {
        this.POIs.putIfAbsent(subName, poi);
    }

}
