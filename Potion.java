import java.util.ArrayList;
/**
 * Write a description of class Potion here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Potion extends Item
{
    // instance variables - replace the example below with your own
    private int effect;
    private String type;
    protected static ArrayList<Potion> potionlist = new ArrayList<>();
    /**
     * Constructor for objects of class Potion
     */
    public Potion(String parsename, String parserarity, String potionGender, String parsetype, int parseeffect)
    {
        super(parsename, parserarity, potionGender);
        effect = parseeffect;
        type = parsetype;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public static void createPotions()
    {
        // Potions (p)
        Potion potion0 = new Potion("Gifttrank", "(Gewöhnlich)", "maskulin", "Damage", 1);
        Potion potion1 = new Potion("Schadenstrank", "(Ungewöhnlich)", "maskulin", "Damage", 2);
        Potion potion2 = new Potion("Heilungstrank", "(Selten)", "maskulin", "Healing", 3);
        //Potions zur Potionlist hinzufügen
        potionlist.add(potion0);
        potionlist.add(potion1);
        potionlist.add(potion2);
    }
    
    public String getpotiontype() {
        return type;
    }
    
    public int getpotioneffect() {
        return effect;
    }
}
