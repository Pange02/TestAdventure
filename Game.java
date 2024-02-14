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
    
    private ArrayList<String> story = new ArrayList<>();
    private ArrayList<String> intro = new ArrayList<>();
    
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
        Stage2 stage2 = new Stage2();
        
        
        // Die Einführung in das Spiel
        story.add("Du bist ein Lord in einer mittelalterlichen Fantasy-Welt.");
        story.add("Ein anderer, verfeindeter Lord versucht die Herrschaft nach dem Tod des Königs an sich zu reißen. Nun musst du dich verteidigen und den bösen Lord, Sir Archibald Dunkan, sowie seine zahlreichen Handlanger besiegen.");
        story.add("Doch bevor du dich Sir Archibald Dunkan stellen kannst, musst du dich durch die zahlreichen Festungen seiner Handlanger kämpfen und diesen schlussendlich besiegen.");
        story.add("Allerdings musst du alleine losziehen, damit der Feind davon nichts mitbekommt. Deswegen ziehst du kämpfend durchs Land, bis du vor der großen, imposanten Festung von Sir Archibald Dunkan stehst und nur noch einen letzten Kampf beschreiten musst.");
        
        Scanner nameParser = new Scanner(System.in);
        
        System.out.println("Geschichte:");
        System.out.println();
        try {
            Thread.sleep(2000);
        }   
        catch(Exception e) {
            Thread.currentThread().interrupt();
        }
        for(String i: story) {
            System.out.println(i);
            try {
                Thread.sleep(6000);
            }   
            catch(Exception e) {
                Thread.currentThread().interrupt();
            }            
        }
        System.out.println();
        
        // Abfragen des Spielernamens
        System.out.println("Hallo mein Herr. Wie darf ich euch nennen?");
        System.out.println("Lege deinen Namen fest:");
        // Texteingabe des Namens abfragen
        Player player1 = new Player(nameParser.nextLine(), stage0);
        // Cheatcode zum Testen
        if(player1.getplayername().equals("LeonardFreistedt")) {
            for(int i = 0; i < Item.getitemlist().length; i++) {
                for(int j = 0; j < Item.getitemlist()[i].size(); j++) {
                    player1.additemtoinventory(((Item) Item.getitemlist()[i].get(j)));
                }
            }
        }
        
        intro.add("Lord " + player1.getplayername() + ", Sir Archibald Dunkan versucht sich euer Ländereien zu bemächtigen. Wir müssen uns verteidigen!");
        intro.add("Unsere Spione haben uns allerdings berichtetet, dass er noch eine Weile brauchen wird, bis er angreifen kann. Wir müssen ihm zuvorkommen! Lasst uns aufbrechen, mein Herr.");
        intro.add("Hier sind wir, dies ist die erste Festung von Dreien, die wir einnehmen müssen, um Sir Archibald Dunkan zu besiegen.");
        intro.add("Geht voran, ich kann euch nicht folgen, aber wenn ihr die Festung betreten habt, werdet Ihr einen Spion von uns treffen, Harry. Redet mit ihm, in dem ihr \"rede Harry\" eingebt, er wird euch weiterhelfen.");
        
        // Begrüßen des Spielers
        try {
            Thread.sleep(2000);
        }   
        catch(Exception e) {
            Thread.currentThread().interrupt();
        }
        for(String i: intro) {
            System.out.println(i);
            try {
                Thread.sleep(6000);
            }   
            catch(Exception e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();

        System.out.println("Ihr betretet die Festung.");
        System.out.println();
        //Ein neuen Parser erstellen für das Spielerobjekt.
        Parser mainParser = new Parser(this, stage0, player1, new Stage[]{stage0, stage1, stage2});
    }
}