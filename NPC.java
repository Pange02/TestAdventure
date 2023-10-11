import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
/**
 * Write a description of class NPC here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class NPC
{
    // instance variables - replace the example below with your own
    private ArrayList<String> dialogue = new ArrayList<>();

    /**
     * Constructor for objects of class NPC
     */
    public NPC()
    {
        dialogue.add("Test NPC Satz.");
        dialogue.add("Nach 1 Sekunde kommt das hier");
        dialogue.add("2 Sekunden");
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void speak()
    {
        for(int i = 0; i < dialogue.size(); i++) {
            System.out.println(dialogue.get(i));
            try {
                Thread.sleep(3000);
            }
            catch(Exception e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
