import java.util.Scanner;
import java.util.ArrayList;
/**
 * Beschreiben Sie hier die Klasse Parser.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Parser
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    
    //Aktionsliste für alle möglichen Aktionen. Verwendet von help Befehl.
    private String[] actionlist = {"hilfe", "öffne (truhe)", "stats", "inv", "rede <Name>", "kaufe <Item>", "info <Inventarslot>", "benutze <Inventarslot>", "ablegen <Inventarslot>", "umgucken", "gehe <Himmelsrichtung>"};
    
    private String[] combatactionlist = {"attackiere", "benutze <Inventarslot>"};
    
    //Array für den Input des Spielers
    private String[] input = new String[10];
    
    //Array für den BuyInput des Spielers
    private String[] buyinput = new String[10];
    
    //Boolean gibt an, ob der Parser (das Spiel) aktiv ist.
    private boolean running;
    
    private boolean combat;
    
    //Aktiver Spieler vom Parser
    private static Player activeplayer;
    
    private int weaponnumber;
    
    private int inventorynumber;
    
    private Weapon playerweapon;
    
    private Mob activemob;
    
    private boolean mobattack;
    
    private int poisonrounds;
    
    private int poisoneffect;
    
    private boolean firstfight;
    
    private boolean weaponselected;
    
    /**
     * Konstruktor für Objekte der Klasse Parser mit Spieler
     */
    public Parser(Player parseplayer)
    {
        running = true;
        combat = false;
        firstfight = true;
        weaponselected = false;
        mobattack = true;
        activeplayer = parseplayer;
        System.out.println("Was möchtest du tun? Für Hilfe: hilfe");
        while(running && !combat) {
            Scanner parser = new Scanner(System.in);
            getaction(parser.nextLine(), activeplayer);
            System.out.println();
            while(running && combat) {
                if(firstfight == true) {
                    firstfight = false;
                    System.out.println("Aktuell kannst du folgenden Kampfaktionen machen:");
                    for(int i = 0; i < combatactionlist.length; i++) {
                        System.out.println(combatactionlist[i]);
                    }
                    System.out.println();
                }
                if(poisonrounds > 1 && mobattack) {
                    activemob.setmobhealth(activemob.getmobhealth() - poisoneffect);
                    poisonrounds -= 1;
                    System.out.println("Durch das Gift nimmt " + activemob.getArtikel("nominativ", "bestimmt") + " " + activemob.getmobname() + " " + poisoneffect + " Schaden. " + activemob.getArtikel("nominativ", "bestimmt").substring(0, 1).toUpperCase() 
                    + activemob.getArtikel("nominativ", "bestimmt").substring(1) + " " + activemob.getmobname() + " hat jetzt " + activemob.getmobhealth() + " Leben. Das Gift hält noch " + poisonrounds + " weitere Runden.");
                }
                else if(poisonrounds == 1 && mobattack) {
                    activemob.setmobhealth(activemob.getmobhealth() - poisoneffect);
                    poisonrounds -= 1;
                    System.out.println("Durch das Gift nimmt " + activemob.getArtikel("nominativ", "bestimmt") + " " + activemob.getmobname() + " " + poisoneffect + " Schaden. " + activemob.getArtikel("nominativ", "bestimmt").substring(0, 1).toUpperCase() 
                    + activemob.getArtikel("nominativ", "bestimmt").substring(1) + " " + activemob.getmobname() + " hat jetzt " + activemob.getmobhealth() + " Leben. Der Gifteffekt hat nun seine Wirkung verloren.");
                }
                Scanner compatparser = new Scanner(System.in);
                getcombataction(parser.nextLine(), activeplayer, activemob);
                if(activemob.getmobhealth() > 0 && mobattack) {
                    activemob.attack(activeplayer);
                }
                if(parseplayer.getplayerhealth() <= 0) {
                    System.out.println("Du bist gestorben!");
                    System.out.println("Schreibe \"Neustart\" um das Level zu wiederholen.");
                    Scanner endparser = new Scanner(System.in);
                    if(endparser.nextLine().equals("Neustart")) {
                        combat = false;
                        running = false;
                        Game nextGame = new Game();
                    }
                    else {
                        combat = false;
                        running = false;
                    }
                }
                mobattack = true;
                System.out.println();
                }
        }
    }

    /**
     * Alle möglichen Inputs werden vom Parser nach Leerzeichen getrennt und überprüft, ob sie einer Aktion entsprechen und dann von ihm ausgeführt.
     */
    public void getaction(String parseaction, Player parseplayer)
    {
        input = parseaction.toLowerCase().split("\\s+");
        if(input[0].equals("hilfe")) {
            System.out.println("Aktuell kannst du folgenden Aktionen machen:");
            for(int i = 1; i < actionlist.length; i++) {
                System.out.println(actionlist[i]);
            }
        }
        
        else if(input[0].equals("öffne")) {
            try {
                if(input[1].equals("truhe") && parseplayer.getcurrentroom().getChestInfo()) {
                    parseplayer.getcurrentroom().getChest().openChest(Parser.activeplayer, parseplayer.getcurrentroom().getChest());
                    parseplayer.addexperience(5);
                }
                else if(input[1].equals("truhe") && (parseplayer.getcurrentroom().getChestInfo() == false)) {
                    System.out.println("Dieser Raum hat keine Truhe");
                }
                else {
                    System.out.println("Du kannst dieses Objekt nicht öffnen!");
                } 
            }
            catch(Exception e) {
                System.out.println(e);
                System.out.println("Du kannst dies nicht öffnen!");
            }
        }    
        else if(input[0].equals("umgucken")) {
            if(parseplayer.getcurrentroom().getChestInfo()) {
                System.out.println("Du entdeckst eine Truhe in dem Raum.");
            }
            if(parseplayer.getcurrentroom().getNPCInfo()) {
                System.out.println("Du entdeckst die Person " + parseplayer.getcurrentroom().getNPC().getNPCname() + " im Raum.");
            }
            System.out.println("Du siehst Türen im:");
            for(int i = 0; i < parseplayer.getcurrentroom().getRoomDirections().size(); i++) {
                    System.out.println(parseplayer.getcurrentroom().getRoomDirections().get(i));
            }            
        }
        else if(input[0].equals("rede")) {
            try {
                if(input[1].toLowerCase().equals(parseplayer.getcurrentroom().getNPC().getNPCname().toLowerCase())) {
                    parseplayer.getcurrentroom().getNPC().speak(); 
                    if(parseplayer.getcurrentroom().getNPC().getClass() == Merchant.class) {
                        Scanner buyparser = new Scanner(System.in);
                        buyinput = buyparser.nextLine().toLowerCase().split("\\s+");
                        if(buyinput[0].equals("kaufe")) {
                            ((Merchant) parseplayer.getcurrentroom().getNPC()).buyitem(activeplayer, buyinput[1]);
                        }
                    }
                }
                else {
                    System.out.println("Diese Person scheint nicht im Raum zu sein!");
                }
            }
            catch(Exception e) {
                System.out.println("Du musst angeben, mit wem du reden willst!");
            }
        }
        else if(input[0].equals("inv")) {
            parseplayer.getinventorycontent();
        }
        else if(input[0].equals("stats")) {
            parseplayer.getplayerstats();
        }
        else if(input[0].equals("benutze")) {
            try {
                inventorynumber = Integer.parseInt(input[1]);
                if(parseplayer.getitemfrominventory(inventorynumber).getClass() == Potion.class) {
                    parseplayer.drink(inventorynumber);
                }
                else if(parseplayer.getitemfrominventory(inventorynumber).getClass() == Armor.class) {
                    parseplayer.equiparmor(((Armor) parseplayer.getitemfrominventory(inventorynumber)));
                }
                else if(parseplayer.getitemfrominventory(inventorynumber).getClass() == Consumable.class) {
                    parseplayer.consume(inventorynumber);
                }
            }
            catch(Exception e) {
                System.out.println("Dies ist keine gültige Zahl aus deinem Inventar!");
                System.out.println(e);
            }
        }
        else if(input[0].equals("ablegen")) {
            try {
                if(input[1].toLowerCase().equals("helm")) {
                    parseplayer.unequiparmor("helmet");
                }
                else if(input[1].toLowerCase().equals("brustplatte")) {
                    parseplayer.unequiparmor("chestplate");
                }
                else if(input[1].toLowerCase().equals("hose") || input[1].toLowerCase().equals("beinschutz")) {
                    parseplayer.unequiparmor("leggings");
                }
                else if(input[1].toLowerCase().equals("schuhe")) {
                    parseplayer.unequiparmor("boots");
                }
                else {
                    System.out.println("Du kannst dies nicht ablegen! Wähle zwischen Helm, Brustplatte, Hose und Schuhe.");
                }
            }
            catch(Exception e) {
                System.out.println("Du musst das Rüstungsteil, welches du ablegen willst, angeben.");
            }
        }
        else if(input[0].equals("info")) {
            try {
                try {
                   inventorynumber = Integer.parseInt(input[1]);
                   parseplayer.getitemfrominventory(inventorynumber).getiteminfo();
                }
                catch(Exception e) {
                    System.out.println("Dies ist keine gültige Zahl für dein Inventar");
                }
            }
            catch(Exception e) {
                System.out.println("Du musst einen Inventarslot angeben");
            }
        }
        else if(input[0].equals("ainfo")) {
            try {
                try {
                   inventorynumber = Integer.parseInt(input[1]);
                   parseplayer.getitemfromaccessories(inventorynumber).getiteminfo();
                }
                catch(Exception e) {
                    System.out.println("Dies ist keine gültige Zahl für dein Inventar");
                }
            }
            catch(Exception e) {
                System.out.println("Du musst einen Inventarslot angeben");
            }
        }
        else if(input[0].equals("gehe")) {
            try {
                if(input[1].equals("norden") && parseplayer.getcurrentroom().getConnectedRooms("north") != null) {
                    System.out.println("Du bewegst dich durch die Tür in den neuen Raum im Norden");
                    parseplayer.setcurrentroom(parseplayer.getcurrentroom().getConnectedRooms("north"));
                    if(parseplayer.getcurrentroom().getMobInfo() == true && parseplayer.getcurrentroom().getRoomMob().getmobstatus() == true) {
                        entercombat(parseplayer, parseplayer.getcurrentroom().getRoomMob());
                        combat = true;
                    }
                }
                else if(input[1].equals("norden") && parseplayer.getcurrentroom().getConnectedRooms("north") == null) {
                    System.out.println("In dieser Richtung befindet sich keine Tür in einen anderen Raum.");
                }
                else if(input[1].equals("osten") && parseplayer.getcurrentroom().getConnectedRooms("east") != null) {
                    System.out.println("Du bewegst dich durch die Tür in den neuen Raum im Osten");
                    parseplayer.setcurrentroom(parseplayer.getcurrentroom().getConnectedRooms("east"));
                    if(parseplayer.getcurrentroom().getMobInfo() == true && parseplayer.getcurrentroom().getRoomMob().getmobstatus() == true) {
                        entercombat(parseplayer, parseplayer.getcurrentroom().getRoomMob());
                        combat = true;
                    }
                }
                else if(input[1].equals("osten") && parseplayer.getcurrentroom().getConnectedRooms("east") == null) {
                    System.out.println("In dieser Richtung befindet sich keine Tür in einen anderen Raum.");
                }
                else if(input[1].equals("süden") && parseplayer.getcurrentroom().getConnectedRooms("south") != null) {
                    System.out.println("Du bewegst dich durch die Tür in den neuen Raum im Süden");
                    parseplayer.setcurrentroom(parseplayer.getcurrentroom().getConnectedRooms("south"));
                    if(parseplayer.getcurrentroom().getMobInfo() == true && parseplayer.getcurrentroom().getRoomMob().getmobstatus() == true) {
                        entercombat(parseplayer, parseplayer.getcurrentroom().getRoomMob());
                        combat = true;
                    }
                }
                else if(input[1].equals("süden") && parseplayer.getcurrentroom().getConnectedRooms("south") == null) {
                    System.out.println("In dieser Richtung befindet sich keine Tür in einen anderen Raum.");
                }
                else if(input[1].equals("westen") && parseplayer.getcurrentroom().getConnectedRooms("west") != null) {
                    System.out.println("Du bewegst dich durch die Tür in den neuen Raum im Westen");
                    parseplayer.setcurrentroom(parseplayer.getcurrentroom().getConnectedRooms("west"));
                    if(parseplayer.getcurrentroom().getMobInfo() == true && parseplayer.getcurrentroom().getRoomMob().getmobstatus() == true) {
                        entercombat(parseplayer, parseplayer.getcurrentroom().getRoomMob());
                        combat = true;
                    }
                }
                else if(input[1].equals("westen") && parseplayer.getcurrentroom().getConnectedRooms("west") == null) {
                    System.out.println("In dieser Richtung befindet sich keine Tür in einen anderen Raum.");
                }
                else {
                    System.out.println("Das ist keine gültige Richtung!");
                }
            }
            catch(Exception e) {
                System.out.println("Du musst eine Richtung angeben. Gültige Richtung sind: north, east, south, west.");
            }
        }
        else if(input[0].equals("exit")) {
            System.out.println("Du verlässt das Dungeon.");
            running = false;
        }
        else {
            System.out.println("Dies ist kein gültiger Befehl");
        }
    }
    
    public void entercombat(Player parseplayer, Mob parsemob) {
        activemob = parsemob;
        weaponselected = false;
        System.out.println("Als du den Raum betrittst, entdeckst du " + parsemob.getArtikel("akkusativ", "unbestimmt") + " " + parsemob.getmobname() + ". Es kommt zum Kampf.");
        System.out.println("Welche Waffe möchtest du für den Kampf benutzen?");
        parseplayer.getinventorycontent();
        System.out.println();
        while(!weaponselected) {
            Scanner weaponparser = new Scanner(System.in);
            try {
                weaponnumber = Integer.parseInt(weaponparser.nextLine());
                try {
                    playerweapon = ((Weapon) parseplayer.getitemfrominventory(weaponnumber));
                    weaponselected = true;
                }
                catch(Exception e) {
                    System.out.println("Du musst eine Waffe für den Kampf wählen. Benutze dafür eine Zahl aus deinem Inventar mit einer Waffe.");
                }
            }
            catch(Exception e) {
                System.out.println("Dies ist keine gültige Zahl für dein Inventar");
            }
        }
        System.out.println("Du wählst " + playerweapon.getArtikel("akkusativ", "bestimmt") + " " + playerweapon.getitemname() + ".");
        System.out.println();
    }
    
    public void getcombataction(String parseaction, Player parseplayer, Mob parsemob) {
        input = parseaction.toLowerCase().split("\\s+"); 
        if(input[0].equals("hilfe")) {
            System.out.println("Aktuell kannst du folgenden Aktionen machen:");
            for(int i = 0; i < combatactionlist.length; i++) {
                System.out.println(combatactionlist[i]);
            }
            mobattack = false;
        }
        else if(input[0].equals("inv")) {
            parseplayer.getinventorycontent();
            mobattack = false;
        }
        else if(input[0].equals("benutze")) {
            try {
                inventorynumber = Integer.parseInt(input[1]);
                if(parseplayer.getitemfrominventory(inventorynumber).getClass() == Potion.class) {
                    if(((Potion) parseplayer.getitemfrominventory(inventorynumber)).getpotiontype() == "Damage") {
                        parsemob.setmobhealth(parsemob.getmobhealth() - ((Potion) parseplayer.getitemfrominventory(inventorynumber)).getpotioneffect());
                        parseplayer.removeitemfrominventory(inventorynumber);
                        System.out.println("Du wirfst den Schadenstrank auf " + parsemob.getArtikel("akkusativ", "bestimmt") + " " + parsemob.getmobname() + " und machst " + ((Potion) parseplayer.getitemfrominventory(inventorynumber)).getpotioneffect() + " Schaden!");
                    }
                    else if(((Potion) parseplayer.getitemfrominventory(inventorynumber)).getpotiontype() == "Poison") {
                        poisonrounds = 3;
                        poisoneffect = ((Potion) parseplayer.getitemfrominventory(inventorynumber)).getpotioneffect();
                        parseplayer.removeitemfrominventory(inventorynumber);
                        System.out.println("Du wirfst den Gifttrank auf " + parsemob.getArtikel("akkusativ", "bestimmt") + " " + parsemob.getmobname() + ". " + parsemob.getArtikel("nominativ", "bestimmt").substring(0, 1).toUpperCase() + parsemob.getArtikel("nominativ", "bestimmt").substring(1) + " " + parsemob.getmobname() + " ist nun vergiftet.");
                    }
                    else if(((Potion) parseplayer.getitemfrominventory(inventorynumber)).getpotiontype() == "Healing") {
                        parseplayer.drink(inventorynumber);
                    }
                }
                else if(parseplayer.getitemfrominventory(inventorynumber).getClass() == Consumable.class) {
                    parseplayer.consume(inventorynumber);
                }
                else {
                    System.out.println("Du kannst nur Tränke und Essen im Kampf verwenden");
                }
            }
            catch(Exception e) {
                System.out.println("Dies ist keine gültige Zahl für dein Inventar");
            }
        }
        else if(input[0].equals("attackiere")) {
            parseplayer.attack(parsemob, playerweapon);
            if(parsemob.getmobhealth() <= 0) {
                System.out.println("Du greifst " + parsemob.getArtikel("akkusativ", "bestimmt") + " " + parsemob.getmobname() + " mit " + playerweapon.getArtikel("dativ", "bestimmt") + " " + playerweapon.getitemname() + " an und machst " + parseplayer.getplayerdamage() + " Schaden.");
                parsemob.setmobstatus(false);
                System.out.println("Du hast den " + parsemob.getmobname() + " besiegt. ");
                parsemob.droploot(parseplayer, parsemob);
                parseplayer.addexperience(parsemob.getmobxp());
                combat = false;
                running = true;
            }
            else {
                System.out.println("Du greifst " + parsemob.getArtikel("akkusativ", "bestimmt") + " " + parsemob.getmobname() + " mit " + playerweapon.getArtikel("dativ", "bestimmt") + " " + playerweapon.getitemname() + " an und machst " + parseplayer.getplayerdamage() + " Schaden.");
                System.out.println(parsemob.getArtikel("nominativ", "bestimmt").substring(0, 1).toUpperCase() + parsemob.getArtikel("nominativ", "bestimmt").substring(1) + " " + parsemob.getmobname() + " hat nun " + parsemob.getmobhealth() + " Leben.");
            }
        }
        else if(input[0].equals("exit")) {
            System.out.println("Du verlässt den Kampf.");
            combat = false;
        }
    }
}
