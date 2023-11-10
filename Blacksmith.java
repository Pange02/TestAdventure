import java.util.Random;
/**
 * Write a description of class Blacksmith here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Blacksmith extends NPC
{
    // instance variables - replace the example below with your own
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
    
    public void speak(Player parseplayer) {
        System.out.println("Guten Tag! Mein Name ist " + name + ".");
        try {
            Thread.sleep(1500);
        }   
        catch(Exception e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Möchtest du vielleicht eines deiner Items für 5 Coins verbessern? Sage einfach verbessere und nenne mir dazu eine Zahl aus deinem Inventar.");
        try {
            Thread.sleep(1500);
        }   
        catch(Exception e) {
            Thread.currentThread().interrupt();
        }
        parseplayer.getinventorycontent();
    }
    
    public void reforge(Item parseitem) {
        if(parseitem.getClass() == Weapon.class) {
            System.out.println(Reforge.getweaponreforgelist().size());
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
            System.out.println("Du kannst nur Waffen und Rüstungsteile verbessern!");
        }
        System.out.println("Möchtest du dein Item noch einmal reforgen?");
    }
}
