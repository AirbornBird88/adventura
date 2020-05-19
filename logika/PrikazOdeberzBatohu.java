package logika;

/**
 *  Třída PrikazOdeberzBatohu implementuje pro hru příkaz odeberzBatohu.
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Do Hoang Viet Anh
 *@version    pro školní rok 2019/2020
 *
 */

import java.util.ArrayList;
import java.util.List;


public class PrikazOdeberzBatohu implements  IPrikaz{
    private static final String NAZEV = "odeberzBatohu";
    private HerniPlan plan;

    /**
     *  Konstruktor třídy
     *  Metoda odebere věc z batohu.
     *
     *  @param plan herní plán, ve kterém se bude ve hře "odkládat věci z batohu"
     */
    public PrikazOdeberzBatohu(HerniPlan plan) {
        this.plan = plan;
    }


    /**
     * Metoda pro provedení příkazu ve hře.
     * Počet parametrů je závislý na konkrétním příkazu,
     * např. příkazy konec a napoveda nemají parametry
     * příkazy jdi, seber, polož mají jeden parametr
     * příkaz pouzij může mít dva parametry.
     *
     * @param parametry počet parametrů závisí na konkrétním příkazu.
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo, tak ....
            return "Co mam jako sebrat? Musis mi to rict!";
        }

        int i;
        List<Vec> veci = new ArrayList<>();
        for (i = 0; i < parametry.length; i++) {
            String vec = parametry[i];

            Vec vecVBatohu = this.plan.getBatoh().getVec(vec);
            if (vecVBatohu == null) {
                return "V batohu žádná věc jako " + vec + " není";
            } else {
                this.plan.getBatoh().odeberVec(vecVBatohu);
                this.plan.getAktualniProstor().vlozVecdoProstoru(vecVBatohu);
                if (vecVBatohu instanceof Klic) {
                    this.plan.getBatoh().odstranKliczBatohu((Klic) vecVBatohu);
                    this.plan.vlozitKlic((Klic) vecVBatohu);
                    veci.add(vecVBatohu);
                    return "Odebral jste " + vecVBatohu + " z batohu a vložil jste ho do zámku.";
                }
            }
        } return "Odebral jste " + veci.toString() + " z batohu";
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
