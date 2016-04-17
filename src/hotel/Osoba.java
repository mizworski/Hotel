package hotel;

import hotel.pokoj.Ankieta;
import hotel.pokoj.Pokoj;

public abstract class Osoba {
    final static protected int LICZBA_WSZYSTKICH_WYMAGAN = 6;

    protected String imie;
    protected String nazwisko;
    protected String etykieta;
    protected String strategia;

    public Osoba(String imie, String nazwisko) {
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    // TODO this.etykieta zamiast getEtykieta() ?
    public void wypiszWizytowke() {
        System.out.println(this.etykieta + ": " + this.imie + " "
                + this.nazwisko + ", " + this.strategia + ".");
    }

    public int liczbaSpelnionychWymagan(Pokoj propozycjaPokoju,
                                        Ankieta wypelnionaAnkieta) {
        int liczbaSpelnionychWymagan = 0;

        if (propozycjaPokoju.getMaksymalnaLiczbaOsob() >=
                wypelnionaAnkieta.getMaksymalnaLiczbaOsob()) {
            liczbaSpelnionychWymagan++;
        }
        if (propozycjaPokoju.getCenaNajmu() <=
                wypelnionaAnkieta.getCenaNajmu()) {
            liczbaSpelnionychWymagan++;
        }
        if (propozycjaPokoju.getStyl().
                equals(wypelnionaAnkieta.getStyl())) {
            liczbaSpelnionychWymagan++;
        }
        if (propozycjaPokoju.getKolorystyka().
                equals(wypelnionaAnkieta.getKolorystyka())) {
            liczbaSpelnionychWymagan++;
        }
        if (propozycjaPokoju.getKierunek().
                equals(wypelnionaAnkieta.getKierunek())) {
            liczbaSpelnionychWymagan++;
        }
        if (propozycjaPokoju.czyDostepDoInternetu() ==
                wypelnionaAnkieta.czyDostepDoInternetu()) {
            liczbaSpelnionychWymagan++;
        }

        return liczbaSpelnionychWymagan;
    }
}
