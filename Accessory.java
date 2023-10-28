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
    private int critchance;
    private int critdamage;
    protected static ArrayList<Accessory> accessorylist = new ArrayList<>();
    /**
     * Constructor for objects of class Accessories
     */
    public Accessory(String parsename, String parsedescription, String parserarity, String accessoryGender, int parsestrength, int parsecritchance, int parsecritdamage)
    {
        super(parsename, parsedescription, parserarity, accessoryGender);
        strength = parsestrength;
        critchance = parsecritchance;
        critdamage = parsecritdamage;
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
        Accessory accessory0 = new Accessory("Artefakt des Himmels", "Ein Artefakt, das von den Göttern erschaffen wurde", "(Episch)", "neutrum", 3, 2, 5);
        Accessory accessory1 = new Accessory("Ring der Stärke", "Der Ring ist strahlend und vibriert leicht", "(Selten)", "maskulin", 5, 2, 3);
        //Accessories zur accessorylist hinzufügen
        accessorylist.add(accessory0);
        accessorylist.add(accessory1);
    }
    
    public int getaccessorystrength() {
        return strength;
    }
    
    @Override public void getiteminfo() {
        descriptionstring = " " + description;
        statsstring = " " + "Stärke: " + strength + " Crit Chance: " + critchance + " Crit Schaden: " + critdamage;
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