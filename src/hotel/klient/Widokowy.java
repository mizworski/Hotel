package hotel.klient;

import hotel.pokoj.Ankieta;
import hotel.pokoj.Pokoj;

/**
 * Klasa klienta widokowego, który akceptuje propozycję pokoju, o ile pokój
 * jest skierowany w stronę podaną w ankiecie.
 */
public class Widokowy extends Klient {
    /**
     * Konstruktor klasy Widokowy.
     *
     * @param imie              niepusty string przechowujący imie klienta
     * @param nazwisko          niepusty string przechowujący nazwisko klienta
     */
    public Widokowy(String imie, String nazwisko) {
        super(imie, nazwisko);
        this.strategia = "widokowa";
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
        return propozycjaPokoju.getKierunek().equals(wypelnionaAnkieta.getKierunek());
    }
}
