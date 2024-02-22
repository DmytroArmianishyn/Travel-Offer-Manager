package zad1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.DataFormatException;

public class TravelData {

    File file;

    List<String> ls = new ArrayList<>();

    public TravelData(File file) {
        this.file = file;
        readdirektory(file);
    }

List<Wycieczka> list = new ArrayList<>();
    List<Wycieczka> list2 = new ArrayList<>();
    public void pathfile(File file){

        try(BufferedReader buf = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line=buf.readLine())!=null){

                String[] split = line.split("\t");

                list.add(new Wycieczka(split[0],split[1],split[2],split[3],split[4],split[5],split[6]));


            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


    public List<String> getOffersDescriptionsList(String lokale, String dateformat){
        ls.clear();


        List<String> pl_name = new ArrayList<>();
        List<String> ang_name = new ArrayList<>();




//////////////////////////////////////
        Map<String, String> countryPL_US = new HashMap<>();
        Map<String, String> countryUS_PL = new HashMap<>();
        Locale localePL = new Locale("pl", "PL");
        Locale localeUS = new Locale("en", "GB");
        for (Locale loc: Locale.getAvailableLocales()) {
            countryPL_US.put(loc.getDisplayCountry(localePL), loc.getDisplayCountry(localeUS));
            countryUS_PL.put(loc.getDisplayCountry(localeUS), loc.getDisplayCountry(localePL));
        }
        /////////////////////////////////////////

        for (int i = 0; i < list.size(); i++) {
            if (lokale.startsWith("en")) {



                String word = " ";
                ResourceBundle bundl = ResourceBundle.getBundle("Place",new Locale("en","GB"));
                if (countryPL_US.get(list.get(i).country)!=null) {
                    word = countryPL_US.get(list.get(i).country);
                }
                else {
                    word = list.get(i).country;
                }
                String place = changepolish(list.get(i).place);
                String word2 = bundl.getString(place);
                NumberFormat instance = NumberFormat.getInstance(new Locale("en", "GB"));
                String cena = null;
                try {
                    cena = instance.format(instance.parse(list.get(i).cena));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }



                if (!dataformat(list.get(i).data_star,dateformat)||!dataformat(list.get(i).data_end ,dateformat))
                    throw new FormatDataExeption();



                String wordmix =    word+ " "  + list.get(i).data_star+ " " +  list.get(i).data_end + " "+  word2 + " "+   cena+ " " +  list.get(i).value;

                ls.add(wordmix);
list2.add(new Wycieczka("en",word ,list.get(i).data_star,list.get(i).data_end,word2 ,cena,list.get(i).value));



            }else if (lokale.startsWith("pl")){

                ResourceBundle bundl = ResourceBundle.getBundle("Place",new Locale("pl","PL"));
                String word = " ";
                if (countryUS_PL.get(list.get(i).country)!=null) {
                    word = countryUS_PL.get(list.get(i).country);
                }
                else {
                    word = list.get(i).country;
                }String place = changepolish(list.get(i).place);
                String word2 = bundl.getString(place);
                NumberFormat instance = NumberFormat.getInstance(new Locale("pl", "PL"));
                String cena = null;
                try {
                    cena = instance.format(instance.parse(list.get(i).cena));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                if (!dataformat(list.get(i).data_star,dateformat)||!dataformat(list.get(i).data_end ,dateformat))
                    throw new FormatDataExeption();

                String wordmix =  word+ " " +  list.get(i).data_star+ " " +  list.get(i).data_end + " "+  word2 + " "+ cena+ " " +  list.get(i).value;


                ls.add(wordmix);
                list2.add(new Wycieczka("pl",word ,list.get(i).data_star,list.get(i).data_end,word2 , list.get(i).cena,list.get(i).value));
            }
        }

        return ls;
    }
    public  static String changepolish(String word){


        String replacr = word.replace('ó','o').replace('ą','a')
                .replace('ę','e').replace('ł','l').replace('ż','z');



        return replacr;
    }


    public void readdirektory(File filee){
        File folder = new File(String.valueOf(filee));


        if ((folder.exists() && folder.isDirectory())){
            File[] files = folder.listFiles();


            if (files != null && files.length>0){


                for (File fil : files) {
                    if (fil.isFile()) {

                        pathfile(fil);
                    }
                }

            }



        }


    }


    public boolean dataformat(String data,String format){

        SimpleDateFormat sm = new SimpleDateFormat(format);
        try {
            sm.parse(data);
return true;
        }catch (ParseException e){
            return false;
        }


    }
}
