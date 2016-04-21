package hotel.klient;

import hotel.pokoj.Ankieta;
import hotel.pokoj.Pokoj;

/**
 * Klasa klienta połowicznego, który akceptuje propozycję pokoju
 * wtedy i tylko wtedy, gdy proponowany pokój spełnia przynajmniej połowe z
 * jego wymagań.
 */
public class Polowiczny extends Klient {
    /**
     * Konstruktor klasy Polowiczny.
     *
     * @param imie              niepusty string przechowujący imie klienta
     * @param nazwisko          niepusty string przechowujący nazwisko klienta
     */
    public Polowiczny(String imie, String nazwisko) {
        super(imie, nazwisko);
        this.strategia = "polowiczna";
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
        return liczbaSpelnionychWymagan(propozycjaPokoju, wypelnionaAnkieta) >= LICZBA_WSZYSTKICH_WYMAGAN / 2;
    }
}
