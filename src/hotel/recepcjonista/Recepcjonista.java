package hotel.recepcjonista;

import hotel.Osoba;
import hotel.Zamowienie;
import hotel.klient.Klient;
import hotel.pokoj.Pokoj;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Abstrakcyjna klasa Recepcjonista, która jest nadklasą wszystkich strategii recepcjonistów.
 * Dodatkowo przechowuje etykietę recepcjonistów.
 */
public abstract class Recepcjonista extends Osoba {
    final static int LIMIT_PONOWNYCH_ROZPATRZEN = 3;

    /**
     * Konstruktor klasy Recepcjonista.
     *
     * @param imie              niepusty string przechowujący imie recepcjonisty
     * @param nazwisko          niepusty string przechowujący nazwisko recepcjonisty
     */
    Recepcjonista(String imie, String nazwisko) {
        super(imie, nazwisko);
        this.etykieta = "Recepcjonista";
    }

    /**
     * Obsługuje pierwsze zamówienie z kolejki zamówień, korzystając z pokoi, które podane są
     * jako lista pokoi.
     *
     * @param kolejkaZamowien   kolejka zamówień
     * @param listaPokoi        lista pokoi, które można zaproponować
     */
    public void obsluzZamowienie(Queue<Zamowienie> kolejkaZamowien,
                                 List<Pokoj> listaPokoi) {
        Zamowienie obecneZamowienie;
        Klient obecnyKlient;
        List<Pokoj> listaWolnychPokoi;
        Pokoj propozycjaPokoju;
        boolean czyAkceptuje;

        obecneZamowienie = kolejkaZamowien.poll();

        // Wyznacza listę wolnych pokoi dla danego terminiu zamówienia.
        listaWolnychPokoi = getListaWolnychPokoi(obecneZamowienie, listaPokoi);

        if (!listaWolnychPokoi.isEmpty()) {
            obecnyKlient = obecneZamowienie.getKlient();
            propozycjaPokoju = wybierzPokoj(obecneZamowienie, listaWolnychPokoi);

            // Jeśli znaleziono jakikolwiek pokój dostępny w terminie, to wykonywane są dalsze
            // czynności (w szczególności wrzucenie zamówienia z powrotem do kolejki, jeśli
            // propozycja zostanie odrzucona).
            // W przeciwnym wypadku zamówienie nawet nie jest wrzucane z powrotem do kolejki
            if (propozycjaPokoju != null) {
                wypiszPropozycje(obecneZamowienie, propozycjaPokoju);

                czyAkceptuje =
                        obecnyKlient.czyAkceptujeZamowienie(propozycjaPokoju, obecneZamowienie.getAnkieta());

                if (czyAkceptuje) {
                    System.out.println("tak");
                    propozycjaPokoju.dodajTerminPobytu(obecneZamowienie.getAnkieta().getPierwszyDzienPobytu(),
                                                       obecneZamowienie.getAnkieta().getDlugoscPobytu());
                } else {
                    System.out.println("nie");
                    obecneZamowienie.setLicznikOdrucen();

                    // Wrzuca ponownie do kolejki, jeśli zamówienie nie przekroczyło limitu
                    // ponownych rozparzeń.
                    if (obecneZamowienie.getLicznikOdrucen() < LIMIT_PONOWNYCH_ROZPATRZEN) {
                        kolejkaZamowien.add(obecneZamowienie);
                    }
                }
                System.out.println(""); // Wolna linia po każdym komunikacie.
            }
        }
    }

    /**
     * Wyznacza listę wolnych pokoi dostępnych dla podanego w zamówieniu terminu.
     *
     * @param zamowienie        zamówienie, którego termin jest sprawdzany
     * @param listaPokoi        lista pokoi, z których wybierane są wolne
     * @return                  lista pokoi wolnych w danym terminie
     */
    private List<Pokoj> getListaWolnychPokoi(Zamowienie zamowienie,
                                             List<Pokoj> listaPokoi) {
        List<Pokoj> listaWolnychPokoi = new LinkedList<>();

        for (Pokoj pokoj: listaPokoi) {
            if (pokoj.czyDostepnyTermin(zamowienie.getAnkieta().getPierwszyDzienPobytu(),
                    zamowienie.getAnkieta().getDlugoscPobytu())) {
                listaWolnychPokoi.add(pokoj);
            }
        }

        return listaWolnychPokoi;
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
    abstract Pokoj wybierzPokoj(Zamowienie obecneZamowienie,
                                List<Pokoj> listaPokoi);

    /**
     * Wypisuje komunikat zgodny ze specyfikacją zadania.
     * Wizytówkę recepcjonisty, preferencje z ankiety, propozycję pokoju oraz wizytówkę klienta.
     *
     * @param zamowienie        zamówienie, którego preferencje ankiety oraz wizytówka klienta
     *                          są wypisywane
     * @param propozycjaPokoju  proponowany pokój
     */
    private void wypiszPropozycje(Zamowienie zamowienie,
                                  Pokoj propozycjaPokoju) {
        this.wypiszWizytowke();
        zamowienie.getAnkieta().wypiszOpis();
        propozycjaPokoju.wypiszOpis();
        zamowienie.getKlient().wypiszWizytowke();
    }
}
