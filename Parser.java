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
    
    //Aktionsliste für alle möglichen Aktionen. Verwendet von help Befehl.
    private String[] actionlist = {"help", "open", "inv", "look", "move", "exit"};
    
    //Array für den Input des Spielers
    private String[] input = new String[10];
    
    //Boolean gibt an, ob der Parser (das Spiel) aktiv ist.
    private boolean running;
    
    //Aktiver Spieler vom Parser
    private static Player activeplayer;

    /**
     * Konstruktor für Objekte der Klasse Parser mit Spieler
     */
    public Parser(Player parseplayer)
    {
        running = true;
        activeplayer = parseplayer;
        System.out.println("Was möchtest du tun? Für Hilfe: help");
        while(running) {
            Scanner parser = new Scanner(System.in);
            getaction(parser.nextLine(), activeplayer);
        }
    }

    /**
     * Alle möglichen Inputs werden vom Parser nach Leerzeichen getrennt und überprüft, ob sie einer Aktion entsprechen und dann von ihm ausgeführt.
     */
    public void getaction(String parseaction, Player parseplayer)
    {
        input = parseaction.split("\\s+");
        if(input[0].equals("help")) {
            System.out.println("Aktuell kannst du folgenden Aktionen machen:");
            for(int i = 1; i < actionlist.length; i++) {
                System.out.println(actionlist[i]);
            }
        }
        
        else if(input[0].equals("open")) {
            try {
                if(input[1].equals("chest") && parseplayer.getcurrentroom(parseplayer).getchestinfo(parseplayer.getcurrentroom(parseplayer))) {
                    parseplayer.getcurrentroom(parseplayer).getchest(parseplayer.getcurrentroom(parseplayer)).openchest(Parser.activeplayer, parseplayer.getcurrentroom(parseplayer).getchest(parseplayer.getcurrentroom(parseplayer)));
                }
                else if(input[1].equals("chest") && (parseplayer.getcurrentroom(parseplayer).getchestinfo(parseplayer.getcurrentroom(parseplayer)) == false)) {
                    System.out.println("Dieser Raum hat keine Kiste");
                }
                else {
                    System.out.println("Du kannst dieses Objekt nicht öffnen!");
                } 
            }
            catch(Exception e) {
                System.out.println("Du kannst dies nicht öffnen!");
            }
        }    
        else if(input[0].equals("look")) {
            if(parseplayer.getcurrentroom(parseplayer).getchestinfo(parseplayer.getcurrentroom(parseplayer))) {
                System.out.println("Du entdeckst eine Truhe in dem Raum.");
                System.out.println("Du siehst Türen im:");
                for(int i = 0; i < parseplayer.getcurrentroom(parseplayer).getroomdirections(parseplayer.getcurrentroom(parseplayer)).size(); i++) {
                    System.out.println(parseplayer.getcurrentroom(parseplayer).getroomdirections(parseplayer.getcurrentroom(parseplayer)).get(i));
                }
            }
            else {
                System.out.println("Du siehst Türen im:");
                for(int i = 0; i < parseplayer.getcurrentroom(parseplayer).getroomdirections(parseplayer.getcurrentroom(parseplayer)).size(); i++) {
                    System.out.println(parseplayer.getcurrentroom(parseplayer).getroomdirections(parseplayer.getcurrentroom(parseplayer)).get(i));
                }
            }
        }
        else if(input[0].equals("inv")) {
            parseplayer.getinventorycontent(Parser.activeplayer);
        }
        else if(input[0].equals("move")) {
            try {
                if(input[1].equals("north") && parseplayer.getcurrentroom(parseplayer).getconnectedrooms(parseplayer.getcurrentroom(parseplayer), "north") != null) {
                    System.out.println("Du bewegst dich durch die Tür in den neuen Raum im Norden");
                    parseplayer.setcurrentroom(parseplayer, parseplayer.getcurrentroom(parseplayer).getconnectedrooms(parseplayer.getcurrentroom(parseplayer), "north"));
                }
                else if(input[1].equals("north") && parseplayer.getcurrentroom(parseplayer).getconnectedrooms(parseplayer.getcurrentroom(parseplayer), "north") == null) {
                    System.out.println("In dieser Richtung befindet sich keine Tür in einen anderen Raum.");
                }
                else if(input[1].equals("east") && parseplayer.getcurrentroom(parseplayer).getconnectedrooms(parseplayer.getcurrentroom(parseplayer), "east") != null) {
                    System.out.println("Du bewegst dich durch die Tür in den neuen Raum im Osten");
                    parseplayer.setcurrentroom(parseplayer, parseplayer.getcurrentroom(parseplayer).getconnectedrooms(parseplayer.getcurrentroom(parseplayer), "east"));
                }
                else if(input[1].equals("east") && parseplayer.getcurrentroom(parseplayer).getconnectedrooms(parseplayer.getcurrentroom(parseplayer), "east") == null) {
                    System.out.println("In dieser Richtung befindet sich keine Tür in einen anderen Raum.");
                }
                else if(input[1].equals("south") && parseplayer.getcurrentroom(parseplayer).getconnectedrooms(parseplayer.getcurrentroom(parseplayer), "south") != null) {
                    System.out.println("Du bewegst dich durch die Tür in den neuen Raum im Süden");
                    parseplayer.setcurrentroom(parseplayer, parseplayer.getcurrentroom(parseplayer).getconnectedrooms(parseplayer.getcurrentroom(parseplayer), "south"));
                }
                else if(input[1].equals("south") && parseplayer.getcurrentroom(parseplayer).getconnectedrooms(parseplayer.getcurrentroom(parseplayer), "south") == null) {
                    System.out.println("In dieser Richtung befindet sich keine Tür in einen anderen Raum.");
                }
                else if(input[1].equals("west") && parseplayer.getcurrentroom(parseplayer).getconnectedrooms(parseplayer.getcurrentroom(parseplayer), "west") != null) {
                    System.out.println("Du bewegst dich durch die Tür in den neuen Raum im Westen");
                    parseplayer.setcurrentroom(parseplayer, parseplayer.getcurrentroom(parseplayer).getconnectedrooms(parseplayer.getcurrentroom(parseplayer), "west"));
                }
                else if(input[1].equals("west") && parseplayer.getcurrentroom(parseplayer).getconnectedrooms(parseplayer.getcurrentroom(parseplayer), "west") == null) {
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
        else if(input[0].equals("exit")) {
            System.out.println("Du verlässt das Dungeon.");
            running = false;
        }
    }
}
