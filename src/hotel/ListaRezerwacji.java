package hotel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Klasa ListaRezerwacji, która zawiera wszystkie rezerwacje, w porządku chronologicznym.
 */
public class ListaRezerwacji {
    List<Rezerwacja> rezerwacje;

    /**
     * Konstruktor klasy ListaRezerwacji.
     */
    public ListaRezerwacji() {
        this.rezerwacje = new ArrayList<>();
    }

    /**
     * Sprawdza czy podany termin jest dostępny, tzn. czy podana rezerwacja koliduje z innymi,
     * już istniejącymi rezerwacjami.
     *
     * @param pierwszyDzienPobytu   pierwszy dzień rezerwacji, której dostępność jest sprawdzana
     * @param dlugoscPobytu         długość rezerwacji, której dostępność jest sprawdzana
     * @return                      true jeśli podana rezerwacja nie koliduje z innymi
     *                              istniejącymi rezerwacjami
     */
    public boolean czyDostepnyTermin(Date pierwszyDzienPobytu,
                                     int dlugoscPobytu) {
        // Zakładam, że pokój jest dostępny w danym terminie.
        boolean czyDostepny = true;

        // Indeks rezerwacji, która następuje bezpośrednio po podanej rezerwacji lub
        // rezerwacje.size(), gdy po podanej rezerwacji nie następuje żadna inna.
        int indeksRezerwacjiPo = znajdzIndeksKolejnejRezerwacji(pierwszyDzienPobytu);

        // Dzień, w którym podana rezerwacja się kończy, a pokój jest zwalniany.
        Date dzienZakonczeniaPobytu = dzienZakonczeniaPobytu(pierwszyDzienPobytu, dlugoscPobytu);

        // Sprawdzam czy po danej rezerwacji następuje inna oraz czy dzień zakończenia danej
        // rezerwacji występuje po dniu rozpoczęcia się następnej.
        // W tym wypadku pokój nie jest dostępny.
        // Leniwe sprawdzanie (inaczej drugi warunek → odwołanie się do nulla).
        if (indeksRezerwacjiPo < rezerwacje.size() &&
            dzienZakonczeniaPobytu.compareTo(rezerwacje.get(indeksRezerwacjiPo).getPoczatekPobytu())
                    > 0) {
            czyDostepny = false;
        }

        // Jeśli rezerwacja nie kończy się przed rozpoczęciem innej rezerwacji oraz nasza
        // rezerwacja nie jest najwcześniejszą rezerwacją (indeksRezerwacjiPo == 0 → żadna
        // rezerwacja nie rozpoczyna się przed podaną),
        // Sprawdzam czy nasze zamówienie nie rozpoczyna się przed zakończeniem wcześniejszej
        // rezerwacji.
        if (czyDostepny && indeksRezerwacjiPo > 0) {
            // Wyznaczam datę zakończenia rezerwacji, która odbywa się przed podaną rezerwacją.
            Date dzienZwolnieniaPokoju = dzienZakonczeniaPobytu
                        (rezerwacje.get(indeksRezerwacjiPo - 1).getPoczatekPobytu(),
                         rezerwacje.get(indeksRezerwacjiPo - 1).getDlugoscPobytu());

            if (pierwszyDzienPobytu.compareTo(dzienZwolnieniaPokoju) < 0) {
                czyDostepny = false;
            }
        }

        // Jeśli któryś z warunków został spełniony, to otrzymaliśmy sprzeczność i pokój jest
        // zajęty. W przeciwnym przypadku pokój jest wolny w danym terminie.
        return czyDostepny;
    }

    /**
     * Dodaje podaną rezerwację do listy rezerwacji.
     *
     * @param pierwszyDzienPobytu   pierwszy dzień dodawanej rezerwacji
     * @param dlugoscPobytu         długość dodawanej rezerwacji
     */
    public void dodajTerminPobytu(Date pierwszyDzienPobytu,
                                  int dlugoscPobytu) {
        Rezerwacja nowa = new Rezerwacja(pierwszyDzienPobytu, dlugoscPobytu);
        int indeksKolejnejRezerwacji = znajdzIndeksKolejnejRezerwacji(pierwszyDzienPobytu);

        if(rezerwacje.size() == 0) {
            rezerwacje.add(nowa);
        } else {
            rezerwacje.add(indeksKolejnejRezerwacji, nowa);
        }
    }

    /**
     * Zakładamy że lista rezerwacji danej klasy jest poprawnie skonstruowana, tzn. żadne dwie
     * różne rezerwacje nie kolidują ze sobą.
     *
     * Szuka indeks rezerwacji, która zaczyna się po dniu pierwszego dnia rezerwacji, podanego
     * jako argument (niekoniecznie rezerwacja, której indeks znajdziemy, rozpoczyna się po danej
     * rezerwacji, gdyż nie uwzględnia czasu trwania rezerwacji, której pierwszy dzień został
     * podany).
     *
     * Innymi słowy szukamy dwóch rezerwacji, pomiędzy których rozpoczęciami możemy wstawić nasz
     * dzień zakwaterowania, po czym zwracamy indeks późniejszej. (Z założenia istnieją zawsze
     * takie dwie, gdyż nie istnieją dwie takie rezerwacje, których długości pobytu są niezerowe
     * (a takie długości wykluczamy), które rozpoczynałyby się w ten sam dzień).
     *
     * @param pierwszyDzienPobytu   pierwszy dzień podanej rezerwacji
     * @return                      indeks rezerwacji, której dzień zaktwaterowania następuje po
     *                              dniu zaktwateriowania danej rezerwacji, której (znalezionej
     *                              rezerwacji) rezerwacja poprzedzająca rozpoczyna się przed
     *                              podaną rezerwacją.
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
                    ostatni = srodkowy - 1;
                }
            } else {
                pierwszy = srodkowy + 1;
            }
        }

        return pierwszy;
    }

    /**
     * Wyznacza dzień zakończenia rezerwacji.
     *
     * @param pierwszyDzienPobytu pierwszy dzień danej rezerwacji
     * @param dlugoscPobytu czas trwania danej rezerwacji
     * @return dzień zakończenia danej rezerwacji
     */
    private Date dzienZakonczeniaPobytu(Date pierwszyDzienPobytu,
                                        int dlugoscPobytu) {
        Calendar cal = Calendar.getInstance();

        cal.setTime(pierwszyDzienPobytu);
        cal.add(Calendar.DATE, dlugoscPobytu);
        pierwszyDzienPobytu = cal.getTime();

        return pierwszyDzienPobytu;
    }
}
