package logika;

/**
 *  Třída PrikazzobrazKlice implementuje pro hru příkaz ukaž_klíče.
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Do Hoang Viet Anh
 *@version    pro školní rok 2019/2020
 *
 */

public class PrikazZobrazKlice implements IPrikaz{
    private static final String NAZEV = "ukaž_klíče";
    private HerniPlan plan;

    /**
     *  Konstruktor třídy
     *
     *  @param plan herní plán, ve kterém se bude ve hře "chodit"
     */
    public PrikazZobrazKlice(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * Metoda pro provedení příkazu ve hře.
     * Metoda zobrazí kód (dvojice id= název klíče) každých klíčů.
     *
     * @param parametry počet parametrů závisí na konkrétním příkazu.
     */
    @Override
    public String provedPrikaz(String... parametry) {
        return this.plan.getPoradiKlicu().entrySet().toString();
    }

    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     * @return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
