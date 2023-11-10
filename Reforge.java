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
    private static ArrayList<Reforge> reforgelist;
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
        reforgelist = new ArrayList<>();
    }
    
    public static void makeReforges() {
        Reforge reforge1 = new Reforge("Stark", 3, 2, 5, 0, 0);
        reforgelist.add(reforge1);
    }
    
    public static ArrayList<Reforge> getreforgelist() {
        return reforgelist;
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
    
    public int getcritchance() {
        return critchance;
    }
    
    public int getcritdamage() {
        return critdamage;
    }
}
