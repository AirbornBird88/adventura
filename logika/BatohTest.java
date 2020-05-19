package logika;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BatohTest {
    Batoh batoh = new Batoh("batoh", true, 3);
    Vec vec1 = new Vec("aaa", true);
    Vec vec2 = new Vec("bbb", true);
    Vec vec3 = new Vec("ccc", true);
    Vec vec4 = new Vec("zzz", false);

    Klic klic1 = new Klic("ddd",true, 11);
    Klic klic2 = new Klic("eee", true, 20);
    Klic klic3 = new Klic("fff", true, 3);

    @Before
    public void setUp() {
        batoh.vlozVec(vec1);
        batoh.vlozVec(vec2);
        batoh.vlozVec(vec3);
    }

    @Test
    public void odeberVec() {
        batoh.odeberVec(vec1);
        batoh.vlozVec(vec4);
        assertEquals(2, batoh.pocetVeci());
    }

    @Test
    public void getVec() {
        assertEquals(vec2, batoh.getVec("bbb"));
    }

    @Test
    public void vratKlic() {
        batoh.vlozKlicdoBatohu(klic1);
        batoh.vlozKlicdoBatohu(klic2);
        batoh.vlozKlicdoBatohu(klic3);
        assertEquals(klic3, batoh.vratKlic("fff"));
    }

    @Test
    public void sizeBatohu() {
        batoh.vlozKlicdoBatohu(klic1);
        assertEquals(3, batoh.pocetVeci());
    }
}
