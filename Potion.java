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
    public Potion(String parsename, String parsedescription, String parserarity, String potionGender, String parsetype, int parseeffect)
    {
        super(parsename, parsedescription, parserarity, potionGender);
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
        Potion potion0 = new Potion("Gifttrank", "Ein Gifttrank", "(Gewöhnlich)", "maskulin", "Damage", 1);
        Potion potion1 = new Potion("Schadenstrank", "Ein Schadestrank", "(Ungewöhnlich)", "maskulin", "Damage", 2);
        Potion potion2 = new Potion("Heilungstrank", "Ein Heilungstrank", "(Selten)", "maskulin", "Healing", 3);
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
    
    @Override public void getiteminfo() {
        descriptionstring = " " + description;
        statsstring = " " + "Effekt: " + effect;
        spaces = Math.max(descriptionstring.length(), statsstring.length());
        for(int i = 0; i <= (spaces - name.length())/2 - 1; i++) {
            System.out.print("-");
        }
        System.out.print(" ");
        System.out.print(name);
        System.out.print(" ");
        for(int i = 0; i <= (spaces - name.length())/2 - 1; i++) {
            if(i == (spaces - name.length())/2 - 1) {
                System.out.println("-");
            }
            else {
                System.out.print("-");
            }
        }
        System.out.println(descriptionstring);
        System.out.println(statsstring);
        for(int i = 0; i <= (spaces - rarity.length() + 1)/2 - 1; i++) {
            System.out.print("-");
        }
        System.out.print(" ");
        System.out.print(rarity);
        System.out.print(" ");
        for(int i = 0; i <= (spaces - rarity.length() + 1)/2 - 1; i++) {
            System.out.print("-");
        }
    }
}
