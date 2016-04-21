package hotel.recepcjonista;

import hotel.Zamowienie;
import hotel.pokoj.Pokoj;

import java.util.List;

/**
 * Klasa RecepcjonistaPerfekcjonista, reprezentująca recepcjonistę posługującego się strategią
 * perfekcjonistyczną.
 */
public class RecepcjonistaPerfekcjonista extends Recepcjonista {
    /**
     * Konstruktor klasy RecepcjonistaPerfekcjonista.
     *
     * @param imie              niepusty string przechowujący imie recepcjonisty
     * @param nazwisko          niepusty string przechowujący nazwisko recepcjonisty
     */
    public RecepcjonistaPerfekcjonista(String imie,
                                       String nazwisko) {
        super(imie, nazwisko);
        this.strategia = "perfekcjonistyczna";
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
        boolean znalezionoPokoj = false;
        Pokoj propozycjaPokoju = null;
        for (Pokoj pokoj: listaPokoi) {
            if (liczbaSpelnionychWymagan(pokoj, obecneZamowienie.getAnkieta()) == LICZBA_WSZYSTKICH_WYMAGAN) {
                return pokoj;
            }
        }

        // W przypadku znalezienia ofery, nie znajdziemy już innej, lepszej oferty, zatem
        // propozycja nigdy nie zostanie nadpisana.
        // Ze względu na to, że iterujemy po pokojach, które są uporządkowane rosnąco ze względu
        // na swój numer pokoju, w przypadku wystąpienia dwóch równie dobrych ofert, wybierzemy
        // tę z niższym numerem pokoju.
        for (int numerPokoju = 0; !znalezionoPokoj && numerPokoju < listaPokoi.size(); numerPokoju++) {
            if (liczbaSpelnionychWymagan(listaPokoi.get(numerPokoju),
                    obecneZamowienie.getAnkieta()) == LICZBA_WSZYSTKICH_WYMAGAN) {
                znalezionoPokoj = true;
                propozycjaPokoju = listaPokoi.get(numerPokoju);
            }
        }

        return propozycjaPokoju;
    }
}
