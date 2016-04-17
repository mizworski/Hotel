package hotel.recepcjonista;

import hotel.Zamowienie;
import hotel.pokoj.Pokoj;

import java.util.List;

public class Aproksymacyjny extends Recepcjonista{
    public Aproksymacyjny(String imie, String nazwisko) {
        super(imie, nazwisko);
        this.strategia = "aproksymacyjna";
    }

    @Override
    Pokoj wybierzPokoj(Zamowienie obecneZamowienie,
                       List<Pokoj> listaPokoi) {
        Pokoj propozycjaPokoju = null;
        int maksymalnaIloscSpelnionychWymagan = 0;
        int najwyzszaCenaSposrodWybranych = -1;

/*
        for (int numerPokoju = 0; numerPokoju < listaPokoi.size(); numerPokoju++) {
            if (liczbaSpelnionychWymagan(listaPokoi.get(numerPokoju),
                    obecneZamowienie.getAnkieta()) >=
                    maksymalnaIloscSpelnionychWymagan &&
                    listaPokoi.get(numerPokoju).getCenaNajmu() >
                            najwyzszaCenaSposrodWybranych) {
                maksymalnaIloscSpelnionychWymagan =
                        liczbaSpelnionychWymagan(listaPokoi.get(numerPokoju),
                                obecneZamowienie.getAnkieta());
                najwyzszaCenaSposrodWybranych =
                        listaPokoi.get(numerPokoju).getCenaNajmu();
                propozycjaPokoju = listaPokoi.get(numerPokoju);
            }
        }
*/

        // Alternatywnie

        for (Pokoj pokoj : listaPokoi) {
            int obecnaLiczbaSpelnionychWymagan =
                    liczbaSpelnionychWymagan(pokoj,
                            obecneZamowienie.getAnkieta());
            if (obecnaLiczbaSpelnionychWymagan >=
                    maksymalnaIloscSpelnionychWymagan &&
                    pokoj.getCenaNajmu() > najwyzszaCenaSposrodWybranych) {
                maksymalnaIloscSpelnionychWymagan =
                        obecnaLiczbaSpelnionychWymagan;
                najwyzszaCenaSposrodWybranych =
                        pokoj.getCenaNajmu();
                propozycjaPokoju = pokoj;
            }
        }

        return propozycjaPokoju;
    }
}
