package hotel.klient;

import hotel.pokoj.Ankieta;
import hotel.pokoj.Pokoj;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;

public class KlientPerfekcjonista extends Klient {
    public KlientPerfekcjonista(String imie, String nazwisko) {
        super(imie, nazwisko);
        this.strategia = "perfekcjonistyczna";
    }

    @Override
    public boolean czyAkceptujeZamowienie(Pokoj propozycjaPokoju,
                                          Ankieta wypelnionaAnkieta) {
        return liczbaSpelnionychWymagan(propozycjaPokoju,
                                        wypelnionaAnkieta) ==
                LICZBA_WSZYSTKICH_WYMAGAN;
    }
}
