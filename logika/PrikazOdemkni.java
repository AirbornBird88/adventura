package logika;

/**
 *  Třída PrikazOdemkni implementuje pro hru příkaz odemkni.
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Do Hoang Viet Anh
 *@version    pro školní rok 2019/2020
 *
 */

public class PrikazOdemkni implements IPrikaz {
    private static final String NAZEV = "odemkni";
    private HerniPlan plan;

    /**
     *  Konstruktor třídy
     *
     *  @param plan herní plán, je potřeba zjistit, zda jsem v místnosti
     *                    vedle místnosti, která se má odemknout
     */
    public PrikazOdemkni(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     *  Provádí příkaz "odemkni". Zjišťuji, zda z aktuální místnosti je
     *  východ do zadané místnosti. Pokud místnost existuje a je zamčená,
     *  tak se zjistí, zda v batohu je potřebný klíč. Pokud ano, odemknu
     *  místnost.
     *
     *@param parametry - jako  parametr obsahuje jméno místnosti,
     *                         do které se má jít.
     *@return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String...parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední místnost), tak ....
            return "Co mám odemknout? Musíš zadat jméno místnosti";
        }
        String prostor = parametry[0];

        Prostor sousedniProstor = plan.getAktualniProstor().vratSousedniProstor(prostor);
        if (sousedniProstor == null) {
            return "Odsud nevedou dveře do prostoru "+prostor+" !";
        }
        else {
            if (sousedniProstor.jeZamcen()) {
                   if(this.plan.getPoradiKlicu().equals(this.plan.getVlozitdoZamku())){
                    sousedniProstor.zamknout(false);
                    return "Podařilo se ti otevřít dveře do místnosti "
                            + sousedniProstor + ". Nyní je cesta volná. Postupně odemkněte všechny prostory.\n" +
                            "Vemte z nich všechny snímače, pak se vraťte do haly a zadejte příkaz ,,jdi startovací_rampa,,";
                } else {
                    return "Pro odemčení dveří do " + sousedniProstor + " potřebuješ vlozit správný klíč do snímače kódu - "
                            + "příkazem ,,odebrat (klic),, vlozíš klíč do snímače";
                }
            } else {
                return "Prostor " + sousedniProstor + " již byl odemčená!!!";
            }
        }
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

}
