package logika;

/**
 *  Třída PrikazSpocitejSoucet implementuje pro hru příkaz spocitej_soucet.
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Do Hoang Viet Anh
 *@version    pro školní rok 2019/2020
 *
 */


public class PrikazSpocitejSoucet implements  IPrikaz{
    private static final String NAZEV = "spocitej_soucet";
    private HerniPlan plan;

    /**
     *  Konstruktor třídy
     *
     *  @param plan herní plán, ve kterém se bude ve hře "počítat součty"
     */
    public PrikazSpocitejSoucet(HerniPlan plan) {
        this.plan = plan;
    }


    /**
     * Metoda pro provedení příkazu ve hře.
     * Metoda spočítá součet id klíčů.
     *
     * @param parametry počet parametrů závisí na konkrétním příkazu.
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo, tak ....
            return "Co mam vzít? Musíš říct kokrétní věc.";
        }
        int i;
        int sum = 0;
        for (i = 0; i < parametry.length; i++) {
            String x = parametry[i];
            Klic klic = this.plan.getBatoh().vratKlic(x);
            sum += klic.getId();
        }
       return "Soucet je: " + sum;
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
