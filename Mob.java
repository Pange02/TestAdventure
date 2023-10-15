import java.util.ArrayList;
import java.lang.Math;
/**
 * Beschreiben Sie hier die Klasse Mob.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Mob
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private String name;
    private double health;
    private int damage;
    private double finaldamage;
    private boolean alive;
    private String gender;
    private ArrayList<Item> mobloot = new ArrayList<>();
    /**
     * Konstruktor f�r Objekte der Klasse Mob
     */
    public Mob(String parsename, int parsehealth, Weapon weapon, ArrayList parsemobloot, String parsegender)
    {
        name = parsename;
        health = parsehealth;
        damage = weapon.getweapondamage();
        mobloot = parsemobloot;
        alive = true;
        gender = parsegender;
    }
    
    public void droploot(Player parseplayer, Mob parsemob) {
        for(int i = 0; i < parsemob.mobloot.size(); i++) {
                parseplayer.additemtoinventory(parsemob.mobloot.get(i));
                if(parsemob.mobloot.get(i).getClass() == Weapon.class) {
                   System.out.println("Du findest ein "  + " "+ parsemob.mobloot.get(i).getitemname() + " " + parsemob.mobloot.get(i).getitemrarity() + " mit " + ((Weapon) parsemob.mobloot.get(i)).getweapondamage() + " Schaden."); 
                }
                if(parsemob.mobloot.get(i).getClass() == Armor.class) {
                   System.out.println("Du findest eine "  + " "+ parsemob.mobloot.get(i).getitemname() + " " + parsemob.mobloot.get(i).getitemrarity() + " mit " + ((Armor) parsemob.mobloot.get(i)).getarmordefense() + " Verteidigung."); 
                }
                if(parsemob.mobloot.get(i).getClass() == Potion.class && ((Potion) mobloot.get(i)).getpotiontype() == "Damage") {
                   System.out.println("Du findest ein "  + " "+ parsemob.mobloot.get(i).getitemname() + " " + parsemob.mobloot.get(i).getitemrarity() + " mit " + ((Potion) parsemob.mobloot.get(i)).getpotioneffect() + " Schaden."); 
                }
                if(parsemob.mobloot.get(i).getClass() == Potion.class && ((Potion) mobloot.get(i)).getpotiontype() == "Healing") {
                   System.out.println("Du findest ein "  + " "+ parsemob.mobloot.get(i).getitemname() + " " + parsemob.mobloot.get(i).getitemrarity() + " mit " + ((Potion) parsemob.mobloot.get(i)).getpotioneffect() + " Heilung."); 
                }
                if(parsemob.mobloot.get(i).getClass() == Accessory.class) {
                   System.out.println("Du findest ein "  + " "+ parsemob.mobloot.get(i).getitemname() + " " + parsemob.mobloot.get(i).getitemrarity() + " mit " + ((Accessory) parsemob.mobloot.get(i)).getaccessorystrength() + " St�rke."); 
                }
        }
    }
    
    public double getmobhealth() {
        return health;
    }
    
    public void setmobhealth(double parsehealth) {
        health = parsehealth;
    }
    
    public void setmobstatus(boolean parsestatus) {
        alive = parsestatus;
    }
    
    public boolean getmobstatus() {
        return alive;
    }
    
    public String getmobname() {
        return name;
    }
    
    public int getmobdamage() {
        return damage;
    }
    
    public void attack(Player parseplayer) {
        finaldamage = Math.round((1 - (parseplayer.getplayerdefense()/(10 + parseplayer.getplayerdefense()))) * damage * 10.0) / 10.0;
        parseplayer.setplayerhealth(Math.round((parseplayer.getplayerhealth() - finaldamage) * 10.0) / 10.0);
        System.out.println(getArtikel("nominativ", "bestimmt").substring(0, 1).toUpperCase() + getArtikel("nominativ", "bestimmt").substring(1) + " " + name + " greift dich an und macht " + finaldamage + " Schaden.");
        System.out.println("Du hast durch den Angriff jetzt " + parseplayer.getplayerhealth() + " Leben.");
    }
    
    /**
     * Gibt zu einem Item und einem Kasus einen bestimmten oder unbestimmten Artikel aus.
     */
    public String getArtikel(String kasus, String art){
        if (art.toLowerCase().equals("bestimmt")){
            return Grammar.getArtikel(kasus, gender);
        }
        else if (art.toLowerCase().equals("unbestimmt")){
            return Grammar.getUnArtikel(kasus, gender);
        }
        else {
            return "kein Artikel in Mob";
        }
    }
}
