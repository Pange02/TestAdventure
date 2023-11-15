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
    public Consumable(String parsename, String parsedescription, String parserarity, String parsegender, int parseeffect)
    {
        super(parsename, parsedescription, parserarity, parsegender);
        effect = parseeffect;
    }
    
    public static void createConsumables() {
        Consumable consumable1 = new Consumable("Apfel", "Was ist schlimmer als ein angebissener Apfel mit einem ganzen Wurm drin? ... Ein angebissener Apfel mit einem halben Wurm.", "(Gewöhnlich)", "maskulin", 2);
        consumablelist.add(consumable1);
    }
    
    public int getconsumableeffect() {
        return effect;
    }
    
    @Override public void getiteminfo() {
        descriptionstring = " " + description;
        statsstring = " " + "Effekt: +" + effect + " HP";
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
        System.out.println();
    }
}
