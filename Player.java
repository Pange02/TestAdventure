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
    public void additemtoinventory(Item parseitem)
    {
        if(parseitem.getClass() == Weapon.class || parseitem.getClass() == Potion.class) {
            inventory.add(parseitem);
        }
        else if(parseitem.getClass() == Armor.class) {
            armor.add(parseitem);
        }
        else if(parseitem.getClass() == Accessory.class) {
            accessories.add(parseitem);
        }
    }
    
    public Item getitemfrominventory(int itemnumber) {
        return inventory.get(itemnumber);
    }
    
    public String getplayername() {
        return name;
    }
    
    public int getplayerhealth() {
        return health;
    }
    
    public void setplayerhealth(int parsehealth) {
        health = parsehealth;
    }
    
    public Room getcurrentroom() {
        return currentroom;
    }
    
    public void setcurrentroom(Room parseroom) {
        currentroom = parseroom;
    }
    
    /**
     * Gibt den Inventarinhalt des Spielers auf der Konsole aus.
     */
    public void getinventorycontent() {
        System.out.println("Dein Inventar:");
        for(int i = 0; i < inventory.size(); i++) {
            System.out.println(i + " - " + inventory.get(i).getitemname() + " " + inventory.get(i).getitemrarity());
        }
        System.out.println("Deine Rüstung:");
        for(int i = 0; i < armor.size(); i++) {
            System.out.println(i + " - " + armor.get(i).getitemname() + " " + armor.get(i).getitemrarity());
        }
        System.out.println("Deine Accessoires:");
        for(int i = 0; i < accessories.size(); i++) {
            System.out.println(i + " - " + accessories.get(i).getitemname() + " " + accessories.get(i).getitemrarity());
        }
    }
    
    public void attack(Mob parsemob, Weapon parseweapon) {
        parsemob.setmobhealth(parsemob, parsemob.getmobhealth(parsemob) - parseweapon.getweapondamage());
    }
}
