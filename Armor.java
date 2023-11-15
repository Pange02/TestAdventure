import java.util.ArrayList;
/**
 * Write a description of class Armor here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Armor extends Item
{
    private int defense;
    private Reforge reforge;
    private String armortype;
    protected static ArrayList<Armor> armorlist = new ArrayList<>();
    /**
     * Constructor for objects of class Armor
     */
    public Armor(String parsename, String parsedescription, String parserarity, String armorGender, String parsearmortype, int parsedefense)
    {
        super(parsename, parsedescription, parserarity, armorGender);
        armortype = parsearmortype;
        defense = parsedefense;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public static void createArmor()
    {
        // Armor (a)
        Armor armor0 = new Armor("Verrostete Brustplatte", "Eine einst m�chtiges R�stungsst�ck. Allerdings haben die Zeit und ihre vielen Eins�tze ihre Zeichen hinterlassen. Der Vorbesitzer scheint nicht besonder sorgsam mit ihr umgegangen zu sein.", "(Ungew�hnlich)", "feminin", "Chestplate", 2);
        Armor armor1 = new Armor("Kettenhemd", "Ein Kettenhemd, herrgestellt von Kobolden in ihren H�hlen ist sie zwar nicht sonderlich stark, daf�r aber sehr leicht.", "(Gew�hnlich)", "neutrum", "Chestplate", 3);
        Armor armor2 = new Armor("Lederkappe", "Eine Lederkappe. Sie bietet werder guten Schutz, noch sieht sie sonderlich gut aus. Vielleicht h�tte man aus dem Leder besser einen T�rstopper mach sollen.", "(Gew�hnlich)", "feminin", "Helmet", 1);
        Armor armor3 = new Armor("Alte Stiefel", "Normale alte Stiefel. Etwas heruntergekommen und wirklich wasserdicht sind Sie auch nicht mehr. Aber besser als garkein Schutz... wenn sie nur nicht so stinken w�rden.", "(Gew�hnlich)", "maskulin", "Boots", 1);
        Armor armor4 = new Armor("Ritterhelm", "Ein Ritterhelm. So gut wie neu. Der Schmied der diesen Helm hergestellt hat versteht sein Handwerk. Der Helm sch�tzt deinen Kopf und gl�nzt auch noch... was will man mehr?", "(Selten)", "maskulin", "Helmet", 3);
        //Armor zur Armorliste hinzuf�gen
        armorlist.add(armor0);
        armorlist.add(armor1);
        armorlist.add(armor2);
        armorlist.add(armor3);
        armorlist.add(armor4);
    }
    
    public int getarmordefense() {
        return defense;
    }
    
    public String getarmortype() {
        return armortype;
    }
    
    @Override public void getiteminfo() {
        descriptionstring = " " + description;
        statsstring = " " + "Verteidigung: " + defense;
        spaces = Math.max(descriptionstring.length(), statsstring.length());
        for(int i = 0; i <= (spaces - name.length())/2 - 1; i++) {
            System.out.print("-");
        }
        System.out.print(" ");
        System.out.print(name);
        System.out.print(" ");
        for(int i = 0; i <= (spaces - name.length())/2 - 1; i++) {
            if(i == (spaces - name.length())/2 - 1) {
                System.out.println("-");
            }
            else {
                System.out.print("-");
            }
        }
        System.out.println(descriptionstring);
        System.out.println(statsstring);
        for(int i = 0; i <= (spaces - rarity.length() + 1)/2 - 1; i++) {
            System.out.print("-");
        }
        System.out.print(" ");
        System.out.print(rarity);
        System.out.print(" ");
        for(int i = 0; i <= (spaces - rarity.length() + 1)/2 - 1; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
    
    public void applyreforge(Reforge parsereforge) {
        reforge = parsereforge;
        defense += parsereforge.getdefense()[getreforgerarity()];
    }
    
    public boolean getreforge() {
        return (reforge != null);
    }
}
