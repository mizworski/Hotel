package hotel.pokoj;

import hotel.ListaRezerwacji;

import java.util.Date;

public class Pokoj extends OpisPokoju {
    int numerPokoju;
    ListaRezerwacji listaRezerwacji = new ListaRezerwacji();

    /**
     * Konstruktor klasy Pokoj.
     *
     * @param maksymalnaLiczbaOsob  maksymalna liczba osób, które mogą mieszkać w danym pokoju
     * @param cenaNajmuZaDobe       cena najmu za jedną dobę danego pokoju
     * @param styl                  styl danego pokoju
     * @param kolorystyka           kolorystyka danego pokoju
     * @param kierunek              kierunek okien danego pokoju
     * @param dostepDoInternetu     czy jest dostęp do internetu w danym pokoju
     * @param numerPokoju           numer danego pokoju
     */
    public Pokoj(int maksymalnaLiczbaOsob,
          int cenaNajmuZaDobe, Styl styl,
          Kolorystyka kolorystyka,
          Kierunek kierunek,
          boolean dostepDoInternetu,
          int numerPokoju) {
        super(maksymalnaLiczbaOsob,
                cenaNajmuZaDobe,
                styl,
                kolorystyka,
                kierunek,
                dostepDoInternetu);
        this.numerPokoju = numerPokoju;
    }

    /**
     * Sprawdza czy pokój jest dostępny w danym terminie.
     *
     * @param pierwszyDzienPobytu   pierwszy dzień zakwaterowania, dla którego dostępność jest
     *                              sprawdzana
     * @param dlugoscPobytu         długość zakwaterowania, dla którego dostępność jest sprawdzana
     * @return                      czy pokój jest dostępny w danym terminie
     */
    public boolean czyDostepnyTermin(Date pierwszyDzienPobytu,
                                     int dlugoscPobytu) {
        return listaRezerwacji.czyDostepnyTermin(pierwszyDzienPobytu, dlugoscPobytu);
    }

    /**
     * Dodaje termin pobytu do listy rezerwacji danego pokoju.
     *
     * @param pierwszyDzienPobytu   pierwszy dzień zaktwaterowania rezerwacji, która jest
     *                              dodawana do listy rezerwacji danego pokoju
     * @param dlugoscPobytu         okres trwania danej rezerwacji, która jest
     *                              dodawana do listy rezerwacji danego pokoju
     */
    public void dodajTerminPobytu(Date pierwszyDzienPobytu,
                                  int dlugoscPobytu) {
        listaRezerwacji.dodajTerminPobytu(pierwszyDzienPobytu, dlugoscPobytu);
    }

    /**
     * Getter numeru pokoju.
     *
     * @return numer danego pokoju
     */
    public int getNumerPokoju() {
        return numerPokoju;
    }

    /**
     * Wypisuje opis danego pokoju.
     */
    public void wypiszOpis() {
        System.out.printf("Proponowany pokój: ");
        super.wypiszOpis();
    }
}
