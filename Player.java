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
    private int health;
    private int defense;
    private ArrayList<Item> inventory;
    private ArrayList<Item> armor;
    private ArrayList<Item> accessories;
    private Room currentroom;
    
    /**
     * Konstruktor für Objekte der Klasse Player mit Namen und Anfangsraum.
     */
    public Player(String parsename, Room parseroom)
    {
        name = parsename;
        health = 10;
        inventory = new ArrayList<>();
        armor = new ArrayList<>();
        accessories = new ArrayList<>();
        currentroom = parseroom;
    }

    /**
     * Methode um die Items aus den Kisten zum Inventar des Spielers hinzuzufügen.
     */
    public void additemtoinventory(Player parseplayer, Item parseitem)
    {
        if(parseitem.getClass() == Weapon.class || parseitem.getClass() == Potion.class) {
            parseplayer.inventory.add(parseitem);
        }
        else if(parseitem.getClass() == Armor.class) {
            parseplayer.armor.add(parseitem);
        }
        else if(parseitem.getClass() == Accessory.class) {
            parseplayer.accessories.add(parseitem);
        }
    }
    
    public Item getitemfrominventory(Player parseplayer, int itemnumber) {
        return parseplayer.inventory.get(itemnumber);
    }
    
    public String getplayername(Player parseplayer) {
        return parseplayer.name;
    }
    
    public int getplayerhealth(Player parseplayer) {
        return parseplayer.health;
    }
    
    public void setplayerhealth(Player parseplayer, int parsehealth) {
        parseplayer.health = parsehealth;
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
            System.out.println(i + " - " + parseplayer.inventory.get(i).getitemname() + " " + parseplayer.inventory.get(i).getitemrarity());
        }
        System.out.println("Deine Rüstung:");
        for(int i = 0; i < parseplayer.armor.size(); i++) {
            System.out.println(i + " - " + parseplayer.armor.get(i).getitemname() + " " + parseplayer.armor.get(i).getitemrarity());
        }
        System.out.println("Deine Accessoires:");
        for(int i = 0; i < parseplayer.accessories.size(); i++) {
            System.out.println(i + " - " + parseplayer.accessories.get(i).getitemname() + " " + parseplayer.accessories.get(i).getitemrarity());
        }
    }
    
    public void attack(Player parseplayer, Mob parsemob, Weapon parseweapon) {
        parsemob.setmobhealth(parsemob, parsemob.getmobhealth(parsemob) - parseweapon.getweapondamage());
    }
}
