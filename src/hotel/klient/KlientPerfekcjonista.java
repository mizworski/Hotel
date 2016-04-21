package hotel.klient;

import hotel.pokoj.Ankieta;
import hotel.pokoj.Pokoj;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;

/**
 * Klasa klienta perfekcjonistycznego, który akceptuje propozycję pokoju
 * wtedy i tylko wtedy, gdy proponowany pokój spełnia wszystkie jego wymagania.
 */
public class KlientPerfekcjonista extends Klient {
    /**
     * Konstruktor klasy KlientPerfekcjonista.
     *
     * @param imie              niepusty string przechowujący imie klienta
     * @param nazwisko          niepusty string przechowujący nazwisko klienta
     */
    public KlientPerfekcjonista(String imie, String nazwisko) {
        super(imie, nazwisko);
        this.strategia = "perfekcjonistyczna";
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
        return liczbaSpelnionychWymagan(propozycjaPokoju, wypelnionaAnkieta) == LICZBA_WSZYSTKICH_WYMAGAN;
    }
}
