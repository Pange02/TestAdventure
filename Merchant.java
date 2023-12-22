import java.util.HashMap;
import java.util.Map;
/**
 * Diese Klasse wird von der Klasse �NPC� vererbt. Bei dem H�ndler k�nnen entsprechend f�r Coins andere Items im Shop erworben werden.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Merchant extends NPC
{
    private HashMap<Item, Integer> merchantshop = new HashMap<>();
    private boolean itembought;
    /**
     * Konstruktor f�r Objekte der Klasse Merchant
     */
    public Merchant(String parsename, HashMap<Item, Integer> parsemerchantshop)
    {
        super(parsename);
        merchantshop = parsemerchantshop;
        itembought = false;
    }
    
    // Ausgabe des "Einkaufsmen�s"
    @Override public void speak() {
        System.out.println("Hallo! Ich bin " + name);
        try {
            Thread.sleep(1500);
        }   
        catch(Exception e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("M�chtest du vielleicht etwas aus meinem Shop kaufen?");
        try {
            Thread.sleep(1500);
        }   
        catch(Exception e) {
            Thread.currentThread().interrupt();
        }
        getmerchantshop();
    }
    
    // Methode zum Zur�ckgeben der Ware des H�ndlers
    public void getmerchantshop() {
        for(Map.Entry<Item, Integer> entry : merchantshop.entrySet()) {
            Item key = entry.getKey();
            int value = entry.getValue();
            System.out.println(key.getitemname() + " - " + value + " Coins");
        }
    }
    
    // Methode damit der Spieler Items kaufen kann
    public void buyitem(Player parseplayer, String parseitemname) {
        for(Map.Entry<Item, Integer> entry : merchantshop.entrySet()) {
            if(entry.getKey().getitemname().toLowerCase().equals(parseitemname.toLowerCase())) {
                if(parseplayer.getcoins() >= entry.getValue()) {
                    parseplayer.removecoins(entry.getValue());
                    parseplayer.additemtoinventory(entry.getKey());
                    System.out.println("Du kaufst " + entry.getKey().getArtikel("akkusativ", "bestimmt") + " " + entry.getKey().getitemname() + " f�r " + entry.getValue() + " Coins.");
                    System.out.println("Du hast jetzt " + parseplayer.getcoins() + " Coins.");
                    itembought = true;
                }
                else {
                    System.out.println("Du hast f�r dieses Item nicht genug Coins!");
                }
            }
        }
        if(!itembought) {
            System.out.println("Dieses Item wird nicht angeboten.");
            System.out.println("Ok, vielleicht sp�ter!");
        }
    }
}
