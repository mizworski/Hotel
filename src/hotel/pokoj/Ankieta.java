package hotel.pokoj;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ankieta extends OpisPokoju {
    Date pierwszyDzienPobytu;
    int dlugoscPobytu;
    final static String formatDaty = "dd-MM-yyyy";

    public Ankieta(int maksymalnaLiczbaOsob,
            int cenaNajmu,
            Styl styl,
            Kolorystyka kolorystyka,
            Kierunek kierunek,
            boolean dostepDoInternetu,
            Date pierwszyDzienPobytu,
            int dlugoscPobytu) {
        super(maksymalnaLiczbaOsob,
                cenaNajmu,
                styl,
                kolorystyka,
                kierunek,
                dostepDoInternetu);
        this.pierwszyDzienPobytu = pierwszyDzienPobytu;
        this.dlugoscPobytu = dlugoscPobytu;
    }

    public Ankieta(int maksymalnaLiczbaOsob,
            int cenaNajmu,
            Styl styl,
            Kolorystyka kolorystyka,
            Kierunek kierunek,
            boolean dostepDoInternetu,
            String pierwszyDzienPobytu,
            int dlugoscPobytu) {
        super(maksymalnaLiczbaOsob,
                cenaNajmu,
                styl,
                kolorystyka,
                kierunek,
                dostepDoInternetu);
        SimpleDateFormat tempDataString =
                new java.text.SimpleDateFormat(formatDaty);
        Date data;
        try {
            data = tempDataString.parse(pierwszyDzienPobytu);
        } catch (ParseException e) {
            data = null;
        }
        this.pierwszyDzienPobytu = data;
        this.dlugoscPobytu = dlugoscPobytu;
    }

    public Date getPierwszyDzienPobytu() {
        return pierwszyDzienPobytu;
    }

    public int getDlugoscPobytu() {
        return dlugoscPobytu;
    }

    public void wypiszOpis() {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String dzienToString = df.format(pierwszyDzienPobytu);

        System.out.printf("Zamówienie: " + dzienToString + " " + dlugoscPobytu + " ");
        super.wypiszOpis();
    }
}
