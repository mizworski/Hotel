package hotel;

import hotel.klient.Klient;
import hotel.pokoj.Ankieta;
import hotel.pokoj.Pokoj;
import hotel.recepcjonista.Recepcjonista;

import java.util.*;

/**
 * Klasa Hotel zawierająca informacje o wszystkich pokojach w danym hotelu oraz o wszystkich
 * recepcjonistach pracujących w tym hotelu.
 *
 * Pozwala na przyjmowanie zamówień oraz akceptowanie danych zamówień na podstawie dostępności
 * pokoi w danym terminie przy użyciu dostępnych recepcjonistów.
 */
public class Hotel {
    private Pokoj[] tablicaPokoi;
    private Recepcjonista[] tablicaRecepcjonistow;

    /**
     * Konstruktor klasy Hotel.
     *
     * @param tablicaPokoi              tablica elementów typu Pokoj, reprezentuje wszystkie pokoje,
     *                                  danego hotelu
     * @param recepcjonisci             tablica elementów typu Recepcjonista, reprezentuje wszytkich
     *                                  recepcjonistów, którzy pracują w danym  hotelu
     */
    Hotel(Pokoj[] tablicaPokoi,
          Recepcjonista[] recepcjonisci) {
        this.tablicaPokoi = tablicaPokoi.clone();
        this.tablicaRecepcjonistow = recepcjonisci.clone();
    }

    /**
     * Rozpatruje podane zamówienia przy wykorzystaniu pokoi oraz recepcjonistów podanych jako
     * argumenty.
     *
     * @param tablicaZamowien           tablica wszystkich zamówień, które powinny zostać rozpatrzone
     * @param tablicaPokoi              tablica elementów typu Pokoj, która zawiera pokoje, które
     *                                  powinny zostać uwzględnione podczas rozpatrywania zamówień
     * @param tablicaRecepcjonistow     tablica elementów typu Recepcjonista, która zawiera
     *                                  recepcjonistów, którzy powinni rozpatrzeć zamówienia
     */
    public void akceptuj(Zamowienie[] tablicaZamowien,
                         Pokoj[] tablicaPokoi,
                         Recepcjonista[] tablicaRecepcjonistow) {
        try {
            czyLegalnaTablicaPokoi(tablicaPokoi);
        } catch (Exception e) {
            System.out.println("Błędna tablica pokoi.");
        }
        try {
            czyLegalnaTablicaRecepcjonistow(tablicaRecepcjonistow);
        } catch (Exception e) {
            System.out.println("Błędna tablica recepcjonistów.");
        }
        try {
            czyTablicaNiepusta(tablicaRecepcjonistow);
        } catch (PustaTablica pustaTablica) {
            System.out.println("Nie podano żadnego recepcjonisty.");
        }

        Queue<Zamowienie> kolejkaZamowien = new LinkedList<>();
        Queue<Recepcjonista> kolejkaRecepcjonistow = new LinkedList<>();
        List<Pokoj> listaPokoi = new LinkedList<>();

        kolejkaZamowien.addAll(Arrays.asList(tablicaZamowien));
        kolejkaRecepcjonistow.addAll(Arrays.asList(tablicaRecepcjonistow));
        listaPokoi.addAll(Arrays.asList(tablicaPokoi));

        // Obsługuj zamówienia dopóki kolejka się nie opróżni.
        // Opróżni się w skończonym czasie, gdyż podajemy skończoną liczbę zamówień, a z nich
        // każde jest obsługiwane co najwyżej 3 razy.
        // Ponadto kolejka recepcjonistów jest niepusta i nigdy się nie opróżni, po każdym
        // wykonaniu pętli, w kolejce pozostaje stała liczba recepcjonistów.
        while (!kolejkaZamowien.isEmpty()) {
            Recepcjonista obecnyRecepcjonista;
            obecnyRecepcjonista = kolejkaRecepcjonistow.poll();
            obecnyRecepcjonista.obsluzZamowienie(kolejkaZamowien, listaPokoi);
            kolejkaRecepcjonistow.add(obecnyRecepcjonista);
        }
    }

    /**
     * Zwraca nowe zamówienie z wymaganiami zawartymi w podanej ankiecie przez danego klienta.
     *
     * @param klient                    klient, który jest przypisany do danego zamówienia
     * @param ankieta                   ankieta, która jest przypisana do danego zamówienia
     * @return                          nowe zamówienie
     */
    public Zamowienie przyjmijZamowienie(Klient klient,
                                         Ankieta ankieta) {
        return new Zamowienie(klient, ankieta);
    }

    /**
     * Getter tablicy pokoi w danym hotelu.
     * @return                          tablica wszystkich pokoi w danym hotelu
     */
    public Pokoj[] getTablicaPokoi() {
        return tablicaPokoi;
    }

    /**
     * Getter tablicy recepcjonistow w danym hotelu.
     * @return                          tablica wszystkich recepcjonistow w danym hotelu
     */
    public Recepcjonista[] getTablicaRecepcjonistow() {
        return tablicaRecepcjonistow;
    }

    /**
     * Sprawdza czy dana tablica recepcjonistów jest podzbiorem tablicy recepcjonistów podanych w
     * konstruktorze klasy.
     * @param tablicaRecepcjonistow     tablica recepcjonistów do sprawdzenia
     * @throws Exception                podana tablica nie jest podzbiorem tablicy podanej w
     *                                  konstruktorze klasy
     */
    private void czyLegalnaTablicaRecepcjonistow(Recepcjonista[] tablicaRecepcjonistow) throws Exception {
        for (Recepcjonista recepcjonista : tablicaRecepcjonistow) {
            if (!czyJestRecepcjonista(recepcjonista, this.tablicaRecepcjonistow)) {
                throw new Exception();
            }
        }
    }

    /**
     * Wyjątek, gdy tablica jest pusta.
     */
    class PustaTablica extends Exception {

    }

    /**
     * Sprawdza czy podana tablica recepcjonistów jest pusta.
     * @param tablicaRecepcjonistow     sprawdzana tablica recepcjonistów
     * @throws PustaTablica             podana tablica jest pusta
     */
    private void czyTablicaNiepusta(Recepcjonista[] tablicaRecepcjonistow) throws PustaTablica {
        if (tablicaRecepcjonistow == null)
            throw new PustaTablica();
    }

    /**
     * Sprawdza czy dana tablica pokoi jest podzbiorem tablicy pokoi podanych w
     * konstruktorze klasy.
     * @param tablicaPokoi              tablica pokoi do sprawdzenia
     * @throws Exception                podana tablica nie jest podzbiorem tablicy podanej w
     *                                  konstruktorze klasy
     */
    private void czyLegalnaTablicaPokoi(Pokoj[] tablicaPokoi) throws Exception {
        for (Pokoj pokoj : tablicaPokoi) {
            if (!czyIstniejePokoj(pokoj, this.tablicaPokoi)) {
                throw new Exception();
            }
        }
    }

    /**
     * Sprawdza czy podany recepcjonista należy do podanej tablicy recepcjonistów.
     *
     * @param sprawdzanyRecepcjonista   sprawdzany recepcjonista
     * @param tablicaRecepcjonistow     tablica do której recepcjonista powinien należeć
     * @return                          czy podany recepcjonista należy do podanej tablicy
     */
    private boolean czyJestRecepcjonista(Recepcjonista sprawdzanyRecepcjonista,
                                         Recepcjonista[] tablicaRecepcjonistow) {
        boolean istnieje = false;

        for (Recepcjonista recepcjonista : tablicaRecepcjonistow) {
            if (sprawdzanyRecepcjonista.getImie().equals(recepcjonista.getImie()) &&
                sprawdzanyRecepcjonista.getNazwisko().equals(recepcjonista.getNazwisko())) {
                istnieje = true;
            }
        }

        return istnieje;
    }

    /**
     * Sprawdza czy podany pokój należy do podanej tablicy pokoi.
     *
     * @param sprawdzanyPokoj           sprawdzany pokój
     * @param tablicaPokoi              tablica do której pokój powinien należeć
     * @return                          czy podany pokój należy do podanej tablicy
     */
    private boolean czyIstniejePokoj(Pokoj sprawdzanyPokoj,
                                     Pokoj[] tablicaPokoi) {
        boolean istnieje = false;
        for (Pokoj pokoj : tablicaPokoi) {
            if (sprawdzanyPokoj.getNumerPokoju() == pokoj.getNumerPokoju()) {
                istnieje = true;
            }
        }

        return istnieje;
    }

    /**
     * Tworzy losowe talblice z danymi po czym wykonuje metode akceptuj dla losowych danych.
     */
    public static void main(String [] args) {
        int liczbaZamowien = 150;
        int liczbaPokoi = 25;
        int liczbaRecepcjonistow = 35;
        GeneratorDanych generatorDanych = new GeneratorDanych();

        Klient[] tablicaKlientow = generatorDanych.generujTabliceKlientow(liczbaZamowien);
        Ankieta[] tablicaAnkiet = generatorDanych.generujTabliceAnkiet(liczbaZamowien);
        Zamowienie[] tablicaZamowien = new Zamowienie[liczbaZamowien];
        Pokoj[] tablicaPokoi = generatorDanych.generujTablicePokoi(liczbaPokoi);
        Recepcjonista[] tablicaRecepcjonistow = generatorDanych.generujTabliceRecepcjonistow(liczbaRecepcjonistow);
        Hotel nowyHotel = new Hotel(tablicaPokoi, tablicaRecepcjonistow);

        for (int i = 0; i < liczbaZamowien; i++) {
            tablicaZamowien[i] = nowyHotel.przyjmijZamowienie(tablicaKlientow[i], tablicaAnkiet[i]);
        }

        nowyHotel.akceptuj(tablicaZamowien, nowyHotel.getTablicaPokoi(), nowyHotel.getTablicaRecepcjonistow());
    }
}
