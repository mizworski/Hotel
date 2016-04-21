package hotel.pokoj;

/**
 * Klasa OpisPokoju, która zawiera wszystkie parametry opisujące dany pokój.
 */
public abstract class OpisPokoju {
    /**
     * Enumerator, zawierający opis stylu pokoju.
     */
    public enum Styl {
        ORIENTALNY("orientalny"),
        MORSKI("morski"),
        NOWOCZESNY("nowoczesny"),
        SECESYJNY("secesyjny"),
        RUSTYKALNY("rustykalny");

        private String tekst;

        /**
         * Konstruktor enumeratora Styl
         *
         * @param tekst             styl danego pokoju
         */
        Styl(String tekst) {
            this.tekst = tekst;
        }

        @Override
        public String toString() {
            return this.tekst;
        }
    }

    /**
     * Enumerator, zawierający opis kolorystyki pokoju.
     */
    public enum Kolorystyka {
        SZARY("szary"),
        STALOWY("stalowy"),
        PURPUROWY("purpurowy"),
        MORSKI("morski"),
        SELEDYNOWY("seledynowy"),
        JASNOZIELONY("jasnozielony");

        private String tekst;

        /**
         * Konstruktor enumeratora Kolorystyka.
         *
         * @param tekst             kolorystyka danego pokoju
         */
        Kolorystyka(String tekst) {
            this.tekst = tekst;
        }

        @Override
        public String toString() {
            return this.tekst;
        }
    }

    /**
     * Enumerator, zawierający kierunek pokoju.
     */
    public enum Kierunek {
        POLNOC("polnoc"),
        POLUDNIE("poludnie"),
        WSCHOD("wschod"),
        ZACHOD("zachod");

        private String tekst;

        /**
         * Konstruktor enumeratora Kierunek.
         *
         * @param tekst             kierunek okien danego pokoju
         */
        Kierunek(String tekst) {
            this.tekst = tekst;
        }

        @Override
        public String toString() {
            return this.tekst;
        }
    }

    int maksymalnaLiczbaOsob;
    int cenaNajmuZaDobe;
    Styl styl;
    Kolorystyka kolorystyka;
    Kierunek kierunek;
    boolean dostepDoInternetu;

    /**
     * Konstruktor klasy Pokoj.
     *
     * @param maksymalnaLiczbaOsob  maksymalna liczba osób, które mogą mieszkać w danym pokoju
     * @param cenaNajmuZaDobe       cena najmu za jedną dobę danego pokoju
     * @param styl                  styl danego pokoju
     * @param kolorystyka           kolorystyka danego pokoju
     * @param kierunek              kierunek okien danego pokoju
     * @param dostepDoInternetu     czy jest dostęp do internetu w danym pokoju
     */
    OpisPokoju(int maksymalnaLiczbaOsob,
               int cenaNajmuZaDobe, Styl styl,
               Kolorystyka kolorystyka,
               Kierunek kierunek,
               boolean dostepDoInternetu) {
        this.maksymalnaLiczbaOsob = maksymalnaLiczbaOsob;
        this.cenaNajmuZaDobe = cenaNajmuZaDobe;
        this.styl = styl;
        this.kolorystyka = kolorystyka;
        this.kierunek = kierunek;
        this.dostepDoInternetu = dostepDoInternetu;
    }

    /**
     * Getter maksymalnej liczby osób.
     *
     * @return                      maksymalna liczba osób
     */
    public int getMaksymalnaLiczbaOsob() {
        return maksymalnaLiczbaOsob;
    }

    /**
     * Getter ceny najmu za jedną dobę.
     *
     * @return                      cena najmu za jedną dobę
     */
    public int getCenaNajmuZaDobe() {
        return cenaNajmuZaDobe;
    }

    /**
     * Getter stylu opisu pokoju.
     *
     * @return                      styl opisu pokoju
     */
    public Styl getStyl() {
        return styl;
    }

    /**
     * Getter kolorystyki opisu pokoju.
     *
     * @return                      kolorystyka opisu pokoju
     */
    public Kolorystyka getKolorystyka() {
        return kolorystyka;
    }

    /**
     * Getter kierunku opisu pokoju.
     *
     * @return                      kierunek opisu pokoju
     */
    public Kierunek getKierunek() {
        return kierunek;
    }

    /**
     * Getter dostępu do internetu.
     *
     * @return                      czy jest dosęp do interentu
     */
    public boolean czyDostepDoInternetu() {
        return dostepDoInternetu;
    }

    /**
     * Wypisuje dany opis pokoju.
     */
    public void wypiszOpis() {
        System.out.printf("Liczba osób: " +
                          this.maksymalnaLiczbaOsob +
                          " Cena najmu za dobę: " +
                          this.cenaNajmuZaDobe +
                          " Styl: " +
                          this.styl +
                          " Kolorystyka " +
                          this.kolorystyka +
                          " Kierunek: " +
                          this.kierunek +
                          " Dostęp do internetu: " +
                          (czyDostepDoInternetu() ? "tak" : "nie") + "\n");
    }
}