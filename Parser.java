import java.util.Scanner;
import java.util.ArrayList;
import java.lang.Math;
/**
 * Die Klasse “Parser” nimmt die Eingaben des Spielers entgegen und gibt entsprechend der Eingaben die Befehle weiter und löst somit andere Ereignisse aus.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Parser
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    
    //Aktionsliste für alle möglichen Aktionen. Verwendet von help Befehl.
    private String[] actionlist = {"hilfe", "öffne (truhe)", "stats", "inv", "rede <Name>", "info <Inventarslot>", "benutze <Inventarslot>", "ablegen <Inventarslot>", "umgucken", "gehe <Himmelsrichtung>"};
    
    private String[] combatactionlist = {"attackiere", "benutze <Inventarslot>"};
    
    //Array für den Input des Spielers
    private String[] input = new String[10];
    
    //Array für den BuyInput des Spielers
    private String[] buyinput = new String[10];
    
    private int reforgeinputint;
    
    //Boolean gibt an, ob der Parser (das Spiel) aktiv ist.
    private boolean running;
    
    private Game activegame;
    
    private Stage activestage;
    
    private boolean combat;
    
    //Aktiver Spieler vom Parser
    private static Player activeplayer;
    
    private int weaponnumber;
    
    private int inventorynumber;
    
    private Weapon playerweapon;
    
    private Mob activemob;
    
    private boolean mobattack;
    
    private int mobpoisonrounds;
    
    private int mobpoisoneffect;
    
    private int playerpoisonrounds;
    
    private int playerpoisoneffect;
    
    private boolean firstfight;
    
    private boolean weaponselected;
    
    /**
     * Konstruktor für Objekte der Klasse Parser mit Spieler
     */
    public Parser(Game parsegame, Stage parsestage, Player parseplayer)
    {
        running = true;
        activegame = parsegame;
        activestage = parsestage;
        combat = false;
        firstfight = true;
        weaponselected = false;
        mobattack = true;
        activeplayer = parseplayer;
        System.out.println("Was möchtest du tun? Für Hilfe: hilfe");
        while(running && !combat) {
            Scanner parser = new Scanner(System.in);
            getaction(parser.nextLine(), activeplayer);
            System.out.println();
            while(running && combat) {
                if(firstfight == true) {
                    firstfight = false;
                    System.out.println("Aktuell kannst du folgenden Kampfaktionen machen:");
                    for(int i = 0; i < combatactionlist.length; i++) {
                        System.out.println(combatactionlist[i]);
                    }
                    System.out.println();
                }
                if(playerpoisonrounds > 1) {
                    if(playerpoisoneffect == 0) {
                        playerpoisonrounds = activeplayer.getpoisonrounds();
                    }
                    playerpoisoneffect = 5;
                    activeplayer.setplayerhealth(Math.round((activeplayer.getplayerhealth() - (activeplayer.getplayerhealth() * (playerpoisoneffect * 0.01))) * 10) * 0.1);
                    playerpoisonrounds -= 1;
                    System.out.println("Durch das Gift nimmst du " + playerpoisoneffect + "% Schaden. Du hast jetzt " + activeplayer.getplayerhealth() + " Leben.");
                    System.out.println("Das Gift hält noch " + playerpoisonrounds + " weitere Runden. ");
                }
                if(activeplayer.getpoisonrounds() == 1) {
                    activeplayer.setplayerhealth(Math.round((activeplayer.getplayerhealth() - (activeplayer.getplayerhealth() * (playerpoisoneffect * 0.01))) * 10) * 0.1);
                    playerpoisonrounds -= 1;
                    System.out.println("Durch das Gift nimmst du " + playerpoisoneffect + "% Schaden. Du hast jetzt " + activeplayer.getplayerhealth() + " Leben.");
                    System.out.println("Das Gift hat nun seine Wirkung verloren.");
                }
                if(mobpoisonrounds > 1 && mobattack) {
                    activemob.setmobhealth(Math.round((activemob.getmobhealth() - (activemob.getmobhealth() * (mobpoisoneffect * 0.01))) * 10)/10);
                    mobpoisonrounds -= 1;
                    System.out.println("Durch das Gift nimmt " + activemob.getArtikel("nominativ", "bestimmt") + " " + activemob.getmobname() + " " + mobpoisoneffect + "% Schaden. " + activemob.getArtikel("nominativ", "bestimmt").substring(0, 1).toUpperCase() 
                    + activemob.getArtikel("nominativ", "bestimmt").substring(1) + " " + activemob.getmobname() + " hat jetzt " + activemob.getmobhealth() + " Leben. Das Gift hält noch " + mobpoisonrounds + " weitere Runden.");
                }
                else if(mobpoisonrounds == 1 && mobattack) {
                    activemob.setmobhealth(Math.floor((activemob.getmobhealth() - (activemob.getmobhealth() * (mobpoisoneffect * 0.01))) * 10)/10);
                    mobpoisonrounds -= 1;
                    System.out.println("Durch das Gift nimmt " + activemob.getArtikel("nominativ", "bestimmt") + " " + activemob.getmobname() + " " + mobpoisoneffect + "% Schaden. " + activemob.getArtikel("nominativ", "bestimmt").substring(0, 1).toUpperCase() 
                    + activemob.getArtikel("nominativ", "bestimmt").substring(1) + " " + activemob.getmobname() + " hat jetzt " + activemob.getmobhealth() + " Leben. Der Gifteffekt hat nun seine Wirkung verloren.");
                }
                Scanner compatparser = new Scanner(System.in);
                getcombataction(parser.nextLine(), activeplayer, activemob);
                if(activemob.getmobhealth() > 0 && mobattack) {
                    activemob.attack(activeplayer);
                    if(activeplayer.getplayerhealth() < 0) {
                        activeplayer.setplayerhealth(0);
                    }
                }
                if(parseplayer.getplayerhealth() == 0) {
                    System.out.println("Du bist gestorben!");
                    System.out.println("Schreibe \"Neustart\" um das Level zu wiederholen.");
                    Scanner endparser = new Scanner(System.in);
                    if(endparser.nextLine().equals("Neustart")) {
                        combat = false;
                        running = false;
                        Game nextGame = new Game();
                    }
                    else {
                        combat = false;
                        running = false;
                    }
                }
                mobattack = true;
                System.out.println();
                }
        }
    }

    /**
     * Alle möglichen Inputs werden vom Parser nach Leerzeichen getrennt und überprüft, ob sie einer Aktion entsprechen und dann von ihm ausgeführt.
     */
    public void getaction(String parseaction, Player parseplayer)
    {
        input = parseaction.toLowerCase().split("\\s+");
        if(input[0].equals("hilfe")) {
            System.out.println("Aktuell kannst du folgenden Aktionen machen:");
            for(int i = 1; i < actionlist.length; i++) {
                System.out.println(actionlist[i]);
            }
        }
        
        else if(input[0].equals("öffne")) {
            try {
                if(input[1].equals("truhe") && parseplayer.getcurrentroom().getChestInfo()) {
                    parseplayer.getcurrentroom().getChest().openChest(Parser.activeplayer, parseplayer.getcurrentroom().getChest());
                    parseplayer.addexperience(5);
                }
                else if(input[1].equals("truhe") && (parseplayer.getcurrentroom().getChestInfo() == false)) {
                    System.out.println("Dieser Raum hat keine Truhe");
                }
                else {
                    System.out.println("Du kannst dieses Objekt nicht öffnen!");
                } 
            }
            catch(Exception e) {
                System.out.println("Du musst angeben, was du öffnen willst.");
            }
        }    
        else if(input[0].equals("umgucken")) {
            if(parseplayer.getcurrentroom().getChestInfo()) {
                System.out.println("Du entdeckst eine Truhe in dem Raum.");
            }
            if(parseplayer.getcurrentroom().getNPCInfo()) {
                System.out.println("Du entdeckst die Person " + parseplayer.getcurrentroom().getNPC().getNPCname() + " im Raum.");
            }
            System.out.println("Du siehst Türen im:");
            for(int i = 0; i < parseplayer.getcurrentroom().getRoomDirections().size(); i++) {
                    System.out.println(parseplayer.getcurrentroom().getRoomDirections().get(i));
            }            
        }
        else if(input[0].equals("rede")) {
            try {
                if(input[1].toLowerCase().equals(parseplayer.getcurrentroom().getNPC().getNPCname().toLowerCase())) {
                    if(parseplayer.getcurrentroom().getNPC().getClass() == Merchant.class) {
                        ((Merchant) parseplayer.getcurrentroom().getNPC()).speak();
                        Scanner buyparser = new Scanner(System.in);
                        buyinput = buyparser.nextLine().toLowerCase().split("\\s+");
                        if(buyinput[0].equals("kaufe")) {
                            if(buyinput[1] != null) {
                               ((Merchant) parseplayer.getcurrentroom().getNPC()).buyitem(activeplayer, buyinput[1]);
                            }
                            else {
                                System.out.println("Du musst das Item angeben, was du kaufen willst.");
                            }
                        }
                        else if(buyinput[0].equals("ja")) {
                            System.out.println("Dazu musst du sagen: kaufe + Itemname");
                        }
                        else {
                            System.out.println("Ok, vielleicht später!");
                        }
                    }
                    else if(parseplayer.getcurrentroom().getNPC().getClass() == Speaker.class) {
                        ((Speaker) parseplayer.getcurrentroom().getNPC()).speak(parseplayer);
                    }
                    else if(parseplayer.getcurrentroom().getNPC().getClass() == Blacksmith.class) {
                        ((Blacksmith) parseplayer.getcurrentroom().getNPC()).speak(parseplayer);
                        Scanner reforgeparser = new Scanner(System.in);
                        String[] reforgeinput = new String[10];
                        reforgeinput = reforgeparser.nextLine().toLowerCase().split("\\s+");
                        if(reforgeinput[0].equals("verbessere")) {
                            try {
                                if(parseplayer.getcoins() >= 5) {
                                    ((Blacksmith) parseplayer.getcurrentroom().getNPC()).reforge(parseplayer.getitemfrominventory(Integer.parseInt(reforgeinput[1])));
                                    parseplayer.removecoins(5);
                                    System.out.println("Deine Coins: " + parseplayer.getcoins());
                                    while(reforgeparser.nextLine().toLowerCase().split("\\s+")[0].toLowerCase().equals("ja")) {
                                        if(parseplayer.getcoins() >= 5) {
                                            ((Blacksmith) parseplayer.getcurrentroom().getNPC()).reforge(parseplayer.getitemfrominventory(Integer.parseInt(reforgeinput[1])));
                                            parseplayer.removecoins(5);
                                            System.out.println("Deine Coins: " + parseplayer.getcoins());
                                        }
                                        else {
                                            System.out.println("Du hast nicht genügend Coins dafür!");
                                        }
                                    }
                                }
                                else {
                                    System.out.println("Du hast nicht genügend Coins dafür!");
                                }
                                System.out.println("Ok, vielen Dank für das Geschäft.");
                            }
                            catch(Exception e) {
                                System.out.println("Du musst eine Zahl für dein Inventar angeben");
                            }  
                        }
                        else {
                            System.out.println("Ok, vielleicht beim nächsten Mal!");
                        }
                    }
                }
                else {
                    System.out.println("Diese Person scheint nicht im Raum zu sein!");
                }
            }
            catch(Exception e) {
                System.out.println("Du musst angeben, mit wem du reden willst!");
            }
        }
        else if(input[0].equals("inv")) {
            parseplayer.getinventorycontent();
        }
        else if(input[0].equals("stats")) {
            parseplayer.getplayerstats();
        }
        else if(input[0].equals("benutze")) {
            try {
                inventorynumber = Integer.parseInt(input[1]);
                if(parseplayer.getitemfrominventory(inventorynumber).getClass() == Potion.class) {
                    parseplayer.drink(inventorynumber);
                }
                else if(parseplayer.getitemfrominventory(inventorynumber).getClass() == Armor.class) {
                    parseplayer.equiparmor(((Armor) parseplayer.getitemfrominventory(inventorynumber)));
                }
                else if(parseplayer.getitemfrominventory(inventorynumber).getClass() == Consumable.class) {
                    parseplayer.consume(inventorynumber);
                }
                else if(parseplayer.getitemfrominventory(inventorynumber).getClass() == Key.class) {
                    try {
                        if(input[2].toLowerCase().equals("norden")) {
                            parseplayer.getcurrentroom().getLock("north").openLock(parseplayer.getcurrentroom(), ((Key) parseplayer.getitemfrominventory(inventorynumber)));
                            parseplayer.removeitemfrominventory(inventorynumber);
                        }
                        else if(parseplayer.getcurrentroom().getLock(input[2]) != null && input[2].toLowerCase().equals("osten")) {
                            parseplayer.getcurrentroom().getLock("east").openLock(parseplayer.getcurrentroom(), ((Key) parseplayer.getitemfrominventory(inventorynumber)));
                            parseplayer.removeitemfrominventory(inventorynumber);
                        }
                        else if(parseplayer.getcurrentroom().getLock(input[2]) != null && input[2].toLowerCase().equals("süden")) {
                            parseplayer.getcurrentroom().getLock("south").openLock(parseplayer.getcurrentroom(), ((Key) parseplayer.getitemfrominventory(inventorynumber)));
                            parseplayer.removeitemfrominventory(inventorynumber);
                        }
                        else if(parseplayer.getcurrentroom().getLock(input[2]) != null && input[2].toLowerCase().equals("westen")) {
                            parseplayer.getcurrentroom().getLock("west").openLock(parseplayer.getcurrentroom(), ((Key) parseplayer.getitemfrominventory(inventorynumber)));
                            parseplayer.removeitemfrominventory(inventorynumber);
                        }
                        else {
                            System.out.println("Dies ist keine gültige Richtung. Gültige Richtungen sind: Norden, Osten, Süden, Westen.");
                        }
                    }
                    catch(Exception e) {
                        System.out.println("Du musst eine Richtung angeben, in welcher du den Schlüssel benutzen möchtest!");
                    }
                }
                else {
                    System.out.println("Du kannst dieses Item akutell nicht benutzen!");
                }
            }
            catch(Exception e) {
                System.out.println("Dies ist keine gültige Zahl aus deinem Inventar!");
            }
        }
        else if(input[0].equals("ablegen")) {
            try {
                if(input[1].toLowerCase().equals("helm")) {
                    parseplayer.unequiparmor("helmet");
                }
                else if(input[1].toLowerCase().equals("brustplatte")) {
                    parseplayer.unequiparmor("chestplate");
                }
                else if(input[1].toLowerCase().equals("hose") || input[1].toLowerCase().equals("beinschutz")) {
                    parseplayer.unequiparmor("leggings");
                }
                else if(input[1].toLowerCase().equals("schuhe")) {
                    parseplayer.unequiparmor("boots");
                }
                else {
                    System.out.println("Du kannst dies nicht ablegen! Wähle zwischen Helm, Brustplatte, Hose und Schuhe.");
                }
            }
            catch(Exception e) {
                System.out.println("Du musst das Rüstungsteil, welches du ablegen willst, angeben.");
            }
        }
        else if(input[0].equals("info")) {
            try {
                try {
                   inventorynumber = Integer.parseInt(input[1]);
                   parseplayer.getitemfrominventory(inventorynumber).getiteminfo();
                }
                catch(Exception e) {
                    System.out.println("Dies ist keine gültige Zahl für dein Inventar");
                }
            }
            catch(Exception e) {
                System.out.println("Du musst einen Inventarslot angeben");
            }
        }
        else if(input[0].equals("ainfo")) {
            try {
                try {
                   inventorynumber = Integer.parseInt(input[1]);
                   parseplayer.getitemfromaccessories(inventorynumber).getiteminfo();
                }
                catch(Exception e) {
                    System.out.println("Dies ist keine gültige Zahl für dein Inventar");
                }
            }
            catch(Exception e) {
                System.out.println("Du musst einen Inventarslot angeben");
            }
        }
        else if(input[0].equals("skill")) {
            try {
                if(input[1].toLowerCase().equals("stärke")) {
                    try {
                        parseplayer.addskillpoints("strength", Integer.parseInt(input[2]));
                    }
                    catch(Exception e) {
                        System.out.println("Du musst als Zahl angeben, wie viele Punkte du in Stärke investieren möchtest.");
                    }
                }
                else if(input[1].toLowerCase().equals("verteidigung")) {
                    try {
                        parseplayer.addskillpoints("defense", Integer.parseInt(input[2]));
                    }
                    catch(Exception e) {
                        System.out.println("Du musst als Zahl angeben, wie viele Punkte du in Verteidigung investieren möchtest.");
                    }
                }
            }
            catch(Exception e) {
                System.out.println("Du musst angeben, in welche Fähigkeit du deine Skillpunkte investieren möchtest. Möglich sind Stärke und Verteidigung");
            }
        }
        else if(input[0].equals("gehe")) {
            try {
                if(input[1].equals("norden") && parseplayer.getcurrentroom().getConnectedRooms("north") != null) {
                    if(parseplayer.getcurrentroom().getLock("north") != null) {
                        System.out.println("Du entdeckst " + parseplayer.getcurrentroom().getLock("north").getArtikel("akkusativ", "unbestimmt") + " " + parseplayer.getcurrentroom().getLock("north").getname() + " an der Tür. Du benötigst ein Schlüssel für dieses Schloss.");
                    }
                    else {
                        System.out.println("Du bewegst dich durch die Tür in den neuen Raum im Norden");
                        parseplayer.setcurrentroom(parseplayer.getcurrentroom().getConnectedRooms("north"));
                        if(parseplayer.getcurrentroom().getMobInfo() == true && parseplayer.getcurrentroom().getRoomMob().getmobstatus() == true) {
                            entercombat(parseplayer, parseplayer.getcurrentroom().getRoomMob());
                            combat = true;
                        }
                    }
                }
                else if(input[1].equals("norden") && parseplayer.getcurrentroom().getConnectedRooms("north") == null) {
                    System.out.println("In dieser Richtung befindet sich keine Tür in einen anderen Raum.");
                }
                else if(input[1].equals("osten") && parseplayer.getcurrentroom().getConnectedRooms("east") != null) {
                    if(parseplayer.getcurrentroom().getLock("east") != null) {
                        System.out.println("Du entdeckst " + parseplayer.getcurrentroom().getLock("east").getArtikel("akkusativ", "unbestimmt") + " " + parseplayer.getcurrentroom().getLock("east").getname() + " an der Tür. Du benötigst ein Schlüssel für dieses Schloss.");
                    }
                    else {
                        System.out.println("Du bewegst dich durch die Tür in den neuen Raum im Osten");
                        parseplayer.setcurrentroom(parseplayer.getcurrentroom().getConnectedRooms("east"));
                        if(parseplayer.getcurrentroom().getMobInfo() == true && parseplayer.getcurrentroom().getRoomMob().getmobstatus() == true) {
                            entercombat(parseplayer, parseplayer.getcurrentroom().getRoomMob());
                            combat = true;
                        }   
                    }
                }
                else if(input[1].equals("osten") && parseplayer.getcurrentroom().getConnectedRooms("east") == null) {
                    System.out.println("In dieser Richtung befindet sich keine Tür in einen anderen Raum.");
                }
                else if(input[1].equals("süden") && parseplayer.getcurrentroom().getConnectedRooms("south") != null) {
                    if(parseplayer.getcurrentroom().getLock("south") != null) {
                        System.out.println("Du entdeckst " + parseplayer.getcurrentroom().getLock("south").getArtikel("akkusativ", "unbestimmt") + " " + parseplayer.getcurrentroom().getLock("south").getname() + " an der Tür. Du benötigst ein Schlüssel für dieses Schloss.");
                    }
                    else {
                        System.out.println("Du bewegst dich durch die Tür in den neuen Raum im Süden");
                        parseplayer.setcurrentroom(parseplayer.getcurrentroom().getConnectedRooms("south"));
                        if(parseplayer.getcurrentroom().getMobInfo() == true && parseplayer.getcurrentroom().getRoomMob().getmobstatus() == true) {
                            entercombat(parseplayer, parseplayer.getcurrentroom().getRoomMob());
                            combat = true;
                        }
                    }
                }
                else if(input[1].equals("süden") && parseplayer.getcurrentroom().getConnectedRooms("south") == null) {
                    System.out.println("In dieser Richtung befindet sich keine Tür in einen anderen Raum.");
                }
                else if(input[1].equals("westen") && parseplayer.getcurrentroom().getConnectedRooms("west") != null) {
                    if(parseplayer.getcurrentroom().getLock("west") != null) {
                        System.out.println("Du entdeckst " + parseplayer.getcurrentroom().getLock("west").getArtikel("akkusativ", "unbestimmt") + " " + parseplayer.getcurrentroom().getLock("west").getname() + " an der Tür. Du benötigst ein Schlüssel für dieses Schloss.");
                    }
                    else {
                        System.out.println("Du bewegst dich durch die Tür in den neuen Raum im Westen");
                        parseplayer.setcurrentroom(parseplayer.getcurrentroom().getConnectedRooms("west"));
                        if(parseplayer.getcurrentroom().getMobInfo() == true && parseplayer.getcurrentroom().getRoomMob().getmobstatus() == true) {
                            entercombat(parseplayer, parseplayer.getcurrentroom().getRoomMob());
                            combat = true;
                        }
                    }
                }
                else if(input[1].equals("westen") && parseplayer.getcurrentroom().getConnectedRooms("west") == null) {
                    System.out.println("In dieser Richtung befindet sich keine Tür in einen anderen Raum.");
                }
                else {
                    System.out.println("Das ist keine gültige Richtung!");
                }
            }
            catch(Exception e) {
                System.out.println("Du musst eine Richtung angeben. Gültige Richtung sind: north, east, south, west.");
            }
        }
        else if(input[0].equals("tp") && parseplayer.getplayername().equals("Leonard")) {
            parseplayer.setcurrentroom(activestage.getroomlist().get(Integer.parseInt(input[1])));
            if(parseplayer.getcurrentroom().getMobInfo() == true && parseplayer.getcurrentroom().getRoomMob().getmobstatus() == true) {
                entercombat(parseplayer, parseplayer.getcurrentroom().getRoomMob());
                combat = true;
            }
        }
        else if(input[0].equals("exit")) {
            System.out.println("Du verlässt das Dungeon.");
            running = false;
        }
        else {
            System.out.println("Dies ist kein gültiger Befehl");
        }
    }
    
    public void entercombat(Player parseplayer, Mob parsemob) {
        activemob = parsemob;
        weaponselected = false;
        System.out.println("Als du den Raum betrittst, entdeckst du " + parsemob.getArtikel("akkusativ", "unbestimmt") + " [Level " + parsemob.getlevel() + "] " + parsemob.getmobname() + ". Es kommt zum Kampf.");
        System.out.println("Welche Waffe möchtest du für den Kampf benutzen?");
        parseplayer.getinventorycontent();
        System.out.println();
        while(!weaponselected) {
            Scanner weaponparser = new Scanner(System.in);
            try {
                weaponnumber = Integer.parseInt(weaponparser.nextLine());
                try {
                    playerweapon = ((Weapon) parseplayer.getitemfrominventory(weaponnumber));
                    weaponselected = true;
                }
                catch(Exception e) {
                    System.out.println("Du musst eine Waffe für den Kampf wählen. Benutze dafür eine Zahl aus deinem Inventar mit einer Waffe.");
                }
            }
            catch(Exception e) {
                System.out.println("Dies ist keine gültige Zahl für dein Inventar");
            }
        }
        System.out.println("Du wählst " + playerweapon.getArtikel("akkusativ", "bestimmt") + " " + playerweapon.getitemname() + ".");
        System.out.println();
    }
    
    public void getcombataction(String parseaction, Player parseplayer, Mob parsemob) {
        input = parseaction.toLowerCase().split("\\s+"); 
        if(input[0].equals("hilfe")) {
            System.out.println("Aktuell kannst du folgenden Aktionen machen:");
            for(int i = 0; i < combatactionlist.length; i++) {
                System.out.println(combatactionlist[i]);
            }
            mobattack = false;
        }
        else if(input[0].equals("inv")) {
            parseplayer.getinventorycontent();
            mobattack = false;
        }
        else if(input[0].equals("benutze")) {
            try {
                inventorynumber = Integer.parseInt(input[1]);
                if(parseplayer.getitemfrominventory(inventorynumber).getClass() == Potion.class) {
                    if(((Potion) parseplayer.getitemfrominventory(inventorynumber)).getpotiontype() == "Damage") {
                        parsemob.setmobhealth(parsemob.getmobhealth() - ((Potion) parseplayer.getitemfrominventory(inventorynumber)).getpotioneffect());
                        parseplayer.removeitemfrominventory(inventorynumber);
                        System.out.println("Du wirfst den Schadenstrank auf " + parsemob.getArtikel("akkusativ", "bestimmt") + " " + parsemob.getmobname() + " und machst " + ((Potion) parseplayer.getitemfrominventory(inventorynumber)).getpotioneffect() + " Schaden!");
                    }
                    else if(((Potion) parseplayer.getitemfrominventory(inventorynumber)).getpotiontype() == "Poison") {
                        mobpoisonrounds = 3;
                        mobpoisoneffect = ((Potion) parseplayer.getitemfrominventory(inventorynumber)).getpotioneffect();
                        parseplayer.removeitemfrominventory(inventorynumber);
                        System.out.println("Du wirfst den Gifttrank auf " + parsemob.getArtikel("akkusativ", "bestimmt") + " " + parsemob.getmobname() + ". " + parsemob.getArtikel("nominativ", "bestimmt").substring(0, 1).toUpperCase() + parsemob.getArtikel("nominativ", "bestimmt").substring(1) + " " + parsemob.getmobname() + " ist nun vergiftet.");
                    }
                    else if(((Potion) parseplayer.getitemfrominventory(inventorynumber)).getpotiontype() == "Healing") {
                        parseplayer.drink(inventorynumber);
                    }
                }
                else if(parseplayer.getitemfrominventory(inventorynumber).getClass() == Consumable.class) {
                    parseplayer.consume(inventorynumber);
                }
                else {
                    System.out.println("Du kannst nur Tränke und Essen im Kampf verwenden");
                    mobattack = false;
                }
            }
            catch(Exception e) {
                System.out.println("Dies ist keine gültige Zahl für dein Inventar");
            }
        }
        else if(input[0].equals("attackiere")) {
            parseplayer.attack(parsemob, playerweapon);
            if(parsemob.getmobhealth() <= 0) {
                if(parseplayer.getWeakened()) {
                    parseplayer.setStrength(parseplayer.getPlayerStrength() * 1.25);
                    parseplayer.setWeakened(false);
                }
                System.out.println("Du greifst " + parsemob.getArtikel("akkusativ", "bestimmt") + " " + parsemob.getmobname() + " mit " + playerweapon.getArtikel("dativ", "bestimmt") + " " + playerweapon.getitemname() + " an und machst " + parseplayer.getplayerdamage() + " Schaden.");
                parsemob.setmobstatus(false);
                System.out.println("Du hast den " + parsemob.getmobname() + " besiegt. ");
                parsemob.droploot(parseplayer, parsemob);
                parseplayer.addexperience(parsemob.getmobxp());
                combat = false;
                running = true;
            }
            else {
                System.out.println("Du greifst " + parsemob.getArtikel("akkusativ", "bestimmt") + " " + parsemob.getmobname() + " mit " + playerweapon.getArtikel("dativ", "bestimmt") + " " + playerweapon.getitemname() + " an und machst " + parseplayer.getplayerdamage() + " Schaden.");
                System.out.println(parsemob.getArtikel("nominativ", "bestimmt").substring(0, 1).toUpperCase() + parsemob.getArtikel("nominativ", "bestimmt").substring(1) + " " + parsemob.getmobname() + " hat nun " + parsemob.getmobhealth() + " Leben.");
            }
        }
        else if(input[0].equals("exit")) {
            System.out.println("Du verlässt den Kampf.");
            combat = false;
        }
        else {
            System.out.println("Dies ist kein gültiger Befehl");
            mobattack = false;
        }
    }
}
