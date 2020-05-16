package logika;

public class Klic extends Vec{
    private String kod;

    public Klic(String nazev, boolean prenositelna, String kod) {
        super(nazev, prenositelna);
        this.kod = kod;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }
}
