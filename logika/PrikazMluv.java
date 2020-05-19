package logika;

/**
 *  Třída PrikazMluv implementuje pro hru příkaz mluv.
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Do Hoang Viet Anh
 *@version    pro školní rok 2019/2020
 *
 */

public class PrikazMluv implements IPrikaz{
    private static final String NAZEV = "mluv";
    private HerniPlan plan;

    /**
     *  Konstruktor třídy
     *
     *  @param plan herní plán, ve kterém se bude ve hře "mluvit s postavou"
     */
    public PrikazMluv(HerniPlan plan) {
        this.plan = plan;
    }


    /**
     * Metoda pro provedení příkazu ve hře.
     * Metoda promluví na postavu.
     *
     * @param parametry počet parametrů závisí na konkrétním příkazu.
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Kam mám jít? Musíš zadat jméno východu";
        }

        String postava = parametry[0];

        Postava postavavProstoru = this.plan.getAktualniProstor().vratPostavu(postava);
        if (postavavProstoru == null) {
            return "Tady není " + postava + "!";
        } else {
            return postavavProstoru.getProslov();
        }
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
