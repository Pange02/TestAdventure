import java.util.ArrayList;
import java.lang.Math;
/**
 * Diese Klasse erstellt alle im Spiel verfügbaren Waffen samt ihren Eigenschaften.
 *
 * @author Max Mustermann
 * @version 1.0.1
 */
public class Weapon extends Item

{
    //die Werte der Waffe, sowie die ArrayList in der die Waffen gespeichert werden.
    private int damage;
    private int strength;
    private int critchance;
    private int critdamage;
    private Reforge reforge;
    protected static ArrayList<Weapon> weaponlist = new ArrayList<>();
    
    /**
     * Konstruktor der Waffenklasse
     */
    public Weapon(String parsename, String parsedescription, String parserarity, String weaponGender, int parsedamage, int parsestrength, int parsecritchance, int parsecritdamage)
    {
        super(parsename, parsedescription, parserarity, weaponGender);
        reforge = null;
        damage = parsedamage;
        strength = parsestrength;
        critchance = parsecritchance;
        critdamage = parsecritdamage;
    }

    /**
     * In dieser Methode werden die Waffen erstellt und danach in die ArrayList eingefügt
     */
    public static void createWeapons()
    {
        // Waffen (Name, Beschreibung, Seltenheit, Grammatikalisches Geschlecht, Schaden, Stärke, Chance auf kritische Treffer, kritischer Schaden)
        Weapon weapon0 = new Weapon("Langschwert", "Einst wurde dieses Schwert von den Kriegern der Feuernation geschwungen. Doch jetzt liegt es in Euren Händen. Möge es Euch gute Dienste leisten!", "(Ungewöhnlich)", "neutrum", 5, 3, 10, 5);
        Weapon weapon1 = new Weapon("Holzstock", "Abgebrochen von einer alten Buche ist er keine besonders starke Waffe. Aber besser als nichts. Am besten betest Du nicht angegriffen zu werden.", "(Gewöhnlich)", "maskulin", 1, 1, 0, 0);
        Weapon weapon2 = new Weapon("Streitaxt" , "Geschmiedet in den Küstenregionen im Norden ist diese zweischneidige Axt äußerst robust. Ihre mystischen Verziehrungen stellen nordische Götter dar, die Dir im Kampf beistehen. Durch die Klingen auf beiden Seiten ist sie besonders gut für den Kampf gegen mehrere Gegner geeignet.", "(Gewöhnlich)", "feminin", 3, 5, 0, 8);
        Weapon weapon3 = new Weapon("Dolch" , "Geschmiedet in den Schattenschmieden der Assasinen ist dieser kleine Dolch leicht zu verstecken und eignet sich hervorragend für Angriffe aus dem Hinterhalt.", "(Selten)", "maskulin", 2, 3, 20, 10);
        Weapon weapon4 = new Weapon("Speer", "Dieser schlichte Speer ist trotz seiner simplen Erscheinung eine sehr starke und effektive Waffe. Durch den langen Stiehl ist er gut geignet um Gegner auf Distanz zu halten.", "(Selten)", "maskulin", 4, 5, 5, 5);
        Weapon weapon5 = new Weapon("Katana", "Dieses Schwert aus den östlichen Regionen wurde mit viel Mühe aus mehreren Schichten harten Stahls geschmiedet. Die mit einem furchteinflößenden Drachen verzierte Tsuba schützt deine Hände. Diese äußerst scharfe und gut ausbalancierte Schwert eignet sich sehr für schnelle und tödliche Angriffe.", "(Legendär)", "neutrum", 8, 10, 20, 10);
        
        //Waffen zur Waffenliste hinzufügen
        weaponlist.add(weapon0);
        weaponlist.add(weapon1);
        weaponlist.add(weapon2);
        weaponlist.add(weapon3);
        weaponlist.add(weapon4);
        weaponlist.add(weapon5);
    }
    
    //Rückgabe des Schadens, den eine Waffe verursacht
    public int getweapondamage()
    {
        return damage;
    }
    
    //Rückgabe der Stärke, die eine Waffe verleiht
    public int getstrength() {
        return strength;
    }
    
    //Rückgabe der Chance auf kritische Treffer, einer Waffe
    public int getcritchance()
    {
        return critchance;
    }
    
    //Rückgabe des Schadensboosts bei kritischen Treffern
    public int getcritdamage()
    {
        return critdamage;
    }
    
    // Ausgabe der Beschreibung der Waffe, mit sämtlichen Werten.
    @Override public void getiteminfo() {
        descriptionstring = " " + description;
        statsstring = " " + "Schaden: " + damage + " Stärke: " + strength + " Crit Chance: " + critchance + " Crit Schaden: " + critdamage;
        spaces = Math.max(descriptionstring.length(), statsstring.length());
        for(int i = 0; i <= (spaces - name.length())/2 - 1; i++) {
            System.out.print("-");
        }
        System.out.print(" ");
        System.out.print(name);
        System.out.print(" ");
        for(int i = 0; i <= (spaces - name.length())/2 - 1; i++) {
            if(i == (spaces - name.length())/2 - 1) {
                System.out.println("-");
            }
            else {
                System.out.print("-");
            }
        }
        System.out.println(descriptionstring);
        System.out.println(statsstring);
        for(int i = 0; i <= (spaces - rarity.length() + 1)/2 - 1; i++) {
            System.out.print("-");
        }
        System.out.print(" ");
        System.out.print(rarity);
        System.out.print(" ");
        for(int i = 0; i <= (spaces - rarity.length() + 1)/2 - 1; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
    
    //anwenden eines Reforges auf eine Waffe (übergeben wird der Reforge, welcher angewendet werden soll)
    public void applyreforge(Reforge parsereforge) {
        
        if(reforge != null) {
            strength -= reforge.getstrength()[getreforgerarity()];
            critchance -= reforge.getcritchance()[getreforgerarity()];
            critdamage -= reforge.getcritdamage()[getreforgerarity()];
            reforge = parsereforge;
            strength += parsereforge.getstrength()[getreforgerarity()];
            critchance += parsereforge.getcritchance()[getreforgerarity()];
            critdamage += parsereforge.getcritdamage()[getreforgerarity()];
        }
        else {
            reforge = parsereforge;
            strength += parsereforge.getstrength()[getreforgerarity()];
            critchance += parsereforge.getcritchance()[getreforgerarity()];
            critdamage += parsereforge.getcritdamage()[getreforgerarity()];
        }
    }
}
