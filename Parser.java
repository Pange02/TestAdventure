import java.util.Scanner;
import java.util.ArrayList;
/**
 * Beschreiben Sie hier die Klasse Parser.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Parser
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    
    //Aktionsliste f�r alle m�glichen Aktionen. Verwendet von help Befehl.
    private String[] actionlist = {"hilfe", "�ffne", "inv", "umgucken", "gehe"};
    
    private String[] combatactionlist = {"attackiere"};
    
    //Array f�r den Input des Spielers
    private String[] input = new String[10];
    
    //Boolean gibt an, ob der Parser (das Spiel) aktiv ist.
    private boolean running;
    
    private boolean combat;
    
    //Aktiver Spieler vom Parser
    private static Player activeplayer;
    
    private int weaponnumber;
    
    private Weapon playerweapon;
    
    private Mob activemob;
    
    private boolean firstfight;
    
    private boolean weaponselected;
    
    /**
     * Konstruktor f�r Objekte der Klasse Parser mit Spieler
     */
    public Parser(Player parseplayer)
    {
        running = true;
        combat = false;
        firstfight = true;
        weaponselected = false;
        activeplayer = parseplayer;
        System.out.println("Was m�chtest du tun? F�r Hilfe: hilfe");
        while(running && !combat) {
            Scanner parser = new Scanner(System.in);
            getaction(parser.nextLine(), activeplayer);
            while(running && combat) {
                if(firstfight == true) {
                    firstfight = false;
                    System.out.println("Aktuell kannst du folgenden Kampfaktionen machen:");
                    for(int i = 0; i < combatactionlist.length; i++) {
                        System.out.println(combatactionlist[i]);
                    }
                }
                Scanner compatparser = new Scanner(System.in);
                getcombataction(parser.nextLine(), activeplayer, activemob);
                if(activemob.getmobhealth() > 0) {
                    activemob.attack(activeplayer);
                    System.out.println("Der " + activemob.getmobname() + " greift dich an und macht " + activemob.getmobdamage() + " Schaden.");
                    System.out.println("Du hast durch den Angriff jetzt " + parseplayer.getplayerhealth() + " Leben.");
                }
                }
        }
    }

    /**
     * Alle m�glichen Inputs werden vom Parser nach Leerzeichen getrennt und �berpr�ft, ob sie einer Aktion entsprechen und dann von ihm ausgef�hrt.
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
        
        else if(input[0].equals("�ffne")) {
            try {
                if(input[1].equals("truhe") && parseplayer.getcurrentroom().getchestinfo()) {
                    parseplayer.getcurrentroom().getchest().openchest(Parser.activeplayer, parseplayer.getcurrentroom().getchest());
                }
                else if(input[1].equals("truhe") && (parseplayer.getcurrentroom().getchestinfo() == false)) {
                    System.out.println("Dieser Raum hat keine Truhe");
                }
                else {
                    System.out.println("Du kannst dieses Objekt nicht �ffnen!");
                } 
            }
            catch(Exception e) {
                System.out.println(e);
                System.out.println("Du kannst dies nicht �ffnen!");
            }
        }    
        else if(input[0].equals("umgucken")) {
            if(parseplayer.getcurrentroom().getchestinfo()) {
                System.out.println("Du entdeckst eine Truhe in dem Raum.");
                System.out.println("Du siehst T�ren im:");
                for(int i = 0; i < parseplayer.getcurrentroom().getroomdirections().size(); i++) {
                    System.out.println(parseplayer.getcurrentroom().getroomdirections().get(i));
                }
            }
            else {
                System.out.println("Du siehst T�ren im:");
                for(int i = 0; i < parseplayer.getcurrentroom().getroomdirections().size(); i++) {
                    System.out.println(parseplayer.getcurrentroom().getroomdirections().get(i));
                }
            }
        }
        else if(input[0].equals("inv")) {
            parseplayer.getinventorycontent();
        }
        else if(input[0].equals("gehe")) {
            try {
                if(input[1].equals("norden") && parseplayer.getcurrentroom().getconnectedrooms("north") != null) {
                    System.out.println("Du bewegst dich durch die T�r in den neuen Raum im Norden");
                    parseplayer.setcurrentroom(parseplayer.getcurrentroom().getconnectedrooms("north"));
                    if(parseplayer.getcurrentroom().getmobinfo() == true && parseplayer.getcurrentroom().getroommob().getmobstatus() == true) {
                        entercombat(parseplayer, parseplayer.getcurrentroom().getroommob());
                        combat = true;
                    }
                }
                else if(input[1].equals("norden") && parseplayer.getcurrentroom().getconnectedrooms("north") == null) {
                    System.out.println("In dieser Richtung befindet sich keine T�r in einen anderen Raum.");
                }
                else if(input[1].equals("osten") && parseplayer.getcurrentroom().getconnectedrooms("east") != null) {
                    System.out.println("Du bewegst dich durch die T�r in den neuen Raum im Osten");
                    parseplayer.setcurrentroom(parseplayer.getcurrentroom().getconnectedrooms("east"));
                    if(parseplayer.getcurrentroom().getmobinfo() == true && parseplayer.getcurrentroom().getroommob().getmobstatus() == true) {
                        entercombat(parseplayer, parseplayer.getcurrentroom().getroommob());
                        combat = true;
                    }
                }
                else if(input[1].equals("osten") && parseplayer.getcurrentroom().getconnectedrooms("east") == null) {
                    System.out.println("In dieser Richtung befindet sich keine T�r in einen anderen Raum.");
                }
                else if(input[1].equals("s�den") && parseplayer.getcurrentroom().getconnectedrooms("south") != null) {
                    System.out.println("Du bewegst dich durch die T�r in den neuen Raum im S�den");
                    parseplayer.setcurrentroom(parseplayer.getcurrentroom().getconnectedrooms("south"));
                    if(parseplayer.getcurrentroom().getmobinfo() == true && parseplayer.getcurrentroom().getroommob().getmobstatus() == true) {
                        entercombat(parseplayer, parseplayer.getcurrentroom().getroommob());
                        combat = true;
                    }
                }
                else if(input[1].equals("s�den") && parseplayer.getcurrentroom().getconnectedrooms("south") == null) {
                    System.out.println("In dieser Richtung befindet sich keine T�r in einen anderen Raum.");
                }
                else if(input[1].equals("westen") && parseplayer.getcurrentroom().getconnectedrooms("west") != null) {
                    System.out.println("Du bewegst dich durch die T�r in den neuen Raum im Westen");
                    parseplayer.setcurrentroom(parseplayer.getcurrentroom().getconnectedrooms("west"));
                    if(parseplayer.getcurrentroom().getmobinfo() == true && parseplayer.getcurrentroom().getroommob().getmobstatus() == true) {
                        entercombat(parseplayer, parseplayer.getcurrentroom().getroommob());
                        combat = true;
                    }
                }
                else if(input[1].equals("westen") && parseplayer.getcurrentroom().getconnectedrooms("west") == null) {
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
        else if(input[0].equals("exit")) {
            System.out.println("Du verl�sst das Dungeon.");
            running = false;
        }
    }
    
    public void entercombat(Player parseplayer, Mob parsemob) {
        activemob = parsemob;
        System.out.println("Als du den Raum betrittst, entdeckst du ein " + parsemob.getmobname() + ". Es kommt zum Kampf.");
        System.out.println("Welche Waffe m�chtest du f�r den Kampf benutzen?");
        parseplayer.getinventorycontent();
        while(!weaponselected) {
            Scanner weaponparser = new Scanner(System.in);
            try {
                weaponnumber = Integer.parseInt(weaponparser.nextLine()); 
            }
            catch(Exception e) {
                System.out.println("Dies ist keine g�ltige Zahl f�r dein Inventar");
            }
            try {
                playerweapon = ((Weapon) parseplayer.getitemfrominventory(weaponnumber));
                weaponselected = true;
            }
            catch(Exception e) {
                System.out.println("Du musst eine Waffe f�r den Kampf w�hlen. Benutze daf�r eine Zahl aus deinem Inventar mit einer Waffe.");
            }  
        }
        System.out.println("Du w�hlst die " + playerweapon.getitemname() + ".");
    }
    
    public void getcombataction(String parseaction, Player parseplayer, Mob parsemob) {
        input = parseaction.toLowerCase().split("\\s+"); 
        if(input[0].equals("hilfe")) {
            System.out.println("Aktuell kannst du folgenden Aktionen machen:");
            for(int i = 0; i < combatactionlist.length; i++) {
                System.out.println(combatactionlist[i]);
            }
        }
        else if(input[0].equals("attackiere")) {
            parseplayer.attack(parsemob, playerweapon);
            if(parsemob.getmobhealth() <= 0) {
                parsemob.setmobstatus(false);
                System.out.println("Du hast den " + parsemob.getmobname() + " besiegt. ");
                parsemob.droploot(parseplayer, parsemob);
                combat = false;
                running = true;
            }
            else {
                System.out.println("Du greifst den " + parsemob.getmobname() + " mit " + playerweapon.getitemname() + " an und machst " + parseplayer.getplayerdamage() + " Schaden.");
                System.out.println("Der " + parsemob.getmobname() + " hat nun " + parsemob.getmobhealth() + " Leben.");
            }
        }
        else if(input[0].equals("exit")) {
            System.out.println("Du verl�sst den Kampf.");
            combat = false;
        }
    }
}
