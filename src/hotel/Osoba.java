package hotel;

import hotel.pokoj.Ankieta;
import hotel.pokoj.Pokoj;

/**
 * Abstrakcyjna klasa Osoba, która jest nadklasą dla klientów oraz recepcjonistów.
*/
public abstract class Osoba {
    final static protected int LICZBA_WSZYSTKICH_WYMAGAN = 6;

    protected String imie;
    protected String nazwisko;
    protected String etykieta;
    protected String strategia;

    /**
     * Konstruktor klasy Osoba.
     *
     * @param imie      niepusty string przechowujący imie osoby
     * @param nazwisko  niepusty string przechowujący nazwisko osoby
     */
    public Osoba(String imie, String nazwisko) {
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    /**
     * Getter imienia.
     *
     * @return imię danej osoby
     */
    public String getImie() {
        return imie;
    }

    /**
     * Getter nazwiska.
     *
     * @return nazwisko danej osoby
     */
    public String getNazwisko() {
        return nazwisko;
    }

    /**
     * Wypisuje wizytówkę danej osoby, która zawiera informacje o funkcjonalności danej osoby
     * (Klient/Recepcjonista), jego imię wraz z nazwiskiem oraz strategię, którą kieruje się
     * podczas wyboru pokoju.
     */

    public void wypiszWizytowke() {
        System.out.println(this.etykieta + ": " + this.imie + " "
                + this.nazwisko + ", " + this.strategia + ".");
    }

    /**
     * Liczy ilość wymagań zawartych w podanej ankiecie, jaką spełnia podany pokój.
     *
     * @param propozycjaPokoju  proponowany pokój, który zostaje porównywany
     * @param wypelnionaAnkieta ankieta, która zawiera kryteria, względem których porównywany
     *                          jest pokój
     * @return                  ilość wymagań, które spełnia dany pokój
     */
    public int liczbaSpelnionychWymagan(Pokoj propozycjaPokoju,
                                        Ankieta wypelnionaAnkieta) {
        int liczbaSpelnionychWymagan = 0;

        if (propozycjaPokoju.getMaksymalnaLiczbaOsob() >= wypelnionaAnkieta.getMaksymalnaLiczbaOsob()) {
            liczbaSpelnionychWymagan++;
        }
        if (propozycjaPokoju.getCenaNajmuZaDobe() <= wypelnionaAnkieta.getCenaNajmuZaDobe()) {
            liczbaSpelnionychWymagan++;
        }
        if (propozycjaPokoju.getStyl().equals(wypelnionaAnkieta.getStyl())) {
            liczbaSpelnionychWymagan++;
        }
        if (propozycjaPokoju.getKolorystyka().equals(wypelnionaAnkieta.getKolorystyka())) {
            liczbaSpelnionychWymagan++;
        }
        if (propozycjaPokoju.getKierunek().equals(wypelnionaAnkieta.getKierunek())) {
            liczbaSpelnionychWymagan++;
        }
        if (propozycjaPokoju.czyDostepDoInternetu() == wypelnionaAnkieta.czyDostepDoInternetu()) {
            liczbaSpelnionychWymagan++;
        }

        return liczbaSpelnionychWymagan;
    }
}
