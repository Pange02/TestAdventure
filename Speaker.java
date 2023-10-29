import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
/**
 * Beschreiben Sie hier die Klasse Speaker.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Speaker extends NPC
{
    private ArrayList<String> dialogue = new ArrayList<>();
    
    /**
     * Konstruktor für Objekte der Klasse Speaker
     */
    public Speaker(String parsename, ArrayList<String> parsedialogue)
    {
        super(parsename);
        dialogue = parsedialogue;
    }

    public void speak() {
        for(int i = 0; i < dialogue.size(); i++) {
            System.out.println(dialogue.get(i));
            try {
                Thread.sleep(1000);
            }   
            catch(Exception e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
