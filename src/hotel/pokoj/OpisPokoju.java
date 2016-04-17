package hotel.pokoj;

public abstract class OpisPokoju {
    public enum Styl {
        ORIENTALNY("orientalny"),
        MORSKI("morski"),
        NOWOCZESNY("nowoczesny"),
        SECESYJNY("secesyjny"),
        RUSTYKALNY("rustykalny");

        private String tekst;

        Styl(String tekst) {
            this.tekst = tekst;
        }

        @Override
        public String toString() {
            return this.tekst;
        }
    }
    public enum Kolorystyka {
        SZARY("szary"),
        STALOWY("stalowy"),
        PURPUROWY("purpurowy"),
        MORSKI("morski"),
        SELEDYNOWY("seledynowy"),
        JASNOZIELONY("jasnozielony");

        private String tekst;

        Kolorystyka(String tekst) {
            this.tekst = tekst;
        }

        @Override
        public String toString() {
            return this.tekst;
        }
    }
    public enum Kierunek {
        POLNOC("polnoc"),
        POLUDNIE("poludnie"),
        WSCHOD("wschod"),
        ZACHOD("zachod");

        private String tekst;

        Kierunek(String tekst) {
            this.tekst = tekst;
        }

        @Override
        public String toString() {
            return this.tekst;
        }
    }

    final static public boolean DOSTEP_DO_INTERNETU = true;
    final static public boolean BRAK_DOSTEPU_DO_INTERNETU = false;
    int maksymalnaLiczbaOsob;
    int cenaNajmu;
    Styl styl;
    Kolorystyka kolorystyka;
    Kierunek kierunek;
    boolean dostepDoInternetu;

    OpisPokoju(int maksymalnaLiczbaOsob,
               int cenaNajmu, Styl styl,
               Kolorystyka kolorystyka,
               Kierunek kierunek,
               boolean dostepDoInternetu) {
        this.maksymalnaLiczbaOsob = maksymalnaLiczbaOsob;
        this.cenaNajmu = cenaNajmu;
        this.styl = styl;
        this.kolorystyka = kolorystyka;
        this.kierunek = kierunek;
        this.dostepDoInternetu = dostepDoInternetu;
    }

    public int getMaksymalnaLiczbaOsob() {
        return maksymalnaLiczbaOsob;
    }

    public int getCenaNajmu() {
        return cenaNajmu;
    }

    public Styl getStyl() {
        return styl;
    }

    public Kolorystyka getKolorystyka() {
        return kolorystyka;
    }

    public Kierunek getKierunek() {
        return kierunek;
    }

    public boolean czyDostepDoInternetu() {
        return dostepDoInternetu;
    }


    public void wypiszOpis() {
        System.out.printf(this.maksymalnaLiczbaOsob + " " +
                          this.cenaNajmu + " " +
                          this.styl + " " +
                          this.kolorystyka + " " +
                          this.kierunek + " " +
                          (czyDostepDoInternetu() ? "tak" : "nie") + "\n");
    }

}