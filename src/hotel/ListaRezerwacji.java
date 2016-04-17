package hotel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ListaRezerwacji {
    List<Rezerwacja> rezerwacje = new ArrayList<>();

    public boolean czyDostepnyTermin(Date pierwszyDzienPobytu,
                                     int dlugoscPobytu) {
        boolean czyDostepny = true;
        int indeksRezerwacjiPo =
                znajdzIndeksKolejnejRezerwacji(pierwszyDzienPobytu);
        Date dzienZakonczeniaPobytu =
                dzienZakonczeniaPobytu(pierwszyDzienPobytu, dlugoscPobytu);

        if (indeksRezerwacjiPo < rezerwacje.size() &&
            dzienZakonczeniaPobytu.compareTo(rezerwacje.get(indeksRezerwacjiPo).getPoczatekPobytu())
                    > 0) {
            czyDostepny = false;
        }

        if (indeksRezerwacjiPo > 0 && czyDostepny) {
            Date dzienZwolnieniaPokoju =
                dzienZakonczeniaPobytu
                        (rezerwacje.get(indeksRezerwacjiPo - 1).getPoczatekPobytu(),
                         rezerwacje.get(indeksRezerwacjiPo - 1).getDlugoscPobytu());

            if (pierwszyDzienPobytu.compareTo(dzienZwolnieniaPokoju) < 0) {
                czyDostepny = false;
            }
        }

        return czyDostepny;
    }

    public void dodajTerminPobytu(Date pierwszyDzienPobytu,
                                  int dlugoscPobytu) {
        Rezerwacja nowa = new Rezerwacja(pierwszyDzienPobytu, dlugoscPobytu);
        int indeksKolejnejRezerwacji =
                znajdzIndeksKolejnejRezerwacji(pierwszyDzienPobytu);

        if(rezerwacje.size() == 0) {
            rezerwacje.add(nowa);
        } else {
            rezerwacje.add(indeksKolejnejRezerwacji, nowa);
        }
    }

    /**
     * Zwraca indeks rezerwacji, ktora zaczyna sie po rozpoczeciu dnia
     * okreslonego w parametrze pierwszyDzienPobytu, a ktorego poprzednik jest -1 lub
     * rozpoczyna sie przed dniem okreslonym w parametrze pierwszyDzienPobytu.
     */
    private int znajdzIndeksKolejnejRezerwacji(Date pierwszyDzienPobytu) {
        int pierwszy = 0;
        int ostatni = rezerwacje.size();
        int srodkowy;

        while (pierwszy < ostatni) {
            srodkowy = pierwszy + (ostatni - pierwszy) / 2;
            if (pierwszyDzienPobytu.compareTo(rezerwacje.get(srodkowy).getPoczatekPobytu()) < 0) {
                if (srodkowy == 0 || pierwszyDzienPobytu.compareTo
                    (rezerwacje.get(srodkowy - 1).getPoczatekPobytu()) >= 0) {
                    ostatni = srodkowy;
                } else {
                    // srodkowy na pewno nie jest kolejnym wiekszym, bo
                    // srodkowy - 1 jest wiekszy
                    ostatni = srodkowy - 1;
                }
            } else {
                pierwszy = srodkowy + 1;
            }
        }

        return pierwszy;
    }

    private Date dzienZakonczeniaPobytu(Date pierwszyDzienPobytu,
                                        int dlugoscPobytu) {
        Calendar cal = Calendar.getInstance();

        cal.setTime(pierwszyDzienPobytu);
        cal.add(Calendar.DATE, dlugoscPobytu);
        pierwszyDzienPobytu = cal.getTime();

        return pierwszyDzienPobytu;
    }
}
