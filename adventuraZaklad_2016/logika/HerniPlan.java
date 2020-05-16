package logika;


/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 *@version    pro školní rok 2016/2017
 */
public class HerniPlan {
    
    private Prostor aktualniProstor;
    private Batoh batoh = new Batoh("batoh", true, 8);

    
     /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan() {
        zalozProstoryHry();

    }
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví domeček.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor hala = new Prostor("Hala","domeček, ve kterém bydlí Karkulka");
        Prostor vytah = new Prostor("Výtah", "Zadej do snímače čísla (prikaz zadejKlice)");
        Prostor prostorOtevreny200 = new Prostor("20","na displeji je 0");
        Prostor prostorOtevreny201 = new Prostor("21","na displeji je 1");
        Prostor prostorOtevreny12 = new Prostor("13","na displeji je 1");
        Prostor prostorOtevreny5 = new Prostor("5","na displeji je 0");
        Prostor prostorZamceny1 = new Prostor("Zamčenýprostor1", "prostor za zamčeným portálem");
        Prostor prostorZamceny2 = new Prostor("Zamčenýprostor2", "prostor za zamčeným portálem");
        Prostor prostorZamceny3 = new Prostor("Zamčenýprostor3", "prostor za zamčeným portálem");
        Prostor prostorZamceny4 = new Prostor("Zamčenýprostor4", "prostor za zamčeným portálem");
        Prostor prostorZamceny5 = new Prostor("Zamčenýprostor5", "prostor za zamčeným portálem");
        Prostor prostorZamceny6 = new Prostor("zamceny6", "prostor za zamčeným portálem");

        
        // přiřazují se průchody mezi prostory (sousedící prostory)
        hala.setVychod(vytah);
        vytah.setVychod(hala);
        prostorOtevreny200.setVychod(prostorZamceny1);
        prostorOtevreny200.setVychod(prostorZamceny2);
        prostorOtevreny200.setVychod(vytah);
        prostorOtevreny201.setVychod(prostorZamceny5);
        prostorOtevreny201.setVychod(vytah);
        prostorOtevreny12.setVychod(prostorZamceny6);
        prostorOtevreny12.setVychod(vytah);
        prostorOtevreny5.setVychod(vytah);
        prostorOtevreny5.setVychod(prostorZamceny3);
        prostorOtevreny5.setVychod(prostorZamceny4);


        vytah.setDostupneProstory(prostorOtevreny200);
        vytah.setDostupneProstory(prostorOtevreny201);
        vytah.setDostupneProstory(prostorOtevreny12);
        vytah.setDostupneProstory(prostorOtevreny5);


        // na začátku hry vytvoříme zamčené místnosti a odpovídající klíče
        prostorZamceny1.zamknout(true);
        Klic klic1 = new Klic("Klíč A", true, "A");
        prostorZamceny1.nastavKlic(klic1);

        prostorZamceny2.zamknout(true);
        Klic klic2 = new Klic("Klíč D", true, "D");
        prostorZamceny2.nastavKlic(klic2);

        prostorZamceny3.zamknout(true);
        Klic klic3 = new Klic("Klíč", true, "F");
        prostorZamceny3.nastavKlic(klic3);

        prostorZamceny4.zamknout(true);
        Klic klic4 = new Klic("Klíč ", true, "B");
        prostorZamceny4.nastavKlic(klic4);

        prostorZamceny5.zamknout(true);
        Klic klic5 = new Klic("Klíč ", true, "C");
        prostorZamceny5.nastavKlic(klic5);

        prostorZamceny6.zamknout(true);
        Klic klic6 = new Klic("KlíčE", true, "E");
        prostorZamceny6.nastavKlic(klic6);

        batoh.vlozVec(klic1);
        batoh.vlozVec(klic2);
        batoh.vlozVec(klic3);
        batoh.vlozVec(klic4);
        batoh.vlozVec(klic5);
        batoh.vlozVec(klic6);
        Vec jkl = new Vec("jkl", true);
        Klic klic8 = new Klic("klič8", false,"D");
        Vec jablko = new Vec("jablko", true);
        hala.vlozVecdoProstoru(jablko);
        vytah.vlozVecdoProstoru(jkl);
        vytah.vlozVecdoProstoru(klic8);
        hala.vlozVecdoProstoru(new Vec("hruška", true));

                
        aktualniProstor = hala;  // hra začíná v hale
    }
    
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */
    
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }
    
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
    }

    //getBatoh

    public Batoh getBatoh() {return batoh;}

    public void setBatoh(Batoh batoh) {
        this.batoh = batoh;
    }
}
