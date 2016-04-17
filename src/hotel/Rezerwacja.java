package hotel;

import java.util.Date;

public class Rezerwacja {
    Date poczatekPobytu;
    int dlugoscPobytu;

    Rezerwacja(Date poczatekPobytu, int dlugoscPobytu) {
        this.poczatekPobytu = poczatekPobytu;
        this.dlugoscPobytu = dlugoscPobytu;
    }

    public Date getPoczatekPobytu() {
        return this.poczatekPobytu;
    }

    public int getDlugoscPobytu() {
        return this.dlugoscPobytu;
    }

    public String poczatekPobytuToString() {
        return null;
    }
}
