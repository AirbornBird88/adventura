package logika;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Trida Prostor - popisuje jednotlivé prostory (místnosti) hry
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * "Prostor" reprezentuje jedno místo (místnost, prostor, ..) ve scénáři hry.
 * Prostor může mít sousední prostory připojené přes východy. Pro každý východ
 * si prostor ukládá odkaz na sousedící prostor.
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 * @version pro školní rok 2016/2017
 */
public class Prostor {

    private String nazev;
    private String popis;
    private Set<Prostor> vychody;   // obsahuje sousední místnosti
    private Set<Prostor> dostupneProstory;
    private Map<String, Vec> veci; //seznam věcí v prostoru
    private Set<Postava> postavy;

    private boolean zamcen;//zamcený prostor
    private Klic klic;

    private boolean dostupny;

    private boolean viditelny = true;



    /**
     * Vytvoření prostoru se zadaným popisem, např. "kuchyň", "hala", "trávník
     * před domem"
     *
     * @param nazev nazev prostoru, jednoznačný identifikátor, jedno slovo nebo
     * víceslovný název bez mezer.
     * @param popis Popis prostoru.
     */
    public Prostor(String nazev, String popis) {
        this.nazev = nazev;
        this.popis = popis;
        vychody = new HashSet<>();
        dostupneProstory = new HashSet<>();
        veci = new HashMap<>();
        postavy = new HashSet<>();
    }

    /**
     * Definuje východ z prostoru (sousední/vedlejsi prostor). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední prostor uveden
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední místnosti).
     * Druhé zadání stejného prostoru tiše přepíše předchozí zadání (neobjeví se
     * žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     * @param vedlejsi prostor, který sousedi s aktualnim prostorem.
     *
     */
    public void setVychod(Prostor vedlejsi) {
        vychody.add(vedlejsi);
    }

    /**
     * Metoda equals pro porovnání dvou prostorů. Překrývá se metoda equals ze
     * třídy Object. Dva prostory jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     *
     * Bližší popis metody equals je u třídy Object.
     *
     * @param o object, který se má porovnávat s aktuálním
     * @return hodnotu true, pokud má zadaný prostor stejný název, jinak false
     */  
      @Override
    public boolean equals(Object o) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == o) {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (!(o instanceof Prostor)) {
            return false;    // pokud parametr není typu Prostor, vrátíme false
        }
        // přetypujeme parametr na typ Prostor 
        Prostor druhy = (Prostor) o;

        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů. 
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.

       return (java.util.Objects.equals(this.nazev, druhy.nazev));       
    }

    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object
     */
    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }
      

    /**
     * Vrací název prostoru (byl zadán při vytváření prostoru jako parametr
     * konstruktoru)
     *
     * @return název prostoru
     */
    public String getNazev() {
        return nazev;       
    }

    /**
     * Vrací "dlouhý" popis prostoru, který může vypadat následovně: Jsi v
     * mistnosti/prostoru vstupni hala budovy VSE na Jiznim meste. vychody:
     * chodba bufet ucebna
     *
     * @return Dlouhý popis prostoru
     */
    public String dlouhyPopis() {
        return "Jsi v mistnosti/prostoru " + popis + ".\n"
                + popisVychodu() + "\n"
                + seznamVeci() + "\n"
                + seznamPostav();
    }

    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "vychody: hala ".
     *
     * @return Popis východů - názvů sousedních prostorů
     */
    private String popisVychodu() {
        StringBuilder vracenyText = new StringBuilder("východy:");
        for (Prostor sousedni : vychody) {
            if (sousedni.jeViditelny() == true) {
            vracenyText.append(" ").append(sousedni.getNazev());
                if (sousedni.jeZamcen()) { //zamknutá místnost
                    vracenyText.append("(zamknuto)");
                }
            }
        }
        return vracenyText.toString();
    }

    /**
     * vrati popis veci v prostoru
     * @return text popisu veci
     */
    private String seznamVeci() {
        StringBuilder vracenyText = new StringBuilder("věci, které jsou v prostoru: ");
        if(veci.size() > 0) {
            for (Map.Entry<String, Vec> vec : veci.entrySet()) {
                vracenyText.append(vec.getValue().getNazev()).append(" ");
                if(!vec.getValue().jePrenositelna()) {
                    vracenyText.append("- (nepřenositelná) ");
                }
            }
            return vracenyText.toString();
        }
            return "Zde není žádný předmět.";
    }

    /**
     * vrati popis veci v prostoru
     * @return text popisu veci
     */
    private String seznamPostav() {
        StringBuilder vracenyText = new StringBuilder("postavy:");
        if(postavy.size() > 0) {
            for (Postava postava : postavy) {
                vracenyText.append(" ").append(postava.getJmeno());
            }
            return vracenyText.toString();
        } return "Zde není žádná postava.";
    }

    /**
     * Vrací prostor, který sousedí s aktuálním prostorem a jehož název je zadán
     * jako parametr. Pokud prostor s udaným jménem nesousedí s aktuálním
     * prostorem, vrací se hodnota null.
     *
     * @param nazevSouseda Jméno sousedního prostoru (východu)
     * @return Prostor, který se nachází za příslušným východem, nebo hodnota
     * null, pokud prostor zadaného jména není sousedem.
     */
    public Prostor vratSousedniProstor(String nazevSouseda) {
        List<Prostor>hledaneProstory = 
            vychody.stream()
                   .filter(sousedni -> sousedni.getNazev().equals(nazevSouseda))
                   .collect(Collectors.toList());
        if(hledaneProstory.isEmpty()){
            return null;
        }
        else {
            return hledaneProstory.get(0);
        }
    }

    /**
     * Vrací kolekci obsahující prostory, se kterými tento prostor sousedí.
     * Takto získaný seznam sousedních prostor nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Prostor.
     *
     * @return Nemodifikovatelná kolekce prostorů (východů), se kterými tento
     * prostor sousedí.
     */
    public Collection<Prostor> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }

    //additional methods for Zamcena atribut
    public boolean jeZamcen() { //getter
        return zamcen;
    }

    public void zamknout(boolean zamcen) { //metoda pro nastavení datového atributu, setter?
        this.zamcen = zamcen;
    }

    /**
     * Getter a setter pro klíč
     *
     * @return mapa pořadí klíčů
     */

    public Klic getKlic() {
        return klic;
    }

    public void nastavKlic(Klic klic) {
        this.klic = klic;
    }

    /**
     * Metody pro práci s množinou dostupných prostorů.
     *
     */
    public boolean jeDostupny() {
        return dostupny;
    }

    public void setDostupneProstory(Prostor vedlejsi) {
        dostupneProstory.add(vedlejsi);
    }

    public Prostor vratDostupnyProstor(String nazevDostupnehoProstoru) {
        List<Prostor>zadaneProstory =
                dostupneProstory.stream()
                        .filter(sousedni -> sousedni.getNazev().equals(nazevDostupnehoProstoru))
                        .collect(Collectors.toList());
        if(zadaneProstory.isEmpty()){
            return null;
        }
        else {
            return zadaneProstory.get(0);
        }
    }

    /**
     * Metody pro práci s mapou věcí.
     *
     */

    public void vlozVecdoProstoru (Vec vec) {
        this.veci.put(vec.getNazev(), vec);
    }


    public Vec odeberVec (String vec) {
        return this.veci.remove(vec);
    }

    public Vec vratVec(String nazevVeci) {
        return this.veci.get(nazevVeci);
    }

    /**
     * Metody pro práci s množinou postav.
     *
     */
    public void vlozPostavu(Postava postava) {
        this.postavy.add(postava);
    }

    public Postava vratPostavu(String jmenoPostavy) {
        List<Postava>hledanePostavy =
                postavy.stream()
                        .filter(postava -> postava.getJmeno().equals(jmenoPostavy))
                        .collect(Collectors.toList());
        if(hledanePostavy.isEmpty()){
            return null;
        }
        else {
            return hledanePostavy.get(0);
        }
    }
    /**
     * Metody pro práci s neviditelnými místnostmi (rampa).
     *
     */
    public void budeViditelny(boolean viditelna) {
        this.viditelny = viditelna;
    }

    public boolean jeViditelny() {
        return viditelny;
    }

    @Override
    public String toString() {
        return this.getNazev();
    }
}
