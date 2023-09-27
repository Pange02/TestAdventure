import java.util.ArrayList;
/**
 * Beschreiben Sie hier die Klasse Item.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Item
{
    // Attribute eines Items
    protected String name;
    protected String rarity;
        
    //Liste mit allen m�glichen Seltenheitsgraden. Dient nur als �bersicht aktuell.
    private static String[] itemtiers = {"(Gew�hnlich)", "(Ungew�hnlich)", "(Selten)", "(Episch)", "(Legend�r)", "(Mythisch)"};
    
    /**
     * Konstruktor f�r Objekte der Klasse Item mit Namen, Damage, Seltenheit und Typ.
     */
    public Item(String parsename, String parserarity)
    {
        name = parsename;
        rarity = parserarity;
    }

    /**
     * Methode zur Generierung von allen Items im Spiel. Wird am Anfang von der Game Klasse aufgerufen.
     */
    public static void fillitemlist()
    {
        // Erstellen von Items Name entsteht aus item + itemnr + Kategorie
        
        
        
        // Potions (p)
        Item potion1 = new Item("Gifttrank", 2, "(Gew�hnlich)", "Potion");
        Item potion2 = new Item("Schadenstrank", 3, "(Ungew�hnlich)", "Potion");
        Item potion3 = new Item("Heilungstrank", 0, "(Selten)", "Potion");
        
        // Armor (a)
        Item armor1 = new Item("Verrostete Brustplatte", 0, "(Ungew�hnlich)", "Armor");
        Item armor2 = new Item("Kettenhemd", 0, "(Gew�hnlich)", "Armor");
        Item armor3 = new Item("Lederkappe", 0, "(Gew�hnlich)", "Armor");
        Item armor4 = new Item("Alte Stiefel", 0, "(Gew�hnlich)", "Armor");
        Item armor5 = new Item("Ritterhelm", 0, "(Ungew�hnlich)", "Armor");
        
        // Accessory (x)
        Item accessorie1 = new Item("Artefakt des Himmels", 2, "(Episch)", "Accessory");
        Item accessorie2 = new Item("Ring der St�rke", 1, "(Selten)", "Accessory");
    }
    
    public static Item getitemfromlist(int arrayslot) 
    {
        return itemlist.get(arrayslot);
    }
    
    public String getitemname(Item parseitem)
    {
        return parseitem.name;
    }
    
    public int getitemdamage(Item parseitem)
    {
        return parseitem.damage;
    }
    
    public static int getitemlistsize()
    {
        return itemlist.size();
    }
    
    public String getitemrarity(Item parseitem)
    {
        return parseitem.rarity;
    }
}
