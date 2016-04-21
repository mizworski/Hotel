package hotel;

import hotel.klient.Klient;
import hotel.pokoj.Ankieta;

/**
 * Klasa Zamowienie, zawierająca informacje o kliencie, który złożył dane zamówienie oraz
 * ankiete, która zawiera preferencje danego klienta.
 */
public class Zamowienie {
    Klient klient;
    Ankieta ankieta;
    int licznikOdrucen;

    /**
     * Konstruktor klasy Zamowienie.
     *
     * @param klient    klient skladajacy dane zamowienie
     * @param ankieta   ankieta preferencji danego klienta
     */
    Zamowienie(Klient klient,
               Ankieta ankieta) {
        this.klient = klient;
        this.ankieta = ankieta;
        licznikOdrucen = 0;
    }

    /**
     * Getter klienta przypisanego do danego zamówienia.
     *
     * @return klient, który złożył dane zamówienie
     */
    public Klient getKlient() {
        return klient;
    }

    /**
     * Getter ankiety przypisanej do danego zamówienia.
     *
     * @return ankieta, która zawiera preferencje klienta odnośnie danego zamówienia
     */
    public Ankieta getAnkieta() {
        return ankieta;
    }

    /**
     * Getter licznika odrzuceń danego zamówienia.
     *
     * @return liczbę odrzuceń propozycji, które zostały złożone danemu klientowi przez
     * recepcjonistów (również liczba ponownych umieszczeń w kolejce zamówień)
     */
    public int getLicznikOdrucen() {
        return this.licznikOdrucen;
    }

    /**
     * Setter licznika odrzuceń danego zamówienia. Zwiększa liczbę odrzuceń danego zamówienia o 1.
     */
    public void setLicznikOdrucen() {
        this.licznikOdrucen++;
    }
}
