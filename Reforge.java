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
    private int health;
    private int defense;
    private int strength;
    private int critchance;
    private int critdamage;
    private static ArrayList<Reforge> weaponreforgelist;
    private static ArrayList<Reforge> armorreforgelist;
    /**
     * Constructor for objects of class Reforge
     */
    public Reforge(String parsename, int parsehealth, int parsedefense, int parsestrength, int parsecritchance, int parsecritdamage)
    {
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
        
        Reforge weaponreforge0 = new Reforge("Stark", 0, 0, 5, 0, 0);
        Reforge weaponreforge1 = new Reforge("Schnell", 0, 0, 0, 10, 20);
        weaponreforgelist.add(weaponreforge0);
        weaponreforgelist.add(weaponreforge1);
        
        Reforge armorreforge0 = new Reforge("Solide", 0, 5, 0, 0, 0);
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
    
    public int gethealth() {
        return health;
    }
    
    public int getdefense() {
        return defense;
    }
    
    public int getstrength() {
        return strength;
    }
    
    public int getcritchance() {
        return critchance;
    }
    
    public int getcritdamage() {
        return critdamage;
    }
}
