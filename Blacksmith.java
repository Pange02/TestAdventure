import java.util.Random;
/**
 * Write a description of class Blacksmith here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Blacksmith extends NPC
{
    // Instanzvariablen der Klasse Blacksmith
    private Random reforgegenerator;
    private Reforge randomreforge;
    
    /**
     * Constructor for objects of class Blacksmith
     */
    public Blacksmith(String parsename)
    {
        super(parsename);
        reforgegenerator = new Random();
    }
    
    // Methode zum Interagieren mit dem Spieler
    public void speak(Player parseplayer) {
        System.out.println("Guten Tag! Mein Name ist " + name + ".");
        try {
            Thread.sleep(1500);
        }   
        catch(Exception e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("M�chtest du vielleicht eines deiner Items f�r 5 Coins verbessern? Sage einfach verbessere und nenne mir dazu eine Zahl aus deinem Inventar.");
        try {
            Thread.sleep(1500);
        }   
        catch(Exception e) {
            Thread.currentThread().interrupt();
        }
        parseplayer.getinventorycontent();
    }
    
    // Methode um einen Reforge auf ein Item anzuwenden �bergabewert (Item)
    public void reforge(Item parseitem) {
        if(parseitem.getClass() == Weapon.class) {
            randomreforge = Reforge.getweaponreforgelist().get(reforgegenerator.nextInt(Reforge.getweaponreforgelist().size()));  
            ((Weapon) parseitem).applyreforge(randomreforge);
            System.out.println("Du verbesserst " + parseitem.getArtikel("akkusativ", "bestimmt") + " " + parseitem.getitemname() + " zu " + randomreforge.getname() + " " + parseitem.getitemname());
        }
        else if(parseitem.getClass() == Armor.class) {
            randomreforge = Reforge.getarmorreforgelist().get(reforgegenerator.nextInt(0, Reforge.getarmorreforgelist().size()));
            ((Armor) parseitem).applyreforge(randomreforge);
            System.out.println("Du verbesserst " + parseitem.getArtikel("akkusativ", "bestimmt") + " " + parseitem.getitemname() + " zu " + randomreforge.getname() + " " + parseitem.getitemname());
        }
        else {
            System.out.println("Du kannst nur Waffen und R�stungsteile verbessern!");
        }
        System.out.println("M�chtest du dein Item noch einmal reforgen?");
    }
}
