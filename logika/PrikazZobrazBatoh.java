package logika;

/**
 *  Třída PrikazZobrazBatoh implementuje pro hru příkaz batoh.
 *  Zobrazí věci uchované v batohu.
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Do Hoang Viet Anh
 *@version    pro školní rok 2019/2020
 *
 */


public class PrikazZobrazBatoh implements IPrikaz {
    private static final String NAZEV = "batoh";
    private HerniPlan plan;

    /**
     *  Konstruktor třídy
     *
     * Metoda vrací seznam věcí v batohu.
     *  @param plan herní plán, ve kterém se bude ve hře "zobrazovat batoh"
     */
    public PrikazZobrazBatoh(HerniPlan plan) {
        this.plan = plan;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        return this.plan.getBatoh().getNazvy();
    }

    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     *
     * @return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
