package hotel.klient;

import hotel.Osoba;
import hotel.pokoj.Ankieta;
import hotel.pokoj.Pokoj;

public abstract class Klient extends Osoba{

    Klient(String imie, String nazwisko) {
        super(imie, nazwisko);
        this.etykieta = "Klient";
    }

    abstract public boolean czyAkceptujeZamowienie(Pokoj propozycjaPokoju,
                                                   Ankieta wypelnionaAnkieta);
}
