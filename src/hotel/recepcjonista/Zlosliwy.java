package hotel.recepcjonista;

import hotel.Zamowienie;
import hotel.pokoj.Pokoj;

import java.util.List;

/**
 * Klasa Zlosliwy, reprezentująca recepcjonistę posługującego się strategią złośliwą.
 */
public class Zlosliwy extends Recepcjonista {
    /**
     * Konstruktor klasy Zlosliwy.
     *
     * @param imie              niepusty string przechowujący imie recepcjonisty
     * @param nazwisko          niepusty string przechowujący nazwisko recepcjonisty
     */
    public Zlosliwy(String imie, String nazwisko) {
        super(imie, nazwisko);
        this.strategia = "zlosliwa";
    }

    /**
     * Wybiera pokój z listy pokoi zgodnie ze strategią danego recepcjonisty dla ankiety podanej w
     * zamówieniu.
     *
     * @param obecneZamowienie  zamówienie, dla którego wybierany jest pokój
     * @param listaPokoi        lista pokoi, z których wybierany jest pokój
     * @return                  propozycje pokoju zgodną ze strategią recepcjonisty dla
     *                          preferencji ankiety w zamówieniu
     */
    @Override
    Pokoj wybierzPokoj(Zamowienie obecneZamowienie,
                       List<Pokoj> listaPokoi) {
        Pokoj propozycjaPokoju = null;
        int minimalnaIloscSpelnionychWymagan = LICZBA_WSZYSTKICH_WYMAGAN + 1;
        int najwyzszaCenaSposrodWybranych = -1;


        for (Pokoj pokoj : listaPokoi) {
            int obecnaLiczbaSpelnionychWymagan = liczbaSpelnionychWymagan(pokoj, obecneZamowienie.getAnkieta());

            // W przypadku znalezienia nielepszej oferty, propozycja pokoju zostaje nadpisana. Ze
            // względu na to, że iterujemy po pokojach, które są uporządkowane rosnąco ze względu
            // na swój numer pokoju, to w efekcie uzyskamy najgorszą opcje lub w przypadku
            // wystąpienia dwóch spełniających tę samą ilość wymagań → tę z wyszszym numerem pokoju.
            if (obecnaLiczbaSpelnionychWymagan <= minimalnaIloscSpelnionychWymagan &&
                    pokoj.getCenaNajmuZaDobe() >= najwyzszaCenaSposrodWybranych) {
                minimalnaIloscSpelnionychWymagan = obecnaLiczbaSpelnionychWymagan;
                najwyzszaCenaSposrodWybranych = pokoj.getCenaNajmuZaDobe();
                propozycjaPokoju = pokoj;
            }
        }

        return propozycjaPokoju;
    }
}
