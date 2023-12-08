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
    public Boss(String parsename, int parselevel, int parsehealth, Weapon parseweapon, ArrayList parsemobloot, int parsemobxp, String parsegender)
    {
        super(parsename, parselevel, parsehealth, parseweapon, parsemobloot, parsemobxp, parsegender);
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
            parseplayer.setStrength(parseplayer.getPlayerStrength() * 0.8);
            parseplayer.setWeakened(true);
            System.out.println(name + " hat einen schwächenden Schlag ausgeführt, deine Stärke wurde um 20% reduziert. Deine Stärke beträgt nun: " + parseplayer.getPlayerStrength() + ".");
        }
    }
    
    public void doubleAttack(Player parseplayer){
        System.out.println(getArtikel("nominativ", "bestimmt").substring(0, 1).toUpperCase() + getArtikel("nominativ", "bestimmt").substring(1) + " " + name + " führt zwei schnelle Schläge durch.");
        finaldamage = Math.round((1 - (parseplayer.getplayerdefense()/(10 + parseplayer.getplayerdefense()))) * damage * 10.0) / 10.0;
        parseplayer.setplayerhealth(Math.round((parseplayer.getplayerhealth() - finaldamage) * 10.0) / 10.0);
        System.out.println(getArtikel("nominativ", "bestimmt").substring(0, 1).toUpperCase() + getArtikel("nominativ", "bestimmt").substring(1) + " " + name + " greift dich an und macht " + finaldamage + " Schaden.");
        parseplayer.setplayerhealth(Math.round((parseplayer.getplayerhealth() - finaldamage) * 10.0) / 10.0);
        System.out.println(getArtikel("nominativ", "bestimmt").substring(0, 1).toUpperCase() + getArtikel("nominativ", "bestimmt").substring(1) + " " + name + " greift dich an und macht " + finaldamage + " Schaden.");
        System.out.println("Du hast durch den Angriff jetzt " + parseplayer.getplayerhealth() + " Leben.");
    }
    
    public void reduceDamage(Player parseplayer){
        System.out.println(getArtikel("nominativ", "bestimmt").substring(0, 1).toUpperCase() + getArtikel("nominativ", "bestimmt").substring(1) + " " + name + " benutzt sein Schild und nimmt 90% weniger Schaden für die nächsten 3 Runden. " +
        "Dabei kannst du allerdings nicht angegriffen werden.");
        setMobDefense(90);
    }
}


