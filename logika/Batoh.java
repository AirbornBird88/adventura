package logika;

/**
 *  Třída Batoh, kde si můžete uložit věci.
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Do Hoang Viet Anh
 *@version    pro školní rok 2019/2020
 *
 */


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Batoh extends Vec{
   private List<Vec> uloziste;
   private int kapacita;
   private  Map<Integer, Klic> klice;

    /**
     * Konstruktor batohu
     *
     */

    public Batoh(String nazev, boolean prenositelna, int kapacita) {
        super(nazev, prenositelna);
        this.kapacita = kapacita;
        this.uloziste = new ArrayList<>();
        this.klice = new LinkedHashMap<>();
    }

    /**
     * Základní metody pro práci s mapou (uloziste)
     * a seznamem (klice).
     * add, remove, get, put...
     *
     */
    public List<Vec> getUloziste() {
        return uloziste;
    }

    public void vlozVec(final Vec vec) {
        if(vec.jePrenositelna())
        this.uloziste.add(vec);
    }

    public void odeberVec(final Vec nazev) {
        this.uloziste.remove(nazev);
    }

    public int pocetVeci() {
        return this.uloziste.size();
    }


    public Vec getVec(final String nazev) {
        for (Vec vec : this.uloziste) {
            if (vec.getNazev().equals(nazev)) {
                return vec;
            }
        }
        return null;
    }

    public void vlozKlicdoBatohu(final Klic klic) {
        this.klice.put(klic.getId(), klic);
    }

    public void odstranKliczBatohu(final Klic klic) {
        this.klice.remove(klic);
    }

    /**
     * vrati popis veci v prostoru
     * @return text popisu veci
     */

    public Klic vratKlic(String nazevKlice) {
        List<Klic> zadaneKlice =
                new ArrayList<>();
        for (Map.Entry<Integer, Klic> klic : klice.entrySet()) {
            if (klic.getValue().getNazev().equals(nazevKlice)) {
                Klic value = klic.getValue();
                zadaneKlice.add(value);
            }
        }
        if(zadaneKlice.isEmpty()) {
            return null;
        } else {
            return zadaneKlice.get(0);
        }
    }

    /**
     * Gettery názvy věcí v batohu a kapacita
     */

    public String getNazvy() {
        return this.uloziste.toString();
    }

    public int getKapacita() {
        return kapacita;
    }

}
