package logika;

import java.util.HashMap;
import java.util.Map;

public class Batoh extends Vec{
   private Map<String, Vec> uloziste;
   private int kapacita;

    public Batoh(String nazev, boolean prenositelna, int kapacita) {
        super(nazev, prenositelna);
        this.kapacita = kapacita;
        this.uloziste = new HashMap<>();
    }

    public Map<String, Vec> getUloziste() {
        return uloziste;
    }

    public Vec odeberVec(final String nazev) {
        return this.uloziste.remove(nazev);
    }

    public int pocetVeci() {
        return this.uloziste.size();
    }

    public boolean obsahujeVec(final String nazev) { //jestli obsahuje Vec x
        return this.uloziste.containsKey(nazev);
    }

    public Vec getVec(final String nazev) {
        return this.uloziste.get(nazev);
    }


    public String getNazvy() {
        String obsah = this.uloziste.keySet().toString();
        return obsah;
    }

    public String vlozVec(final Vec vec) {
        if(this.uloziste.size() >= kapacita) {
            this.uloziste.put(vec.getNazev(), vec);
        } return "Batoh je plný";
    }
}
