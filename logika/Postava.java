package logika;

/**
 *  Třída Postava popisuje atributy a metody postavy.
 *
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Do Hoang Viet Anh
 *@version    pro školní rok 2019/2020
 *
 */

public class Postava {
    private String jmeno;
    private String proslov;

    /**
     *  Metody konstruktor a gettery
     *
     */
    public Postava(String jmeno, String proslov) {
        this.jmeno = jmeno;
        this.proslov = proslov;
    }

    public String getJmeno() {
        return jmeno;
    }

    public String getProslov() {
        return proslov;
    }
}
