import java.util.ArrayList;
/**
 * Beschreiben Sie hier die Klasse Merchant.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Merchant extends NPC
{
    private ArrayList<Item> merchantshop = new ArrayList<>();
    /**
     * Konstruktor für Objekte der Klasse Merchant
     */
    public Merchant(ArrayList<Item> parsemerchantshop)
    {
        super();
        merchantshop = parsemerchantshop;
    }
    
    public void getmerchantshop() {
        for(int i = 0; i < merchantshop.size(); i++) {
            System.out.println(i + ": " + merchantshop.get(i) + " " + "Preis: " + merchantshop.get(i));
        }
    }
    
    public void buyitem(Player parseplayer, int arrayslot) {
        
    }

}
