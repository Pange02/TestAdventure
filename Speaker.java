import java.util.ArrayList;
import java.util.Random;
/**
 * Diese Klasse wird von der Klasse „NPC“ vererbt und kann lediglich dem Spieler etwas erzählen, was vorher in festgelegt wurde, hierfür wird eine ArrayList benötigt.
 * Die Begrüßung, die jeder Interaktion vorangeht wird mit einer von drei zufällig ausgelösten Begrüßungen gestartet, anschließend wird das was in der ArrayList hinterlegt ist wieder gegeben,
 * wobei zwischen jedem Eintrag eine kurze Wartesequenz folgt, was das Gefühl von einem sprechen vermitteln soll.
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
    
    // Methode damit NPCs sprechen können
    public void speak(Player parseplayer) {
        Random randomentry = new Random();
        entrydialogue = randomentry.nextInt(0, 3);
        // Begrüßen des Spielers und vorstellen
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
        
        for(int i = 0; i < dialogue.size(); i++) {
            System.out.println(dialogue.get(i));
            try {
                Thread.sleep(6000);
            }   
            catch(Exception e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
