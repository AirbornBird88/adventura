package logika;

/**
 *  Třída Klíč popisuje atributy a metody klíče.
 *
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Do Hoang Viet Anh
 *@version    pro školní rok 2019/2020
 *
 */

public class Klic extends Vec{
    private int id;


    public Klic(String nazev, boolean prenositelna, int id) {
        super(nazev, prenositelna);
        this.id = id;
    }
    /**
     * Gettery a settery atributu id.
     *
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
