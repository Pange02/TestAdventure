import java.util.Scanner;
/**
 * Beschreiben Sie hier die Klasse Parser.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Parser
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private String[] actionlist = {"help", "open", "inv", "look"};
    private String[] input = new String[10];
    /**
     * Konstruktor für Objekte der Klasse Parser
     */
    public Parser(Player parseplayer)
    {
        Player activeplayer = parseplayer;
        System.out.println("Was möchtest du tun? Für Hilfe: help");
        while(true) {
            Scanner parser = new Scanner(System.in);
            getaction(parser.nextLine(), activeplayer);
        }
    }

    /**
     * Ein Beispiel einer Methode - ersetzen Sie diesen Kommentar mit Ihrem eigenen
     * 
     * @param  y    ein Beispielparameter für eine Methode
     * @return        die Summe aus x und y
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
        
        else if(input[0].equals("open") && (input[1] != null )) {
            if(input[1].equals("chest") && Room.getchestinfo(Player.getcurrentroom(parseplayer))) {
                Chest.openchest(Room.getchest(Player.getcurrentroom(parseplayer)));
            }
            else if(input[1].equals("chest") && (Room.getchestinfo(Player.getcurrentroom(parseplayer)) == false)) {
                System.out.println("Dieser Raum hat keine Kiste");
            }
            else {
                System.out.println("Du kannst dieses Objekt nicht öffnen!");
            }
        }    
        else if(input[0].equals("look")) {
            if(Room.getchestinfo(Player.getcurrentroom(parseplayer))) {
                System.out.println("Du entdeckst eine Truhe in dem Raum.");
            }
        }
        else if(input[0].equals("inv")) {
            Player.getinventorycontent();
        }
    }
}
