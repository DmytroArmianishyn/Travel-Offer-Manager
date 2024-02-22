package zad1;

public class Wycieczka {

    String localization;
    String country;

    String data_star;

    String data_end;

    String place;
 String cena;

    String value;

    public Wycieczka(String lokalizacja, String country, String data_star, String data_end, String place, String cena, String value) {
        this.localization = lokalizacja;
        this.country = country;
        this.data_star = data_star;
        this.data_end = data_end;
        this.place = place;
        this.cena = cena;
        this.value = value;
    }

    @Override
    public String toString() {
        return  localization + '\'' +
                country + '\'' +
                 data_star + '\'' +
                 data_end + '\'' +
                place + '\'' +
              cena + '\'' +
                 value + '\''
               ;
    }
}
