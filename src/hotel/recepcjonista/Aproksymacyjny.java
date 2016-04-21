package hotel.recepcjonista;

import hotel.Zamowienie;
import hotel.pokoj.Pokoj;

import java.util.List;

/**
 * Klasa Aproksymacyjny, reprezentująca recepcjonistę posługującego się strategią aproksymacyjną.
 */
public class Aproksymacyjny extends Recepcjonista{
    /**
     * Konstruktor klasy Aproksymacyjny.
     *
     * @param imie              niepusty string przechowujący imie recepcjonisty
     * @param nazwisko          niepusty string przechowujący nazwisko recepcjonisty
     */
    public Aproksymacyjny(String imie, String nazwisko) {
        super(imie, nazwisko);
        this.strategia = "aproksymacyjna";
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
        int maksymalnaIloscSpelnionychWymagan = 0;
        int najwyzszaCenaSposrodWybranych = -1;

        for (Pokoj pokoj : listaPokoi) {
            int obecnaLiczbaSpelnionychWymagan = liczbaSpelnionychWymagan(pokoj, obecneZamowienie.getAnkieta());

            // W przypadku znalezienia ofery, która spełnia tę samą liczbę wymagań, propozycja nie
            // zostanie nadpisana. Jest ona nadpisywana jedynie gdy znaleziona zostaje lepsza oferta.
            // Ze względu na to, że iterujemy po pokojach, które są uporządkowane rosnąco ze względu
            // na swój numer pokoju, w przypadku wystąpienia dwóch równie dobrych ofert, wybierzemy
            // tę z niższym numerem pokoju.
            if (obecnaLiczbaSpelnionychWymagan >= maksymalnaIloscSpelnionychWymagan &&
                    pokoj.getCenaNajmuZaDobe() > najwyzszaCenaSposrodWybranych) {
                maksymalnaIloscSpelnionychWymagan = obecnaLiczbaSpelnionychWymagan;
                najwyzszaCenaSposrodWybranych = pokoj.getCenaNajmuZaDobe();
                propozycjaPokoju = pokoj;
            }
        }

        return propozycjaPokoju;
    }
}
