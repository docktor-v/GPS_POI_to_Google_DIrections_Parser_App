/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.personal;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author MadMax
 */
public class POIFactory {

    public POIFactory() {

    }

    public POIs createPOIs(String fileName) {
        POI POIsingle;
        POIs POIList = new POIs();

        try ( Scanner scanner = new Scanner(Paths.get(fileName))) {
            System.out.println("Good");
            String collumns = scanner.nextLine();
            String[] collumnParts = collumns.split(",");

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                String[] POIparts = line.split(",");
                String latitude = POIparts[0];
                String longitude = POIparts[1];
                String subName = POIparts[2];
                String city = POIparts[5];
                POIsingle = new POI(latitude, longitude, subName, city);
                POIList.addPOI(subName, POIsingle);

            }
        } catch (Exception e) {
            System.out.println("Error, file not found.");
        }
        System.out.println(POIList.getPOIs().values().stream().count());
        return POIList;
    }

}
