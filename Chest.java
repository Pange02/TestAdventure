import java.util.ArrayList;
import java.util.Random;
/**
 * Beschreiben Sie hier die Klasse Chest.
 * 
 * @creator: Leonard
 * @editors: 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Chest
{
    // Instanzvariablen der Kisten Klasse
    
    //number f�r eventuelle random Generierungen (aktuell nicht verwendet).
    private int number;
    
    //isOpenable gibt an, ob man die Kiste �ffnen kann.
    private boolean isOpenable;
    
    //Der Loot f�r jede Kiste
    private ArrayList<Item> chestLoot;
    
    private int coinamount;
    
    // Konstruktor f�r Objekte der Klasse Chest mit der Loot ArrayList aus der Game Klasse als Argument.
    public Chest(ArrayList<Item> parseItemList)
    {
        chestLoot = parseItemList;
        isOpenable = true;
        Random coingenerator = new Random();
        coinamount = coingenerator.nextInt(10, 26);
    }
    
    // Methode f�r das Bef�llen von Kisten. Nicht verwendet, da es aktuell klar definiert ist, welcher Loot wo zu finden ist.
    private void fillChest()
    {
        //Random r = new Random();
        //number = r.nextInt(Item.getitemlistlength());
        //chestLoot.add(Item.getitemfromlist(0));
        //chestLoot.add(Item.getitemfromlist(1));
        //chestLoot.add(Item.getitemfromlist(2));
    }
   
    
    // Methode f�r das �ffnen von Kisten. 
    public void openChest(Player parsePlayer, Chest parseChest)
    {
        if(parseChest.isOpenable == true) {
            System.out.println("Die Kiste �ffnet sich:");
            for(int i = 0; i < chestLoot.size(); i++) {
                // Hinzuf�gen des Loots in das Inventar des Spielers
                parsePlayer.additemtoinventory(chestLoot.get(i));
                
                // �berpr�fen der Art von Loot und Beschreibung des Loots
                if(chestLoot.get(i).getClass() == Weapon.class) {
                   System.out.println("Du findest " + chestLoot.get(i).getArtikel("akkusativ", "unbestimmt") +  " " + chestLoot.get(i).getitemname() + " " + chestLoot.get(i).getitemrarity() + " mit " + ((Weapon) chestLoot.get(i)).getweapondamage() + " Schaden."); 
                }
                if(chestLoot.get(i).getClass() == Consumable.class) {
                   System.out.println("Du findest " + chestLoot.get(i).getArtikel("akkusativ", "unbestimmt") +  " " + chestLoot.get(i).getitemname() + " " + chestLoot.get(i).getitemrarity() + " mit " + ((Consumable) chestLoot.get(i)).getconsumableeffect() + " Heilung."); 
                }
                if(chestLoot.get(i).getClass() == Armor.class) {
                   System.out.println("Du findest " + chestLoot.get(i).getArtikel("akkusativ", "unbestimmt") +  " " + chestLoot.get(i).getitemname() + " " + chestLoot.get(i).getitemrarity() + " mit " + ((Armor) chestLoot.get(i)).getarmordefense() + " Verteidigung."); 
                }
                if(chestLoot.get(i).getClass() == Potion.class && ((Potion) chestLoot.get(i)).getpotiontype() == "Damage") {
                   System.out.println("Du findest " + chestLoot.get(i).getArtikel("akkusativ", "unbestimmt") + " " + chestLoot.get(i).getitemname() + " " + chestLoot.get(i).getitemrarity() + " mit " + ((Potion) chestLoot.get(i)).getpotioneffect() + " Schaden."); 
                }
                if(chestLoot.get(i).getClass() == Potion.class && ((Potion) chestLoot.get(i)).getpotiontype() == "Poison") {
                   System.out.println("Du findest " + chestLoot.get(i).getArtikel("akkusativ", "unbestimmt") + " " + chestLoot.get(i).getitemname() + " " + chestLoot.get(i).getitemrarity() + " mit " + ((Potion) chestLoot.get(i)).getpotioneffect() + " Schaden �ber 3 Runden."); 
                }
                if(chestLoot.get(i).getClass() == Potion.class && ((Potion) chestLoot.get(i)).getpotiontype() == "Healing") {
                   System.out.println("Du findest " + chestLoot.get(i).getArtikel("akkusativ", "unbestimmt") +  " " + chestLoot.get(i).getitemname() + " " + chestLoot.get(i).getitemrarity() + " mit " + ((Potion) chestLoot.get(i)).getpotioneffect() + " Heilung."); 
                }
                if(chestLoot.get(i).getClass() == Accessory.class) {
                   System.out.println("Du findest " + chestLoot.get(i).getArtikel("akkusativ", "unbestimmt") +  " " + chestLoot.get(i).getitemname() + " " + chestLoot.get(i).getitemrarity() + " mit " + ((Accessory) chestLoot.get(i)).getaccessorystrength() + " St�rke."); 
                }
            }
            parsePlayer.addcoins(coinamount);
            System.out.println("Du findest " + coinamount + " Coins in der Truhe.");
            // Kiste wird "verschlossen" und kann nicht mehr ge�ffnet werden
            parseChest.isOpenable = false;
        }
        else {
            System.out.println("Du hast diese Kiste bereits ge�ffnet!");
        }
    }
    
    /**
     * Methode zur �berpr�fung von isopenable (privat).
     */
    public boolean getIsOpenable(Chest parseChest) 
    {
        return parseChest.isOpenable;
    }
}
