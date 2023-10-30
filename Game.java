import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * Beschreibung der Klasse Game.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Game {
    
    private static boolean firstGame = true;
    // Hier wird der Loot der einzelnen Räume als ArrayList definiert.
    private ArrayList<Item> startroomLoot = new ArrayList<>();
    private ArrayList<Item> room1Loot = new ArrayList<>();
    private ArrayList<Item> mob1Loot = new ArrayList<>();
    private ArrayList<Item> room2Loot = new ArrayList<>();
    private ArrayList<Item> room5Loot = new ArrayList<>();
    private ArrayList<Item> room7Loot = new ArrayList<>();
    private ArrayList<Item> room9Loot = new ArrayList<>();
    
    private HashMap merchantloot1 = new HashMap<Item, Integer>();
    
    public Game() 
    {
        if(firstGame) {
            //Es werden alle Items aus der Item Klasse in die Liste eingefügt.
            Weapon.createWeapons();
            Armor.createArmor();
            Potion.createPotions();
            Accessory.createAccessories();
            Consumable.createConsumables();
            Item.fillitemlist();
            firstGame = false;
        }
        
        Grammar grammar = new Grammar();
        
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
        Merchant merchant1 = new Merchant("TraderJoe", merchantloot1);
        Chest startChest = new Chest(startroomLoot);
        Room startRoom = new Room(startChest, null, merchant1);    
        
        // Raum 1 mit einer Truhe und einem Gegner
        room1Loot.add(Item.getitemfromlist("Potion", 0));
        Chest chest1 = new Chest(room1Loot);
        mob1Loot.add(Item.getitemfromlist("Potion", 2));
        Mob mob1 = new Mob("Zombie", 10, ((Weapon) Item.getitemfromlist("Weapon", 1)), mob1Loot, 10, "maskulin");
        Room room1 = new Room(chest1, mob1, null);
        
        // Raum 2 mit einer Truhe
        room2Loot.add(Item.getitemfromlist("Potion", 1));
        Chest chest2 = new Chest(room2Loot);
        Room room2 = new Room(chest2, null, null);
        
        // Raum 3 leer
        Room room3 = new Room(null, null, null);
        
        // Raum 4 leer
        Room room4 = new Room(null, null, null);
        
        // Raum 5 eine Truhe
        room5Loot.add(Item.getitemfromlist("Weapon", 2));
        room5Loot.add(Item.getitemfromlist("Consumable", 0));
        Chest chest5 = new Chest(room5Loot);
        Room room5 = new Room(chest5, null, null);
        
        // Raum 6 leer
        Room room6 = new Room(null, null, null);
        
        // Raum 7 eine Truhe mit 2 Loot
        room7Loot.add(Item.getitemfromlist("Accessory", 1));
        room7Loot.add(Item.getitemfromlist("Armor", 1));
        Chest chest7 = new Chest(room7Loot);
        Room room7 = new Room(chest7, null, null);
        
        // Raum 8 leer
        Room room8 = new Room(null, null, null);
        
        // Raum 9 eine Truhe mit 2 Loot
        room9Loot.add(Item.getitemfromlist("Accessory", 0));
        room9Loot.add(Item.getitemfromlist("Potion", 2));
        Chest chest9 = new Chest(room9Loot);
        Room room9 = new Room(chest9, null, null);
        
        //Hier werden alle Verbindungen zwischen den Räumen eingetraden mit (Norden, Osten, Süden, Westen).
        // <Raumname>.setchonnectedrooms(<Raum im Norden>, <Raum im Osten>, <Raum im Süden>, <Raum im Westen>);
        
        // Startraum verbunden mit Raum 1, 4 und 6
        startRoom.setConnectedRooms(room1, room6, null, room4);
        // Raum 1 verbunden mit Startraum und Raum 2
        room1.setConnectedRooms(room2, null, startRoom, null);
        // Raum 2 verbunden mit Raum 1 und 3
        room2.setConnectedRooms(room3, null, room1, null);
        // Raum 3 verbunden mit Raum 2
        room3.setConnectedRooms(null, null, room2, null);
        // Raum 4 verbunden mit Startraum und Raum 5
        room4.setConnectedRooms(room5, startRoom, null, null);
        // Raum 5 verbunden mit Raum 4
        room5.setConnectedRooms(null, null, room4, null);
        // Raum 6 verbunden mit Startraum und Raum 7
        room6.setConnectedRooms(null, room7, null, startRoom);
        // Raum 7 verbunden mit Raum 6 und 8
        room7.setConnectedRooms(room8, null, null, room6);
        // Raum 8 verbunden mit Raum 7 und 9
        room8.setConnectedRooms(room9, null, room7, null);
        // Raum 9 verbunden mit Raum 8
        room9.setConnectedRooms(null, null, room8, null);
        
        
        // Die Einführung in das Spiel
        
        // Abfragen des Spielernamens
        System.out.println("Lege deinen Namen fest:");
        // Texteingabe des Namens abfragen
        Scanner nameParser = new Scanner(System.in);
        Player player1 = new Player(nameParser.nextLine(), startRoom);
        // Cheatcode zum Testen
        if(player1.getplayername().equals("Leonard")) {
            for(int i = 0; i < Item.getitemlist().length; i++) {
                for(int j = 0; j < Item.getitemlist()[i].size(); j++) {
                    player1.additemtoinventory(((Item) Item.getitemlist()[i].get(j)));
                }
            }
        }
        // Begrüßen des Spielers
        System.out.println("Willkommen " + player1.getplayername() + "!");
        System.out.println(" ");
        
        //Ein neuen Parser erstellen für das Spielerobjekt.
        Parser mainParser = new Parser(player1);
    }
}