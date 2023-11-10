import java.util.Random;
/**
 * Write a description of class Blacksmith here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Blacksmith extends NPC
{
    // instance variables - replace the example below with your own
    private Random reforgegenerator;

    /**
     * Constructor for objects of class Blacksmith
     */
    public Blacksmith(String parsename)
    {
        super(parsename);
        reforgegenerator = new Random();
    }
    
    @Override public void speak() {
        System.out.println("Guten Tag! Mein Name ist " + name + ".");
        try {
            Thread.sleep(1500);
        }   
        catch(Exception e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Möchtest du vielleicht eines deiner Items verbessern?");
        try {
            Thread.sleep(1500);
        }   
        catch(Exception e) {
            Thread.currentThread().interrupt();
        }
    }
    
    public void reforge(Item parseitem) {
        parseitem.applyreforge(Reforge.getreforgelist().get(reforgegenerator.nextInt(Reforge.getreforgelist().size())));
    }
}
