package hotel.recepcjonista;

import hotel.Zamowienie;
import hotel.pokoj.Pokoj;

import java.util.List;
import java.util.Random;

/**
 * Klasa Losowy, reprezentująca recepcjonistę posługującego się strategią losową.
 */
public class Losowy extends Recepcjonista {
    Random generatorLosowy = new Random();

    /**
     * Konstruktor klasy Losowy.
     *
     * @param imie              niepusty string przechowujący imie recepcjonisty
     * @param nazwisko          niepusty string przechowujący nazwisko recepcjonisty
     */
    public Losowy(String imie, String nazwisko) {
        super(imie, nazwisko);
        this.strategia = "losowa";
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
        return listaPokoi.get(generatorLosowy.nextInt(listaPokoi.size()));
    }
}
