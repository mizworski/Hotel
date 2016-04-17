package hotel.recepcjonista;

import hotel.Zamowienie;
import hotel.pokoj.Pokoj;

import java.util.List;

public class RecepcjonistaPerfekcjonista extends Recepcjonista {
    public RecepcjonistaPerfekcjonista(String imie,
                                       String nazwisko) {
        super(imie, nazwisko);
        this.strategia = "perfekcjonistyczna";
    }

    @Override
    Pokoj wybierzPokoj(Zamowienie obecneZamowienie,
                       List<Pokoj> listaPokoi) {
        boolean znalezionoPokoj = false;
        Pokoj propozycjaPokoju = null;
        for (Pokoj pokoj: listaPokoi) {
            if (liczbaSpelnionychWymagan(pokoj, obecneZamowienie.getAnkieta()) ==
                    LICZBA_WSZYSTKICH_WYMAGAN) {
                return pokoj;
            }
        }

        // Alternatywnie

        for (int numerPokoju = 0; !znalezionoPokoj &&
                numerPokoju < listaPokoi.size(); numerPokoju++) {
            if (liczbaSpelnionychWymagan(listaPokoi.get(numerPokoju),
                    obecneZamowienie.getAnkieta()) == LICZBA_WSZYSTKICH_WYMAGAN) {
                znalezionoPokoj = true;
                propozycjaPokoju = listaPokoi.get(numerPokoju);
            }
        }

        return propozycjaPokoju;
    }
}
