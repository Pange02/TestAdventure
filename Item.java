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
    private static String[] itemtiers = {"(Gewöhnlich)", "(Ungewöhnlich)", "(Selten)", "(Episch)", "(Legendär)", "(Mythisch)"};
    
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
        // Erstellen von Items Name entsteht aus item + itemnr + Kategorie
        
        // Waffen (w)
        Item item1w = new Item("Langschwert", 5, "(Ungewöhnlich)", "Weapon");
        Item item2w = new Item("Holzstock", 1, "(Gewöhnlich)", "Weapon");
        Item item3w = new Item("Streitaxt" , 3, "(Gewöhnlich)", "Weapon");
        Item item4w = new Item("Dolch" , 2, "(Selten)", "Weapon");
        Item item5w = new Item("Speer", 4, "(Selten)", "Weapon");
        Item item6w = new Item("Katana", 8, "(Legendär)", "Weapon");
        
        // Potions (p)
        Item item1p = new Item("Gifttrank", 2, "(Gewöhnlich)", "Potion");
        Item item2p = new Item("Schadenstrank", 3, "(Ungewöhnlich)", "Potion");
        Item item3p = new Item("Heilungstrank", 0, "(Selten)", "Potion");
        
        // Armor (a)
        Item item1a = new Item("Verrostete Brustplatte", 0, "(Ungewöhnlich)", "Armor");
        Item item2a = new Item("Kettenhemd", 0, "(Gewöhnlich)", "Armor");
        Item item3a = new Item("Lederkappe", 0, "(Gewöhnlich)", "Armor");
        Item item4a = new Item("Alte Stiefel", 0, "(Gewöhnlich)", "Armor");
        Item item5a = new Item("Ritterhelm", 0, "Ungewöhnlich)", "Armor");
        
        // Accessory (x)
        Item item1x = new Item("Artefakt des Himmels", 2, "(Episch)", "Accessory");
        Item item2x = new Item("Ring der Stärke", 1, "(Selten)", "Accessory");
        
        
        // Hinzufügen der Items zur Liste aller Items
        
        itemlist.add(item1w);
        itemlist.add(item2w);
        itemlist.add(item3w);
        itemlist.add(item4w);
        itemlist.add(item5w);
        itemlist.add(item6w);
        
        itemlist.add(item1p);
        itemlist.add(item2p);
        itemlist.add(item3p);
        
        itemlist.add(item1a);
        itemlist.add(item2a);
        itemlist.add(item3a);
        itemlist.add(item4a);
        itemlist.add(item5a);
        
        itemlist.add(item1x);
        itemlist.add(item2x);
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
