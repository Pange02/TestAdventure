
/**
 * Write a description of class Weapon here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Weapon extends Item

{
    // instance variables - replace the example below with your own
    private int damage;

    /**
     * Constructor for objects of class Weapon
     */
    public Weapon(String parsename, String parserarity, int parsedamage)
    {
        name = parsename;
        rarity = parserarity;
        damage = parsedamage;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public int sampleMethod(int y)
    {
        // Waffen (w)
        Weapon weapon1 = new Weapon("Langschwert", "(Ungewöhnlich)", 5);
        Weapon weapon2 = new Weapon("Holzstock", "(Gewöhnlich)", 1);
        Weapon weapon3 = new Weapon("Streitaxt" , "(Gewöhnlich)", 3);
        Weapon weapon4 = new Weapon("Dolch" , "(Selten)", 2);
        Weapon weapon5 = new Weapon("Speer", "(Selten)", 4);
        Weapon weapon6 = new Weapon("Katana", "(Legendär)", 8);
    }
}
