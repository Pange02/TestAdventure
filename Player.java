import java.util.ArrayList;
import java.math.BigDecimal;
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
    private double healthcap;
    private double health;
    private double defense;
    private double weapondamage;
    private double damagemultiplicator;
    private double playerdamage;
    private double strength;
    private double critchance;
    private double critdamage;
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
        healthcap = 10;
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
            strength += ((Accessory) parseitem).getaccessorystrength();
            System.out.println(strength);
        }
    }
    
    public Item getitemfrominventory(int itemnumber) {
        return inventory.get(itemnumber);
    }
    
    public String getplayername() {
        return name;
    }
    
    public double getplayerhealth() {
        return health;
    }
    
    public void setplayerhealth(double parsehealth) {
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
        weapondamage = parseweapon.getweapondamage();
        damagemultiplicator = (1 + (strength)/10);
        playerdamage = Math.round(weapondamage * damagemultiplicator * 10.0) / 10.0;
        parsemob.setmobhealth(Math.round((parsemob.getmobhealth() - playerdamage) * 10.0) / 10.0);
    }
    
    public double getplayerdamage() {
        return playerdamage;
    }
}
