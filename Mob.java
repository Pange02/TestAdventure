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
    private ArrayList<Item> mobloot = new ArrayList<>();
    /**
     * Konstruktor für Objekte der Klasse Mob
     */
    public Mob(String parsename, int parsehealth, Item weapon, ArrayList parsemobloot)
    {
        name = parsename;
        health = parsehealth;
        damage = weapon.getitemdamage(weapon);
        mobloot = parsemobloot;
    }
    
    public void droploot(Player parseplayer, Mob parsemob) {
        for(int i = 0; i < parsemob.mobloot.size(); i++) {
                parseplayer.additemtoinventory(parseplayer, parsemob.mobloot.get(i));
                System.out.println("Du findest ein "  + " "+ parsemob.mobloot.get(i).getitemname(parsemob.mobloot.get(i)) + " " + parsemob.mobloot.get(i).getitemrarity(parsemob.mobloot.get(i)) + " mit " + parsemob.mobloot.get(i).getitemdamage(parsemob.mobloot.get(i)) + " Schaden.");
        }
    }
    
    public int getmobhealth(Mob parsemob) {
        return parsemob.health;
    }
    
    public void setmobhealth(Mob parsemob, int parsehealth) {
        parsemob.health = parsehealth;
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
