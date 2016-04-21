package hotel.klient;

import hotel.pokoj.Ankieta;
import hotel.pokoj.Pokoj;

/**
 * Klasa klienta ugodowego, który akceptuje każdą propozycję pokoju bez
 * względu na ilość spełnionych warunków.
 */
public class Ugodowy extends Klient {
    /**
     * Konstruktor klasy Ugodowy.
     *
     * @param imie              niepusty string przechowujący imie klienta
     * @param nazwisko          niepusty string przechowujący nazwisko klienta
     */
    public Ugodowy(String imie, String nazwisko) {
        super(imie, nazwisko);
        this.strategia = "ugodowa";
    }

    /**
     * Decyduje czy zaakcpetować daną propozycję pokoju zgodnie ze strategią danego klienta dla
     * preferencji podanych w danej ankiecie.
     *
     * @param propozycjaPokoju  proponowany pokój
     * @param wypelnionaAnkieta ankieta zawierająca preferencje danego klienta
     * @return                  true jeśli dany pokój spełnia kryteria danej strategii dla danej
     *                          ankiety
     */
    @Override
    public boolean czyAkceptujeZamowienie(Pokoj propozycjaPokoju,
                                          Ankieta wypelnionaAnkieta) {
        return true;
    }
}
