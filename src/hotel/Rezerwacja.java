package hotel;

import java.util.Date;

/**
 * Klasa Rezerwacja, zawierająca pierwszy dzień zakwaterowania oraz długość danego zakwaterowania
 * wyrażoną w dniach.
 */
public class Rezerwacja {
    Date poczatekPobytu;
    int dlugoscPobytu;

    /**
     * Konstruktor klasy Rezerwacja.
     *
     * @param poczatekPobytu    pierwszy dzień zakwaterowania
     * @param dlugoscPobytu     długość pobytu wyrażona w dniach
     * @see Date
     */
    Rezerwacja(Date poczatekPobytu, int dlugoscPobytu) {
        this.poczatekPobytu = poczatekPobytu;
        this.dlugoscPobytu = dlugoscPobytu;
    }

    /**
     * Getter pierwszego dnia zakwaterowania.
     *
     * @return pierwszy dzień zakwaterowania
     * @see Date
     */
    public Date getPoczatekPobytu() {
        return this.poczatekPobytu;
    }

    /**
     * Getter długości pobytu.
     *
     * @return długość pobytu wyrażona w dniach
     */
    public int getDlugoscPobytu() {
        return this.dlugoscPobytu;
    }
}
