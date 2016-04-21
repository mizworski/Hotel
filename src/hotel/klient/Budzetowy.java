package hotel.klient;

import hotel.pokoj.Ankieta;
import hotel.pokoj.Pokoj;

/**
 * Klasa klienta budżetowego, który akceptuje propozycję pokoju, o ile cena
 * wynajmu jest nie większa, niż ta podana w ankiecie.
 */
public class Budzetowy extends Klient{
    /**
     * Konstruktor klasy Budzetowy.
     *
     * @param imie              niepusty string przechowujący imie klienta
     * @param nazwisko          niepusty string przechowujący nazwisko klienta
     */
    public Budzetowy(String imie, String nazwisko) {
        super(imie, nazwisko);
        this.strategia = "budzetowa";
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
        return (propozycjaPokoju.getCenaNajmuZaDobe() <= wypelnionaAnkieta.getCenaNajmuZaDobe());
    }
}
