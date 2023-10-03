import java.util.Scanner;
import java.util.ArrayList;
public class Game {
    //Hier wird der Loot der einzelnen Räume als ArrayList definiert.
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
         * Chest <name> = new Chest(ArrayList mit Loot)
         * Room <name> = new Room(Kiste ja/nein, Mob ja/nein)
         * 
         */
        
        Room startroom = new Room(null, null);
        
        room1loot.add(Item.getitemfromlist("Potion", 0));
        Chest chest1 = new Chest(room1loot);
        mob1loot.add(Item.getitemfromlist("Potion", 2));
        System.out.println(mob1loot.get(0));
        Mob mob1 = new Mob("Zombie", 10, ((Weapon) Item.getitemfromlist("Weapon", 1)), mob1loot);
        Room room1 = new Room(chest1, mob1);
        
        room2loot.add(Item.getitemfromlist("Potion", 1));
        Chest chest2 = new Chest(room2loot);
        Room room2 = new Room(chest2, null);
        
        Room room3 = new Room(null, null);
        
        Room room4 = new Room(null, null);
        
        room5loot.add(Item.getitemfromlist("Weapon", 2));
        Chest chest5 = new Chest(room5loot);
        Room room5 = new Room(chest5, null);
        
        Room room6 = new Room(null, null);
        
        room7loot.add(Item.getitemfromlist("Accessory", 1));        
        Chest chest7 = new Chest(room7loot);
        Room room7 = new Room(chest7, null);
        
        Room room8 = new Room(null, null);
        
        room9loot.add(Item.getitemfromlist("Accessory", 0));
        room9loot.add(Item.getitemfromlist("Potion", 2));
        Chest chest9 = new Chest(room9loot);
        Room room9 = new Room(chest9, null);
        
        //Hier werden alle Verbindungen zwischen den Räumen eingetraden mit (Raum, Norden, Osten, Süden, Westen).
        startroom.setconnectedrooms(startroom, room1, room6, null, room4);
        room1.setconnectedrooms(room1, room2, null, startroom, null);
        room2.setconnectedrooms(room2, room3, null, room1, null);
        room3.setconnectedrooms(room3, null, null, room2, null);
        room4.setconnectedrooms(room4, room5, startroom, null, null);
        room5.setconnectedrooms(room5, null, null, room4, null);
        room6.setconnectedrooms(room6, null, room7, null, startroom);
        room7.setconnectedrooms(room7, room8, null, null, room6);
        room8.setconnectedrooms(room8, room9, null, room7, null);
        room9.setconnectedrooms(room9, null, null, room8, null);
        
        
        //Die Einführung in das Spiel
        System.out.println("Lege deinen Namen fest:");
        Scanner nameparser = new Scanner(System.in);
        Player player1 = new Player(nameparser.nextLine(), startroom);
        System.out.println("Willkommen " + player1.getplayername(player1) + "!");
        System.out.println(" ");
        
        //Ein neuen Parser erstellen für das Spielerobjekt.
        Parser mainparser = new Parser(player1);
    }
}