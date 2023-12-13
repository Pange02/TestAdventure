import java.util.ArrayList;
import java.util.HashMap;
/**
 * Write a description of class Stage2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Stage2 extends Stage
{
    private ArrayList<Item> startroomLoot = new ArrayList<>();
    private ArrayList<Item> room2Loot = new ArrayList<>();
    private ArrayList<Item> room3Loot = new ArrayList<>();
    private ArrayList<Item> room4Loot = new ArrayList<>();
    private ArrayList<Item> room5Loot = new ArrayList<>();
    private ArrayList<Item> room7Loot = new ArrayList<>();
    private ArrayList<Item> room9Loot = new ArrayList<>();
    private ArrayList<Item> room10Loot = new ArrayList<>();
    private ArrayList<Item> room11Loot = new ArrayList<>();
    private ArrayList<Item> room12Loot = new ArrayList<>();
    private ArrayList<Item> room13Loot = new ArrayList<>();
    
    private ArrayList<Item> mob1Loot = new ArrayList<>();
    private ArrayList<Item> mob2Loot = new ArrayList<>();
    private ArrayList<Item> mob3Loot = new ArrayList<>();
    private ArrayList<Item> mob4Loot = new ArrayList<>();
    private ArrayList<Item> mob5Loot = new ArrayList<>();
    private ArrayList<Item> mob6Loot = new ArrayList<>();
    private ArrayList<Item> mob7Loot = new ArrayList<>();
    private ArrayList<Item> mob8Loot = new ArrayList<>();
    private ArrayList<Item> mob9Loot = new ArrayList<>();
    private ArrayList<Item> mob10Loot = new ArrayList<>();
    private ArrayList<Item> bossLoot = new ArrayList<>();
    
    private HashMap merchantloot1 = new HashMap<Item, Integer>();
    private ArrayList<String> speakerdialogue = new ArrayList<>();
    
    public Stage2()
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
        
        //erster Raum der Stage
        speakerdialogue.add("Willkommen in Stage 3!");
        Speaker startSpeaker = new Speaker("Harry", speakerdialogue);
        Room startRoom = new Room(1, null, null, startSpeaker);
        
        //zweiter Raum der Stage
        mob1Loot.add(Item.getitemfromlist("Consumable", 0));
        Mob mob1 = new Mob("Troll", 7, 15, ((Weapon) Item.getitemfromlist("Weapon", 2)), mob1Loot, 20, "maskulin");
        Room room2 = new Room(2, null, mob1, null);
        
        //dritter Raum der Stage
        Key key1 = new Key("Holzschlüssel", "Ein einfacher Holzschlüssel", "(Gewöhnlich)", "maskulin");
        room3Loot.add(key1);
        room3Loot.add(Item.getitemfromlist("Weapon", 2));
        Chest chest1 = new Chest(room3Loot);
        mob2Loot.add(Item.getitemfromlist("Potion", 2));
        Mob mob2 = new Mob("Skellett", 4, 10, ((Weapon) Item.getitemfromlist("Weapon", 3)), mob2Loot, 18, "neutrum");
        Room room3 = new Room(3, chest1, mob2, null);
        
        //vierter Raum der Stage
        Room room4 = new Room(4, null, mob2, null);
        Lock lock1 = new Lock("Holzschloss",room4 , "east", key1, "neutrum");
        
        //fünfter Raum der Stage
        room5Loot.add(Item.getitemfromlist("Armor", 2));
        Chest chest2 = new Chest(room4Loot);
        Room room5 = new Room(5, chest2, null, null);
        
        //sechster Raum der Stage
        merchantloot1.put(Item.getitemfromlist("Potion", 2), 100);
        merchantloot1.put(Item.getitemfromlist("Potion", 0), 50);
        Merchant merchant1 = new Merchant("Tante Emma", merchantloot1);
        Room room6 = new Room(6, null, null, merchant1);
        
        //siebter Raum der Stage
        Key key2 = new Key("Metallschlüssel", "Ein einfacher Metallschlüssel", "(Gewöhnlich)", "maskulin");
        room3Loot.add(key2);
        Chest chest3 = new Chest(room7Loot);
        mob3Loot.add(Item.getitemfromlist("Potion", 2));
        Mob mob3 = new Mob("Spinne", 3, 7, ((Weapon) Item.getitemfromlist("Weapon", 1)), mob2Loot, 10, "feminin");
        Room room7 = new Room(7, chest3, mob3, null);
        
        //achter Raum der Stage
        Room room8 = new Room(8, null, mob2, null);
        
        //neunter Raum der Stage
        Room room9 = new Room(9, null, mob3, null);
        Lock lock2 = new Lock("Metallschloss",room9 , "north", key2, "neutrum");
        
        //zehnter Raum der Stage
        Room room10 = new Room(10, null, mob2, null);
        
        //elfter Raum der Stage
        Room room11 = new Room(11, null, mob2, null);
        
        //zwölfter Raum der Stage
        Room room12 = new Room(12, null, mob3, null);
        
        //dreizehnter Raum der Stage
        bossLoot.add(Item.getitemfromlist("Weapon", 5));
        Hangman hangman = new Hangman("Sir Archibald Duncan", 100, 50, ((Weapon) Item.getitemfromlist("Weapon", 5)), bossLoot, 200, "maskulin");
        Room room13 = new Room(13, null, null, null);
        
        //Verbinden der Räume miteinander
        
        //Startraum verbunden mit Raum 2.
        startRoom.setConnectedRooms(room2, null, null, null);
        
        //Raum 2 verbunden mit: Raum 3, Raum 4, Raum 1 und Raum 8
        room2.setConnectedRooms(room3, room4, startRoom, room8);
        
        //Raum 3 verbunden mit: Raum 2
        room3.setConnectedRooms(null, null, room2, null);
        
        //Raum 4 verbunden mit: Raum 5 und Raum 2
        room4.setConnectedRooms(null, room5, null, room2);
        
        //Raum 5 verbunden mit: Raum 6 und Raum 4
        room5.setConnectedRooms(room6, null, null, room4);
        
        //Raum 6 verbunden mit: Raum 7 und Raum 5
        room6.setConnectedRooms(null, room7, room5, null);
        
        //Raum 7 verbunden mit: Raum 6
        room7.setConnectedRooms(null, null, room6, null);
        
        //Raum 8 verbunden mit Raum 2 und Raum 9
        room8.setConnectedRooms(null, room2, null, room9);
        
        //Raum 9 verbunden mit Raum 10 und Raum 8
        room9.setConnectedRooms(room10, room8, null, null);
        
        //Raum 10 verbunden mit Raum 11 und Raum 9
        room10.setConnectedRooms(room11, null, room9, null);
        
        //Raum 11 verbuden mit Raum 10 und Raum 12
        room11.setConnectedRooms(null, null, room10, room11);
        
        //Raum 12 verbunden mit Raum 11 und Raum 13
        room12.setConnectedRooms(null, room11, null, room13);
        
        //Raum 13 verbunden mit Raum 12
        room13.setConnectedRooms(null, room12, null, null);
        
        
    }
}
