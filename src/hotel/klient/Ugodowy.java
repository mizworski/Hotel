package hotel.klient;

import hotel.pokoj.Ankieta;
import hotel.pokoj.Pokoj;

public class Ugodowy extends Klient {
    public Ugodowy(String imie, String nazwisko) {
        super(imie, nazwisko);
        this.strategia = "ugodowa";
    }

    @Override
    public boolean czyAkceptujeZamowienie(Pokoj propozycjaPokoju,
                                          Ankieta wypelnionaAnkieta) {
        return true;
    }
}
