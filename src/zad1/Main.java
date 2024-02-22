/**
 *
 *  @author Armianishyn  Dmytro S27705
 *
 */

package zad1;


import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        File dataDir = new File("D:\\Java\\UTP8_AD_S27705\\src\\zad1\\Datta");
        TravelData travelData = new TravelData(dataDir);
        String dateFormat = "yyyy-MM-dd";
        for (String locale : Arrays.asList("pl_PL", "en_GB")) {
            List<String> odlist = travelData.getOffersDescriptionsList(locale, dateFormat);
            for (String od : odlist) System.out.println(od);
        }


        String url = "jdbc:derby:TravelOffer;create=true";
        Database db = new Database(url, travelData);
        db.create();
        db.showGui();
    }
}
