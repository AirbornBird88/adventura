package logika;

/**
 *  Třída PrikazOdemkni implementuje pro hru příkaz odemkni.
 *
 */
public class PrikazOdemkni implements IPrikaz { //implements? TODO
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
    //TODO
    @Override
    public String provedPrikaz(String...parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední místnost), tak ....
            return "Co mám odemknout? Musíš zadat jméno místnosti";
        }

        String prostor = parametry[0];

        // hledám zadanou místnost mezi východy
        Prostor sousedniProstor = plan.getAktualniProstor().vratSousedniProstor(prostor);
        if (sousedniProstor == null) {
            return "Odsud nevedou dveře do prostoru "+prostor+" !";
        }
        else {
            if (sousedniProstor.jeZamcen()) {
                if (plan.getBatoh().obsahujeVec(sousedniProstor.getKlic().getNazev())) { //TODO obsahujeVec dodelat ve tride Vec
                    sousedniProstor.zamknout(false);
                    return "Podařilo se ti otevřít dveře do místnosti "
                            + sousedniProstor + ". Nyní je cesta volná.";
                } else {
                    return "Pro odemčení dveří do " + sousedniProstor + " potřebuješ mít "
                            + "u sebe " + sousedniProstor.getKlic().getNazev();
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
