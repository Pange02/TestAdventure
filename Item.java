
/**
 * Beschreiben Sie hier die Klasse Item.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Item
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private int damage;
    private String name;
    private static Item[] itemlist = new Item[2];
    /**
     * Konstruktor für Objekte der Klasse Item
     */
    public Item(String parsename, int parsedamage)
    {
        name = parsename;
        damage = parsedamage;
    }

    /**
     * Ein Beispiel einer Methode - ersetzen Sie diesen Kommentar mit Ihrem eigenen
     * 
     * @param  y    ein Beispielparameter für eine Methode
     * @return        die Summe aus x und y
     */
    public static void fillitemlist()
    {
        Item item1 = new Item("Diamantschwert", 5);
        Item item2 = new Item("Gifttrank", 2);
        itemlist[0] = item1;
        itemlist[1] = item2;
    }
    
    public static Item getitemfromlist(int arrayslot) 
    {
        return itemlist[arrayslot];
    }
    
    public static String getitemname(Item parseitem)
    {
        return parseitem.name;
    }
    
    public static int getitemdamage(Item parseitem)
    {
        return parseitem.damage;
    }
    
    public static int getitemlistlength()
    {
        return itemlist.length;
    }
}
