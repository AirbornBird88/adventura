package logika;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
    private Hra hra;
    private Prostor aktualniProstor;
    private Batoh aktualnibatoh;
    private Map<Integer, Klic> poradiKlicu = new LinkedHashMap<>();
    private Map<Integer, Klic> vlozitdoZamku = new LinkedHashMap<>();
    private List<Vec> snimace= new ArrayList<>();
    
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
        Prostor hala = new Prostor("hala","hala, hlavní hala objektu");
        Prostor vytah = new Prostor("výtah", "výtah, zadej do snímače čísla (příkaz zadat_cisla)");
        Prostor prostorOtevreny20 = new Prostor("20","20, na displeji je 0");
        Prostor prostorOtevreny21 = new Prostor("21","21, na displeji je 1");
        Prostor prostorOtevreny13 = new Prostor("13","13, na displeji je 11");
        Prostor prostorOtevreny5 = new Prostor("5","5, na displeji je 110");
        Prostor prostorZamceny1 = new Prostor("prostor_00", "prostor_00");
        Prostor prostorZamceny2 = new Prostor("prostor_01", "prostor_01");
        Prostor prostorZamceny3 = new Prostor("prostor_10", "prostor_10");
        Prostor prostorZamceny4 = new Prostor("prostor_111", "prostor_111");
        Prostor prostorZamceny5 = new Prostor("prostor_1100", "prostor_1100");
        Prostor prostorZamceny6 = new Prostor("prostor_1101", "prostor_1101");
        Prostor rampa = new Prostor("startovací_rampa", "startovací rampa, kde je loď, která Vás odveze pryč z planety... Zadejte příkaz ,,konec,, pro ukončení hry.");

        
        // přiřazují se průchody mezi prostory (sousedící prostory)
        hala.setVychod(vytah);
        vytah.setVychod(hala);

        hala.setVychod(rampa);
        rampa.setVychod(hala);

        prostorOtevreny20.setVychod(prostorZamceny1);
        prostorOtevreny20.setVychod(prostorZamceny2);
        prostorZamceny1.setVychod(prostorOtevreny20);
        prostorZamceny2.setVychod(prostorOtevreny20);
        prostorOtevreny20.setVychod(vytah);

        prostorOtevreny21.setVychod(prostorZamceny3);
        prostorZamceny3.setVychod(prostorOtevreny21);
        prostorOtevreny21.setVychod(vytah);

        prostorOtevreny13.setVychod(prostorZamceny4);
        prostorZamceny4.setVychod(prostorOtevreny13);
        prostorOtevreny13.setVychod(vytah);

        prostorOtevreny5.setVychod(prostorZamceny5);
        prostorZamceny5.setVychod(prostorOtevreny5);
        prostorOtevreny5.setVychod(prostorZamceny6);
        prostorZamceny6.setVychod(prostorOtevreny5);
        prostorOtevreny5.setVychod(vytah);


        vytah.setDostupneProstory(prostorOtevreny20);
        vytah.setDostupneProstory(prostorOtevreny21);
        vytah.setDostupneProstory(prostorOtevreny13);
        vytah.setDostupneProstory(prostorOtevreny5);


        // na začátku hry vytvoříme zamčené místnosti a odpovídající klíče
        prostorZamceny1.zamknout(true);
        Klic klic1 = new Klic("Klíč_A", true,  11);
        prostorZamceny1.nastavKlic(klic1);

        prostorZamceny2.zamknout(true);
        Klic klic2 = new Klic("Klíč_D", true, 9);
        prostorZamceny2.nastavKlic(klic2);

        prostorZamceny3.zamknout(true);
        Klic klic3 = new Klic("Klíč_C", true, 8);
        prostorZamceny3.nastavKlic(klic3);

        prostorZamceny4.zamknout(true);
        Klic klic4 = new Klic("Klíč_E", true, 7);
        prostorZamceny4.nastavKlic(klic4);

        prostorZamceny5.zamknout(true);
        Klic klic5 = new Klic("Klíč_F", true, 3);
        prostorZamceny5.nastavKlic(klic5);

        prostorZamceny6.zamknout(true);
        Klic klic6 = new Klic("Klíč_B", true, 2);
        prostorZamceny6.nastavKlic(klic6);

        rampa.budeViditelny(false);

        poradiKlicu.put(11, klic1); //1 pořadí
        poradiKlicu.put(9, klic2); //2
        poradiKlicu.put(8, klic3); //3
        poradiKlicu.put(7, klic4); //4
        poradiKlicu.put(3, klic5); //5
        poradiKlicu.put(2, klic6); //6

        Batoh batoh = new Batoh("batoh", true, 8);


        Vec holoSnimac = new Vec("holografický_snímač", false);
        Vec zrcadlo = new Vec("zrcadlo", false);
        Vec radiator = new Vec("radiátor", false);
        Vec jablko = new Vec("jablko", true);
        Vec auto = new Vec("auto", false);
        Vec lineSnimac = new Vec("lineární_snímač", true);
        Vec addSnimac = new Vec("aditivní_snímač", true);
        Vec subSnimac = new Vec("subtrakční_snímač", true);
        Vec multiSnimac = new Vec("multiplikační_snímač", true);
        Vec fracSnimac = new Vec("frakční_snímač", true);
        Vec topoSnimac = new Vec("topologický_snímač", true);

        snimace.add(lineSnimac);
        snimace.add(addSnimac);
        snimace.add(subSnimac);
        snimace.add(multiSnimac);
        snimace.add(fracSnimac);
        snimace.add(topoSnimac);


        hala.vlozVecdoProstoru(klic1);
        hala.vlozVecdoProstoru(klic2);
        hala.vlozVecdoProstoru(klic3);
        hala.vlozVecdoProstoru(klic4);
        hala.vlozVecdoProstoru(klic5);
        hala.vlozVecdoProstoru(klic6);
        hala.vlozVecdoProstoru(jablko);
        hala.vlozVecdoProstoru(radiator);
        hala.vlozVecdoProstoru(auto);
        vytah.vlozVecdoProstoru(holoSnimac);
        vytah.vlozVecdoProstoru(zrcadlo);
        hala.vlozVecdoProstoru(new Vec("hruška", true));

        prostorZamceny1.vlozVecdoProstoru(lineSnimac);
        prostorZamceny2.vlozVecdoProstoru(addSnimac);
        prostorZamceny3.vlozVecdoProstoru(subSnimac);
        prostorZamceny4.vlozVecdoProstoru(multiSnimac);
        prostorZamceny5.vlozVecdoProstoru(fracSnimac);
        prostorZamceny6.vlozVecdoProstoru(topoSnimac);

        Postava vojin = new Postava("vojín", "Zdravím Tě, příteli, musíš zachránit lokální supercluster před zničením. Musíš všem popsat, co se tady stalo.\n"
        + "Musíš najít předměty, v němž jsou uchována data, z nichž mohou vědci analyzovat průběh kavazarských erupcí. Ty předměty najdeš v zamčených místnostech\n"
        + "Já to tu přesně neznám, ale výtahem se dostaneš k jednotlivým místnostem, musíš tam zadat číslo, nevím přesně jaká čísla, ale když zadáš správné číslo, tak tě to mělo dopravit k určité zamčené místnosti.\n"
        + "Pak musíš klíče vložit postupně ve správném pořadí do zámků jednotlivých zamčených portálů. Až budou ve všech zámcích odpovídající klíče vložené ve správném pořadí, \n"
        + " tak se portály otevřou a ty budeš mít volný přístup do těchto místností...." + " (vojín náhle zemřel v důsledku těžkých popálenin)");

        hala.vlozPostavu(vojin);

        aktualnibatoh= batoh;
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

    /**
     * Getter batohu
     */

    public Batoh getBatoh() {return aktualnibatoh;}


    /**
     * getter pořadí klíčů, v jakém mají být do zámků vkládány
     * @return mapa pořadí klíčů
     */
    public Map<Integer, Klic> getPoradiKlicu() {
        return poradiKlicu;
    }

    /**
     *
     * getter pořadí klíčů, v jakém byly hráčem vkládány do zámků
     * @return mapa pořadí klíčů
     */
    public Map<Integer, Klic> getVlozitdoZamku() {
        return vlozitdoZamku;
    }

    /**
     * Metoda pro vložení klíče do zámku
     *
     * @param klic vloží klíč
     */
    public void vlozitKlic(Klic klic) {
        this.vlozitdoZamku.put(klic.getId(), klic);
    }

    /**
     * Metoda pro odebrání klíče ze zámku.
     *
     * @param klic
     */
    public void odeberKlic (Klic klic) {
        this.vlozitdoZamku.remove(klic);
    }

    /**
     * Metoda pro provedení příkazu ve hře.
     *
     * @return mapa pořadí klíčů
     */
    public List<Vec> getSnimace() {
        return snimace;
    }
}
