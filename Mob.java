import java.util.ArrayList;
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
    private int health;
    private int damage;
    private boolean alive;
    private ArrayList<Item> mobloot = new ArrayList<>();
    /**
     * Konstruktor f�r Objekte der Klasse Mob
     */
    public Mob(String parsename, int parsehealth, Weapon weapon, ArrayList parsemobloot)
    {
        name = parsename;
        health = parsehealth;
        damage = weapon.getweapondamage();
        mobloot = parsemobloot;
        alive = true;
    }
    
    public void droploot(Player parseplayer, Mob parsemob) {
        for(int i = 0; i < parsemob.mobloot.size(); i++) {
                parseplayer.additemtoinventory(parseplayer, parsemob.mobloot.get(i));
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
    
    public int getmobhealth(Mob parsemob) {
        return parsemob.health;
    }
    
    public void setmobhealth(Mob parsemob, int parsehealth) {
        parsemob.health = parsehealth;
    }
    
    public void setmobstatus(boolean parsestatus) {
        alive = parsestatus;
    }
    
    public boolean getmobstatus() {
        return alive;
    }
    
    public String getmobname(Mob parsemob) {
        return parsemob.name;
    }
    
    public int getmobdamage(Mob parsemob) {
        return parsemob.damage;
    }
    
    public void attack(Player parseplayer, Mob parsemob) {
        parseplayer.setplayerhealth(parseplayer, parseplayer.getplayerhealth(parseplayer) - parsemob.damage);
    }
    
}
