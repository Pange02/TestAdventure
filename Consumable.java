import java.util.ArrayList;
/**
 * Write a description of class Consumable here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Consumable extends Item
{
    private int effect;
    protected static ArrayList<Consumable> consumablelist = new ArrayList<>();
    
    /**
     * Constructor for objects of class Consumable
     */
    public Consumable(String parsename, String parserarity, String parsegender, int parseeffect)
    {
        super(parsename, parserarity, parsegender);
        effect = parseeffect;
    }
    
    public static void createConsumables() {
        Consumable consumable1 = new Consumable("Apfel", "(Gewöhnlich)", "maskulin", 2);
        consumablelist.add(consumable1);
    }
    
    public int getconsumableeffect() {
        return effect;
    }
}
