package logika;

/**
 *  Třída PrikazVlozdoBatohu implementuje pro hru příkaz vezmi.
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Do Hoang Viet Anh
 *@version    pro školní rok 2019/2020
 *
 */


import java.util.ArrayList;
import java.util.List;

public class PrikazVlozdoBatohu implements IPrikaz{
    private static final String NAZEV = "vezmi";
    private HerniPlan plan;

    /**
     *  Konstruktor třídy
     *
     *  @param plan herní plán, ve kterém se bude ve hře "vkládat věci do batohu"
     */
    public PrikazVlozdoBatohu(HerniPlan plan) {
        this.plan = plan;
    }


    /**
     * Metoda pro provedení příkazu ve hře.
     * Metoda vloží věc do batohu.
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
        List<Vec> veci = new ArrayList<>();
        for (i = 0; i < parametry.length; i++) {
            String vec = parametry[i];

            Vec vecVProstoru = this.plan.getAktualniProstor().vratVec(vec);
            if (vecVProstoru == null) {
                return "V prostoru žádná věc jako " + vec + " není";
            } else if (vecVProstoru.jePrenositelna() && this.plan.getBatoh().pocetVeci() < this.plan.getBatoh().getKapacita()) {
                this.plan.getBatoh().vlozVec(vecVProstoru);
                this.plan.getAktualniProstor().odeberVec(vec);
                if (vecVProstoru instanceof Klic) {
                    this.plan.getBatoh().vlozKlicdoBatohu((Klic) vecVProstoru);
                    this.plan.odeberKlic((Klic) vecVProstoru);
                    veci.add(vecVProstoru);
                }
            } else {
                if(veci.isEmpty()) {
                    return vec + " nelze vzít";
                }
                return vec + " nelze vzít, ale už jste vložil " + veci.toString() + " do batohu.";
            }
        } return "Vložil jste zadané předměty do batohu.";
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
