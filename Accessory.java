import java.util.ArrayList;
/**
 * Write a description of class Accessories here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Accessory extends Item
{
    private int strength;
    protected static ArrayList<Accessory> accessorylist = new ArrayList<>();
    /**
     * Constructor for objects of class Accessories
     */
    public Accessory(String parsename, String parserarity, String accessoryGender, int parsestrength, int parsecritchance, int parsecritdamage)
    {
        super(parsename, parserarity, accessoryGender);
        strength = parsestrength;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public static void createAccessories()
    {
        // Accessory (x)
        Accessory accessory0 = new Accessory("Artefakt des Himmels", "(Episch)", "neutrum", 3, 2, 5);
        Accessory accessory1 = new Accessory("Ring der Stärke", "(Selten)", "maskulin", 5, 2, 3);
        //Accessories zur accessorylist hinzufügen
        accessorylist.add(accessory0);
        accessorylist.add(accessory1);
    }
    
    public int getaccessorystrength() {
        return strength;
    }
}