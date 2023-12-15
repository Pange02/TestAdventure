import java.util.ArrayList;
import java.util.HashMap;
/**
 * Beschreiben Sie hier die Klasse Stage1.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Stage1 extends Stage
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private ArrayList<Item> startroomLoot = new ArrayList<>();
    private ArrayList<Item> room1Loot = new ArrayList<>();
    private ArrayList<Item> mob1Loot = new ArrayList<>();
    private ArrayList<Item> mob2Loot = new ArrayList<>();
    private ArrayList<Item> room5Loot = new ArrayList<>();

    private ArrayList<Item> room9Loot = new ArrayList<>();
    
    private HashMap merchantloot1 = new HashMap<Item, Integer>();
    private ArrayList<String> speakerdialogue = new ArrayList<>();
    /**
     * Konstruktor für Objekte der Klasse Stage1
     */
    public Stage1()
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
        speakerdialogue.add("INSERT DIALOGUE HERE" );
        Speaker speaker0 = new Speaker("Tom", speakerdialogue);
        Room startRoom = new Room(0, null, null, speaker0);
        
        // Raum 1
        room1Loot.add(Item.getitemfromlist("Potion", 2));
        Chest chest1 = new Chest(room1Loot);
        Room room1 = new Room(1, chest1, null, null);
        
        // Raum 2 mit einer Truhe
        Key key5 = new Key("Holzschlüssel", "Ein einfacher Holzschlüssel", "(Gewöhnlich)", "maskulin");
        mob2Loot.add(Item.getitemfromlist("Potion", 1));
        mob2Loot.add(key5);
        Mob mob2 = new Mob("Riesenspinne", 10, 15, ((Weapon) Item.getitemfromlist("Weapon", 3)), mob2Loot, 50, "feminin");
        Room room2 = new Room(2, null, mob2, null);
        
        // Raum 3 leer
        ArrayList<Item> mob3loot = new ArrayList<>();
        mob3loot.add(Item.getitemfromlist("Potion", 2));
        Mob mob3 = new Mob("Zombie", 5, 5, ((Weapon) Item.getitemfromlist("Weapon", 1)), mob3loot, 20, "maskulin");
        Room room3 = new Room(3, null, mob3, null);
        
        // Raum 4
        ArrayList<Item> room4loot = new ArrayList<>();
        room4loot.add(Item.getitemfromlist("Potion", 2));
        room4loot.add(Item.getitemfromlist("Weapon", 2));
        Chest chest4 = new Chest(room4loot);
        Room room4 = new Room(4, chest4, null, null);
        
        // Raum 5 eine Truhe
        merchantloot1.put(Item.getitemfromlist("Potion", 2), 100);
        merchantloot1.put(Item.getitemfromlist("Potion", 0), 50);
        Merchant merchant1 = new Merchant("TraderJoe", merchantloot1);
        Room room5 = new Room(5, null, null, merchant1);
        Lock lock5 = new Lock("Holzschloss", room5, "north", key5, "neutrum");
        
        // Raum 6 leer
        ArrayList<Item> room6loot = new ArrayList<>();
        room6loot.add(Item.getitemfromlist("Weapon", 3));
        Chest chest6 = new Chest(room6loot);
        Room room6 = new Room(6, chest6, null, null);
        
        // Raum 7 eine Truhe mit 2 Loot
        ArrayList<Item> boss7Loot = new ArrayList<>();
        boss7Loot.add(Item.getitemfromlist("Weapon", 3));
        boss7Loot.add(Item.getitemfromlist("Accessory", 1));
        boss7Loot.add(Item.getitemfromlist("Armor", 1));
        Hangman hangman7 = new Hangman("Henker", 15, 20, ((Weapon) Item.getitemfromlist("Weapon", 2)), boss7Loot, 50, "maskulin");
        Room room7 = new Room(7, null, hangman7, null);
        
        
        //Hier werden alle Verbindungen zwischen den Räumen eingetraden mit (Norden, Osten, Süden, Westen).
        // <Raumname>.setchonnectedrooms(<Raum im Norden>, <Raum im Osten>, <Raum im Süden>, <Raum im Westen>);
        
        // Startraum verbunden mit Raum 1, 4 und 6
        startRoom.setConnectedRooms(room1, null, null, null);
        // Raum 1 verbunden mit Startraum und Raum 2
        room1.setConnectedRooms(room3, room2, startRoom, null);
        // Raum 2 verbunden mit Raum 1 und 3
        room2.setConnectedRooms(null, null, null, room1);
        // Raum 3 verbunden mit Raum 2
        room3.setConnectedRooms(room4, null, room1, null);
        // Raum 4 verbunden mit Startraum und Raum 5
        room4.setConnectedRooms(null, null, room3, room5);
        // Raum 5 verbunden mit Raum 4
        room5.setConnectedRooms(room6, room4, null, room7);
        // Raum 6 verbunden mit Startraum und Raum 7
        room6.setConnectedRooms(null, null, room5, null);
        // Raum 7 verbunden mit Raum 6 und 8
        room7.setConnectedRooms(null, room6, null, null);

        
        roomlist.add(startRoom);
        roomlist.add(room1);
        roomlist.add(room2);
        roomlist.add(room3);
        roomlist.add(room4);
        roomlist.add(room5);
        roomlist.add(room6);
        roomlist.add(room7);
        
        playerstartroom = startRoom;
    }

}
