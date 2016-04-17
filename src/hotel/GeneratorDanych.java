package hotel;

import hotel.klient.*;
import hotel.pokoj.Ankieta;
import hotel.pokoj.OpisPokoju;
import hotel.pokoj.Pokoj;
import hotel.recepcjonista.*;

import java.util.Random;
import java.util.StringJoiner;

public class GeneratorDanych {
    Random generatorLosowy = new Random();
    final static int LICZBA_TYPOW_RECEPCJONISTOW = 4;
    final static int LICZBA_TYPOW_KLIENTOW = 5;

    final static int ID_PERFEKCJONISTA = 0;
    final static int ID_APROKSYMACYJNY = 1;
    final static int ID_LOSOWY = 2;
    final static int ID_ZLOSIWY = 3;

    final static int ID_BUDZETOWY = 1;
    final static int ID_POLOWICZNY = 2;
    final static int ID_UGODOWY = 3;
    final static int ID_WIDOKOWY = 4;

    public Pokoj[] generujTablicePokoi(int liczbaPokoi) {
        Pokoj[] tablicaPokoi = new Pokoj[liczbaPokoi];

        for (int i = 0; i < liczbaPokoi; i++) {
            LosowyEnum<OpisPokoju.Styl> losowyStyl = new LosowyEnum<>
                    (OpisPokoju.Styl.class);
            LosowyEnum<OpisPokoju.Kolorystyka> losowaKolorystyka = new LosowyEnum<>
                    (OpisPokoju.Kolorystyka.class);
            LosowyEnum<OpisPokoju.Kierunek> losowyKierunek = new LosowyEnum<>
                    (OpisPokoju.Kierunek.class);
            Pokoj nowyPokoj =
                    new Pokoj(generatorLosowy.nextInt(10) + 1,
                            generatorLosowy.nextInt(20) * 100, losowyStyl.losuj(),
                            losowaKolorystyka.losuj(), losowyKierunek.losuj(),
                            generatorLosowy.nextBoolean(), i + 1);
            tablicaPokoi[i] = nowyPokoj;
        }

        return tablicaPokoi;
    }

    public Ankieta[] generujTabliceAnkiet(int liczbaAnkiet) {
        Ankieta[] tablicaAnkiet = new Ankieta[liczbaAnkiet];

        for (int i = 0; i < liczbaAnkiet; i++) {
            LosowyEnum<OpisPokoju.Styl> losowyStyl =
                    new LosowyEnum<>(OpisPokoju.Styl.class);
            LosowyEnum<OpisPokoju.Kolorystyka> losowaKolorystyka =
                    new LosowyEnum<>(OpisPokoju.Kolorystyka.class);
            LosowyEnum<OpisPokoju.Kierunek> losowyKierunek =
                    new LosowyEnum<>(OpisPokoju.Kierunek.class);
            String losowaData = losujDate();

            Ankieta nowaAnkieta =
                    new Ankieta(generatorLosowy.nextInt(10) + 1,
                            generatorLosowy.nextInt(20) * 100, losowyStyl.losuj(),
                            losowaKolorystyka.losuj(), losowyKierunek.losuj(),
                            generatorLosowy.nextBoolean(), losowaData,
                            generatorLosowy.nextInt(14) + 1);
            tablicaAnkiet[i] = nowaAnkieta;
        }

        return tablicaAnkiet;
    }

    public Recepcjonista[] generujTabliceRecepcjonistow(int liczbaRecepcjonistow) {
        int liczbaPerfekcjonistow = 0;
        int liczbaAproksymacyjnych = 0;
        int liczbaLosowych = 0;
        int liczbaZlosliwych = 0;
        int identyfikatorWylosowanego;
        Recepcjonista[] tablicaRecepcjonistow =
                new Recepcjonista[liczbaRecepcjonistow];

        for (int i = 0; i < liczbaRecepcjonistow; i++) {
            Recepcjonista nowyRecepcjonista;
            identyfikatorWylosowanego =
                    generatorLosowy.nextInt(LICZBA_TYPOW_RECEPCJONISTOW);

            switch (identyfikatorWylosowanego) {
                case ID_PERFEKCJONISTA:
                    nowyRecepcjonista =
                            new RecepcjonistaPerfekcjonista("Recepcjonista", "Perfekcjonista" + ++liczbaPerfekcjonistow);
                    break;
                case ID_APROKSYMACYJNY:
                    nowyRecepcjonista =
                            new Aproksymacyjny("Recepcjonista", "Aproksymacyjny" + ++liczbaAproksymacyjnych);
                    break;
                case ID_LOSOWY:
                    nowyRecepcjonista =
                            new Losowy("Recepcjonista", "Losowy" + ++liczbaLosowych);
                    break;
                case ID_ZLOSIWY:
                    nowyRecepcjonista =
                            new Zlosliwy("Recepcjonista", "Zlosliwy" + ++liczbaZlosliwych);
                    break;
                default:
                    nowyRecepcjonista = null;
                    break;
            }
            tablicaRecepcjonistow[i] = nowyRecepcjonista;
        }

        return tablicaRecepcjonistow;
    }

    public Klient[] generujTabliceKlientow(int liczbaKlientow) {
        int liczbaPerfekcjonistow = 0;
        int liczbaBudzetowych = 0;
        int liczbaPolowicznych = 0;
        int liczbaUgodowych = 0;
        int liczbaWidokowych = 0;
        int identyfikatorWylosowanego;
        Klient[] tablicaKlientow =
                new Klient[liczbaKlientow];

        for (int i = 0; i < liczbaKlientow; i++) {
            Klient nowyKlient;
            identyfikatorWylosowanego =
                    generatorLosowy.nextInt(LICZBA_TYPOW_KLIENTOW);

            switch (identyfikatorWylosowanego) {
                case ID_PERFEKCJONISTA:
                    nowyKlient =
                            new KlientPerfekcjonista("Klient", "Perfekcjonista" + ++liczbaPerfekcjonistow);
                    break;
                case ID_BUDZETOWY:
                    nowyKlient =
                            new Budzetowy("Klient", "Budżetowy" + ++liczbaBudzetowych);
                    break;
                case ID_POLOWICZNY:
                    nowyKlient =
                            new Polowiczny("Klient", "Połowiczny" + ++liczbaPolowicznych);
                    break;
                case ID_UGODOWY:
                    nowyKlient =
                            new Ugodowy("Klient", "Ugodowy" + ++liczbaUgodowych);
                    break;
                case ID_WIDOKOWY:
                    nowyKlient =
                            new Widokowy("Klient", "Widokowy" + ++liczbaWidokowych);
                    break;
                default:
                    nowyKlient = null;
                    break;
            }
            tablicaKlientow[i] = nowyKlient;
        }

        return tablicaKlientow;
    }

    public Zamowienie[] generujTabliceZamowien(int liczbaZamowien) {
        Zamowienie[] tablicaZamowien = new Zamowienie[liczbaZamowien];
        Klient[] tablicaKlientow = generujTabliceKlientow(liczbaZamowien);
        Ankieta[] tablicaAnkiet = generujTabliceAnkiet(liczbaZamowien);

        for (int i = 0; i < liczbaZamowien; i++) {
            tablicaZamowien[i] = new Zamowienie(tablicaKlientow[i], tablicaAnkiet[i]);
        }

/*
        // Alternatywnie
        Hotel hotel = new Hotel(null, null);
        for (int i = 0; i < liczbaZamowien; i++) {
            tablicaZamowien[i] = hotel.przyjmijZamowienie(tablicaKlientow[i], tablicaAnkiet[i]);
        }
*/

        return tablicaZamowien;
    }

    private static class LosowyEnum<E extends Enum> {
        private static final Random RND = new Random();
        private final E[] values;

        public LosowyEnum(Class<E> token) {
            values = token.getEnumConstants();
        }

        public E losuj() {
            return values[RND.nextInt(values.length)];
        }
    }

    private String losujDate() {
        int dzien = generatorLosowy.nextInt(30) + 1;

        return (dzien  < 10 ? "0" : "") + dzien + "-05-2016";
    }

    private String losujDateZakresLat(int liczbaLat) {
        int dzien = generatorLosowy.nextInt(30) + 1;
        int miesiac = generatorLosowy.nextInt(12) + 1;
        int rok = generatorLosowy.nextInt(liczbaLat) + 2016;

        return (dzien < 10 ? "0" : "") + dzien + "-" +
                (miesiac < 10 ? "0" : "") + miesiac + "-" + rok;
    }
}
