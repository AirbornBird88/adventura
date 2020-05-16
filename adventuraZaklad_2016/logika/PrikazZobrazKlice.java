package logika;

import java.util.HashMap;
import java.util.Map;

public class PrikazZobrazKlice {
    private static final String NAZEV = "zobraz klíče";
    private Map<Integer, Klic> klice = new HashMap<>();

    public PrikazZobrazKlice() {
        this.klice = new HashMap<>();
    }


}
