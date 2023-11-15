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
    private String name;
    
    /**
     * Constructor for objects of class Boss
     */
    public Boss(String parsename, int parsehealth, Weapon parseweapon, ArrayList parsemobloot, int parsemobxp, String parsegender)
    {
        super(parsename, parsehealth, parseweapon, parsemobloot, parsemobxp, parsegender);
        name = parsename;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void weakeningHit(Player parseplayer) {
        if (parseplayer.getPlayerStrength() != 0){
            parseplayer.reduceStrength(parseplayer.getPlayerStrength() * 0.8);
            System.out.println(name + " hat einen schw�chenden Schlag ausgef�hrt, deine St�rke wurde um 20% reduziert. Deine St�rke betr�gt nun: " + parseplayer.getPlayerStrength() + ".");
        }
    }
    
    public void doubleAttack(Player parseplayer){
        System.out.println(getArtikel("nominativ", "bestimmt").substring(0, 1).toUpperCase() + getArtikel("nominativ", "bestimmt").substring(1) + " " + name + " f�hrt zwei schnelle Schl�ge durch.");
        finaldamage = Math.round((1 - (parseplayer.getplayerdefense()/(10 + parseplayer.getplayerdefense()))) * damage * 10.0) / 10.0;
        parseplayer.setplayerhealth(Math.round((parseplayer.getplayerhealth() - finaldamage) * 10.0) / 10.0);
        System.out.println(getArtikel("nominativ", "bestimmt").substring(0, 1).toUpperCase() + getArtikel("nominativ", "bestimmt").substring(1) + " " + name + " greift dich an und macht " + finaldamage + " Schaden.");
        System.out.println("Du hast durch den Angriff jetzt " + parseplayer.getplayerhealth() + " Leben.");
    }
    
    public void reduceDamage(){
        System.out.println(getArtikel("nominativ", "bestimmt").substring(0, 1).toUpperCase() + getArtikel("nominativ", "bestimmt").substring(1) + " " + name + " legt eine R�stung an er hat nun 91% R�stung.");
        setMobDefense(100);
    }
}


