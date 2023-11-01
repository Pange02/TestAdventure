import java.util.ArrayList;
import java.util.Random;
/**
 * Beschreiben Sie hier die Klasse Speaker.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Speaker extends NPC
{
    private ArrayList<String> dialogue = new ArrayList<>();
    private int entrydialogue;
    /**
     * Konstruktor für Objekte der Klasse Speaker
     */
    public Speaker(String parsename, ArrayList<String> parsedialogue)
    {
        super(parsename);
        dialogue = parsedialogue;
    }
    
    public void speak(Player parseplayer) {
        Random randomentry = new Random();
        entrydialogue = randomentry.nextInt(0, 4);
        if(entrydialogue == 0) {
            System.out.println("Hallo " + parseplayer.getplayername() + "!");
            try {
                Thread.sleep(1500);
            }   
            catch(Exception e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Ich bin " + name + "! ");
            try {
                Thread.sleep(1500);
            }   
            catch(Exception e) {
                Thread.currentThread().interrupt();
            }
        }
        else if(entrydialogue == 1) {
            System.out.println("Sei gegrüßt Reisender " + parseplayer.getplayername() + "!");
            try {
                Thread.sleep(1500);
            }   
            catch(Exception e) {
                Thread.currentThread().interrupt();
            }
            System.out.println(name + " mein Name. ");
            try {
                Thread.sleep(1500);
            }   
            catch(Exception e) {
                Thread.currentThread().interrupt();
            }
        }
        else if(entrydialogue == 2) {
            System.out.println("Ich habe schon von euch gehört " + parseplayer.getplayername() + "!");
            try {
                Thread.sleep(1500);
            }   
            catch(Exception e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Erlaubt mir mich vorzustellen. Ich bin " + name + ".");
            try {
                Thread.sleep(1500);
            }   
            catch(Exception e) {
                Thread.currentThread().interrupt();
            }
        }
        else if(entrydialogue == 3) {
            System.out.println("Grüßs Gotthle " + parseplayer.getplayername() + "!");
            try {
                Thread.sleep(1500);
            }   
            catch(Exception e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Mein Noome ischt " + name + "! ");
            try {
                Thread.sleep(1500);
            }   
            catch(Exception e) {
                Thread.currentThread().interrupt();
            }
        }
        
        for(int i = 0; i < dialogue.size(); i++) {
            System.out.println(dialogue.get(i));
            try {
                Thread.sleep(1500);
            }   
            catch(Exception e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
