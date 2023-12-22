import java.util.ArrayList;
/**
 * Diese Klasse wird von der Klasse „Item“ vererbt und erstellt besondere sammelbare Objekte. Diese Objekte erhöhen die Stärke, die Kritchance und den Kritschaden des Spielers.
 *
 * @author (your name)
 * @version 1.0.1
 */
public class Accessory extends Item
{
    //erstellen der Variablen für die Werte der Accesssories und der ArrayList zum Speichern.
    private int strength;
    private int critchance;
    private int critdamage;
    protected static ArrayList<Accessory> accessorylist = new ArrayList<>();
    /**
     * Konstruktor der Klasse für Accessories
     */
    public Accessory(String parsename, String parsedescription, String parserarity, String accessoryGender, int parsestrength, int parsecritchance, int parsecritdamage)
    {
        super(parsename, parsedescription, parserarity, accessoryGender);
        strength = parsestrength;
        critchance = parsecritchance;
        critdamage = parsecritdamage;
    }

    /**
     * In dieser Methode werden Accessories erstellt und in ihrer ArrayList gespeichert.
     */
    public static void createAccessories()
    {
        // Accessory (Name, Beschreibung, Seltenheit, Grammatikalisches Geschlecht, Stärkeboost, Chance auf kritische Treffer, Schadensboost bei kritischen Treffern)
        Accessory accessory0 = new Accessory("Artefakt des Himmels", "Ein von den Göttern geschaffenes Artefakt. Gefallen von einer Wolke unterstützt es nun Euch und verleiht euch Stärke.", "(Episch)", "neutrum", 3, 2, 5);
        Accessory accessory1 = new Accessory("Ring der Stärke", "Der Ring sieht aus wie ein Drache, der sich um Euren Finger wickelt. Ein roter Stein ist in das Maul des Drachens eingelassen. Der Stein leuchtet rot und strahlt die Stärke die der Ring Euch verleiht aus. Es sieht so aus als würde der Ring Feuer speien.", "(Selten)", "maskulin", 5, 2, 3);
        
        //Accessories zur accessorylist hinzufügen
        accessorylist.add(accessory0);
        accessorylist.add(accessory1);
    }
    
    //Rückgeben des Stärkeboosts eines Accessories
    public int getaccessorystrength() {
        return strength;
    }
    
    //Ausgabe der Beschreibung und Werte eines Accessories.
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
        System.out.println();
    }
}