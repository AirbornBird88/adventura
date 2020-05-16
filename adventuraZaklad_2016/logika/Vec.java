package logika;

public class Vec {
    private String nazev;
    private boolean prenositelna; //jestli je vec prenositelna
    private boolean prozkoumana = false;

    public Vec(String nazev, boolean prenositelna) {
        this.nazev = nazev;
        this.prenositelna = prenositelna;
    }

    public String getNazev() {
        return nazev;
    }

    public boolean jePrenositelna() {
        return prenositelna;
    }

    public boolean jeProzkoumana() {
        return prozkoumana;
    }

    public void prozkoumano(boolean prozkoumana) {
        this.prozkoumana = prozkoumana;
    }

    @Override
    public String toString() {
        return this.getNazev();
    }

}
