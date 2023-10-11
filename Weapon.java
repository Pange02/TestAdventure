import java.util.ArrayList;
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
    protected static ArrayList<Weapon> weaponlist = new ArrayList<>();
    
    /**
     * Constructor for objects of class Weapon
     */
    public Weapon(String parsename, String parserarity, String weaponGender, int parsedamage)
    {
        super(parsename, parserarity, weaponGender);
        damage = parsedamage;
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
        Weapon weapon0 = new Weapon("Langschwert", "(Ungewöhnlich)", "neutrum", 5);
        Weapon weapon1 = new Weapon("Holzstock", "(Gewöhnlich)", "maskulin", 1);
        Weapon weapon2 = new Weapon("Streitaxt" , "(Gewöhnlich)", "feminin", 3);
        Weapon weapon3 = new Weapon("Dolch" , "(Selten)", "maskulin", 2);
        Weapon weapon4 = new Weapon("Speer", "(Selten)", "maskulin", 4);
        Weapon weapon5 = new Weapon("Katana", "(Legendär)", "neutrum", 8);
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
}
