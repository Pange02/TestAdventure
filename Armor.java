import java.util.ArrayList;
/**
 * Write a description of class Armor here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Armor extends Item
{
    private int defense;
    private String armortype;
    protected static ArrayList<Armor> armorlist = new ArrayList<>();
    /**
     * Constructor for objects of class Armor
     */
    public Armor(String parsename, String parserarity, String armorGender, String parsearmortype, int parsedefense)
    {
        super(parsename, parserarity, armorGender);
        armortype = parsearmortype;
        defense = parsedefense;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public static void createArmor()
    {
        // Armor (a)
        Armor armor0 = new Armor("Verrostete Brustplatte", "(Ungewöhnlich)", "feminin", "Chestplate", 2);
        Armor armor1 = new Armor("Kettenhemd", "(Gewöhnlich)", "neutrum", "Chestplate", 3);
        Armor armor2 = new Armor("Lederkappe", "(Gewöhnlich)", "feminin", "Helmet", 1);
        Armor armor3 = new Armor("Alte Stiefel", "(Gewöhnlich)", "maskulin", "Boots", 1);
        Armor armor4 = new Armor("Ritterhelm", "(Selten)", "maskulin", "Helmet", 3);
        //Armor zur Armorliste hinzufügen
        armorlist.add(armor0);
        armorlist.add(armor1);
        armorlist.add(armor2);
        armorlist.add(armor3);
        armorlist.add(armor4);
    }
    
    public int getarmordefense() {
        return defense;
    }
    
    public String getarmortype() {
        return armortype;
    }
}
