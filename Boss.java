import java.util.ArrayList;
/**
 * Write a description of class Boss here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Boss extends Mob //die Klasse Boss wird aus der Klasse Mob vererbt
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class Boss
     */
    public Boss(String parsename, int parsehealth, Weapon parseweapon, ArrayList parsemobloot, int parsemobxp, String parsegender)
    {
        super(parsename, parsehealth, parseweapon, parsemobloot, parsemobxp, parsegender);
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void weakingHit(int amount, Player parseplayer) {
        if (parseplayer.getPlayerStrength() != 0){
            parseplayer.reduceStrength(parseplayer.getPlayerStrength() * 0.8);
        }
    }
}
