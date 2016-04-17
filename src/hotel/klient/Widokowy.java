package hotel.klient;

import hotel.pokoj.Ankieta;
import hotel.pokoj.Pokoj;

public class Widokowy extends Klient {
    public Widokowy(String imie, String nazwisko) {
        super(imie, nazwisko);
        this.strategia = "widokowa";
    }

    @Override
    public boolean czyAkceptujeZamowienie(Pokoj propozycjaPokoju,
                                          Ankieta wypelnionaAnkieta) {
        return propozycjaPokoju.getKierunek().
                equals(wypelnionaAnkieta.getKierunek());
    }
}
