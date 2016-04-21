package hotel.klient;

import hotel.Osoba;
import hotel.pokoj.Ankieta;
import hotel.pokoj.Pokoj;

/**
 * Klasa abstrakcyjna klienta. Jest nadklasą wszystkich strategii klientów.
 */
public abstract class Klient extends Osoba{
    /**
     * Konstruktor klasy Klient.
     *
     * @param imie              niepusty string przechowujący imie klienta
     * @param nazwisko          niepusty string przechowujący nazwisko klienta
     */
    Klient(String imie, String nazwisko) {
        super(imie, nazwisko);
        this.etykieta = "Klient";
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
    abstract public boolean czyAkceptujeZamowienie(Pokoj propozycjaPokoju,
                                                   Ankieta wypelnionaAnkieta);
}
