import java.util.ArrayList;
import java.util.Random;
/**
 * Write a description of class Henchman here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Henchman extends Boss
{
    // instance variables - replace the example below with your own
    private String name;
    Random random = new Random();
    private boolean specialAttack;
    private int nextSpecialAttack;
    private int randombound;
    private ArrayList<java.lang.reflect.Method> specialattacklist;
    /**
     * Constructor for objects of class Henchman
     */
    public Henchman(String parsename, int parselevel, int parsehealth, Weapon parseweapon, ArrayList parsemobloot, int parsemobxp, String parsegender)
    {
        super(parsename, parselevel, parsehealth, parseweapon, parsemobloot, parsemobxp, parsegender);
        name = parsename;
        specialAttack = true;
        randombound = 17;
        specialattacklist = new ArrayList<>();
        addSpecialAttacks();
    }
    
    public void addSpecialAttacks() {
        try {
            specialattacklist.add(Boss.class.getMethod("weakeningHit", Player.class));
            specialattacklist.add(Boss.class.getMethod("doubleAttack", Player.class));
            specialattacklist.add(Boss.class.getMethod("reduceDamage", Player.class));
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    
    @Override
    public void attack(Player parseplayer){
        int zufall = random.nextInt(1, randombound);
        if (specialAttack == false){
            nextSpecialAttack -= 1;
            if (nextSpecialAttack == 0 ){
                specialAttack = true;
                nextSpecialAttack = 4;
            }
        }
        if (zufall <= 3 && specialAttack == true){
            zufall = random.nextInt(1, specialattacklist.size());
            try {
                specialattacklist.get(zufall).invoke(this, parseplayer);
            }
            catch(Exception e) {
                System.out.println("Argument Fehler in Henchman: " + e);
            }
        }
        else if(specialAttack == true){
            System.out.println("Der Handlanger hat versucht eine Spezialattacke durchzuführen, es schlug fehl. Er wird es wahrscheinlich in der nächsten Runde erneut versuchen.");
        }
        finaldamage = Math.round((1 - (parseplayer.getplayerdefense()/(10 + parseplayer.getplayerdefense()))) * damage * 10.0) / 10.0;
        parseplayer.setplayerhealth(Math.round((parseplayer.getplayerhealth() - finaldamage) * 10.0) / 10.0);
        System.out.println(getArtikel("nominativ", "bestimmt").substring(0, 1).toUpperCase() + getArtikel("nominativ", "bestimmt").substring(1) + " " + name + " greift dich an und macht " + finaldamage + " Schaden.");
        System.out.println("Du hast durch den Angriff jetzt " + parseplayer.getplayerhealth() + " Leben.");
    }
}
