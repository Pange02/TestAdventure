import java.util.ArrayList;
/**
 * Beschreiben Sie hier die Klasse Player.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Player
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private String name;
    private static ArrayList<Item> inventory = new ArrayList<>();
    private Room currentroom;
    /**
     * Konstruktor für Objekte der Klasse Player
     */
    public Player(String parsename, Room parseroom)
    {
        name = parsename;
        currentroom = parseroom;
    }

    /**
     * Ein Beispiel einer Methode - ersetzen Sie diesen Kommentar mit Ihrem eigenen
     * 
     * @param  y    ein Beispielparameter für eine Methode
     * @return        die Summe aus x und y
     */
    public static void additemtoinventory(Item parseitem)
    {
        inventory.add(parseitem);        
    }
    
    public static String getplayername(Player parseplayer) {
        return parseplayer.name;
    }
    
    public static Room getcurrentroom(Player parseplayer) {
        return parseplayer.currentroom;
    }
    
    public static void getinventorycontent() {
        System.out.println("Dein Inventar:");
        for(int i = 0; i < inventory.size(); i++) {
            System.out.println(i + " - " + Item.getitemname(inventory.get(i)));
        }
    }
}
