import java.util.ArrayList;
/**
 * Diese Klasse macht es dem Spieler m�glich auf Gegenst�nde zuzugreifen.
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
        
    //Liste mit allen m�glichen Seltenheitsgraden.
    private static String[] itemtiers = {"(Gew�hnlich)", "(Ungew�hnlich)", "(Selten)", "(Episch)", "(Legend�r)", "(Mythisch)"};
    
    private static ArrayList[] itemlist = new ArrayList[5];
    
    /**
     * Konstruktor f�r Objekte der Klasse Item mit Namen, Damage, Seltenheit und Typ.
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
    
    //Zugriff auf jedes Item im game �bergeben wird die Art von Item und sein Platz in der entsprechenden Liste.
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
    
    //R�ckgabe des Namen eines Items
    public String getitemname()
    {
        return name;
    }
    
    //R�ckgabe der Menge an Items in einer Kategorie
    public static int getitemlistsize()
    {
        return itemlist.length;
    }
    
    //R�ckgabe der Seltenheit eines Items
    public String getitemrarity()
    {
        return rarity;
    }
    
    //R�ckgabe der ArrayList eines Itemtypes
    public static ArrayList[] getitemlist() {
        return itemlist;
    }
    
    //R�ckgabe der Seltenheit eines Reforges
    public int getreforgerarity() {
        if(rarity.equals("(Gew�hnlich)")) {
            return 0;
        }
        else if(rarity.equals("(Ungew�hnlich)")) {
            return 1;
        }
        else if(rarity.equals("(Selten)")) {
            return 2;
        }
        else if(rarity.equals("(Episch)")) {
            return 3;
        }
        else if(rarity.equals("(Legend�r)")) {
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
