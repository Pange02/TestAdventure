import java.util.ArrayList;
/**
 * Beschreiben Sie hier die Klasse Player.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Player
{
    // Instanzvariablen - Attribute eines Spielers
    private String name;
    private ArrayList<Item> inventory;
    private ArrayList<Item> accessories;
    private Room currentroom;
    
    /**
     * Konstruktor für Objekte der Klasse Player mit Namen und Anfangsraum.
     */
    public Player(String parsename, Room parseroom)
    {
        name = parsename;
        inventory = new ArrayList<>();
        accessories = new ArrayList<>();
        currentroom = parseroom;
    }

    /**
     * Methode um die Items aus den Kisten zum Inventar des Spielers hinzuzufügen.
     */
    public void additemtoinventory(Player parseplayer, Item parseitem)
    {
        if(parseitem.getitemtype(parseitem) == "Weapon" || parseitem.getitemtype(parseitem) == "Potion") {
            parseplayer.inventory.add(parseitem);
        }
        else if(parseitem.getitemtype(parseitem) == "Accessory") {
            parseplayer.accessories.add(parseitem);
        }
    }
    
    public String getplayername(Player parseplayer) {
        return parseplayer.name;
    }
    
    public Room getcurrentroom(Player parseplayer) {
        return parseplayer.currentroom;
    }
    
    public void setcurrentroom(Player parseplayer, Room parseroom) {
        parseplayer.currentroom = parseroom;
    }
    
    /**
     * Gibt den Inventarinhalt des Spielers auf der Konsole aus.
     */
    public void getinventorycontent(Player parseplayer) {
        System.out.println("Dein Inventar:");
        for(int i = 0; i < parseplayer.inventory.size(); i++) {
            System.out.println(i + " - " + parseplayer.inventory.get(i).getitemname(parseplayer.inventory.get(i)) + " " + parseplayer.inventory.get(i).getitemrarity(parseplayer.inventory.get(i)));
        }
        System.out.println("Deine Accessoires:");
        for(int i = 0; i < parseplayer.accessories.size(); i++) {
            System.out.println(i + " - " + parseplayer.accessories.get(i).getitemname(parseplayer.accessories.get(i)) + " " + parseplayer.accessories.get(i).getitemrarity(parseplayer.accessories.get(i)));
        }
    }
}
