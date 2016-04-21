package hotel;

import hotel.klient.*;
import hotel.pokoj.Ankieta;
import hotel.pokoj.OpisPokoju;
import hotel.pokoj.Pokoj;
import hotel.recepcjonista.*;

import java.util.Random;

/**
 * Klasa GeneratorDanych, generująca losowe dane zgodne ze specyfikacją danych wejściowych dla
 * danego zadania.
 */
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

    /**
     * Generuje losową tablicę pokoi wypełnioną pokojami, których parametry są losowe.
     *
     * @param liczbaPokoi           liczba pokoi, które mają zostać wygenerowane
     * @return                      losowa tablica pokoi
     */
    public Pokoj[] generujTablicePokoi(int liczbaPokoi) {
        Pokoj[] tablicaPokoi = new Pokoj[liczbaPokoi];

        for (int i = 0; i < liczbaPokoi; i++) {
            LosowyEnum<OpisPokoju.Styl> losowyStyl = new LosowyEnum<>(OpisPokoju.Styl.class);
            LosowyEnum<OpisPokoju.Kolorystyka> losowaKolorystyka = new LosowyEnum<>(OpisPokoju.Kolorystyka.class);
            LosowyEnum<OpisPokoju.Kierunek> losowyKierunek = new LosowyEnum<>(OpisPokoju.Kierunek.class);

            // Losuje pokoj dla parametrow z zakresu:
            // Liczba osob: 1 - 7
            // Cena: 400 - 2900 (krok co 50)
            // Styl, Kolorystyka, Kierunek - wszystkie dostępne możliwości
            Pokoj nowyPokoj = new Pokoj(generatorLosowy.nextInt(6) + 1,
                                        generatorLosowy.nextInt(50) * 50 + 400,
                                        losowyStyl.losuj(),
                                        losowaKolorystyka.losuj(),
                                        losowyKierunek.losuj(),
                                        generatorLosowy.nextBoolean(), i + 1);

            tablicaPokoi[i] = nowyPokoj;
        }

        return tablicaPokoi;
    }

    /**
     * Generuje losową tablice ankiet, wypełnioną ankietami, których preferencje są losowe.
     *
     * @param liczbaAnkiet          liczba ankiet, które mają zostać wygenerowane
     * @return                      losowa tablica ankiet
     */
    public Ankieta[] generujTabliceAnkiet(int liczbaAnkiet) {
        Ankieta[] tablicaAnkiet = new Ankieta[liczbaAnkiet];

        for (int i = 0; i < liczbaAnkiet; i++) {
            LosowyEnum<OpisPokoju.Styl> losowyStyl = new LosowyEnum<>(OpisPokoju.Styl.class);
            LosowyEnum<OpisPokoju.Kolorystyka> losowaKolorystyka = new LosowyEnum<>(OpisPokoju.Kolorystyka.class);
            LosowyEnum<OpisPokoju.Kierunek> losowyKierunek = new LosowyEnum<>(OpisPokoju.Kierunek.class);
            String losowaData = losujDate();

            // Losuje pokoj dla parametrow z zakresu:
            // Liczba osob: 1 - 7
            // Cena: 400 - 2900 (krok co 50)
            // Styl, Kolorystyka, Kierunek - wszystkie dostępne możliwości
            // Data: 1-31 Maja 2016
            // Dlugość pobytu: 1 - 14
            Ankieta nowaAnkieta = new Ankieta(generatorLosowy.nextInt(6) + 1,
                                              generatorLosowy.nextInt(50) * 50 + 400,
                                              losowyStyl.losuj(),
                                              losowaKolorystyka.losuj(),
                                              losowyKierunek.losuj(),
                                              generatorLosowy.nextBoolean(),
                                              losowaData,
                                              generatorLosowy.nextInt(14) + 1);
            tablicaAnkiet[i] = nowaAnkieta;
        }

        return tablicaAnkiet;
    }

    /**
     * Generuje losową tablicę recepcjonistów, zawierającą recepcjonistów o losowych strategiach.
     * Imiona wygenerowanych recepcjonistów to "Recepcjonista", a ich nazwiska to: "StrategiaXX",
     * gdzie XX to jego liczba porządkowa, w kolejności wylosowania względem innych
     * recepcjonistów o tej samej strategii.
     *
     * @param liczbaRecepcjonistow  liczba recepcjonistów, którzy mają zostać wygenerowani
     * @return                      losowa talblica recepcjonistow
     */
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
            identyfikatorWylosowanego = generatorLosowy.nextInt(LICZBA_TYPOW_RECEPCJONISTOW);

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

    /**
     * Generuje losową tablicę klientów, zawierającą klientów o losowych strategiach.
     * Imiona wygenerowanych klientów to "Klient", a ich nazwiska to: "StrategiaXX",
     * gdzie XX to jego liczba porządkowa, w kolejności wylosowania względem innych
     * klientów o tej samej strategii.
     *
     * @param liczbaKlientow        liczba klientów, którzy mają zostać wygenerowani
     * @return                      losowa talblica klientów
     */
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

    /**
     * Generuje losową tablicę zamówień, zawierającą losowe ankiety oraz losowych klientów.
     *
     * @param liczbaZamowien        liczba ankiet, która ma zostać wygenerowana
     * @return                      losowa tablica zamówień
     */
    public Zamowienie[] generujTabliceZamowien(int liczbaZamowien) {
        Zamowienie[] tablicaZamowien = new Zamowienie[liczbaZamowien];
        Klient[] tablicaKlientow = generujTabliceKlientow(liczbaZamowien);
        Ankieta[] tablicaAnkiet = generujTabliceAnkiet(liczbaZamowien);

        for (int i = 0; i < liczbaZamowien; i++) {
            tablicaZamowien[i] = new Zamowienie(tablicaKlientow[i], tablicaAnkiet[i]);
        }

        return tablicaZamowien;
    }

    /**
     * Klasa LosowyEnum, losująca enumerator.
     *
     * @param <E>                   enumerator, który ma zostać wylosowany
     */
    private static class LosowyEnum<E extends Enum> {
        private static final Random RND = new Random();
        private final E[] values;

        /**
         * Konstruktor klasy LosowyEnum.
         *
         * @param                   token tokeny enumeratora
         */
        public LosowyEnum(Class<E> token) {
            values = token.getEnumConstants();
        }

        /**
         * Generuje losowy enumerator.
         *
         * @return                  losowy enumerator
         */
        public E losuj() {
            return values[RND.nextInt(values.length)];
        }
    }

    /**
     * Generuje losową datę z zakresu 1-31 Maja 2016 roku.
     *
     * @return                      string zawierający losową datę
     */
    private String losujDate() {
        int dzien = generatorLosowy.nextInt(30) + 1;

        return (dzien  < 10 ? "0" : "") + dzien + "-05-2016";
    }

    /**
     * Generuje losową datę rozpoczynającą się po 2016 roku włącznie.
     *
     * @param liczbaLat             liczba lat, na przestrzeni których ma zostać wykonane losowanie.
     * @return                      string zawierający losową datę
     */
    private String losujDateZakresLat(int liczbaLat) {
        int dzien = generatorLosowy.nextInt(30) + 1;
        int miesiac = generatorLosowy.nextInt(12) + 1;
        int rok = generatorLosowy.nextInt(liczbaLat) + 2016;

        return (dzien < 10 ? "0" : "") + dzien + "-" + (miesiac < 10 ? "0" : "") + miesiac + "-" + rok;
    }
}
