package hotel.pokoj;

import hotel.ListaRezerwacji;

import java.util.Date;

public class Pokoj extends OpisPokoju {
    int numerPokoju;
    ListaRezerwacji listaRezerwacji = new ListaRezerwacji();

    public Pokoj(int maksymalnaLiczbaOsob,
          int cenaNajmu, Styl styl,
          Kolorystyka kolorystyka,
          Kierunek kierunek,
          boolean dostepDoInternetu,
          int numerPokoju) {
        super(maksymalnaLiczbaOsob,
                cenaNajmu,
                styl,
                kolorystyka,
                kierunek,
                dostepDoInternetu);
        this.numerPokoju = numerPokoju;
    }

    public boolean czyDostepnyTermin(Date pierwszyDzienPobytu,
                                     int dlugoscPobytu) {
        return listaRezerwacji.czyDostepnyTermin(pierwszyDzienPobytu,
                                                 dlugoscPobytu);
    }

    public void dodajTerminPobytu(Date pierwszyDzienPobytu,
                                  int dlugoscPobytu) {
        listaRezerwacji.dodajTerminPobytu(pierwszyDzienPobytu,
                                          dlugoscPobytu);
    }

    public int getNumerPokoju() {
        return numerPokoju;
    }

    public void wypiszOpis() {
        System.out.printf("Proponowany pokój: ");
        super.wypiszOpis();
    }
}
