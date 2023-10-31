import java.util.ArrayList;
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
    
    public void speak(Player parseplayer) {
        dialogue.set(0, dialogue.get(0).concat(parseplayer.getplayername()));
        dialogue.set(1, dialogue.get(1).concat(name));
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
