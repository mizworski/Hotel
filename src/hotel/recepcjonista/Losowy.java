package hotel.recepcjonista;

import hotel.Zamowienie;
import hotel.pokoj.Pokoj;

import java.util.List;
import java.util.Random;

public class Losowy extends Recepcjonista {
    Random generatorLosowy = new Random();

    public Losowy(String imie, String nazwisko) {
        super(imie, nazwisko);
        this.strategia = "losowa";
    }

    @Override
    Pokoj wybierzPokoj(Zamowienie obecneZamowienie,
                       List<Pokoj> listaPokoi) {
        return listaPokoi.get(generatorLosowy.nextInt(listaPokoi.size()));
    }
}
