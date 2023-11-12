import java.util.ArrayList;
/**
 * Write a description of class Reforge here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Reforge
{
    // instance variables - replace the example below with your own
    private String name;
    private int[] health;
    private int[] defense;
    private int[] strength;
    private int[] critchance;
    private int[] critdamage;
    private static ArrayList<Reforge> weaponreforgelist;
    private static ArrayList<Reforge> armorreforgelist;
    /**
     * Constructor for objects of class Reforge
     */
    public Reforge(String parsename, int[] parsehealth, int[] parsedefense, int[] parsestrength, int[] parsecritchance, int[] parsecritdamage)
    {
        health = new int[6];
        defense = new int[6];
        strength = new int [6];
        critchance = new int[6];
        critdamage = new int [6];
        name = parsename;
        health = parsehealth;
        defense = parsedefense;
        strength = parsestrength;
        critchance = parsecritchance;
        critdamage = parsecritdamage;
    }
    
    public static void makeReforges() {
        weaponreforgelist = new ArrayList<>();
        armorreforgelist = new ArrayList<>();
        
        Reforge weaponreforge0 = new Reforge("Stark", new int[]{0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0}, new int[]{3, 4, 5, 7, 10, 15}, new int[]{0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0});
        Reforge weaponreforge1 = new Reforge("Schnell", new int[]{0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0}, new int[]{5, 10, 15, 20, 25, 30}, new int[]{5, 10, 15, 20, 25, 30});
        weaponreforgelist.add(weaponreforge0);
        weaponreforgelist.add(weaponreforge1);
        
        Reforge armorreforge0 = new Reforge("Solide", new int[]{0, 0, 0, 0, 0, 0}, new int[]{3, 6, 10, 15, 20, 25}, new int[]{0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0});
        armorreforgelist.add(armorreforge0);
    }
    
    public static ArrayList<Reforge> getweaponreforgelist() {
        return weaponreforgelist;
    }
    
    public static ArrayList<Reforge> getarmorreforgelist() {
        return armorreforgelist;
    }
    
    public String getname() {
        return name;
    }
    
    public int[] gethealth() {
        return health;
    }
    
    public int[] getdefense() {
        return defense;
    }
    
    public int[] getstrength() {
        return strength;
    }
    
    public int[] getcritchance() {
        return critchance;
    }
    
    public int[] getcritdamage() {
        return critdamage;
    }
}
