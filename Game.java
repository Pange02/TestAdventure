import java.util.Scanner;
import java.util.ArrayList;
public class Game {
    
    // Hier wird der Loot der einzelnen Räume als ArrayList definiert.
    private ArrayList<Item> startroomloot = new ArrayList<>();
    private ArrayList<Item> room1loot = new ArrayList<>();
    private ArrayList<Item> mob1loot = new ArrayList<>();
    private ArrayList<Item> room2loot = new ArrayList<>();
    private ArrayList<Item> room5loot = new ArrayList<>();
    private ArrayList<Item> room7loot = new ArrayList<>();
    private ArrayList<Item> room9loot = new ArrayList<>();
    
    public Game() 
    {
        //Es werden alle Items aus der Item Klasse in die Liste eingefügt.
        Weapon.createWeapons();
        Armor.createArmor();
        Potion.createPotions();
        Accessory.createAccessories();
        Item.fillitemlist();
        
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
        startroomloot.add(Item.getitemfromlist("Weapon", 1));
        Chest startchest = new Chest(startroomloot);
        Room startroom = new Room(startchest, null);    
        
        // Raum 1 mit einer Truhe und einem Gegner
        room1loot.add(Item.getitemfromlist("Potion", 0));
        Chest chest1 = new Chest(room1loot);
        mob1loot.add(Item.getitemfromlist("Potion", 2));
        Mob mob1 = new Mob("Zombie", 10, ((Weapon) Item.getitemfromlist("Weapon", 1)), mob1loot);
        Room room1 = new Room(chest1, mob1);
        
        // Raum 2 mit einer Truhe
        room2loot.add(Item.getitemfromlist("Potion", 1));
        Chest chest2 = new Chest(room2loot);
        Room room2 = new Room(chest2, null);
        
        // Raum 3 leer
        Room room3 = new Room(null, null);
        
        // Raum 4 leer
        Room room4 = new Room(null, null);
        
        // Raum 5 eine Truhe
        room5loot.add(Item.getitemfromlist("Weapon", 2));
        Chest chest5 = new Chest(room5loot);
        Room room5 = new Room(chest5, null);
        
        // Raum 6 leer
        Room room6 = new Room(null, null);
        
        // Raum 7 eine Truhe
        room7loot.add(Item.getitemfromlist("Accessory", 1));        
        Chest chest7 = new Chest(room7loot);
        Room room7 = new Room(chest7, null);
        
        // Raum 8 leer
        Room room8 = new Room(null, null);
        
        // Raum 9 eine Truhe mit 2 Loot
        room9loot.add(Item.getitemfromlist("Accessory", 0));
        room9loot.add(Item.getitemfromlist("Potion", 2));
        Chest chest9 = new Chest(room9loot);
        Room room9 = new Room(chest9, null);
        
        //Hier werden alle Verbindungen zwischen den Räumen eingetraden mit (Norden, Osten, Süden, Westen).
        // <Raumname>.setchonnectedrooms(<Raum im Norden>, <Raum im Osten>, <Raum im Süden>, <Raum im Westen>);
        
        // Startraum verbunden mit Raum 1, 4 und 6
        startroom.setconnectedrooms(room1, room6, null, room4);
        // Raum 1 verbunden mit Startraum und Raum 2
        room1.setconnectedrooms(room2, null, startroom, null);
        // Raum 2 verbunden mit Raum 1 und 3
        room2.setconnectedrooms(room3, null, room1, null);
        // Raum 3 verbunden mit Raum 2
        room3.setconnectedrooms(null, null, room2, null);
        // Raum 4 verbunden mit Startraum und Raum 5
        room4.setconnectedrooms(room5, startroom, null, null);
        // Raum 5 verbunden mit Raum 4
        room5.setconnectedrooms(null, null, room4, null);
        // Raum 6 verbunden mit Startraum und Raum 7
        room6.setconnectedrooms(null, room7, null, startroom);
        // Raum 7 verbunden mit Raum 6 und 8
        room7.setconnectedrooms(room8, null, null, room6);
        // Raum 8 verbunden mit Raum 7 und 9
        room8.setconnectedrooms(room9, null, room7, null);
        // Raum 9 verbunden mit Raum 8
        room9.setconnectedrooms(null, null, room8, null);
        
        
        //Die Einführung in das Spiel
        System.out.println("Lege deinen Namen fest:");
        Scanner nameparser = new Scanner(System.in);
        Player player1 = new Player(nameparser.nextLine(), startroom);
        if(player1.getplayername().equals("Leonard")) {
            for(int i = 0; i < Item.itemlist.length; i++) {
                for(int j = 0; j < Item.itemlist[i].size(); j++) {
                    player1.additemtoinventory(((Item) Item.itemlist[i].get(j)));
                }
            }
        }
        System.out.println("Willkommen " + player1.getplayername() + "!");
        System.out.println(" ");
        
        //Ein neuen Parser erstellen für das Spielerobjekt.
        Parser mainparser = new Parser(player1);
    }
}