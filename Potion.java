import java.util.ArrayList;
/**
 * Diese Klasse erstellt verschieden Tr�nke, welche verschieden Eigenschaften und Effekte haben.
 *
 * @author (your name)
 * @version 1.0.1
 */
public class Potion extends Item
{
    // die Variablen f�r die Werte der Tr�nke + ArrayList zum Speichern der Tr�nke
    private int effect;
    private String type;
    protected static ArrayList<Potion> potionlist = new ArrayList<>();
    
    /**
     * Konstruktor der Trankklasse
     */
    
    public Potion(String parsename, String parsedescription, String parserarity, String potionGender, String parsetype, int parseeffect)
    {
        super(parsename, parsedescription, parserarity, potionGender);
        effect = parseeffect;
        type = parsetype;
    }

    /**
     * In dieser Methode werden die Tr�nke erstellt und in der ArrayList gespeichert.
     */
    public static void createPotions()
    {
        // Potions (Name, Beschreibung, Seltenheit, Grammatikalisches Geschlecht, Art des Trankes, St�rke des Effektes)
        Potion potion0 = new Potion("Gifttrank", "Eine Flasche  mit gr�ner leicht brodelnder Fl�ssigkeit. Die Hexe die diesen Trank braute hinterlie� eine Notiz an der Flasche: 'nicht trinken'.", "(Gew�hnlich)", "maskulin", "Poison", 1);
        Potion potion1 = new Potion("Schadenstrank", "Ein m�chtiger Trank, gebraut von den Hexen der Sumpfgebiete. Wenn er einen Gegner trifft verursacht er Schaden.", "(Ungew�hnlich)", "maskulin", "Damage", 2);
        Potion potion2 = new Potion("Heilungstrank", "Rot leuchtend stahlt dieser Trank w�rme aus. Wenn Ihr die Flasche ber�hrt wirbeln kleine orangene Partikel vom Boden der Flasche auf und verteilen sich dort wo die Finger die Flasche ber�hren.", "(Selten)", "maskulin", "Healing", 3);
        
        //Potions zur Potionlist hinzuf�gen
        potionlist.add(potion0);
        potionlist.add(potion1);
        potionlist.add(potion2);
    }
    
    //R�ckgabe der Art des Trankes
    public String getpotiontype() {
        return type;
    }
    
    //R�ckgabe der St�rke des Effekt
    public int getpotioneffect() {
        return effect;
    }
    
    //Ausgeben der Beschreibung und der Werte eines Trankes.
    @Override public void getiteminfo() {
        descriptionstring = " " + description;
        if(type == "Damage") {
            statsstring = " " + "Effekt: " + effect + " Schaden";
        }
        else if(type == "Poison") {
            statsstring = " " + "Effekt: " + effect + " Schaden �ber 3 Runden";
        }
        else if(type == "Healing") {
            statsstring = " " + "Effekt: +" + effect + " HP";
        }
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
