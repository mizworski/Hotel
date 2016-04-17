package hotel;

// TODO ogarnij czy trzeba importowac cale pakiety

import hotel.klient.Klient;
import hotel.pokoj.Ankieta;
import hotel.pokoj.Pokoj;
import hotel.recepcjonista.Recepcjonista;

import java.util.*;

public class Hotel {
    private Pokoj[] tablicaPokoi;
    private Recepcjonista[] tablicaRecepcjonistow;
    private Zamowienie[] tablicaZamowien;

    Hotel(Pokoj[] tablicaPokoi,
          Recepcjonista[] recepcjonisci) {
        this.tablicaPokoi = tablicaPokoi.clone();
        this.tablicaRecepcjonistow = recepcjonisci.clone();
    }

    public static void akceptuj(Zamowienie[] tablicaZamowien,
                                Pokoj[] tablicaPokoi,
                                Recepcjonista[] tablicaRecepcjonistow) {
        Queue<Zamowienie> kolejkaZamowien
                = new LinkedList<>();
        Queue<Recepcjonista> kolejkaRecepcjonistow
                = new LinkedList<>();
        List<Pokoj> listaPokoi = new LinkedList<>();

        kolejkaZamowien.addAll(Arrays.asList(tablicaZamowien));
        kolejkaRecepcjonistow.addAll(Arrays.asList(tablicaRecepcjonistow));
        listaPokoi.addAll(Arrays.asList(tablicaPokoi));

        while (!kolejkaZamowien.isEmpty()) {

            Recepcjonista obecnyRecepcjonista;

            obecnyRecepcjonista = kolejkaRecepcjonistow.poll();

            obecnyRecepcjonista.obsluzZamowienie(kolejkaZamowien, listaPokoi);

            kolejkaRecepcjonistow.add(obecnyRecepcjonista);
        }
    }

    public static Zamowienie przyjmijZamowienie(Klient klient,
                                         Ankieta ankieta) {
        return new Zamowienie(klient, ankieta);
    }

    public static void main(String [] args) {
        GeneratorDanych generatorDanych = new GeneratorDanych();

        Klient[] tablicaKlientow = generatorDanych.generujTabliceKlientow(13);
        Ankieta[] tablicaAnkiet = generatorDanych.generujTabliceAnkiet(13);
        Zamowienie[] tablicaZamowien = new Zamowienie[10];
        Pokoj[] tablicaPokoi = generatorDanych.generujTablicePokoi(1);
        Recepcjonista[] tablicaRecepcjonistow = generatorDanych.generujTabliceRecepcjonistow(11);

        for (int i = 0; i < 10; i++) {
            tablicaZamowien[i] = przyjmijZamowienie(tablicaKlientow[i], tablicaAnkiet[i]);
        }

        akceptuj(tablicaZamowien, tablicaPokoi, tablicaRecepcjonistow);
    }
}
