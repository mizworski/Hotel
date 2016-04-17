package hotel.recepcjonista;

import hotel.Zamowienie;
import hotel.pokoj.Pokoj;

import java.util.List;

public class Zlosliwy extends Recepcjonista {
    public Zlosliwy(String imie, String nazwisko) {
        super(imie, nazwisko);
        this.strategia = "zlosliwa";
    }

    @Override
    Pokoj wybierzPokoj(Zamowienie obecneZamowienie,
                       List<Pokoj> listaPokoi) {
        Pokoj propozycjaPokoju = null;
        int minimalnaIloscSpelnionychWymagan = LICZBA_WSZYSTKICH_WYMAGAN + 1;
        int najwyzszaCenaSposrodWybranych = -1;

        // Alternatywnie

        for (Pokoj pokoj : listaPokoi) {
            int obecnaLiczbaSpelnionychWymagan =
                    liczbaSpelnionychWymagan(pokoj,
                            obecneZamowienie.getAnkieta());
            if (obecnaLiczbaSpelnionychWymagan <=
                    minimalnaIloscSpelnionychWymagan &&
                    pokoj.getCenaNajmu() >= najwyzszaCenaSposrodWybranych) {
                minimalnaIloscSpelnionychWymagan =
                        obecnaLiczbaSpelnionychWymagan;
                najwyzszaCenaSposrodWybranych =
                        pokoj.getCenaNajmu();
                propozycjaPokoju = pokoj;
            }
        }

        return propozycjaPokoju;
    }
}
