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
    private int slots;
    private boolean isopenable;
    private ArrayList<Item> chestloot = new ArrayList<>();
    /**
     * Konstruktor für Objekte der Klasse Chest
     */
    public Chest(int chestslots)
    {
        slots = chestslots;
        isopenable = true;
        fillchest();
        openchest();
    }

    /**
     * Ein Beispiel einer Methode - ersetzen Sie diesen Kommentar mit Ihrem eigenen
     * 
     * @param  y    ein Beispielparameter für eine Methode
     * @return        die Summe aus x und y
     */
    public void getslots()
    {
        // tragen Sie hier den Code ein
        System.out.println(slots);
    }
    
    public void fillchest()
    {
        Random r = new Random();
        number = r.nextInt(Item.getitemlistlength());
        chestloot.add(Item.getitemfromlist(number));
    }
    
    private void openchest()
    {
        System.out.println("Du findest ein " + Item.getitemname(chestloot.get(0)) + " mit " + Item.getitemdamage(chestloot.get(0)) + " Schaden.");
    }
}
