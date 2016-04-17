package hotel;

import hotel.klient.Klient;
import hotel.pokoj.Ankieta;

public class Zamowienie {
    Klient klient;
    Ankieta ankieta;
    int licznikOdrucen;

    Zamowienie(Klient klient,
               Ankieta ankieta) {
        this.klient = klient;
        this.ankieta = ankieta;
        licznikOdrucen = 0;
    }

    public Klient getKlient() {
        return klient;
    }

    public Ankieta getAnkieta() {
        return ankieta;
    }

    public int getLicznikOdrucen() {
        return this.licznikOdrucen;
    }

    public void setLicznikOdrucen() {
        this.licznikOdrucen++;
    }
}
