package hotel.klient;

import hotel.pokoj.Ankieta;
import hotel.pokoj.Pokoj;

public class Polowiczny extends Klient {
    public Polowiczny(String imie, String nazwisko) {
        super(imie, nazwisko);
        this.strategia = "polowiczna";
    }

    @Override
    public boolean czyAkceptujeZamowienie(Pokoj propozycjaPokoju,
                                          Ankieta wypelnionaAnkieta) {
        return liczbaSpelnionychWymagan(propozycjaPokoju,
                wypelnionaAnkieta) >=
                LICZBA_WSZYSTKICH_WYMAGAN / 2;
    }
}
