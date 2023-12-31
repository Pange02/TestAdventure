import java.util.ArrayList;
import java.lang.Math;
/**
 * Die Klasse Mob hat die F�higkeit Gegner zu erstellen, diese m�ssen bek�mpft werden, bevor der Raum verlassen werden kann. Mobs k�nnen bek�mpft werden, indem diese mit einer Waffe
 * angegriffen werden, dabei wird abwechselnd geschlagen, entsprechend dem Schaden der Waffe werden bei dem Kampf Lebenspunkte abgezogen.
 * Jeder Mob kann bei seiner Erstellung individuell benannt werden, ebenso k�nnen Leben, Waffe, Loot, XP und Geschlecht f�r jedes Individuum einzeln festgelegt werden.
 * Ebenso besitzt er das Attribut �defense�, welches f�r die Klasse �Boss� ben�tigt wird.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Mob
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private String name;
    private int level;
    private double health;
    private int mobxp;
    protected int damage;
    protected double finaldamage;
    private boolean alive;
    private String gender;
    private ArrayList<Item> mobloot = new ArrayList<>();
    protected double defense;
    
    /**
     * Konstruktor f�r Objekte der Klasse Mob
     */
    public Mob(String parsename, int parselevel, int parsehealth, Weapon parseweapon, ArrayList parsemobloot, int parsemobxp, String parsegender)
    {
        name = parsename;
        level = parselevel;
        health = parsehealth;
        damage = parseweapon.getweapondamage();
        mobloot = parsemobloot;
        mobxp = parsemobxp;
        alive = true;
        gender = parsegender;
        defense = 0.0;
    }
    
    // Methode zum droppen des Loots eines Mobs, wenn es besiegt wird
    public void droploot(Player parseplayer, Mob parsemob) {
        for(int i = 0; i < parsemob.mobloot.size(); i++) {
                parseplayer.additemtoinventory(parsemob.mobloot.get(i));
                if(parsemob.mobloot.get(i).getClass() == Weapon.class) {
                   System.out.println("Du findest " + parsemob.mobloot.get(i).getArtikel("akkusativ", "unbestimmt") + " " + parsemob.mobloot.get(i).getitemname() + " " + parsemob.mobloot.get(i).getitemrarity() + " mit " + ((Weapon) parsemob.mobloot.get(i)).getweapondamage() + " Schaden."); 
                }
                if(parsemob.mobloot.get(i).getClass() == Armor.class) {
                   System.out.println("Du findest " + parsemob.mobloot.get(i).getArtikel("akkusativ", "unbestimmt") + " " + parsemob.mobloot.get(i).getitemname() + " " + parsemob.mobloot.get(i).getitemrarity() + " mit " + ((Armor) parsemob.mobloot.get(i)).getarmordefense() + " Verteidigung."); 
                }
                if(parsemob.mobloot.get(i).getClass() == Potion.class && ((Potion) mobloot.get(i)).getpotiontype() == "Damage") {
                   System.out.println("Du findest " + parsemob.mobloot.get(i).getArtikel("akkusativ", "unbestimmt") + " " + parsemob.mobloot.get(i).getitemname() + " " + parsemob.mobloot.get(i).getitemrarity() + " mit " + ((Potion) parsemob.mobloot.get(i)).getpotioneffect() + " Schaden."); 
                }
                if(parsemob.mobloot.get(i).getClass() == Potion.class && ((Potion) mobloot.get(i)).getpotiontype() == "Poison") {
                   System.out.println("Du findest " + parsemob.mobloot.get(i).getArtikel("akkusativ", "unbestimmt") + " " + parsemob.mobloot.get(i).getitemname() + " " + parsemob.mobloot.get(i).getitemrarity() + " mit " + ((Potion) parsemob.mobloot.get(i)).getpotioneffect() + " Gifteffekt."); 
                }
                if(parsemob.mobloot.get(i).getClass() == Potion.class && ((Potion) mobloot.get(i)).getpotiontype() == "Healing") {
                   System.out.println("Du findest " + parsemob.mobloot.get(i).getArtikel("akkusativ", "unbestimmt") + " " + parsemob.mobloot.get(i).getitemname() + " " + parsemob.mobloot.get(i).getitemrarity() + " mit " + ((Potion) parsemob.mobloot.get(i)).getpotioneffect() + " Heilung."); 
                }
                if(parsemob.mobloot.get(i).getClass() == Accessory.class) {
                   System.out.println("Du findest " + parsemob.mobloot.get(i).getArtikel("akkusativ", "unbestimmt") + " " + parsemob.mobloot.get(i).getitemname() + " " + parsemob.mobloot.get(i).getitemrarity() + " mit " + ((Accessory) parsemob.mobloot.get(i)).getaccessorystrength() + " St�rke."); 
                }
                if(parsemob.mobloot.get(i).getClass() == Consumable.class) {
                   System.out.println("Du findest " + parsemob.mobloot.get(i).getArtikel("akkusativ", "unbestimmt") + " " + parsemob.mobloot.get(i).getitemname() + " " + parsemob.mobloot.get(i).getitemrarity() + " mit " + ((Consumable) parsemob.mobloot.get(i)).getconsumableeffect() + " Heilung."); 
                }
                if(parsemob.mobloot.get(i).getClass() == Key.class) {
                   System.out.println("Du findest " + parsemob.mobloot.get(i).getArtikel("akkusativ", "unbestimmt") + " " + parsemob.mobloot.get(i).getitemname() + " " + parsemob.mobloot.get(i).getitemrarity());
                }
        }
    }
    
    // Methoden zum Ausgeben der Attribute eines Mobs
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
    
    public int getmobxp() {
        return mobxp;
    }
    
    public int getlevel() {
        return level;
    }
    
    // Methode zum Angreifen des Spielers
    public void attack(Player parseplayer) {
        finaldamage = Math.round((1 - (parseplayer.getplayerdefense()/(10 + parseplayer.getplayerdefense()))) * damage * 10.0) / 10.0;
        parseplayer.setplayerhealth(Math.round((parseplayer.getplayerhealth() - finaldamage) * 10.0) / 10.0);
        System.out.println(getArtikel("nominativ", "bestimmt").substring(0, 1).toUpperCase() + getArtikel("nominativ", "bestimmt").substring(1) + " " + name + " greift dich an und macht " + finaldamage + " Schaden.");
        if(parseplayer.getplayerhealth() > 0) {
            System.out.println("Du hast durch den Angriff jetzt " + parseplayer.getplayerhealth() + " Leben.");    
        }
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
    
    // Legt f�r den Kampf wichtige Attribute des Mobs fest
    public void setMobDefense(double newDefense){
        defense = newDefense;
    }
    
    public double getMobDefense(){
        return defense;
    }
}
