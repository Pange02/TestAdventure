import java.util.ArrayList;
public class Room {
    //Boolean zur �berpr�fung, ob sich eine Kiste im Raum befindet.
    private boolean hasChest;
    
    //Boolean zur �berpr�fung, ob sich ein Mob im Raum befindet.
    private boolean hasMob;
    
    //ArrayList mit allen Richtungen, die aus einem Raum f�hren.
    private ArrayList<String> roomDirections;
    
    //Welche R�ume in den Himmelsrichtungen angrenzen.
    private ArrayList<Room> connectedRooms = new ArrayList<>();
    private Room connectedRoomNorth;
    private Room connectedRoomEast;
    private Room connectedRoomSouth;
    private Room connectedRoomWest;
    
    //Ein Truhenobjekt f�r den Raum.
    Chest chest1;
    
    Mob mob1;
    
    /**
     *  Konstruktor f�r die Klasse Raum mit dem Truhenobjekt als Argument.
     */    
    public Room(Chest parseChest, Mob parseMob) {
        if(parseChest != null) {
            hasChest = true;
            chest1 = parseChest;
        }
        else {
            hasChest = false;
        }
        if(parseMob != null) {
            hasMob = true;
            mob1 = parseMob;
        }
        else {
            hasMob = false;
        }
        roomDirections = new ArrayList<>();
    }
    
    /**
     * Methode pr�ft, ob sich in dem Raum eine Kiste befindet.
     */
    public boolean getChestInfo() {
        if(hasChest == true) {
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * Methode gibt die Kiste des Raumes zur�ck.
     */
    public Chest getChest() {
        return chest1;
    }
    
    public boolean getMobInfo() {
        if(hasMob == true) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public Mob getRoomMob() {
        return mob1;
    }
    
    /**
     * Hier werden alle verbundenen R�ume definiert nach (Raum, Norden, Osten, S�den, Westen).
     */
    public void setConnectedRooms(Room parseConnectedRoomNorth, Room parseConnectedRoomEast, Room parseConnectedRoomSouth, Room parseConnectedRoomWest) {
        connectedRoomNorth = parseConnectedRoomNorth;
        connectedRoomEast = parseConnectedRoomEast;
        connectedRoomSouth = parseConnectedRoomSouth;
        connectedRoomWest = parseConnectedRoomWest;
        if(connectedRoomNorth != null) {
            roomDirections.add("Norden");
        }
        if(connectedRoomEast != null) {
            roomDirections.add("Osten");
        }
        if(connectedRoomSouth != null) {
            roomDirections.add("S�den");
        }
        if(connectedRoomWest != null) {
            roomDirections.add("Westen");
        }
    }
    
    
    /**
     * Methode zur R�ckgabe von dem Raum in der Richtung des Arguments.
     */
    public Room getConnectedRooms(String parseDirection) {
        if(parseDirection == "north") {
            return connectedRoomNorth;
        }
        else if(parseDirection == "east") {
            return connectedRoomEast;
        }
        else if(parseDirection == "south") {
            return connectedRoomSouth;
        }
        else if(parseDirection == "west") {
            return connectedRoomWest;
        }
        else {
            System.out.println("In dieser Richtung liegt keine T�r zu einem anderen Raum.");
            return null;
        }
    }
    
    /**
     * R�ckgabe von allen Richtungen als ArrayList. Zuvor definiert f�r jeden Raum in setconnectedrooms(...)
     */
    public ArrayList<String> getRoomDirections() {
        return roomDirections;
    }
}
