package hotel.klient;

import hotel.pokoj.Ankieta;
import hotel.pokoj.Pokoj;

public class Budzetowy extends Klient{
    public Budzetowy(String imie, String nazwisko) {
        super(imie, nazwisko);
        this.strategia = "budzetowa";
    }

    @Override
    public boolean czyAkceptujeZamowienie(Pokoj propozycjaPokoju,
                                          Ankieta wypelnionaAnkieta) {
        return (propozycjaPokoju.getCenaNajmu() <=
                wypelnionaAnkieta.getCenaNajmu());
    }
}
