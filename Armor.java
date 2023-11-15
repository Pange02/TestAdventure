import java.util.ArrayList;
/**
 * Diese Klasse erstellt verschiedene Arten von Rüstungen, welche eine unterschiedliche Schutzkraft beinhalten.
 *
 * @author (your name)
 * @version 1.0.1
 */
public class Armor extends Item
{
    
    //die Werte der Rüstungsteile, sowie die ArrayList, in der die Rüstungsteile gespeichert werden.
    private int defense;
    private Reforge reforge;
    private String armortype;
    protected static ArrayList<Armor> armorlist = new ArrayList<>();
    
    /**
     * Konstruktor der Rüstungsklasse
     */
    public Armor(String parsename, String parsedescription, String parserarity, String armorGender, String parsearmortype, int parsedefense)
    {
        super(parsename, parsedescription, parserarity, armorGender);
        armortype = parsearmortype;
        defense = parsedefense;
    }

    /**
     * In dieser Methode werden die Rüstungsteile erstellt und der ArrayList hinzugefügt.
     */
    public static void createArmor()
    {
        // Armor (Name, Beschreibung, Seltenheit, Grammatikalisches Geschlecht, Art der Rüstung, Verteidigungs Wert)
        Armor armor0 = new Armor("Verrostete Brustplatte", "Eine einst mächtiges Rüstungsstück. Allerdings haben die Zeit und ihre vielen Einsätze ihre Zeichen hinterlassen. Der Vorbesitzer scheint nicht besonder sorgsam mit ihr umgegangen zu sein.", "(Ungewöhnlich)", "feminin", "Chestplate", 2);
        Armor armor1 = new Armor("Kettenhemd", "Ein Kettenhemd, herrgestellt von Kobolden in ihren Höhlen ist sie zwar nicht sonderlich stark, dafür aber sehr leicht.", "(Gewöhnlich)", "neutrum", "Chestplate", 3);
        Armor armor2 = new Armor("Lederkappe", "Eine Lederkappe. Sie bietet werder guten Schutz, noch sieht sie sonderlich gut aus. Vielleicht hätte man aus dem Leder besser einen Türstopper mach sollen.", "(Gewöhnlich)", "feminin", "Helmet", 1);
        Armor armor3 = new Armor("Alte Stiefel", "Normale alte Stiefel. Etwas heruntergekommen und wirklich wasserdicht sind Sie auch nicht mehr. Aber besser als garkein Schutz... wenn sie nur nicht so stinken würden.", "(Gewöhnlich)", "maskulin", "Boots", 1);
        Armor armor4 = new Armor("Ritterhelm", "Ein Ritterhelm. So gut wie neu. Der Schmied der diesen Helm hergestellt hat versteht sein Handwerk. Der Helm schützt deinen Kopf und glänzt auch noch... was will man mehr?", "(Selten)", "maskulin", "Helmet", 3);
        
        //Armor zur Armorliste hinzufügen
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
    
    //Ausgeben der Beschreibung und Werte eines Rüstungsstückes.
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
    
    //Anwenden eines übergebenen Reforges auf ein Rüstungsteil.
    public void applyreforge(Reforge parsereforge) {
        reforge = parsereforge;
        defense += parsereforge.getdefense()[getreforgerarity()];
    }
    
    public boolean getreforge() {
        return (reforge != null);
    }
}
