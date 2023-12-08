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
    private int coins;
    private double healthcap;
    private double health;
    private double defense;
    private double weapondamage;
    private double damagemultiplier;
    private double playerdamage;
    private double strength;
    private double critchance;
    private double critdamage;
    private boolean weakened;
    private int skillpoints;
    private int strengthskillpoints;
    private int defenseskillpoints;
    private int intelligenceskillpoints;
    private int luckskillpoints;
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
    public Player(String parsename, Stage parsestage)
    {
        name = parsename;
        level = 1;
        xp = 0;
        coins = 100;
        healthcap = 10;
        health = 10;
        critchance = 10;
        critdamage = 50;
        weakened = false;
        helmet = null;
        chestplate = null;
        leggings = null;
        boots = null;
        inventory = new ArrayList<>();
        accessories = new ArrayList<>();
        currentroom = parsestage.getstartroom();
    }

    /**
     * Methode um die Items aus den Kisten zum Inventar des Spielers hinzuzufügen.
     */
    public void additemtoinventory(Item parseitem)
    {
        if(parseitem.getClass() == Accessory.class) {
            accessories.add((Accessory) parseitem);
            strength += ((Accessory) parseitem).getaccessorystrength();
        }
        else {
            inventory.add(parseitem);
        }
    }
    
    // Methoden zum Verwenden / Entfernen von Items aus dem Inventar des Spielers
    public Item getitemfrominventory(int itemnumber) {
        return inventory.get(itemnumber);
    }
    
    public Item getitemfromaccessories(int itemnumber) {
        return accessories.get(itemnumber);
    }
    
    public void removeitemfrominventory(int itemnumber) {
        inventory.remove(itemnumber);
    }
    
    // Methoden zum Ausgeben von Name und Leben des Spielers
    public String getplayername() {
        return name;
    }
    
    public double getplayerhealth() {
        return health;
    }
    
    // Methode zum verändern des Leben des Spielers
    public void setplayerhealth(double parsehealth) {
        health = parsehealth;
    }
    
    // Methoden zum Verwalten der Coins des Spielers
    public int getcoins() {
        return coins;
    }
    
    public void addcoins(int parseamount) {
        coins += parseamount;
    }
    
    public void removecoins(int parseamount) {
        coins -= parseamount;
    }
    
    // Methoden zum Ausgeben oder verändern des Raumes in dem der Spieler sich befindet.
    public Room getcurrentroom() {
        return currentroom;
    }
    
    public void setcurrentroom(Room parseroom) {
        currentroom = parseroom;
    }
    
    // Gibt den Inventarinhalt des Spielers auf der Konsole aus.
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
    
    // Gibt die Werte des Spielers aus.
    public void getplayerstats() {
        System.out.println("Level: " + level + " Erfahrungspunkte: " + xp + " Fehlende Erfahrung zum nächsten Level: " + ((level+1)*(level+1) - xp));
        System.out.println("Skill: " + strengthskillpoints + " Stärke " + defenseskillpoints + " Verteidigung");
        System.out.println("Aktuell verfügbare Skillpunkte: " + skillpoints);
        System.out.println("Coins: " + coins);
        System.out.println(health + "/" + healthcap + " Leben  " + defense + " Verteidigung  ");
        System.out.println(strength + " Stärke  " + critchance + "% Crit Chance  " + critdamage + "% Crit Schaden");
    }
    
    // Methode zum Angreifen von Mobs übergeben werden das Mob und die verwendete Waffe.
    public void attack(Mob parsemob, Weapon parseweapon) {
        weapondamage = parseweapon.getweapondamage();
        strength += parseweapon.getstrength();
        critchance += parseweapon.getcritchance();
        critdamage += parseweapon.getcritdamage();
        damagemultiplier = (1 + (strength)/10);
        Random critidentifier = new Random();
        if(critidentifier.nextInt(101) <= critchance) {
            playerdamage = Math.round((weapondamage * damagemultiplier * (1 - (parsemob.getMobDefense()/(10 + parsemob.getMobDefense()))) * (1 + (critdamage/100))) * 10.0) / 10.0;
            System.out.println("Du landest einen kritischen Treffer!");
        }
        else {
            playerdamage = Math.round(weapondamage * damagemultiplier * (1 - (parsemob.getMobDefense()/(10 + parsemob.getMobDefense()))) * 10.0) / 10.0;
        }
        parsemob.setmobhealth(Math.round((parsemob.getmobhealth() - playerdamage) * 10.0) / 10.0);
        strength -= parseweapon.getstrength();
        critchance -= parseweapon.getcritchance();
        critdamage -= parseweapon.getcritdamage();
    }
    
    // Methoden zum Ausgeben des Schadens den der Spieler verursacht, seiner Verteidigungswerte und der maximalen Leben des Spielers
    public double getplayerdamage() {
        return playerdamage;
    }
    
    public double getplayerdefense() {
        return defense;
    }
    
    public double getplayerhealthcap() {
        return healthcap;
    }
    
    // Methode zum Anlegen von Rüstung 
    public void equiparmor(Armor parsearmor) {
       // Helme
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
       // Brustplatten
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
       // Beinschützer
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
       // Schuhe
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
    // Methode zum Ablegen von Rüstung
    public void unequiparmor(String parsearmor) {
        // Helme
        if(parsearmor.equals("helmet")) {
            defense -= helmet.getarmordefense();
            additemtoinventory(helmet);
            System.out.println("Du legst den " + helmet.getitemname() + " ab.");
            helmet = null;
        }
        // Brustplatten
        if(parsearmor.equals("chestplate")) {
            defense -= chestplate.getarmordefense();
            additemtoinventory(chestplate);
            System.out.println("Du legst die " + chestplate.getitemname() + " ab.");
            chestplate = null;
        }
        // Beinschützer
        if(parsearmor.equals("leggings")) {
            defense -= leggings.getarmordefense();
            additemtoinventory(leggings);
            System.out.println("Du legst die " + leggings.getitemname() + " ab.");
            leggings = null;
        }
        // Schuhe
        if(parsearmor.equals("boots")) {
            defense -= boots.getarmordefense();
            additemtoinventory(boots);
            System.out.println("Du legst die " + boots.getitemname() + " ab.");
            boots = null;
        }
    }
    
    // Methode zum Konsumieren von Konsumgegenständen
    public void consume(int inventorynumber) {
        if(getplayerhealth() + ((Consumable) getitemfrominventory(inventorynumber)).getconsumableeffect() == getplayerhealthcap()) {
            setplayerhealth(getplayerhealth() + ((Consumable) getitemfrominventory(inventorynumber)).getconsumableeffect());
            System.out.println("Du konsumierst " + getitemfrominventory(inventorynumber).getArtikel("akkusativ", "bestimmt") + " " + getitemfrominventory(inventorynumber).getitemname() + " und hast nun " + getplayerhealth() + " Leben.");
            removeitemfrominventory(inventorynumber);
        }
        else {
            if(getplayerhealth() + ((Consumable) getitemfrominventory(inventorynumber)).getconsumableeffect() >= getplayerhealthcap()) {
                setplayerhealth(getplayerhealthcap());
                System.out.println("Du konsumierst " + getitemfrominventory(inventorynumber).getArtikel("akkusativ", "bestimmt") + " " + getitemfrominventory(inventorynumber).getitemname() + " und hast nun " + getplayerhealth() + " Leben.");
                removeitemfrominventory(inventorynumber);
            }
            else {
                setplayerhealth(getplayerhealth() + ((Consumable) getitemfrominventory(inventorynumber)).getconsumableeffect());
                System.out.println("Du konsumierst " + getitemfrominventory(inventorynumber).getArtikel("akkusativ", "bestimmt") + " " + getitemfrominventory(inventorynumber).getitemname() + " und hast nun " +getplayerhealth() + " Leben.");
                removeitemfrominventory(inventorynumber);
            } 
        }
    }
    
    // Methode zum Tringen von Tränken
    public void drink(int inventorynumber) {
        if(((Potion) getitemfrominventory(inventorynumber)).getpotiontype() == "Healing") {
            if(getplayerhealth() == getplayerhealthcap()) { // Der Spieler kann sich nicht unendlich heilen
                System.out.println("Du hast bereits " + getplayerhealth() + "/" + getplayerhealthcap() + " Leben."); 
            }
            else {
                if(getplayerhealth() + ((Potion) getitemfrominventory(inventorynumber)).getpotioneffect() >= getplayerhealthcap()) {
                    setplayerhealth(getplayerhealthcap());
                    removeitemfrominventory(inventorynumber);
                    System.out.println("Du benutzt den Heilungstrank und hast nun " + getplayerhealth() + " Leben.");
                }
                else {
                    setplayerhealth(getplayerhealth() + ((Potion) getitemfrominventory(inventorynumber)).getpotioneffect());
                    removeitemfrominventory(inventorynumber);
                    System.out.println("Du benutzt den Heilungstrank und hast nun " + getplayerhealth() + " Leben.");
                } 
            }
        }
        // Tränke die nicht getrunken werden können:
        else if(((Potion) getitemfrominventory(inventorynumber)).getpotiontype() == "Damage") {
                System.out.println("Du kannst diesen Schadenstrank nur im Kampf verwenden");
        }
        else if(((Potion) getitemfrominventory(inventorynumber)).getpotiontype() == "Poison") {
                System.out.println("Du kannst diesen Gifttrank nur im Kampf verwenden");
        }
        // Falls der Spieler eine nicht vergebene Zahl aus seinem Inventar angibt.
        else {
                System.out.println("Diese Zahl aus deinem Inventar ist nicht belegt! Wähle eine gültige Zahl.");
        }
    }
    
    // Methode zum Hinzufügen von Erfahrungspunkten
    public void addexperience(int parsexp) {
        xp += parsexp;
        for(int i = level; i <= 100; i++) {
            if(xp >= ((i+1) * (i+1))) {
                level += 1;
                skillpoints += 1;
                System.out.println("Du hast ein neues Level erreicht und einen Skillpunkt verdient! Du bist jetzt Level: " + level);
            }
            else {
                break;
            }
        }
    }
    
    // Methoden zum Ausgeben und Verändern der Stärke des Spielers
    public double getPlayerStrength(){
        return strength;
    }
    
    public void setStrength(double amount){
        strength = amount;
    }
    
    // Methoden zum Setzten oder Feststellen eine Schwächeeffekt beim Spieler
    public void setWeakened(boolean parsestatus) {
        weakened = parsestatus;
    }
    
    public boolean getWeakened() {
        return weakened;
    }
    
    // Methode zum verwenden von Fähigkeitspunkten
    public void addskillpoints(String type, int amount) {
        // Stärke
        if(type.equals("strength")) {
            strength -= strengthskillpoints;
            strengthskillpoints += amount;
            skillpoints -= amount;
            strength += strengthskillpoints;
            System.out.println("Du fügst " + amount + " Skillpunkte zu Stärke hinzu. Du hast jetzt " + strengthskillpoints + " Punkte in Stärke");
        }
        // Verteidigung
        else if(type.equals("defense")) {
            defense -= defenseskillpoints;
            defenseskillpoints += amount;
            skillpoints -= amount;
            defense += defenseskillpoints;
            System.out.println("Du fügst " + amount + " Skillpunkte zu Verteidigung hinzu. Du hast jetzt " + defenseskillpoints + " Punkte in Verteidigung");
        }
        // Intelligenz (noch nicht fertig eventuell für spätere Versionen oder mit dem Vollversions DLC)
        else if(type.equals("intelligence")) {
            
        }
        // Glück (noch nicht fertig eventuell für spätere Versionen oder mit dem    Vollversions DLC)
        else if(type.equals("luck")) {
            
        }
    }
}
