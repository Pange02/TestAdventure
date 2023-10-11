
/**
 * Write a description of class Consumable here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Consumable extends Item
{
    private int effect;
    
    /**
     * Constructor for objects of class Consumable
     */
    public Consumable(String parsename, String parserarity, int parseeffect)
    {
        super(parsename, parserarity);
        effect = parseeffect;
    }
    
    public int getconsumableeffect() {
        return effect;
    }
}
