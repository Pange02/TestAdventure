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
    public Accessory(String parsename, String parserarity, int parsestrength)
    {
        super(parsename, parserarity);
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
        Accessory accessory0 = new Accessory("Artefakt des Himmels", "(Episch)", 3);
        Accessory accessory1 = new Accessory("Ring der Stärke", "(Selten)", 2);
        //Accessories zur accessorylist hinzufügen
        accessorylist.add(accessory0);
        accessorylist.add(accessory1);
    }
}
