import java.util.ArrayList;
import java.util.Random;
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
    private int level;
    private int xp;
    private double healthcap;
    private double health;
    private double defense;
    private double weapondamage;
    private double damagemultiplier;
    private double playerdamage;
    private double strength;
    private double critchance;
    private double critdamage;
    private ArrayList<Item> inventory;
    private Armor helmet;
    private Armor chestplate;
    private Armor leggings;
    private Armor boots;
    private ArrayList<Accessory> accessories;
    private Room currentroom;
    
    /**
     * Konstruktor für Objekte der Klasse Player mit Namen und Anfangsraum.
     */
    public Player(String parsename, Room parseroom)
    {
        name = parsename;
        level = 1;
        xp = 0;
        healthcap = 10;
        health = 10;
        critchance = 10;
        critdamage = 50;
        helmet = null;
        chestplate = null;
        leggings = null;
        boots = null;
        inventory = new ArrayList<>();
        accessories = new ArrayList<>();
        currentroom = parseroom;
    }

    /**
     * Methode um die Items aus den Kisten zum Inventar des Spielers hinzuzufügen.
     */
    public void additemtoinventory(Item parseitem)
    {
        if(parseitem.getClass() == Weapon.class || parseitem.getClass() == Potion.class || parseitem.getClass() == Armor.class || parseitem.getClass() == Consumable.class) {
            inventory.add(parseitem);
        }
        else if(parseitem.getClass() == Accessory.class) {
            accessories.add((Accessory) parseitem);
            strength += ((Accessory) parseitem).getaccessorystrength();
        }
    }
    
    public Item getitemfrominventory(int itemnumber) {
        return inventory.get(itemnumber);
    }
    
    public Item getitemfromaccessories(int itemnumber) {
        return accessories.get(itemnumber);
    }
    
    public void removeitemfrominventory(int itemnumber) {
        inventory.remove(itemnumber);
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
        if(helmet != null) {
            System.out.println("Helm: " + helmet.getitemname());
        }
        else {
            System.out.println("Helm: ");
        }
        if(chestplate != null) {
            System.out.println("Brustplatte: " + chestplate.getitemname());
        }
        else {
            System.out.println("Brustplatte: ");
        }
        if(leggings != null) {
            System.out.println("Hose: " + leggings.getitemname());
        }
        else {
            System.out.println("Hose: ");
        }
        if(boots != null) {
            System.out.println("Schuhe: " + boots.getitemname());
        }
        else {
            System.out.println("Schuhe: ");
        }
        System.out.println("Deine Accessoires:");
        for(int i = 0; i < accessories.size(); i++) {
            System.out.println(i + " - " + accessories.get(i).getitemname() + " " + accessories.get(i).getitemrarity());
        }
    }
    
    public void getplayerstats() {
        System.out.println("Level: " + level + " Erfahrungspunkte: " + xp + " Fehlende Erfahrung zum nächsten Level: " + ((level+1)*(level+1) - xp));
        System.out.println(health + "/" + healthcap + " Leben  " + defense + " Verteidigung  ");
        System.out.println(strength + " Stärke  " + critchance + "% Crit Chance  " + critdamage + "% Crit Schaden");
    }
    
    public void attack(Mob parsemob, Weapon parseweapon) {
        weapondamage = parseweapon.getweapondamage();
        damagemultiplier = (1 + (strength)/10);
        Random critidentifier = new Random();
        if(critidentifier.nextInt(101) <= critchance) {
            playerdamage = Math.round(((weapondamage * damagemultiplier) * (1 + (critdamage/100))) * 10.0) / 10.0;
            System.out.println("Du landest einen kritischen Treffer!");
        }
        else {
            playerdamage = Math.round(weapondamage * damagemultiplier * 10.0) / 10.0;
        }
        parsemob.setmobhealth(Math.round((parsemob.getmobhealth() - playerdamage) * 10.0) / 10.0);
    }
    
    public double getplayerdamage() {
        return playerdamage;
    }
    
    public double getplayerdefense() {
        return defense;
    }
    
    public double getplayerhealthcap() {
        return healthcap;
    }
    
    public void equiparmor(Armor parsearmor) {
       if(parsearmor.getarmortype() == "Helmet") {
           if(helmet != null) {
               System.out.println("Du hast bereits einen Helm aufgesetzt. Du musst ihn erst ablegen.");
           }
           else {
               helmet = parsearmor;
               defense += helmet.getarmordefense();
               System.out.println("Du legst " + parsearmor.getArtikel("akkusativ", "bestimmt") + " " + parsearmor.getitemname() + " an.");
               removeitemfrominventory(inventory.indexOf(parsearmor));
           }
       }
       if(parsearmor.getarmortype() == "Chestplate") {
           if(chestplate != null) {
               System.out.println("Du hast bereits eine Brustplatte angelegt. Du musst sie erst ablegen.");
           }
           else {
               chestplate = parsearmor;
               defense += chestplate.getarmordefense();
               System.out.println("Du legst " + parsearmor.getArtikel("akkusativ", "bestimmt") + " " + parsearmor.getitemname() + " an.");
               removeitemfrominventory(inventory.indexOf(parsearmor));
           }
       }
       if(parsearmor.getarmortype() == "Leggings") {
           if(leggings != null) {
               System.out.println("Du hast bereits einen Beinschutz angelegt. Du musst ihn erst ablegen.");
           }
           else {
               leggings = parsearmor;
               defense += leggings.getarmordefense();
               System.out.println("Du legst " + parsearmor.getArtikel("akkusativ", "bestimmt") + " " + parsearmor.getitemname() + " an.");
               removeitemfrominventory(inventory.indexOf(parsearmor));
            }
       }
       if(parsearmor.getarmortype() == "Boots") {
           if(boots != null) {
               System.out.println("Du hast bereits Schuhe angezogen. Du musst sie erst ablegen."); 
           }
           else {
               boots = parsearmor;
               defense += boots.getarmordefense();
               System.out.println("Du legst " + parsearmor.getArtikel("akkusativ", "bestimmt") + " " + parsearmor.getitemname() + " an.");
               removeitemfrominventory(inventory.indexOf(parsearmor));
           }
       }
    }
    
    public void unequiparmor(String parsearmor) {
        if(parsearmor.equals("helmet")) {
            defense -= helmet.getarmordefense();
            additemtoinventory(helmet);
            System.out.println("Du legst den " + helmet.getitemname() + " ab.");
            helmet = null;
        }
        if(parsearmor.equals("chestplate")) {
            defense -= chestplate.getarmordefense();
            additemtoinventory(chestplate);
            System.out.println("Du legst die " + chestplate.getitemname() + " ab.");
            chestplate = null;
        }
        if(parsearmor.equals("leggings")) {
            defense -= leggings.getarmordefense();
            additemtoinventory(leggings);
            System.out.println("Du legst die " + leggings.getitemname() + " ab.");
            leggings = null;
        }
        if(parsearmor.equals("boots")) {
            defense -= boots.getarmordefense();
            additemtoinventory(boots);
            System.out.println("Du legst die " + boots.getitemname() + " ab.");
            boots = null;
        }
    }
    
    public void consume(Consumable parseconsumable) {
        if(health + parseconsumable.getconsumableeffect() > healthcap) {
            health = healthcap;
            System.out.println("Du hast durch den " + parseconsumable.getitemname() + " jetzt " + health + "/" + healthcap + " Leben.");
        }
        else if(health == healthcap) {
            System.out.println("Du hast bereits " + health + "/" + healthcap);
        }
        else {
            health += parseconsumable.getconsumableeffect();
            System.out.println("Du hast durch den " + parseconsumable.getitemname() + " jetzt " + health + "/" + healthcap + " Leben.");
        }
    }
    
    public void addexperience(int parsexp) {
        xp += parsexp;
        for(int i = level; i <= 100; i++) {
            if(xp >= ((i+1) * (i+1))) {
                level += 1;
                System.out.println("Du hast ein neues Level erreicht! Du bist jetzt Level: " + level);
            }
            else {
                break;
            }
        }
    }
}
