import java.util.HashMap;
import java.util.Map;
/**
 * Beschreiben Sie hier die Klasse Merchant.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Merchant extends NPC
{
    private HashMap<Item, Integer> merchantshop = new HashMap<>();
    /**
     * Konstruktor für Objekte der Klasse Merchant
     */
    public Merchant(String parsename, HashMap<Item, Integer> parsemerchantshop)
    {
        super(parsename);
        merchantshop = parsemerchantshop;
    }
    
    @Override public void speak() {
        System.out.println("Hallo! Ich bin " + name);
        try {
            Thread.sleep(1500);
        }   
        catch(Exception e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Möchtest du vielleicht etwas aus meinem Shop kaufen?");
        try {
            Thread.sleep(1500);
        }   
        catch(Exception e) {
            Thread.currentThread().interrupt();
        }
        getmerchantshop();
    }
    
    public void getmerchantshop() {
        for(Map.Entry<Item, Integer> entry : merchantshop.entrySet()) {
            Item key = entry.getKey();
            int value = entry.getValue();
            System.out.println(key.getitemname() + " - " + value + " Coins");
        }
    }
    
    public void buyitem(Player parseplayer, String parseitemname) {
        for(Map.Entry<Item, Integer> entry : merchantshop.entrySet()) {
            if(entry.getKey().getitemname().toLowerCase().equals(parseitemname.toLowerCase())) {
                if(parseplayer.getcoins() >= entry.getValue()) {
                    parseplayer.removecoins(entry.getValue());
                    parseplayer.additemtoinventory(entry.getKey());
                    System.out.println("Du kaufst " + entry.getKey().getArtikel("akkusativ", "bestimmt") + " " + entry.getKey().getitemname() + " für " + entry.getValue() + " Coins.");
                    System.out.println("Du hast jetzt " + parseplayer.getcoins() + " Coins.");
                }
                else {
                    System.out.println("Du hast für dieses Item nicht genug Coins!");
                }
            }
        }
    }
}
