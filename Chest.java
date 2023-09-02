import java.util.ArrayList;
import java.util.Random;
/**
 * Beschreiben Sie hier die Klasse Chest.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Chest
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private int number;
    boolean isopenable;
    private static ArrayList<Item> chestloot = new ArrayList<>();
    /**
     * Konstruktor für Objekte der Klasse Chest
     */
    public Chest()
    {
        isopenable = true;
        fillchest();
    }
    
    private void fillchest()
    {
        //Random r = new Random();
        //number = r.nextInt(Item.getitemlistlength());
        chestloot.add(Item.getitemfromlist(0));
        chestloot.add(Item.getitemfromlist(1));
    }
    
    public static void openchest(Chest parsechest)
    {
        if(parsechest.isopenable = true) {
            System.out.println("Die Kiste öffnet sich:");
            for(int i = 0; i < chestloot.size(); i++) {
                Player.additemtoinventory(chestloot.get(i));
                System.out.println("Du findest ein " + Item.getitemrarity(chestloot.get(i)) + " "+ Item.getitemname(chestloot.get(i)) + " mit " + Item.getitemdamage(chestloot.get(i)) + " Schaden.");
            }
            parsechest.isopenable = false;
        }
        else {
            System.out.println("Du hast diese Kiste bereits geöffnet!");
        }
    }
    
}
