package hotel.pokoj;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Klasa ankiety, zawierająca wymaganaia klienta dotyczące zamawianego pokoju.
 */
public class Ankieta extends OpisPokoju {
    Date pierwszyDzienPobytu;
    int dlugoscPobytu;
    final static String formatDaty = "dd-MM-yyyy";

    /**
     * Konstruktor klasy Ankieta.
     *
     * @param maksymalnaLiczbaOsob  preferowana liczba osób, która może zamieszkać w danym pokoju
     * @param cenaNajmuZaDobe       preferowana cena najmu pokoju za jedną dobę
     * @param styl                  preferowany styl pokoju
     * @param kolorystyka           preferowana kolorystyka pokoju
     * @param kierunek              preferowany kierunek pokoju
     * @param dostepDoInternetu     preferowany dostęp do internetu
     * @param pierwszyDzienPobytu   wymagany dzień zakwaterowania
     * @param dlugoscPobytu         wymagana długość pobytu
     */
    public Ankieta(int maksymalnaLiczbaOsob,
            int cenaNajmuZaDobe,
            Styl styl,
            Kolorystyka kolorystyka,
            Kierunek kierunek,
            boolean dostepDoInternetu,
            String pierwszyDzienPobytu,
            int dlugoscPobytu) {
        super(maksymalnaLiczbaOsob,
                cenaNajmuZaDobe,
                styl,
                kolorystyka,
                kierunek,
                dostepDoInternetu);
        SimpleDateFormat tempDataString = new java.text.SimpleDateFormat(formatDaty);
        Date data;
        try {
            data = tempDataString.parse(pierwszyDzienPobytu);
        } catch (ParseException e) {
            data = null;
        }

        this.pierwszyDzienPobytu = data;
        this.dlugoscPobytu = dlugoscPobytu;
    }

    /**
     * Getter pierwszego dnia danego pobytu.
     *
     * @return pierwszy dzien pobytu
     */
    public Date getPierwszyDzienPobytu() {
        return pierwszyDzienPobytu;
    }

    /**
     * Getter długości pobytu.
     *
     * @return długość pobytu
     */
    public int getDlugoscPobytu() {
        return dlugoscPobytu;
    }

    /**
     * Wypisuje datę rezerwacji oraz opis preferowanego pokoju.
     */
    public void wypiszOpis() {
        DateFormat df = new SimpleDateFormat(formatDaty);
        String dzienToString = df.format(pierwszyDzienPobytu);

        System.out.printf("Zamówienie: Pierwszy dzień: " + dzienToString +
                          " Długość pobytu: " + dlugoscPobytu + " ");
        super.wypiszOpis();
    }
}
