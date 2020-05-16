package logika;

public class PrikazVlozdoBatohu implements IPrikaz{
    private static final String NAZEV = "vlozdoBatohu";
    private HerniPlan plan;

    public PrikazVlozdoBatohu(HerniPlan plan) {
        this.plan = plan;
    }


    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo, tak ....
            return "Co mam jako sebrat? Musis mi to rict!";
        }
        String vec = parametry[0];

        Vec vecVProstoru = this.plan.getAktualniProstor().vratVeci(vec);
        if(vecVProstoru == null) {
            return "V prostoru žádná věc jako " + vec + " není";
        }
        else {
            if(vecVProstoru.jeProzkoumana() && vecVProstoru.jePrenositelna()){
                this.plan.getBatoh().vlozVec(vecVProstoru);
                this.plan.getAktualniProstor().odeberVec(vecVProstoru);
            }
        } return "Vložil jste " + vecVProstoru + " do batohu";
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
