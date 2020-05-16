package logika;

import java.security.spec.NamedParameterSpec;

public class PrikazZobrazBatoh implements IPrikaz {
    private static final String NAZEV = "zobrazBatoh";
    private HerniPlan plan;

    public PrikazZobrazBatoh(HerniPlan plan) {
        this.plan = plan;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        return this.plan.getBatoh().getNazvy();
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
