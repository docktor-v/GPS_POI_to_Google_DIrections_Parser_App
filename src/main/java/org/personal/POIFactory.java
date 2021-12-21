/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.personal;

import java.io.File;
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

    public POIs createPOIs(File fileName) {
        POI POIsingle;
        POIs POIList = new POIs();

        try ( Scanner scanner = new Scanner(fileName)) {

            String collumns = scanner.nextLine();

            String[] collumnParts = collumns.split(",");

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                String[] POIparts = line.split(",");
                String latitude = POIparts[0];
                String longitude = POIparts[1];
                String subName = POIparts[2];

                POIsingle = new POI(latitude, longitude, subName);
                POIList.addPOI(subName, POIsingle);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return POIList;
    }

}
