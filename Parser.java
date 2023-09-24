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
    
    private Item playerweapon;
    
    private Mob activemob;
    
    /**
     * Konstruktor f�r Objekte der Klasse Parser mit Spieler
     */
    public Parser(Player parseplayer)
    {
        running = true;
        combat = false;
        activeplayer = parseplayer;
        System.out.println("Was m�chtest du tun? F�r Hilfe: hilfe");
        while(running && !combat) {
            Scanner parser = new Scanner(System.in);
            getaction(parser.nextLine(), activeplayer);
            while(running && combat) {
                Scanner compatparser = new Scanner(System.in);
                getcombataction(parser.nextLine(), activeplayer, activemob);
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
                if(input[1].equals("truhe") && parseplayer.getcurrentroom(parseplayer).getchestinfo(parseplayer.getcurrentroom(parseplayer))) {
                    parseplayer.getcurrentroom(parseplayer).getchest(parseplayer.getcurrentroom(parseplayer)).openchest(Parser.activeplayer, parseplayer.getcurrentroom(parseplayer).getchest(parseplayer.getcurrentroom(parseplayer)));
                }
                else if(input[1].equals("truhe") && (parseplayer.getcurrentroom(parseplayer).getchestinfo(parseplayer.getcurrentroom(parseplayer)) == false)) {
                    System.out.println("Dieser Raum hat keine Truhe");
                }
                else {
                    System.out.println("Du kannst dieses Objekt nicht �ffnen!");
                } 
            }
            catch(Exception e) {
                System.out.println("Du kannst dies nicht �ffnen!");
            }
        }    
        else if(input[0].equals("umgucken")) {
            if(parseplayer.getcurrentroom(parseplayer).getchestinfo(parseplayer.getcurrentroom(parseplayer))) {
                System.out.println("Du entdeckst eine Truhe in dem Raum.");
                System.out.println("Du siehst T�ren im:");
                for(int i = 0; i < parseplayer.getcurrentroom(parseplayer).getroomdirections(parseplayer.getcurrentroom(parseplayer)).size(); i++) {
                    System.out.println(parseplayer.getcurrentroom(parseplayer).getroomdirections(parseplayer.getcurrentroom(parseplayer)).get(i));
                }
            }
            else {
                System.out.println("Du siehst T�ren im:");
                for(int i = 0; i < parseplayer.getcurrentroom(parseplayer).getroomdirections(parseplayer.getcurrentroom(parseplayer)).size(); i++) {
                    System.out.println(parseplayer.getcurrentroom(parseplayer).getroomdirections(parseplayer.getcurrentroom(parseplayer)).get(i));
                }
            }
        }
        else if(input[0].equals("inv")) {
            parseplayer.getinventorycontent(Parser.activeplayer);
        }
        else if(input[0].equals("gehe")) {
            try {
                if(input[1].equals("norden") && parseplayer.getcurrentroom(parseplayer).getconnectedrooms(parseplayer.getcurrentroom(parseplayer), "north") != null) {
                    System.out.println("Du bewegst dich durch die T�r in den neuen Raum im Norden");
                    parseplayer.setcurrentroom(parseplayer, parseplayer.getcurrentroom(parseplayer).getconnectedrooms(parseplayer.getcurrentroom(parseplayer), "north"));
                    if(parseplayer.getcurrentroom(parseplayer).getmobinfo(parseplayer.getcurrentroom(parseplayer)) == true) {
                        entercombat(parseplayer, parseplayer.getcurrentroom(parseplayer).getroommob(parseplayer.getcurrentroom(parseplayer)));
                        combat = true;
                    }
                }
                else if(input[1].equals("norden") && parseplayer.getcurrentroom(parseplayer).getconnectedrooms(parseplayer.getcurrentroom(parseplayer), "north") == null) {
                    System.out.println("In dieser Richtung befindet sich keine T�r in einen anderen Raum.");
                }
                else if(input[1].equals("osten") && parseplayer.getcurrentroom(parseplayer).getconnectedrooms(parseplayer.getcurrentroom(parseplayer), "east") != null) {
                    System.out.println("Du bewegst dich durch die T�r in den neuen Raum im Osten");
                    parseplayer.setcurrentroom(parseplayer, parseplayer.getcurrentroom(parseplayer).getconnectedrooms(parseplayer.getcurrentroom(parseplayer), "east"));
                    if(parseplayer.getcurrentroom(parseplayer).getmobinfo(parseplayer.getcurrentroom(parseplayer)) == true) {
                        entercombat(parseplayer, parseplayer.getcurrentroom(parseplayer).getroommob(parseplayer.getcurrentroom(parseplayer)));
                        combat = true;
                    }
                }
                else if(input[1].equals("osten") && parseplayer.getcurrentroom(parseplayer).getconnectedrooms(parseplayer.getcurrentroom(parseplayer), "east") == null) {
                    System.out.println("In dieser Richtung befindet sich keine T�r in einen anderen Raum.");
                }
                else if(input[1].equals("s�den") && parseplayer.getcurrentroom(parseplayer).getconnectedrooms(parseplayer.getcurrentroom(parseplayer), "south") != null) {
                    System.out.println("Du bewegst dich durch die T�r in den neuen Raum im S�den");
                    parseplayer.setcurrentroom(parseplayer, parseplayer.getcurrentroom(parseplayer).getconnectedrooms(parseplayer.getcurrentroom(parseplayer), "south"));
                    if(parseplayer.getcurrentroom(parseplayer).getmobinfo(parseplayer.getcurrentroom(parseplayer)) == true) {
                        entercombat(parseplayer, parseplayer.getcurrentroom(parseplayer).getroommob(parseplayer.getcurrentroom(parseplayer)));
                        combat = true;
                    }
                }
                else if(input[1].equals("s�den") && parseplayer.getcurrentroom(parseplayer).getconnectedrooms(parseplayer.getcurrentroom(parseplayer), "south") == null) {
                    System.out.println("In dieser Richtung befindet sich keine T�r in einen anderen Raum.");
                }
                else if(input[1].equals("westen") && parseplayer.getcurrentroom(parseplayer).getconnectedrooms(parseplayer.getcurrentroom(parseplayer), "west") != null) {
                    System.out.println("Du bewegst dich durch die T�r in den neuen Raum im Westen");
                    parseplayer.setcurrentroom(parseplayer, parseplayer.getcurrentroom(parseplayer).getconnectedrooms(parseplayer.getcurrentroom(parseplayer), "west"));
                    if(parseplayer.getcurrentroom(parseplayer).getmobinfo(parseplayer.getcurrentroom(parseplayer)) == true) {
                        entercombat(parseplayer, parseplayer.getcurrentroom(parseplayer).getroommob(parseplayer.getcurrentroom(parseplayer)));
                        combat = true;
                    }
                }
                else if(input[1].equals("westen") && parseplayer.getcurrentroom(parseplayer).getconnectedrooms(parseplayer.getcurrentroom(parseplayer), "west") == null) {
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
        System.out.println("Als du den Raum betrittst, entdeckst du ein " + parsemob.getmobname(parsemob) + ". Es kommt zum Kampf.");
        System.out.println("Welche Waffe m�chtest du f�r den Kampf benutzen?");
        parseplayer.getinventorycontent(parseplayer);
        Scanner weaponparser = new Scanner(System.in);
        try {
            weaponnumber = Integer.parseInt(weaponparser.nextLine()); 
        }
        catch(Exception e) {
            System.out.println("Dies ist keine g�ltige Zahl f�r dein Inventar");
        }
        playerweapon = parseplayer.getitemfrominventory(parseplayer, weaponnumber);
        System.out.println("Du w�hlst die " + playerweapon.getitemname(playerweapon) + ".");
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
            parseplayer.attack(parseplayer, parsemob, playerweapon);
            if(parsemob.getmobhealth(parsemob) <= 0) {
               System.out.println("Du hast den " + parsemob.getmobname(parsemob) + " besiegt. ");
               parsemob.droploot(parseplayer, parsemob);
               combat = false;
               running = true;
            }
            else {
                System.out.println("Du greifst den " + parsemob.getmobname(parsemob) + " mit " + playerweapon.getitemname(playerweapon) + " an und machst " + playerweapon.getitemdamage(playerweapon) + " Schaden.");
                System.out.println("Der " + parsemob.getmobname(parsemob) + " hat nun " + parsemob.getmobhealth(parsemob) + " Leben.");
            }
        }
        else if(input[0].equals("exit")) {
            System.out.println("Du verl�sst den Kampf.");
            combat = false;
        }
    }
}
