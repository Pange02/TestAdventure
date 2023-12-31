import java.util.ArrayList;
import java.util.Random;
/**
 * Write a description of class Henchman here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Lord extends Boss
{
    // instance variables - replace the example below with your own
    
    // Konstrukor der Klasse Lord
    public Lord(String parsename, int parselevel, int parsehealth, Weapon parseweapon, ArrayList parsemobloot, int parsemobxp, String parsegender)
    {
        super(parsename, parselevel, parsehealth, parseweapon, parsemobloot, parsemobxp, parsegender);
        name = parsename;
        specialAttack = true;
        randombound = 13;
        shieldrounds = 0;
        shieldactive = false;
        specialattacklist = new ArrayList<>();
        addSpecialAttacks();
    }
    
    // Hinzuf�gen aller Spezialangriffe f�r den letzten Boss
    public void addSpecialAttacks() {
        try {
            specialattacklist.add(Boss.class.getMethod("weakeningHit", Player.class));
            specialattacklist.add(Boss.class.getMethod("doubleAttack", Player.class));
            specialattacklist.add(Boss.class.getMethod("reduceDamage", Player.class));
            specialattacklist.add(Boss.class.getMethod("poisoningHit", Player.class));
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    // Nutzen der Methode attack (aus der Klasse Mob)
    @Override
    public void attack(Player parseplayer){
        int zufall = random.nextInt(0, randombound);
        if (specialAttack == false){
            nextSpecialAttack -= 1;
            if (nextSpecialAttack == 0 ){
                specialAttack = true;
                nextSpecialAttack = 4;
            }
        }
        if(shieldactive) {
            setmobhealth(getmobhealth() * 1.05);
            System.out.println(name + " heilt sich hinter seinem Schild um 5%.");
            shieldrounds -= 1;
            nextSpecialAttack += 1;
            if(shieldrounds > 0) {
                System.out.println("Hans der Lord beh�lt sein Schild noch f�r " + shieldrounds + " Runden.");
            }
            if(shieldrounds == 0) {
                setMobDefense(0);
                shieldactive = false;
                System.out.println("Das Schild ist zerbrochen!");
            }
        }
        else {
            if (zufall < specialattacklist.size() && specialAttack == true){
                try {
                    if(zufall == 2) {
                        shieldactive = true;
                        shieldrounds = 3;
                    }
                    if(zufall == 0 && parseplayer.getWeakened()) {
                        System.out.println("Hans der Lord nutzt einen schw�chenden Schlag. Da du bereits geschw�cht bist, verliert die Spezialattacke ihre Wirkung");
                    }
                    else {
                        specialattacklist.get(zufall).invoke(this, parseplayer);
                    }
                    randombound = 13;
                    specialAttack = false;
                    nextSpecialAttack = 4;
                    }
                catch(Exception e) {
                    System.out.println("Argument Fehler in Lord: " + e);
                }
            }
            else if(specialAttack == true){
                if(randombound >= specialattacklist.size() + 2) {
                    randombound -= 2;
                }
                else {
                    randombound = specialattacklist.size();
                }
                System.out.println("Der Lord hat versucht eine Spezialattacke durchzuf�hren, es schlug fehl. Er wird es in der n�chsten Runde mit erh�hter Wahrscheinlichkeit erneut versuchen.");
                finaldamage = Math.round((1 - (parseplayer.getplayerdefense()/(10 + parseplayer.getplayerdefense()))) * damage * 10.0) / 10.0;
                parseplayer.setplayerhealth(Math.round((parseplayer.getplayerhealth() - finaldamage) * 10.0) / 10.0);
                System.out.println(name + " greift dich an und macht " + finaldamage + " Schaden.");
                System.out.println("Du hast durch den Angriff jetzt " + parseplayer.getplayerhealth() + " Leben.");
            }
            else {
                finaldamage = Math.round((1 - (parseplayer.getplayerdefense()/(10 + parseplayer.getplayerdefense()))) * damage * 10.0) / 10.0;
                parseplayer.setplayerhealth(Math.round((parseplayer.getplayerhealth() - finaldamage) * 10.0) / 10.0);
                System.out.println(name + " greift dich an und macht " + finaldamage + " Schaden.");
                System.out.println("Du hast durch den Angriff jetzt " + parseplayer.getplayerhealth() + " Leben.");
            }
        }
    }
}
