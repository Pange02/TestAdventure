import java.util.ArrayList;
import java.util.HashMap;
/**
 * Die Klasse Stage0 wird von der Klasse Stage vererbt. Im Konstruktor der Klasse werden hierbei die Räume 0 bis 13 erstellt und entsprechend der Karte verbunden.
 * Ebenfalls werden die verschiedenen Kisten und Monster mit entsprechenden Loot befüllt und in den Räumen positioniert, gleiches gilt auch für die NPC die einen entsprechenden Text
 * für Interaktionen zugewiesen bekommen. Alle diese Informationen werden in ArrayListen hinterlegt, die entsprechend erzeugt werden. Der „startRoom“ wird als „playerstartroom“ festgelegt.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Stage2 extends Stage
{
    private ArrayList<Item> startroomLoot = new ArrayList<>();
    private ArrayList<Item> room2Loot = new ArrayList<>();
    private ArrayList<Item> room6Loot = new ArrayList<>();
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
    
    private HashMap merchantloot5 = new HashMap<Item, Integer>();
    private ArrayList<String> speakerdialogue = new ArrayList<>();
    private ArrayList<String> speakerdialogue11 = new ArrayList<>();
    
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
        
        //Startraum der Stage
        speakerdialogue.add("Hier sind wir, seid Ihr euch wirklich sicher, dass Ihr weiter gehen wollt? Man sagt sich, Sie Archibald Dunkan sei einer der besten Kämpfer unserer Lande.");
        speakerdialogue.add("Für das Umkehren ist jedoch nun zu spät.");
        speakerdialogue.add("Also gut, ich wünsche euch viel Glück!");
        Speaker startSpeaker = new Speaker("Harry", speakerdialogue);
        Room startRoom = new Room(0, null, null, startSpeaker);
        
        //erster Raum der Stage
        mob1Loot.add(Item.getitemfromlist("Consumable", 0));
        Mob mob1 = new Mob("Troll", 7, 15, ((Weapon) Item.getitemfromlist("Weapon", 2)), mob1Loot, 20, "maskulin");
        Room room1 = new Room(1, null, mob1, null);
        
        //zweiter Raum der Stage
        Key key2 = new Key("Holzschlüssel", "Ein einfacher Holzschlüssel", "(Gewöhnlich)", "maskulin");
        room2Loot.add(key2);
        room2Loot.add(Item.getitemfromlist("Weapon", 2));
        Chest chest2 = new Chest(room2Loot);
        mob2Loot.add(Item.getitemfromlist("Potion", 2));
        Mob mob2 = new Mob("Skellett", 4, 10, ((Weapon) Item.getitemfromlist("Weapon", 3)), mob2Loot, 18, "neutrum");
        Room room2 = new Room(2, chest2, mob2, null);
        
        //dritter Raum der Stage
        Room room3 = new Room(3, null, null, null);
        Lock lock3 = new Lock("Holzschloss", room3, "east", key2, "neutrum");
        
        //vierter Raum der Stage
        room4Loot.add(Item.getitemfromlist("Armor", 2));
        Chest chest4 = new Chest(room4Loot);
        Room room4 = new Room(4, chest4, null, null);
        
        //fünfter Raum der Stage
        merchantloot5.put(Item.getitemfromlist("Potion", 2), 100);
        merchantloot5.put(Item.getitemfromlist("Potion", 0), 50);
        Merchant merchant5 = new Merchant("Emma", merchantloot5);
        Room room5 = new Room(5, null, null, merchant5);
        
        //sechster Raum der Stage
        Key key6 = new Key("Metallschlüssel", "Ein einfacher Metallschlüssel", "(Gewöhnlich)", "maskulin");
        room6Loot.add(key6);
        Chest chest6 = new Chest(room6Loot);
        mob6Loot.add(Item.getitemfromlist("Potion", 2));
        Mob mob6 = new Mob("Spinne", 3, 7, ((Weapon) Item.getitemfromlist("Weapon", 1)), mob6Loot, 10, "feminin");
        Room room6 = new Room(6, chest6, mob6, null);
        
        //siebter Raum der Stage
        mob7Loot.add(Item.getitemfromlist("Potion", 2));
        Mob mob7 = new Mob("Skellett", 4, 10, ((Weapon) Item.getitemfromlist("Weapon", 3)), mob7Loot, 18, "neutrum");
        Room room7 = new Room(7, null, mob7, null);
        
        //achter Raum der Stage
        mob8Loot.add(Item.getitemfromlist("Consumable", 0));
        Mob mob8 = new Mob("Troll", 7, 15, ((Weapon) Item.getitemfromlist("Weapon", 2)), mob8Loot, 20, "maskulin");
        Room room8 = new Room(8, null, mob8, null);
        Lock lock8 = new Lock("Metallschloss",room8 , "north", key6, "neutrum");
        
        //neunter Raum der Stage
        mob9Loot.add(Item.getitemfromlist("Potion", 2));
        Mob mob9 = new Mob("Skellett", 4, 10, ((Weapon) Item.getitemfromlist("Weapon", 3)), mob9Loot, 18, "neutrum");
        Room room9 = new Room(9, null, mob9, null);
        
        //zehnter Raum der Stage
        Room room10 = new Room(10, null, null, null);
        
        //elfter Raum der Stage
        speakerdialogue11.add("Duncan scheint sich im nächsten Raum zu verstecken! Auf in das Gefecht!");
        Speaker speaker11 = new Speaker("Tom", speakerdialogue);
        Room room11 = new Room(11, null, null, speaker11);
        
        //zwölfter Raum der Stage
        bossLoot.add(Item.getitemfromlist("Weapon", 5));
        Lord lord = new Lord("Sir Archibald Duncan", 100, 50, ((Weapon) Item.getitemfromlist("Weapon", 5)), bossLoot, 200, "maskulin");
        Room room12 = new Room(12, null, lord, null);
        
        //Verbinden der Räume miteinander
        
        //Startraum verbunden mit Raum 1.
        startRoom.setConnectedRooms(room1, null, null, null);
        
        //Raum 2 verbunden mit: Raum 3, Raum 4, Raum 1 und Raum 8
        room1.setConnectedRooms(room2, room3, startRoom, room7);
        
        //Raum 3 verbunden mit: Raum 2
        room2.setConnectedRooms(null, null, room1, null);
        
        //Raum 4 verbunden mit: Raum 5 und Raum 2
        room3.setConnectedRooms(null, room4, null, room1);
        
        //Raum 5 verbunden mit: Raum 6 und Raum 4
        room4.setConnectedRooms(room5, null, null, room3);
        
        //Raum 6 verbunden mit: Raum 7 und Raum 5
        room5.setConnectedRooms(null, room6, room4, null);
        
        //Raum 7 verbunden mit: Raum 6
        room6.setConnectedRooms(null, null, null, room5);
        
        //Raum 8 verbunden mit Raum 2 und Raum 9
        room7.setConnectedRooms(null, room1, null, room8);
        
        //Raum 9 verbunden mit Raum 10 und Raum 8
        room8.setConnectedRooms(room9, room7, null, null);
        
        //Raum 10 verbunden mit Raum 11 und Raum 9
        room9.setConnectedRooms(room10, null, room8, null);
        
        //Raum 11 verbuden mit Raum 10 und Raum 12
        room10.setConnectedRooms(null, null, room9, room11);
        
        //Raum 12 verbunden mit Raum 11 und Raum 13
        room11.setConnectedRooms(null, room10, null, room12);
        
        //Raum 13 verbunden mit Raum 12
        room12.setConnectedRooms(null, room11, null, null);
        
        roomlist.add(startRoom);
        roomlist.add(room1);
        roomlist.add(room2);
        roomlist.add(room3);
        roomlist.add(room4);
        roomlist.add(room5);
        roomlist.add(room6);
        roomlist.add(room7);
        roomlist.add(room8);
        roomlist.add(room9);
        roomlist.add(room10);
        roomlist.add(room11);
        roomlist.add(room12);
        
        playerstartroom = startRoom;
    }
}
