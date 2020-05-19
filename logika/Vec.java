package logika;

/**
 *  Třída Vec popisuje atributy a metody veci.
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Do Hoang Viet Anh
 *@version    pro školní rok 2019/2020
 *
 */


public class Vec {
    private String nazev;
    private boolean prenositelna; //jestli je vec prenositelna

    public Vec(String nazev, boolean prenositelna) {
        this.nazev = nazev;
        this.prenositelna = prenositelna;
    }

    /**
     * Gettery atributů
     */
    public String getNazev() {
        return nazev;
    }

    public boolean jePrenositelna() {
        return this.prenositelna;
    }


    @Override
    public String toString() {
        return this.getNazev();
    }

}
