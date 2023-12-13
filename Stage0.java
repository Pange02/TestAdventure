import java.util.ArrayList;
import java.util.HashMap;
/**
 * Beschreiben Sie hier die Klasse Stage0.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Stage0 extends Stage
{
    private ArrayList<Item> startroomLoot = new ArrayList<>();
    private ArrayList<Item> mob1Loot = new ArrayList<>();
    private ArrayList<Item> room2Loot = new ArrayList<>();
    private ArrayList<Item> mob2Loot = new ArrayList<>();
    private ArrayList<Item> room3Loot = new ArrayList<>();
    private ArrayList<Item> mob3Loot = new ArrayList<>();
    private ArrayList<Item> room4Loot = new ArrayList<>();
    private ArrayList<Item> room5Loot = new ArrayList<>();
    private ArrayList<Item> room7Loot = new ArrayList<>();
    private ArrayList<Item> room9Loot = new ArrayList<>();
    private ArrayList<Item> bossLoot = new ArrayList<>();
    
    private HashMap merchantloot1 = new HashMap<Item, Integer>();
    private ArrayList<String> speakerdialogue = new ArrayList<>();
    
    /**
     * Konstruktor für Objekte der Klasse Stage0
     */
    public Stage0()
    {
        /* Hier werden alle Räume im Spiel nach folgendem Schema erstellt:
         * 
         * Items aus der Liste zum Loot der Kiste hinzufügen
         * <Arraylist für den Loot des Raumes>.add(Item.getitemfromlist("<Itemliste (z.B. Weapon)>", <Platz des Items in der Liste>));
         * Truhe dem Raum hinzufügen und Loot zuweisen
         * Chest <Truhenname> = new Chest(ArrayList mit Loot);
         * Den Raum erstellen und festlegen, welche und welches Mob im Raum anzutreffen sind
         * Room <Raumname> = new Room(<Truhenname> / null, <Mobname> / null);
         * 
         */
        
        // Spawnraum mit einem Holzschwert zum Anfang
        startroomLoot.add(Item.getitemfromlist("Weapon", 1));
        merchantloot1.put(Item.getitemfromlist("Potion", 2), 100);
        merchantloot1.put(Item.getitemfromlist("Potion", 0), 50);
        Chest startChest = new Chest(startroomLoot);
        speakerdialogue.add("Tutorial Basics");
        Speaker startSpeaker = new Speaker("Harry", speakerdialogue);
        Room startRoom = new Room(0, startChest, null, startSpeaker);
        
        
        
        // Raum 1 Tutorial Fight
        speakerdialogue.add("Tutorial Fight");
        Speaker speaker1 = new Speaker("Harry", speakerdialogue);
        mob1Loot.add(Item.getitemfromlist("Potion", 2));
        Mob mob1 = new Mob("Troll", 5, 10, ((Weapon) Item.getitemfromlist("Weapon", 1)), mob1Loot, 10, "maskulin");
        Room room1 = new Room(1, null, mob1, speaker1);
        
        // Raum 2 mit Mob
        room2Loot.add(Item.getitemfromlist("Potion", 1));
        Chest chest2 = new Chest(room2Loot);
        mob2Loot.add(Item.getitemfromlist("Consumable", 0));
        Mob mob2 = new Mob("Zombie", 5, 10, ((Weapon) Item.getitemfromlist("Weapon", 1)), mob1Loot, 10, "maskulin");
        Room room2 = new Room(2, chest2, mob2, null);
        
        // Raum 3 mit Schlüssel
        Key key1 = new Key("Holzschlüssel", "Ein einfacher Holzschlüssel", "(Gewöhnlich)", "maskulin");
        room3Loot.add(key1);
        Chest chest3 = new Chest(room3Loot);
        Room room3 = new Room(3, chest3, null, null);
        Lock lock4 = new Lock("Holzschloss", room3, "north", key1, "neutrum");
        
        // Raum 4
        mob3Loot.add(Item.getitemfromlist("Consumable", 0));
        Mob mob3 = new Mob("Zombie", 5, 10, ((Weapon) Item.getitemfromlist("Weapon", 1)), mob1Loot, 10, "maskulin");
        Room room4 = new Room(4, null, mob3, null);
        
        // Raum 5 Boss
        speakerdialogue.add("Boss Fight");
        Speaker speakerBoss = new Speaker("Harry", speakerdialogue);
        bossLoot.add(Item.getitemfromlist("Armor", 1));
        bossLoot.add(Item.getitemfromlist("Potion", 2));
        Henchman hans = new Henchman("Hans der Handlanger", 10, 50, ((Weapon) Item.getitemfromlist("Weapon", 3)),bossLoot , 20, "");
        room5Loot.add(Item.getitemfromlist("Weapon", 2));
        room5Loot.add(Item.getitemfromlist("Consumable", 0));
        Chest chest5 = new Chest(room5Loot);
        Room room5 = new Room(5, chest5, hans, speakerBoss);
        
        
        //Hier werden alle Verbindungen zwischen den Räumen eingetraden mit (Norden, Osten, Süden, Westen).
        // <Raumname>.setchonnectedrooms(<Raum im Norden>, <Raum im Osten>, <Raum im Süden>, <Raum im Westen>);
        
        // Startraum verbunden mit Raum 1
        startRoom.setConnectedRooms(room1, null, null, null);
        // Raum 1 verbunden mit Startraum und Raum 2
        room1.setConnectedRooms(room2, null, startRoom, null);
        // Raum 2 verbunden mit Raum 3 und 1
        room2.setConnectedRooms(null, null, room1, room3);
        // Raum 3 verbunden mit Raum 4 und 2
        room3.setConnectedRooms(room4, null, null, room2);
        // Raum 4 verbunden mit Raum 5 und 3
        room4.setConnectedRooms(room5, null, room3, null);
        // Raum 5 verbunden mit Raum 4
        room5.setConnectedRooms(null, null, room4, null);
       
        
        roomlist.add(startRoom);
        roomlist.add(room1);
        roomlist.add(room2);
        roomlist.add(room3);
        roomlist.add(room4);
        roomlist.add(room5);
        
        playerstartroom = startRoom;
    }
}
