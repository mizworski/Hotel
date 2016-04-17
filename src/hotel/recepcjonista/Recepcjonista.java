package hotel.recepcjonista;

import hotel.Osoba;
import hotel.Zamowienie;
import hotel.klient.Klient;
import hotel.pokoj.Pokoj;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class Recepcjonista extends Osoba {
    final static int LIMIT_PONOWNYCH_ROZPATRYWAN = 3;

    Recepcjonista(String imie, String nazwisko) {
        super(imie, nazwisko);
        this.etykieta = "Recepcjonista";
    }

    public void obsluzZamowienie(Queue<Zamowienie> kolejkaZamowien,
                                 List<Pokoj> listaPokoi) {
        Zamowienie obecneZamowienie;
        Klient obecnyKlient;
        List<Pokoj> listaWolnychPokoi;
        Pokoj propozycjaPokoju;
        boolean czyAkceptuje;

        obecneZamowienie = kolejkaZamowien.poll();
        listaWolnychPokoi = getListaWolnychPokoi(obecneZamowienie, listaPokoi);

        if (!listaWolnychPokoi.isEmpty()) {
            obecnyKlient = obecneZamowienie.getKlient();
            propozycjaPokoju = wybierzPokoj(obecneZamowienie, listaWolnychPokoi);

            if (propozycjaPokoju != null) {
                wypiszPropozycje(obecneZamowienie, propozycjaPokoju);

                czyAkceptuje =
                        obecnyKlient.czyAkceptujeZamowienie(propozycjaPokoju, obecneZamowienie.getAnkieta());

                if (czyAkceptuje) {
                    System.out.println("tak");
                    propozycjaPokoju.dodajTerminPobytu(obecneZamowienie.getAnkieta()
                            .getPierwszyDzienPobytu(), obecneZamowienie.getAnkieta().getDlugoscPobytu());
                } else {
                    System.out.println("nie");
                    obecneZamowienie.setLicznikOdrucen();
                    if (obecneZamowienie.getLicznikOdrucen() <
                            LIMIT_PONOWNYCH_ROZPATRYWAN) {
                        kolejkaZamowien.add(obecneZamowienie);
                    }
                }
            }
        }
}

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

    abstract Pokoj wybierzPokoj(Zamowienie obecneZamowienie,
                                List<Pokoj> listaPokoi);

    private void wypiszPropozycje(Zamowienie zamowienie,
                                  Pokoj propozycjaPokoju) {
        this.wypiszWizytowke();
        zamowienie.getAnkieta().wypiszOpis();
        propozycjaPokoju.wypiszOpis();
        zamowienie.getKlient().wypiszWizytowke();
    }
}
