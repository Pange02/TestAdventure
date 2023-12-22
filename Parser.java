import java.util.Scanner;
import java.util.ArrayList;
import java.lang.Math;
/**
 * Die Klasse �Parser� nimmt die Eingaben des Spielers entgegen und gibt entsprechend der Eingaben die Befehle weiter und l�st somit andere Ereignisse aus.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Parser
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    
    //Aktionsliste f�r alle m�glichen Aktionen. Verwendet von help Befehl.
    private String[] actionlist = {"umgucken", "�ffne (truhe)", "stats", "benutze <Inventarslot>", "gehe <Himmelsrichtung>", "rede <Name>", "skill <F�higkeit> <Punkte>", "info <Inventarslot>", "inv", "ablegen <R�stungsteil>", "hilfe"};
    
    //Aktionsliste im Kampf.
    private String[] combatactionlist = {"attackiere", "benutze <Inventarslot>"};
    
    //Array f�r den Input des Spielers
    private String[] input = new String[10];
    
    //Array f�r den BuyInput des Spielers
    private String[] buyinput = new String[10];
    
    //Boolean gibt an, ob der Parser (das Spiel) aktiv ist.
    private boolean running;
    
    //Das aktive Spiel
    private Game activegame;
    
    //Die aktive Stage
    private Stage activestage;
    
    //Boolean, ob sich der Spieler im Kampf befindet.
    private boolean combat;
    
    //Aktiver Spieler vom Parser
    private static Player activeplayer;
    
    //Zahl, die am Anfang des Kampfes als Waffe vom Spieler ausgew�hlt wird
    private int weaponnumber;
    
    //Zahl f�r viele Inventaraktionen
    private int inventorynumber;
    
    //Waffe des Spielers im Kampf
    private Weapon playerweapon;
    
    //Aktiver Mob
    private Mob activemob;
    
    //Boolean gibt an, ob der Mob eine Attacke machen kann. Ist false, wenn der Spieler im Kampf Aktionen wie inv oder einen falschen Input nutzt.
    private boolean mobattack;
    
    //Anzahl der Runden, die der Mob vergiftet ist.
    private int mobpoisonrounds;
    
    //St�rke des Gifteffekts bei Mobs in Prozent
    private int mobpoisoneffect;
    
    //Anzahl der Runden, die der Spieler vergiftet ist.
    private int playerpoisonrounds;
    
    //St�rke des Gifteffekts bei Spielern in Prozent.
    private int playerpoisoneffect;
    
    //Boolean f�r den ersten Kampf.
    private boolean firstfight;
    
    //Boolean, ob eine Waffe ausgew�hlt wurde, um falsche Eingaben zu vermeiden.
    private boolean weaponselected;
    
    //Alle Stages als Array f�r das Wechseln zwischen den Stages.
    private Stage[] stagelist;
    
    //Aktuelle Stage des Spielers
    private int currentstage;
    
    //Boolean, ob der Boss der Stage besiegt wurde.
    private boolean stagecompleted;
    
    /**
     * Hier wird das Spiel ausgef�hrt und zum 
     */
    public Parser(Game parsegame, Stage parsestage, Player parseplayer, Stage[] parsestages)
    {
        //Alle Anfangswerte werden gesetzt
        running = true;
        activegame = parsegame;
        activestage = parsestage;
        stagelist = new Stage[3];
        stagelist = parsestages;
        currentstage = 0;
        stagecompleted = false;
        combat = false;
        firstfight = true;
        weaponselected = false;
        mobattack = true;
        activeplayer = parseplayer;
        //Hier die Hauptschleife des Spiels.
        while(running && !combat) {
            Scanner parser = new Scanner(System.in);
            getaction(parser.nextLine(), activeplayer);
            System.out.println();
            //Wenn der Spieler sich im Kampf befindet.
            while(running && combat) {
                //Beim ersten Kampf werden noch die Aktionen angezeigt.
                if(firstfight == true) {
                    firstfight = false;
                    System.out.println("Aktuell kannst du folgenden Kampfaktionen machen:");
                    for(int i = 0; i < combatactionlist.length; i++) {
                        System.out.println(combatactionlist[i]);
                    }
                    System.out.println();
                }
                //Giftlogik f�r Spieler und Mob. Zieht einen prozentualen Anteil des Lebens ab.
                if(playerpoisonrounds > 1 && mobattack) {
                    if(playerpoisoneffect == 0) {
                        playerpoisonrounds = activeplayer.getpoisonrounds();
                    }
                    playerpoisoneffect = 5;
                    activeplayer.setplayerhealth(Math.round((activeplayer.getplayerhealth() - (activeplayer.getplayerhealth() * (playerpoisoneffect * 0.01))) * 10) * 0.1);
                    playerpoisonrounds -= 1;
                    System.out.println("Durch das Gift nimmst du " + playerpoisoneffect + "% Schaden. Du hast jetzt " + activeplayer.getplayerhealth() + " Leben.");
                    System.out.println("Das Gift h�lt noch " + playerpoisonrounds + " weitere Runden. ");
                }
                if(activeplayer.getpoisonrounds() == 1 && mobattack) {
                    activeplayer.setplayerhealth(Math.round((activeplayer.getplayerhealth() - (activeplayer.getplayerhealth() * (playerpoisoneffect * 0.01))) * 10) * 0.1);
                    playerpoisonrounds -= 1;
                    System.out.println("Durch das Gift nimmst du " + playerpoisoneffect + "% Schaden. Du hast jetzt " + activeplayer.getplayerhealth() + " Leben.");
                    System.out.println("Das Gift hat nun seine Wirkung verloren.");
                }
                if(mobpoisonrounds > 1 && mobattack) {
                    activemob.setmobhealth(Math.round((activemob.getmobhealth() - (activemob.getmobhealth() * (mobpoisoneffect * 0.01))) * 10)/10);
                    mobpoisonrounds -= 1;
                    System.out.println("Durch das Gift nimmt " + activemob.getArtikel("nominativ", "bestimmt") + " " + activemob.getmobname() + " " + mobpoisoneffect + "% Schaden. " + activemob.getArtikel("nominativ", "bestimmt").substring(0, 1).toUpperCase() 
                    + activemob.getArtikel("nominativ", "bestimmt").substring(1) + " " + activemob.getmobname() + " hat jetzt " + activemob.getmobhealth() + " Leben. Das Gift h�lt noch " + mobpoisonrounds + " weitere Runden.");
                }
                else if(mobpoisonrounds == 1 && mobattack) {
                    activemob.setmobhealth(Math.floor((activemob.getmobhealth() - (activemob.getmobhealth() * (mobpoisoneffect * 0.01))) * 10)/10);
                    mobpoisonrounds -= 1;
                    System.out.println("Durch das Gift nimmt " + activemob.getArtikel("nominativ", "bestimmt") + " " + activemob.getmobname() + " " + mobpoisoneffect + "% Schaden. " + activemob.getArtikel("nominativ", "bestimmt").substring(0, 1).toUpperCase() 
                    + activemob.getArtikel("nominativ", "bestimmt").substring(1) + " " + activemob.getmobname() + " hat jetzt " + activemob.getmobhealth() + " Leben. Der Gifteffekt hat nun seine Wirkung verloren.");
                }
                mobattack = true;
                Scanner compatparser = new Scanner(System.in);
                getcombataction(parser.nextLine(), activeplayer, activemob);
                //Wenn der Mob noch lebt, macht er eine Attacke, au�er der Spieler hat selbst keinen Angriff gemacht (mobattack).
                if(activemob.getmobhealth() > 0 && mobattack) {
                    activemob.attack(activeplayer);
                    if(activeplayer.getplayerhealth() < 0) {
                        activeplayer.setplayerhealth(0);
                    }
                }
                //Man kann auch sterben und wird somit zu�rck an den Anfang gesetzt.
                if(parseplayer.getplayerhealth() == 0) {
                    System.out.println("Du bist gestorben!");
                    System.out.println("Schreibe \"Neustart\" um das Level zu wiederholen.");
                    Scanner endparser = new Scanner(System.in);
                    if(endparser.nextLine().toLowerCase().equals("neustart")) {
                        combat = false;
                        running = false;
                        Game nextGame = new Game();
                    }
                    else {
                        combat = false;
                        running = false;
                    }
                }
                System.out.println();
                }
        }
    }

    /**
     * Alle m�glichen Inputs werden vom Parser nach Leerzeichen getrennt und �berpr�ft, ob sie einer Aktion entsprechen und dann von ihm ausgef�hrt.
     */
    public void getaction(String parseaction, Player parseplayer)
    {
        //Den Spielerinput in einzelne W�rter splittet.
        input = parseaction.toLowerCase().split("\\s+");
        //Der Hilfe Befehl
        if(input[0].equals("hilfe")) {
            try {
                if(input[1].toLowerCase().equals("hilfe")) {
                    System.out.println("Dieser Befehl hilft dir bei den verschiedenen Aktionen, die du im Spiel machen kannst und generiert diese Texte.");
                }
                else if(input[1].toLowerCase().equals("�ffne")) {
                    System.out.println("Dieser Befehl �ffnet eine Truhe, die in deinem aktuellen Raum ist. Schreibe dazu \"�ffne truhe\". Eine Truhe kann nur einmal ge�ffnet werden.");
                }
                else if(input[1].toLowerCase().equals("stats")) {
                    System.out.println("Dieser Befehl gibt dir Auskunft �ber deine aktuellen Charakter Statistiken.");
                }
                else if(input[1].toLowerCase().equals("inv")) {
                    System.out.println("Dieser Befehl zeigt dir dein Inventar inklusive R�stung und Accessoires.");
                }
                else if(input[1].toLowerCase().equals("rede")) {
                    System.out.println("So kannst du mit Personen sprechen, die dir im Spiel begenen. Dazu musst du ebenfalls ihren Namen angeben mit \"rede <Name>\".");
                }
                else if(input[1].toLowerCase().equals("info")) {
                    System.out.println("Dieser Befehl gibt dir Auskunft �ber einen deiner Gegenst�nde im Inventar. Zus�tzlich musst du angeben, �ber welchen Gegenstand du mehr lernen willst mit \"info <Inventarplatz>\".");
                }
                else if(input[1].toLowerCase().equals("benutze")) {
                    System.out.println("So kannst du Gegenst�nde aus deinem Inventar benutzen. Dies k�nnen sehr viele verschiedene Dinge sein. Achte darauf, dass du auch angibst, welchen Gegenstand du benutzen willst mit \"benutze <Inventarplatz>\".");
                }
                else if(input[1].toLowerCase().equals("ablegen")) {
                    System.out.println("Mit diesem Befehl kannst du ein bereits angezogenes R�stungsteil wieder ablegen. Verwende ihn in der Form \"ablegen (Helm/Brustplatte/Hose/Schuhe)\". Das ausgezogene R�stungsteil wird danach deinem Inventar hinzugef�gt.");
                }
                else if(input[1].toLowerCase().equals("umgucken")) {
                    System.out.println("Dieser Befehl zeigt dir deine aktuelle Umgebung aus deinem Raum.");
                }
                else if(input[1].toLowerCase().equals("skill")) {
                    System.out.println("So kannst du deine mit einem Levelaufstieg verdienten Skillpunkte ausgeben. Du kannst sie entweder in die F�higkeit St�rke oder in die F�higkeit Verteidigung investieren. Schreibe dazu \"skill <F�higkeit> <Punkte>\".");
                }
                else if(input[1].toLowerCase().equals("gehe")) {
                    System.out.println("Mit diesem Befehl kannst du deinen Raum wechseln. Die R�ume liegen immer in einer der vier Himmelsrichtungen Norden, Osten, S�den und Westen. Schreibe dazu \"gehe <Himmelsrichtung>\".");
                }
            }
            catch(Exception e) {
                System.out.println("Aktuell kannst du folgenden Aktionen machen:");
                for(int i = 1; i < actionlist.length; i++) {
                    System.out.println(actionlist[i]);
                }
                System.out.println();
                System.out.println("Du kannst auch \"hilfe <Befehl>\" schreiben, um genauere Informationen zu den einzelnen Befehlen zu erhalten");
            }
        }
        //Zum Truhen �ffnen.
        else if(input[0].equals("�ffne")) {
            try {
                if(input[1].equals("truhe") && parseplayer.getcurrentroom().getChestInfo()) {
                    parseplayer.getcurrentroom().getChest().openChest(Parser.activeplayer);
                }
                else if(input[1].equals("truhe") && (parseplayer.getcurrentroom().getChestInfo() == false)) {
                    System.out.println("Dieser Raum hat keine Truhe");
                }
                else {
                    System.out.println("Du kannst dieses Objekt nicht �ffnen!");
                } 
            }
            catch(Exception e) {
                System.out.println("Du musst angeben, was du �ffnen willst.");
            }
        }
        //umgucken Befehl
        else if(input[0].equals("umgucken")) {
            //Wenn der Raum eine Truhe hat.
            if(parseplayer.getcurrentroom().getChestInfo()) {
                if(parseplayer.getcurrentroom().getChest().getIsOpenable() == true) {
                    System.out.println("Du entdeckst eine Truhe in dem Raum.");
                }
                else {
                    System.out.println("Du entdeckst eine ge�ffnete Truhe in dem Raum.");
                }
            }
            //Wenn der Raum ein NPC hat.
            if(parseplayer.getcurrentroom().getNPCInfo()) {
                System.out.println("Du entdeckst die Person " + parseplayer.getcurrentroom().getNPC().getNPCname() + " im Raum.");
            }
            //Die verbundenen T�ren des Raums.
            System.out.println("Du siehst T�ren im:");
            for(int i = 0; i < parseplayer.getcurrentroom().getRoomDirections().size(); i++) {
                    System.out.println(parseplayer.getcurrentroom().getRoomDirections().get(i));
            }            
        }
        //Mit NPCs reden
        else if(input[0].equals("rede")) {
            try {
                //F�r Merchants.
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
                            System.out.println("Ok, vielleicht sp�ter!");
                        }
                    }
                    //F�r Speaker
                    else if(parseplayer.getcurrentroom().getNPC().getClass() == Speaker.class) {
                        ((Speaker) parseplayer.getcurrentroom().getNPC()).speak(parseplayer);
                    }
                    //F�r Blacksmiths
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
                                            System.out.println("Du hast nicht gen�gend Coins daf�r!");
                                        }
                                    }
                                }
                                else {
                                    System.out.println("Du hast nicht gen�gend Coins daf�r!");
                                }
                                System.out.println("Ok, vielen Dank f�r das Gesch�ft.");
                            }
                            catch(Exception e) {
                                System.out.println("Du musst eine Zahl f�r dein Inventar angeben");
                            }  
                        }
                        else {
                            System.out.println("Ok, vielleicht beim n�chsten Mal!");
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
        //Der Inventar Befehl.
        else if(input[0].equals("inv")) {
            parseplayer.getinventorycontent();
        }
        //Der stats Befehl.
        else if(input[0].equals("stats")) {
            parseplayer.getplayerstats();
        }
        //Zum Inventarobjekte benutzen.
        else if(input[0].equals("benutze")) {
            try {
                inventorynumber = Integer.parseInt(input[1]);
                //Unterscheidung der Itemtypen:
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
                        else if(parseplayer.getcurrentroom().getLock("east") != null && input[2].toLowerCase().equals("osten")) {
                            parseplayer.getcurrentroom().getLock("east").openLock(parseplayer.getcurrentroom(), ((Key) parseplayer.getitemfrominventory(inventorynumber)));
                            parseplayer.removeitemfrominventory(inventorynumber);
                        }
                        else if(parseplayer.getcurrentroom().getLock("south") != null && input[2].toLowerCase().equals("s�den")) {
                            parseplayer.getcurrentroom().getLock("south").openLock(parseplayer.getcurrentroom(), ((Key) parseplayer.getitemfrominventory(inventorynumber)));
                            parseplayer.removeitemfrominventory(inventorynumber);
                        }
                        else if(parseplayer.getcurrentroom().getLock("west") != null && input[2].toLowerCase().equals("westen")) {
                            parseplayer.getcurrentroom().getLock("west").openLock(parseplayer.getcurrentroom(), ((Key) parseplayer.getitemfrominventory(inventorynumber)));
                            parseplayer.removeitemfrominventory(inventorynumber);
                        }
                        else {
                            System.out.println("Dies ist keine g�ltige Richtung. G�ltige Richtungen sind: Norden, Osten, S�den, Westen.");
                        }
                    }
                    catch(Exception e) {
                        System.out.println("Du musst eine Richtung angeben, in welcher du den Schl�ssel benutzen m�chtest!");
                    }
                }
                else {
                    System.out.println("Du kannst dieses Item akutell nicht benutzen!");
                }
            }
            catch(Exception e) {
                System.out.println("Dies ist keine g�ltige Zahl aus deinem Inventar!");
            }
        }
        //R�stungsteile ablegen.
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
                    System.out.println("Du kannst dies nicht ablegen! W�hle zwischen Helm, Brustplatte, Hose und Schuhe.");
                }
            }
            catch(Exception e) {
                System.out.println("Du musst das R�stungsteil, welches du ablegen willst, angeben.");
            }
        }
        //Zus�tzliche Informationen zu dem Inventarobjekt bekommen.
        else if(input[0].equals("info")) {
            try {
                try {
                   inventorynumber = Integer.parseInt(input[1]);
                   parseplayer.getitemfrominventory(inventorynumber).getiteminfo();
                }
                catch(Exception e) {
                    System.out.println("Dies ist keine g�ltige Zahl f�r dein Inventar");
                }
            }
            catch(Exception e) {
                System.out.println("Du musst einen Inventarslot angeben");
            }
        }
        //Zus�tzliche Informationen zu dem Accessoire bekommen.
        else if(input[0].equals("ainfo")) {
            try {
                try {
                   inventorynumber = Integer.parseInt(input[1]);
                   parseplayer.getitemfromaccessories(inventorynumber).getiteminfo();
                }
                catch(Exception e) {
                    System.out.println("Dies ist keine g�ltige Zahl f�r dein Inventar");
                }
            }
            catch(Exception e) {
                System.out.println("Du musst einen Inventarslot angeben");
            }
        }
        //Skillpunkte verteilen
        else if(input[0].equals("skill")) {
            try {
                if(input[1].toLowerCase().equals("st�rke")) {
                    try {
                        parseplayer.addskillpoints("strength", Integer.parseInt(input[2]));
                    }
                    catch(Exception e) {
                        System.out.println("Du musst als Zahl angeben, wie viele Punkte du in St�rke investieren m�chtest.");
                    }
                }
                else if(input[1].toLowerCase().equals("verteidigung")) {
                    try {
                        parseplayer.addskillpoints("defense", Integer.parseInt(input[2]));
                    }
                    catch(Exception e) {
                        System.out.println("Du musst als Zahl angeben, wie viele Punkte du in Verteidigung investieren m�chtest.");
                    }
                }
                else {
                    System.out.println("Dies ist keine g�ltige F�higkeit.");
                }
            }
            catch(Exception e) {
                System.out.println("Du musst angeben, in welche F�higkeit du deine Skillpunkte investieren m�chtest. M�glich sind St�rke und Verteidigung");
            }
        }
        //Wenn die Stage geschafft wurde und der Boss besiegt, kann eine neue Stage begonnen werden.
        else if(input[0].equals("weiter") && stagecompleted) {
            currentstage += 1;
            parseplayer.setstage(stagelist[currentstage]);
            parseplayer.setcurrentroom(stagelist[currentstage].getstartroom());
            stagecompleted = false;
            System.out.println("Du bist am Tore der neuen Festung angekommen.");
        }
        //R�ume im Spiel wechseln.
        else if(input[0].equals("gehe")) {
            try {
                if(input[1].equals("norden") && parseplayer.getcurrentroom().getConnectedRooms("north") != null) {
                    if(parseplayer.getcurrentroom().getLock("north") != null) {
                        System.out.println("Du entdeckst " + parseplayer.getcurrentroom().getLock("north").getArtikel("akkusativ", "unbestimmt") + " " + parseplayer.getcurrentroom().getLock("north").getname() + " an der T�r. Du ben�tigst ein Schl�ssel f�r dieses Schloss.");
                    }
                    else {
                        System.out.println("Du bewegst dich durch die T�r in den neuen Raum im Norden");
                        parseplayer.setcurrentroom(parseplayer.getcurrentroom().getConnectedRooms("north"));
                        if(parseplayer.getcurrentroom().getMobInfo() == true && parseplayer.getcurrentroom().getRoomMob().getmobstatus() == true) {
                            entercombat(parseplayer, parseplayer.getcurrentroom().getRoomMob());
                            combat = true;
                        }
                        else {
                            if(parseplayer.getcurrentroom().getChestInfo()) {
                                if(parseplayer.getcurrentroom().getChest().getIsOpenable() == true) {
                                    System.out.println("Du entdeckst eine Truhe in dem Raum.");
                                }
                                else {
                                    System.out.println("Du entdeckst eine ge�ffnete Truhe in dem Raum.");
                                }
                            }
                            if(parseplayer.getcurrentroom().getNPCInfo()) {
                                System.out.println("Du entdeckst die Person " + parseplayer.getcurrentroom().getNPC().getNPCname() + " im Raum.");
                            }
                            System.out.println("Du siehst T�ren im:");
                            for(int i = 0; i < parseplayer.getcurrentroom().getRoomDirections().size(); i++) {
                                System.out.println(parseplayer.getcurrentroom().getRoomDirections().get(i));
                            } 
                        }
                    }
                }
                else if(input[1].equals("norden") && parseplayer.getcurrentroom().getConnectedRooms("north") == null) {
                    System.out.println("In dieser Richtung befindet sich keine T�r in einen anderen Raum.");
                }
                else if(input[1].equals("osten") && parseplayer.getcurrentroom().getConnectedRooms("east") != null) {
                    if(parseplayer.getcurrentroom().getLock("east") != null) {
                        System.out.println("Du entdeckst " + parseplayer.getcurrentroom().getLock("east").getArtikel("akkusativ", "unbestimmt") + " " + parseplayer.getcurrentroom().getLock("east").getname() + " an der T�r. Du ben�tigst ein Schl�ssel f�r dieses Schloss.");
                    }
                    else {
                        System.out.println("Du bewegst dich durch die T�r in den neuen Raum im Osten");
                        parseplayer.setcurrentroom(parseplayer.getcurrentroom().getConnectedRooms("east"));
                        if(parseplayer.getcurrentroom().getMobInfo() == true && parseplayer.getcurrentroom().getRoomMob().getmobstatus() == true) {
                            entercombat(parseplayer, parseplayer.getcurrentroom().getRoomMob());
                            combat = true;
                        }
                        else {
                            if(parseplayer.getcurrentroom().getChestInfo()) {
                                if(parseplayer.getcurrentroom().getChest().getIsOpenable() == true) {
                                    System.out.println("Du entdeckst eine Truhe in dem Raum.");
                                }
                                else {
                                    System.out.println("Du entdeckst eine ge�ffnete Truhe in dem Raum.");
                                }
                            }
                            if(parseplayer.getcurrentroom().getNPCInfo()) {
                                System.out.println("Du entdeckst die Person " + parseplayer.getcurrentroom().getNPC().getNPCname() + " im Raum.");
                            }
                            System.out.println("Du siehst T�ren im:");
                            for(int i = 0; i < parseplayer.getcurrentroom().getRoomDirections().size(); i++) {
                                System.out.println(parseplayer.getcurrentroom().getRoomDirections().get(i));
                            } 
                        }
                    }
                }
                else if(input[1].equals("osten") && parseplayer.getcurrentroom().getConnectedRooms("east") == null) {
                    System.out.println("In dieser Richtung befindet sich keine T�r in einen anderen Raum.");
                }
                else if(input[1].equals("s�den") && parseplayer.getcurrentroom().getConnectedRooms("south") != null) {
                    if(parseplayer.getcurrentroom().getLock("south") != null) {
                        System.out.println("Du entdeckst " + parseplayer.getcurrentroom().getLock("south").getArtikel("akkusativ", "unbestimmt") + " " + parseplayer.getcurrentroom().getLock("south").getname() + " an der T�r. Du ben�tigst ein Schl�ssel f�r dieses Schloss.");
                    }
                    else {
                        System.out.println("Du bewegst dich durch die T�r in den neuen Raum im S�den");
                        parseplayer.setcurrentroom(parseplayer.getcurrentroom().getConnectedRooms("south"));
                        if(parseplayer.getcurrentroom().getMobInfo() == true && parseplayer.getcurrentroom().getRoomMob().getmobstatus() == true) {
                            entercombat(parseplayer, parseplayer.getcurrentroom().getRoomMob());
                            combat = true;
                        }
                        else {
                            if(parseplayer.getcurrentroom().getChestInfo()) {
                                if(parseplayer.getcurrentroom().getChest().getIsOpenable() == true) {
                                    System.out.println("Du entdeckst eine Truhe in dem Raum.");
                                }
                                else {
                                    System.out.println("Du entdeckst eine ge�ffnete Truhe in dem Raum.");
                                }
                            }
                            if(parseplayer.getcurrentroom().getNPCInfo()) {
                                System.out.println("Du entdeckst die Person " + parseplayer.getcurrentroom().getNPC().getNPCname() + " im Raum.");
                            }
                            System.out.println("Du siehst T�ren im:");
                            for(int i = 0; i < parseplayer.getcurrentroom().getRoomDirections().size(); i++) {
                                System.out.println(parseplayer.getcurrentroom().getRoomDirections().get(i));
                            } 
                        }
                    }
                }
                else if(input[1].equals("s�den") && parseplayer.getcurrentroom().getConnectedRooms("south") == null) {
                    System.out.println("In dieser Richtung befindet sich keine T�r in einen anderen Raum.");
                }
                else if(input[1].equals("westen") && parseplayer.getcurrentroom().getConnectedRooms("west") != null) {
                    if(parseplayer.getcurrentroom().getLock("west") != null) {
                        System.out.println("Du entdeckst " + parseplayer.getcurrentroom().getLock("west").getArtikel("akkusativ", "unbestimmt") + " " + parseplayer.getcurrentroom().getLock("west").getname() + " an der T�r. Du ben�tigst ein Schl�ssel f�r dieses Schloss.");
                    }
                    else {
                        System.out.println("Du bewegst dich durch die T�r in den neuen Raum im Westen");
                        parseplayer.setcurrentroom(parseplayer.getcurrentroom().getConnectedRooms("west"));
                        if(parseplayer.getcurrentroom().getMobInfo() == true && parseplayer.getcurrentroom().getRoomMob().getmobstatus() == true) {
                            entercombat(parseplayer, parseplayer.getcurrentroom().getRoomMob());
                            combat = true;
                        }
                        else {
                            if(parseplayer.getcurrentroom().getChestInfo()) {
                                if(parseplayer.getcurrentroom().getChest().getIsOpenable() == true) {
                                    System.out.println("Du entdeckst eine Truhe in dem Raum.");
                                }
                                else {
                                    System.out.println("Du entdeckst eine ge�ffnete Truhe in dem Raum.");
                                }
                            }
                            if(parseplayer.getcurrentroom().getNPCInfo()) {
                                System.out.println("Du entdeckst die Person " + parseplayer.getcurrentroom().getNPC().getNPCname() + " im Raum.");
                            }
                            System.out.println("Du siehst T�ren im:");
                            for(int i = 0; i < parseplayer.getcurrentroom().getRoomDirections().size(); i++) {
                                System.out.println(parseplayer.getcurrentroom().getRoomDirections().get(i));
                            } 
                        }
                    }
                }
                else if(input[1].equals("westen") && parseplayer.getcurrentroom().getConnectedRooms("west") == null) {
                    System.out.println("In dieser Richtung befindet sich keine T�r in einen anderen Raum.");
                }
                else {
                    System.out.println("Das ist keine g�ltige Richtung!");
                }
            }
            catch(Exception e) {
                System.out.println("Du musst eine Richtung angeben. G�ltige Richtung sind: north, east, south, west.");
            }
        }
        //Teleport Cheat zum Testen.
        else if(input[0].equals("tp") && parseplayer.getplayername().equals("Leonard")) {
            activestage = stagelist[Integer.parseInt(input[1])];
            parseplayer.setstage(activestage);
            parseplayer.setcurrentroom(activestage.getroomlist().get(Integer.parseInt(input[2])));
            if(parseplayer.getcurrentroom().getMobInfo() == true && parseplayer.getcurrentroom().getRoomMob().getmobstatus() == true) {
                entercombat(parseplayer, parseplayer.getcurrentroom().getRoomMob());
                combat = true;
            }
        }
        //Exit Cheat zum Testen.
        else if(input[0].equals("exit")) {
            System.out.println("Du verl�sst das Dungeon.");
            running = false;
        }
        //Sonst kein g�ltiger Befehl.
        else {
            System.out.println("Dies ist kein g�ltiger Befehl");
        }
    }
    
    //Wenn man im neuen Raum einen Mob entdeckt.
    public void entercombat(Player parseplayer, Mob parsemob) {
        activemob = parsemob;
        weaponselected = false;
        System.out.println("Als du den Raum betrittst, entdeckst du " + parsemob.getArtikel("akkusativ", "unbestimmt") + " [Level " + parsemob.getlevel() + "] " + parsemob.getmobname() + ". Es kommt zum Kampf.");
        System.out.println("Welche Waffe m�chtest du f�r den Kampf benutzen?");
        parseplayer.getinventorycontent();
        System.out.println();
        //Waffe ausw�hlen
        while(!weaponselected) {
            Scanner weaponparser = new Scanner(System.in);
            try {
                weaponnumber = Integer.parseInt(weaponparser.nextLine());
                try {
                    playerweapon = ((Weapon) parseplayer.getitemfrominventory(weaponnumber));
                    weaponselected = true;
                }
                catch(Exception e) {
                    System.out.println("Du musst eine Waffe f�r den Kampf w�hlen. Benutze daf�r eine Zahl aus deinem Inventar mit einer Waffe.");
                }
            }
            catch(Exception e) {
                System.out.println("Dies ist keine g�ltige Zahl f�r dein Inventar");
            }
        }
        System.out.println("Du w�hlst " + playerweapon.getArtikel("akkusativ", "bestimmt") + " " + playerweapon.getitemname() + ".");
        System.out.println();
    }
    
    //Aktionen im Kampf.
    public void getcombataction(String parseaction, Player parseplayer, Mob parsemob) {
        input = parseaction.toLowerCase().split("\\s+");
        //Hilfe im Kampf.
        if(input[0].equals("hilfe")) {
            System.out.println("Aktuell kannst du folgenden Aktionen machen:");
            for(int i = 0; i < combatactionlist.length; i++) {
                System.out.println(combatactionlist[i]);
            }
            mobattack = false;
        }
        //Inventar im Kampf.
        else if(input[0].equals("inv")) {
            parseplayer.getinventorycontent();
            mobattack = false;
        }
        //Tr�nke und Consumables im Kampf benutzen.
        else if(input[0].equals("benutze")) {
            try {
                inventorynumber = Integer.parseInt(input[1]);
                if(parseplayer.getitemfrominventory(inventorynumber).getClass() == Potion.class) {
                    if(((Potion) parseplayer.getitemfrominventory(inventorynumber)).getpotiontype() == "Damage") {
                        parsemob.setmobhealth(parsemob.getmobhealth() - ((Potion) parseplayer.getitemfrominventory(inventorynumber)).getpotioneffect());
                        System.out.println("Du wirfst den Schadenstrank auf " + parsemob.getArtikel("akkusativ", "bestimmt") + " " + parsemob.getmobname() + " und machst " + ((Potion) parseplayer.getitemfrominventory(inventorynumber)).getpotioneffect() + " Schaden!");
                        parseplayer.removeitemfrominventory(inventorynumber);
                    }
                    else if(((Potion) parseplayer.getitemfrominventory(inventorynumber)).getpotiontype() == "Poison") {
                        mobpoisonrounds = 3;
                        mobpoisoneffect = ((Potion) parseplayer.getitemfrominventory(inventorynumber)).getpotioneffect();
                        System.out.println("Du wirfst den Gifttrank auf " + parsemob.getArtikel("akkusativ", "bestimmt") + " " + parsemob.getmobname() + ". " + parsemob.getArtikel("nominativ", "bestimmt").substring(0, 1).toUpperCase() + parsemob.getArtikel("nominativ", "bestimmt").substring(1) + " " + parsemob.getmobname() + " ist nun vergiftet.");
                        parseplayer.removeitemfrominventory(inventorynumber);
                    }
                    else if(((Potion) parseplayer.getitemfrominventory(inventorynumber)).getpotiontype() == "Healing") {
                        parseplayer.drink(inventorynumber);
                    }
                }
                else if(parseplayer.getitemfrominventory(inventorynumber).getClass() == Consumable.class) {
                    parseplayer.consume(inventorynumber);
                }
                else {
                    System.out.println("Du kannst nur Tr�nke und Essen im Kampf verwenden");
                    mobattack = false;
                }
                //Wenn der Mob stirbt, soll der Spieler belohnt werden
                if(parsemob.getmobhealth() <= 0) {
                    //Wenn ein Boss die St�rke des Spielers mit einer Spezialattacke um 0.8 multipliziert hat, soll der Wert nach dem Kampf mit 1.25 multipliziert werden, um wieder auf den Anfangswert zu kommen. 
                    if(parseplayer.getWeakened()) {
                        parseplayer.setStrength(parseplayer.getPlayerStrength() * 1.25);
                        parseplayer.setWeakened(false);
                    }
                    parsemob.setmobstatus(false);
                    System.out.println("Du hast den " + parsemob.getmobname() + " besiegt. ");
                    parsemob.droploot(parseplayer, parsemob);
                    parseplayer.addexperience(parsemob.getmobxp());
                    combat = false;
                    running = true;
                    //Wenn es ein Boss ist, hat man entweder die Stage oder das gesamte Spiel geschafft.
                    if(parsemob instanceof Boss) {
                        if(parsemob.getmobname().equals("Sir Archibald Duncan")) {
                            System.out.println("Du hast es geschafft! Die Welt muss sich nun nicht mehr vor dem b�sen Lord f�rchten.");
                            System.out.println("Gut gemacht!");
                            running = false;
                        }
                        else {
                            stagecompleted = true;
                            System.out.println("Du hast dieses Level geschafft! Du kannst nun \"weiter\" schreiben, um das n�chste Level zu beginnen");
                        }
                    }
                }
                else {
                    System.out.println(parsemob.getArtikel("nominativ", "bestimmt").substring(0, 1).toUpperCase() + parsemob.getArtikel("nominativ", "bestimmt").substring(1) + " " + parsemob.getmobname() + " hat nun " + parsemob.getmobhealth() + " Leben.");
                }
            }
            catch(Exception e) {
                System.out.println("Dies ist keine g�ltige Zahl f�r dein Inventar");
            }
        }
        //Den Mob im Kampf attackieren
        else if(input[0].equals("attackiere")) {
            parseplayer.attack(parsemob, playerweapon);
            if(parsemob.getmobhealth() <= 0) {
                //gleiche Logik wie oben.
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
                if(parsemob instanceof Boss) {
                    if(parsemob.getmobname().equals("Sir Archibald Duncan")) {
                        System.out.println("Du hast es geschafft! Die Welt muss sich nun nicht mehr vor dem b�sen Lord f�rchten.");
                        System.out.println("Gut gemacht!");
                        running = false;
                    }
                    else {
                        stagecompleted = true;
                        System.out.println("Du hast dieses Level geschafft! Du kannst nun \"weiter\" schreiben, um das n�chste Level zu beginnen");
                    }
                }
            }
            else {
                System.out.println("Du greifst " + parsemob.getArtikel("akkusativ", "bestimmt") + " " + parsemob.getmobname() + " mit " + playerweapon.getArtikel("dativ", "bestimmt") + " " + playerweapon.getitemname() + " an und machst " + parseplayer.getplayerdamage() + " Schaden.");
                System.out.println(parsemob.getArtikel("nominativ", "bestimmt").substring(0, 1).toUpperCase() + parsemob.getArtikel("nominativ", "bestimmt").substring(1) + " " + parsemob.getmobname() + " hat nun " + parsemob.getmobhealth() + " Leben.");
            }
        }
        //exit Cheat zum Testen.
        else if(input[0].equals("exit")) {
            System.out.println("Du verl�sst den Kampf.");
            combat = false;
        }
        else {
            System.out.println("Dies ist kein g�ltiger Befehl");
            mobattack = false;
        }
    }
}
