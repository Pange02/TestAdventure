import java.util.ArrayList;
/**
 * Diese Klasse macht es dem Spieler möglich auf Gegenstände zuzugreifen.
 * 
 * @author (Ihr Name) 
 * @version 1.1.1
 */
public class Item
{
    // Attribute eines Items
    protected String name;
    protected String description;
    protected String rarity;
    protected String descriptionstring;
    protected String statsstring;
    protected int spaces;
    protected String gender;
        
    //Liste mit allen möglichen Seltenheitsgraden.
    private static String[] itemtiers = {"(Gewöhnlich)", "(Ungewöhnlich)", "(Selten)", "(Episch)", "(Legendär)", "(Mythisch)"};
    
    private static ArrayList[] itemlist = new ArrayList[5];
    
    /**
     * Konstruktor für Objekte der Klasse Item mit Namen, Damage, Seltenheit und Typ.
     */
    public Item(String parsename, String parsedescription, String parserarity, String parsegender)
    {
        name = parsename;
        description = parsedescription;
        rarity = parserarity;
        gender = parsegender;
    }

    /**
     * Methode zur Generierung von allen Items im Spiel. Wird am Anfang von der Game Klasse aufgerufen.
     */
    public static void fillitemlist()
    {
        itemlist[0] = Weapon.weaponlist;
        itemlist[1] = Armor.armorlist;
        itemlist[2] = Potion.potionlist;
        itemlist[3] = Accessory.accessorylist;
        itemlist[4] = Consumable.consumablelist;
    }
    
    //Zugriff auf jedes Item im game übergeben wird die Art von Item und sein Platz in der entsprechenden Liste.
    public static Item getitemfromlist(String type, int arrayslot) 
    {
        if(type.toLowerCase().equals("weapon")) {
            return ((Weapon) itemlist[0].get(arrayslot));
        }
        else if(type.toLowerCase().equals("armor")) {
            return ((Armor) itemlist[1].get(arrayslot));
        }
        else if(type.toLowerCase().equals("potion")) {
            return ((Potion) itemlist[2].get(arrayslot));
        }
        else if(type.toLowerCase().equals("accessory")) {
            return ((Accessory) itemlist[3].get(arrayslot));
        }
        else if(type.toLowerCase().equals("consumable")) {
            return ((Consumable) itemlist[4].get(arrayslot));
        }
        else {
            return null;
        }
    }
    
    //Rückgabe des Namen eines Items
    public String getitemname()
    {
        return name;
    }
    
    //Rückgabe der Menge an Items in einer Kategorie
    public static int getitemlistsize()
    {
        return itemlist.length;
    }
    
    //Rückgabe der Seltenheit eines Items
    public String getitemrarity()
    {
        return rarity;
    }
    
    //Rückgabe der ArrayList eines Itemtypes
    public static ArrayList[] getitemlist() {
        return itemlist;
    }
    
    //Rückgabe der Seltenheit eines Reforges
    public int getreforgerarity() {
        if(rarity.equals("(Gewöhnlich)")) {
            return 0;
        }
        else if(rarity.equals("(Ungewöhnlich)")) {
            return 1;
        }
        else if(rarity.equals("(Selten)")) {
            return 2;
        }
        else if(rarity.equals("(Episch)")) {
            return 3;
        }
        else if(rarity.equals("(Legendär)")) {
            return 4;
        }
        else if(rarity.equals("(Mytisch)")) {
            return 5;
        }
        else {
            return -1;
        }
    }
    
    //Ausgabe der Informationen eines Items
    public void getiteminfo() {
        for(int i = 0; i <= (description.length() - name.length())/2; i++) {
            System.out.print("-");
        }
        System.out.print(name);
        for(int i = 0; i <= (description.length() - name.length())/2; i++) {
            if(i == (description.length() - name.length())/2) {
                System.out.println("-");
            }
            else {
                System.out.print("-");
            }
        }
        System.out.println(" " + description);
        for(int i = 0; i <= (description.length() - rarity.length() + 1)/2; i++) {
            System.out.print("-");
        }
        System.out.print(rarity);
        for(int i = 0; i <= (description.length() - rarity.length() + 1)/2; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
    
    //Gibt zu einem Item und einem Kasus einen bestimmten oder unbestimmten Artikel aus.
    public String getArtikel(String kasus, String art){
        if (art.toLowerCase().equals("bestimmt")){
            return Grammar.getArtikel(kasus, gender);
        }
        else if (art.toLowerCase().equals("unbestimmt")){
            return Grammar.getUnArtikel(kasus, gender);
        }
        else {
            return "kein Artikel in Item";
        }
    }
}
