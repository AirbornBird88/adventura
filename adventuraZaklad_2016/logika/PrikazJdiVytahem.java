package logika;

import java.util.Scanner;

public class PrikazJdiVytahem implements IPrikaz{
    private static final String NAZEV = "zadejKlice";
    private HerniPlan plan;

    public PrikazJdiVytahem(HerniPlan plan) {
        this.plan = plan;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "Kam mám jít? Musíš zadat číla";
        }

        /*int sum = 0;
        Scanner kb;
        while (kb.hasNextInt()) {
            sum += kb.nextInt();
        }*/

        String smer = parametry[0];

        // zkoušíme přejít do sousedního prostoru
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

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
