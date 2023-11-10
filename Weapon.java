import java.util.ArrayList;
import java.lang.Math;
/**
 * Write a description of class Weapon here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Weapon extends Item

{
    // instance variables - replace the example below with your own
    private int damage;
    private int strength;
    private int critchance;
    private int critdamage;
    private Reforge reforge;
    protected static ArrayList<Weapon> weaponlist = new ArrayList<>();
    
    /**
     * Constructor for objects of class Weapon
     */
    public Weapon(String parsename, String parsedescription, String parserarity, String weaponGender, int parsedamage, int parsestrength, int parsecritchance, int parsecritdamage)
    {
        super(parsename, parsedescription, parserarity, weaponGender);
        damage = parsedamage;
        strength = parsestrength;
        critchance = parsecritchance;
        critdamage = parsecritdamage;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public static void createWeapons()
    {
        // Waffen (w)
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
    
    public int getweapondamage()
    {
        return damage;
    }
    
    @Override public void getiteminfo() {
        descriptionstring = " " + description;
        statsstring = " " + "Schaden: " + damage;
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
    
    public void applyreforge(Reforge parsereforge) {
        reforge = parsereforge;
                
    }
}
