package logika;

/**
 *  Třída PrikazJdiVytahem implementuje pro hru příkaz zadat_cisla.
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Do Hoang Viet Anh
 *@version    pro školní rok 2019/2020
 *
 */


public class PrikazJdiVytahem implements IPrikaz{
    private static final String NAZEV = "zadat_cislo";
    private HerniPlan plan;

    /**
     *  Konstruktor třídy
     *
     *  @param plan herní plán, ve kterém se bude ve hře "jezdit výtahem"
     */
    public PrikazJdiVytahem(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * Metoda pro provedení příkazu ve hře.
     * Metoda pro zadávání čísla prostoru, kam se hráč chce dostat.
     *
     * @param parametry počet parametrů závisí na konkrétním příkazu.
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Kam mám jít? Musíš zadat číla";
        }
        String smer = parametry[0];

        // zkoušíme přejít do propojeného prostoru
        Prostor propojenyProstor = plan.getAktualniProstor().vratDostupnyProstor(smer);

        if (propojenyProstor == null) {
            return "Tam se odsud jít nedá!";
        }
        else {
            if (propojenyProstor.jeDostupny()) {
                return "portál do prostoru " + propojenyProstor.getNazev() + " jsou zamčené";
            }
            plan.setAktualniProstor(propojenyProstor);
            return propojenyProstor.dlouhyPopis();
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
