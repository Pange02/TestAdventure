import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * Die Klasse Game erstellt die Räume und ordnet diese so an, dass eine Karte entsteht, durch welche sich der Spieler bewegen kann. Sie stellt Truhen in die Räume.
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
    
    private ArrayList<Room> roomlist = new ArrayList<>();
    
    private HashMap merchantloot1 = new HashMap<Item, Integer>();
    private ArrayList<String> speakerdialogue = new ArrayList<>();
    public Game() 
    {
        if(firstGame) {
            //Es werden alle Items aus der Item Klasse in die Liste eingefügt.
            Weapon.createWeapons();
            Armor.createArmor();
            Potion.createPotions();
            Accessory.createAccessories();
            Consumable.createConsumables();
            Reforge.makeReforges();
            Item.fillitemlist();
            firstGame = false;
        }
        
        Grammar grammar = new Grammar();
        
        TestStage teststage = new TestStage();
        Stage0 stage0 = new Stage0();
        Stage1 stage1 = new Stage1();
        
        
        // Die Einführung in das Spiel
        
        // Abfragen des Spielernamens
        System.out.println("Lege deinen Namen fest:");
        // Texteingabe des Namens abfragen
        Scanner nameParser = new Scanner(System.in);
        Player player1 = new Player(nameParser.nextLine(), stage0);
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
        Parser mainParser = new Parser(this, stage0, player1);
    }
}