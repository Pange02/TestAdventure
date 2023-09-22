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
    private int damage;
    private String name;
    private String rarity;
    private String type;
    
    //Liste mit allen möglichen Items
    private static ArrayList<Item> itemlist = new ArrayList<>();
    
    //Liste mit allen möglichen Seltenheitsgraden. Dient nur als Übersicht aktuell.
    private static String[] itemcolors = {"(Gewöhnlich)", "(Ungewöhnlich)", "(Selten)", "(Episch)", "(Legendär)", "(Mythisch)"};
    
    /**
     * Konstruktor für Objekte der Klasse Item mit Namen, Damage, Seltenheit und Typ.
     */
    public Item(String parsename, int parsedamage, String parserarity, String parsetype)
    {
        name = parsename;
        damage = parsedamage;
        rarity = parserarity;
        type = parsetype;
    }

    /**
     * Methode zur Generierung von allen Items im Spiel. Wird am Anfang von der Game Klasse aufgerufen.
     */
    public static void fillitemlist()
    {
        Item item1 = new Item("Diamantschwert", 5, "(Ungewöhnlich)", "Weapon");
        Item item2 = new Item("Gifttrank", 2, "(Gewöhnlich)", "Potion");
        Item item3 = new Item("Ring der Stärke", 1, "(Selten)", "Accessory");
        Item item4 = new Item("Goldene Brustplatte", 0, "(Ungewöhnlich)", "Armor");
        Item item5 = new Item("Artefakt des Himmels", 2, "(Episch)", "Accessory");
        Item item6 = new Item("Holzschwert", 1, "(Gewöhnlich)", "Weapon");
        itemlist.add(item1);
        itemlist.add(item2);
        itemlist.add(item3);
        itemlist.add(item4);
        itemlist.add(item5);
        itemlist.add(item6);
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
    
    public String getitemtype(Item parseitem)
    {
        return parseitem.type;
    }
}
