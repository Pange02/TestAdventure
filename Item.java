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
    private static ArrayList<Item> weapons = new ArrayList<>();
    private static ArrayList<Item> potions = new ArrayList<>();
    private static ArrayList<Item> armor = new ArrayList<>();
    private static ArrayList<Item> accessories = new ArrayList<>();
        
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
        Item weapon1 = new Item("Langschwert", 5, "(Ungewöhnlich)", "Weapon");
        Item weapon2 = new Item("Holzstock", 1, "(Gewöhnlich)", "Weapon");
        Item weapon3 = new Item("Streitaxt" , 3, "(Gewöhnlich)", "Weapon");
        Item weapon4 = new Item("Dolch" , 2, "(Selten)", "Weapon");
        Item weapon5 = new Item("Speer", 4, "(Selten)", "Weapon");
        Item weapon6 = new Item("Katana", 8, "(Legendär)", "Weapon");
        
        // Potions (p)
        Item potion1 = new Item("Gifttrank", 2, "(Gewöhnlich)", "Potion");
        Item potion2 = new Item("Schadenstrank", 3, "(Ungewöhnlich)", "Potion");
        Item potion3 = new Item("Heilungstrank", 0, "(Selten)", "Potion");
        
        // Armor (a)
        Item armor1 = new Item("Verrostete Brustplatte", 0, "(Ungewöhnlich)", "Armor");
        Item armor2 = new Item("Kettenhemd", 0, "(Gewöhnlich)", "Armor");
        Item armor3 = new Item("Lederkappe", 0, "(Gewöhnlich)", "Armor");
        Item armor4 = new Item("Alte Stiefel", 0, "(Gewöhnlich)", "Armor");
        Item armor5 = new Item("Ritterhelm", 0, "(Ungewöhnlich)", "Armor");
        
        // Accessory (x)
        Item accessorie1 = new Item("Artefakt des Himmels", 2, "(Episch)", "Accessory");
        Item accessorie2 = new Item("Ring der Stärke", 1, "(Selten)", "Accessory");
        
        
        // Hinzufügen der Items zur Liste aller Items
        
        weapons.add(weapon1);
        weapons.add(weapon2);
        weapons.add(weapon3);
        weapons.add(weapon4);
        weapons.add(weapon5);
        weapons.add(weapon6);
        
        potions.add(potion1);
        potions.add(potion2);
        potions.add(potion3);
        
        armor.add(armor1);
        armor.add(armor2);
        armor.add(armor3);
        armor.add(armor4);
        armor.add(armor5);
        
        accessories.add(accessorie1);
        accessories.add(accessorie2);
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
