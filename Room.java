import java.util.ArrayList;
public class Room {
    //Boolean zur �berpr�fung, ob sich eine Kiste im Raum befindet.
    private boolean haschest;
    
    //Boolean zur �berpr�fung, ob sich ein Mob im Raum befindet.
    private boolean hasmob;
    
    //ArrayList mit allen Richtungen, die aus einem Raum f�hren.
    private ArrayList<String> roomdirections;
    
    //Welche R�ume in den Himmelsrichtungen angrenzen.
    private ArrayList<Room> connectedrooms = new ArrayList<>();
    private Room connectedroomnorth;
    private Room connectedroomeast;
    private Room connectedroomsouth;
    private Room connectedroomwest;
    
    //Ein Truhenobjekt f�r den Raum.
    Chest chest1;
    
    Mob mob1;
    
    /**
     *  Konstruktor f�r die Klasse Raum mit dem Truhenobjekt als Argument.
     */    
    public Room(Chest parsechest, Mob parsemob) {
        if(parsechest != null) {
            haschest = true;
            chest1 = parsechest;
        }
        else {
            haschest = false;
        }
        if(parsemob != null) {
            hasmob = true;
            mob1 = parsemob;
        }
        else {
            hasmob = false;
        }
        roomdirections = new ArrayList<>();
    }
    
    /**
     * Methode pr�ft, ob sich in dem Raum eine Kiste befindet.
     */
    public boolean getchestinfo(Room parseroom) {
        if(parseroom.haschest == true) {
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * Methode gibt die Kiste des Raumes zur�ck.
     */
    public Chest getchest(Room parseroom) {
        return parseroom.chest1;
    }
    
    public boolean getmobinfo(Room parseroom) {
        if(parseroom.hasmob == true) {
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * Hier werden alle verbundenen R�ume definiert nach (Raum, Norden, Osten, S�den, Westen).
     */
    public void setconnectedrooms(Room parseroom, Room parseconnectedroomnorth, Room parseconnectedroomeast, Room parseconnectedroomsouth, Room parseconnectedroomwest) {
        parseroom.connectedroomnorth = parseconnectedroomnorth;
        parseroom.connectedroomeast = parseconnectedroomeast;
        parseroom.connectedroomsouth = parseconnectedroomsouth;
        parseroom.connectedroomwest = parseconnectedroomwest;
        if(parseroom.connectedroomnorth != null) {
            parseroom.roomdirections.add("Norden");
        }
        if(parseroom.connectedroomeast != null) {
            parseroom.roomdirections.add("Osten");
        }
        if(parseroom.connectedroomsouth != null) {
            parseroom.roomdirections.add("S�den");
        }
        if(parseroom.connectedroomwest != null) {
            parseroom.roomdirections.add("Westen");
        }
    }
    
    
    /**
     * Methode zur R�ckgabe von dem Raum in der Richtung des Arguments.
     */
    public Room getconnectedrooms(Room parseroom, String parsedirection) {
        if(parsedirection == "north") {
            return parseroom.connectedroomnorth;
        }
        else if(parsedirection == "east") {
            return parseroom.connectedroomeast;
        }
        else if(parsedirection == "south") {
            return parseroom.connectedroomsouth;
        }
        else if(parsedirection == "west") {
            return parseroom.connectedroomwest;
        }
        else {
            System.out.println("In dieser Richtung liegt keine T�r zu einem anderen Raum.");
            return null;
        }
    }
    
    /**
     * R�ckgabe von allen Richtungen als ArrayList. Zuvor definiert f�r jeden Raum in setconnectedrooms(...)
     */
    public ArrayList<String> getroomdirections(Room parseroom) {
        return parseroom.roomdirections;
    }
}
