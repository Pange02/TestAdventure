import java.util.ArrayList;
import java.util.Random;
/**
 * Diese Klasse wird von der Klasse Mob vererbt. Diese Klasse hat verschiedene besondere Angriffe, auf welche der Boss zugreifen kann, w�hrend er sich im Kampf befindet,
 * hierbei kann er den Spieler schw�chen, wobei seine St�rker gesenkt wird (weakiningHit), er kann zweimal angreifen (doubleAttack), er kann ein Schild benutzen und so den Schaden
 * des Spielers minimeren (reduceDamage) und er kann den Spieler vergiften (poisningHit).
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Boss extends Mob //die Klasse Boss wird aus der Klasse Mob vererbt
{
    // instance variables - replace the example below with your own
    protected String name;
    Random random = new Random();
    protected boolean specialAttack;
    protected int nextSpecialAttack;
    protected int randombound;
    protected int shieldrounds;
    protected boolean shieldactive;
    protected ArrayList<java.lang.reflect.Method> specialattacklist;
    /**
     * Constructor for objects of class Boss
     */
    public Boss(String parsename, int parselevel, int parsehealth, Weapon parseweapon, ArrayList parsemobloot, int parsemobxp, String parsegender)
    {
        super(parsename, parselevel, parsehealth, parseweapon, parsemobloot, parsemobxp, parsegender);
        name = parsename;
    }

    // Spezialattacke f�r Endgegner (Schw�chender Angriff)
    public void weakeningHit(Player parseplayer) {
        if (parseplayer.getPlayerStrength() != 0){
            parseplayer.setStrength(parseplayer.getPlayerStrength() * 0.8);
            parseplayer.setWeakened(true);
            System.out.println(name + " hat einen schw�chenden Schlag ausgef�hrt, deine St�rke wurde um 20% reduziert. Deine St�rke betr�gt nun: " + parseplayer.getPlayerStrength() + ".");
        }
    }
    
    // Spezialattacke f�r Endgegner (Doppelangriff)
    public void doubleAttack(Player parseplayer){
        System.out.println(getArtikel("nominativ", "bestimmt").substring(0, 1).toUpperCase() + getArtikel("nominativ", "bestimmt").substring(1) + " " + name + " f�hrt zwei schnelle Schl�ge durch.");
        finaldamage = Math.round((1 - (parseplayer.getplayerdefense()/(10 + parseplayer.getplayerdefense()))) * damage * 10.0) / 10.0;
        parseplayer.setplayerhealth(Math.round((parseplayer.getplayerhealth() - finaldamage) * 10.0) / 10.0);
        System.out.println(getArtikel("nominativ", "bestimmt").substring(0, 1).toUpperCase() + getArtikel("nominativ", "bestimmt").substring(1) + " " + name + " greift dich an und macht " + finaldamage + " Schaden.");
        parseplayer.setplayerhealth(Math.round((parseplayer.getplayerhealth() - finaldamage) * 10.0) / 10.0);
        System.out.println(getArtikel("nominativ", "bestimmt").substring(0, 1).toUpperCase() + getArtikel("nominativ", "bestimmt").substring(1) + " " + name + " greift dich an und macht " + finaldamage + " Schaden.");
        System.out.println("Du hast durch den Angriff jetzt " + parseplayer.getplayerhealth() + " Leben.");
    }
    
    // Spezialattacke f�r Endgegner (Schild)
    public void reduceDamage(Player parseplayer){
        System.out.println(getArtikel("nominativ", "bestimmt").substring(0, 1).toUpperCase() + getArtikel("nominativ", "bestimmt").substring(1) + " " + name + " benutzt sein Schild und nimmt 90% weniger Schaden f�r die n�chsten 3 Runden. " +
        "Dabei kannst du allerdings nicht angegriffen werden.");
        setMobDefense(90);
    }
    
    // Spezialattacke f�r Endgegner (Vergiftender Angriff)
    public void poisoningHit(Player parseplayer){
        parseplayer.setpoisoned(true);
        parseplayer.setpoisonrounds(3);
        System.out.println(getArtikel("nominativ", "bestimmt").substring(0, 1).toUpperCase() + getArtikel("nominativ", "bestimmt").substring(1) + " " + name + " trifft dich mit seiner Giftattacke. Du bist nun f�r drei Runden vergiftet.");
    }
}


