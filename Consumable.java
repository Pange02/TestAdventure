import java.util.ArrayList;
/**
 * Diese Klasse erstellt konsumierbare Gegenst�nde, welche zum regenerieren genutzt werden k�nnen.
 * 
 * @author (your name)
 * @version 1.0.1
 */
public class Consumable extends Item
{
    //der Effekt eines konsumierbaren Gegenstandes und die ArrayList zum Speichern der Gegenst�nde werden erstellt.
    private int effect;
    protected static ArrayList<Consumable> consumablelist = new ArrayList<>();
    
    /**
     * Konstruktor der Klasse f�r konsumierbare Gegenst�nde
     */
    public Consumable(String parsename, String parsedescription, String parserarity, String parsegender, int parseeffect)
    {
        super(parsename, parsedescription, parserarity, parsegender);
        effect = parseeffect;
    }
    
    /**
     * In dieser Methode werden Consumalbes erstellt und ihrer ArrayList hinzugef�gt.
     */
    
    public static void createConsumables() {
        
        //Consumable (Name, Beschreibung, Seltenheit, Gramatikalisches Geschlecht, St�rke der Heilung.
        Consumable consumable1 = new Consumable("Apfel", "Was ist schlimmer als ein angebissener Apfel mit einem ganzen Wurm drin? ... Ein angebissener Apfel mit einem halben Wurm.", "(Gew�hnlich)", "maskulin", 2);
        
        //Consumable zur Consumablelist hinzuf�gen
        consumablelist.add(consumable1);
    }
    
    //R�ckgabe der St�rke der Heilung eines Consumables
    public int getconsumableeffect() {
        return effect;
    }
    
    //Ausgabe der Beschreibung und der Heilungswerte eines Consumables
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
