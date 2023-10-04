import java.util.ArrayList;
import java.util.Random;
/**
 * Beschreiben Sie hier die Klasse Chest.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Chest
{
    // Instanzvariablen der Kisten Klasse
    
    //number f�r eventuelle random Generierungen (aktuell nicht verwendet).
    private int number;
    
    //isopenable gibt an, ob man die Kiste �ffnen kann.
    private boolean isopenable;
    
    //Der Loot f�r jede Kiste
    private ArrayList<Item> chestloot;
    
    /**
     * Konstruktor f�r Objekte der Klasse Chest mit der Loot ArrayList aus der Game Klasse als Argument.
     */
    public Chest(ArrayList<Item> parseitemlist)
    {
        chestloot = parseitemlist;
        isopenable = true;
    }
    
    /**
     * Methode f�r das Bef�llen von Kisten. Nicht verwendet, da es aktuell klar definiert ist, welcher Loot wo zu finden ist.
     */
    private void fillchest()
    {
        //Random r = new Random();
        //number = r.nextInt(Item.getitemlistlength());
        //chestloot.add(Item.getitemfromlist(0));
        //chestloot.add(Item.getitemfromlist(1));
        //chestloot.add(Item.getitemfromlist(2));
    }
    
    /**
     * Methode f�r das �ffnen von Kisten. 
     */
    public void openchest(Player parseplayer, Chest parsechest)
    {
        if(parsechest.isopenable == true) {
            System.out.println("Die Kiste �ffnet sich:");
            for(int i = 0; i < chestloot.size(); i++) {
                parseplayer.additemtoinventory(chestloot.get(i));
                if(chestloot.get(i).getClass() == Weapon.class) {
                   System.out.println("Du findest ein "  + " "+ chestloot.get(i).getitemname() + " " + chestloot.get(i).getitemrarity() + " mit " + ((Weapon) chestloot.get(i)).getweapondamage() + " Schaden."); 
                }
                if(chestloot.get(i).getClass() == Armor.class) {
                   System.out.println("Du findest eine "  + " "+ chestloot.get(i).getitemname() + " " + chestloot.get(i).getitemrarity() + " mit " + ((Armor) chestloot.get(i)).getarmordefense() + " Verteidigung."); 
                }
                if(chestloot.get(i).getClass() == Potion.class && ((Potion) chestloot.get(i)).getpotiontype() == "Damage") {
                   System.out.println("Du findest eine "  + " "+ chestloot.get(i).getitemname() + " " + chestloot.get(i).getitemrarity() + " mit " + ((Potion) chestloot.get(i)).getpotioneffect() + " Schaden."); 
                }
                if(chestloot.get(i).getClass() == Potion.class && ((Potion) chestloot.get(i)).getpotiontype() == "Healing") {
                   System.out.println("Du findest eine "  + " "+ chestloot.get(i).getitemname() + " " + chestloot.get(i).getitemrarity() + " mit " + ((Potion) chestloot.get(i)).getpotioneffect() + " Heilung."); 
                }
                if(chestloot.get(i).getClass() == Accessory.class) {
                   System.out.println("Du findest eine "  + " "+ chestloot.get(i).getitemname() + " " + chestloot.get(i).getitemrarity() + " mit " + ((Accessory) chestloot.get(i)).getaccessorystrength() + " St�rke."); 
                }
            }
            parsechest.isopenable = false;
        }
        else {
            System.out.println("Du hast diese Kiste bereits ge�ffnet!");
        }
    }
    
    /**
     * Methode zur �berpr�fung von isopenable (privat).
     */
    public boolean getisopenable(Chest parsechest) 
    {
        return parsechest.isopenable;
    }
}
