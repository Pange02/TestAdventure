
/**
 * Write a description of class NPC here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class NPC
{
    // instance variables - replace the example below with your own
    protected String name;
    
    /**
     * Constructor for objects of class NPC
     */
    public NPC(String parsename)
    {
        name = parsename;
    }
    
    public void speak() {
        System.out.println("Hallo! Ich bin " + name);
    }
    
    public String getNPCname() {
        return name;
    }
}
