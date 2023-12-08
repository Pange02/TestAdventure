import java.util.ArrayList;
/**
 * Die Klasse “Room” gestaltet die Räume so, dass der Spieler sich nach Truhen umschauen
 * kann und Türen in den vier Himmelsrichtungen entdecken kann, außerdem verortet sie in
 * die Räume zugewiesene Mobs.
 */

public class Room {
    //Boolean zur Überprüfung, ob sich eine Kiste im Raum befindet.
    private boolean hasChest;
    
    //Boolean zur Überprüfung, ob sich ein Mob im Raum befindet.
    private boolean hasMob;
    
    //Boolean zur Überprüfung, ob sich ein NPC im Raum befindet.
    private boolean hasNPC;
    
    private boolean hasLock;
    
    //ArrayList mit allen Richtungen, die aus einem Raum führen.
    private ArrayList<String> roomDirections;
    
    //Welche Räume in den Himmelsrichtungen angrenzen.
    private ArrayList<Room> connectedRooms = new ArrayList<>();
    private Room connectedRoomNorth;
    private Room connectedRoomEast;
    private Room connectedRoomSouth;
    private Room connectedRoomWest;
    
    //Ein Truhenobjekt für den Raum.
    Chest chest1;
    
    Mob mob1;
    
    NPC npc1;
    
    Lock lock;
    
    /**
     *  Konstruktor für die Klasse Raum mit dem Truhenobjekt als Argument.
     */    
    public Room(int RoomNumber, Chest parseChest, Mob parseMob, NPC parseNPC) {
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
        if(parseNPC != null) {
            hasNPC = true;
            npc1 = parseNPC;
        }
        else {
            hasNPC = false;
        }
        roomDirections = new ArrayList<>();
    }
    
    /**
     * Methode prüft, ob sich in dem Raum eine Kiste befindet.
     */
    public boolean getChestInfo() {
        return hasChest;
    }
    
    /**
     * Methode gibt die Kiste des Raumes zurück.
     */
    public Chest getChest() {
        return chest1;
    }
    
    public boolean getMobInfo() {
        return hasMob;
    }
    
    public Mob getRoomMob() {
        return mob1;
    }
    
    public boolean getNPCInfo() {
        return hasNPC;
    }
    
    public NPC getNPC() {
        return npc1;
    }
    
    public void setLock(Lock parseLock) {
        lock = parseLock;
    }
    
    public Lock getLock(String parseLockDirection) {
        if(lock != null && parseLockDirection == lock.getDirection()) {
            return lock;
        }
        else {
            return null;
        }
    }
    
    public String getLockDirection(Lock parseLock) {
        return parseLock.getDirection();
    }
    
    /**
     * Hier werden alle verbundenen Räume definiert nach (Raum, Norden, Osten, Süden, Westen).
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
            roomDirections.add("Süden");
        }
        if(connectedRoomWest != null) {
            roomDirections.add("Westen");
        }
    }
    
    
    /**
     * Methode zur Rückgabe von dem Raum in der Richtung des Arguments.
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
            System.out.println("In dieser Richtung liegt keine Tür zu einem anderen Raum.");
            return null;
        }
    }
    
    /**
     * Rückgabe von allen Richtungen als ArrayList. Zuvor definiert für jeden Raum in setconnectedrooms(...)
     */
    public ArrayList<String> getRoomDirections() {
        return roomDirections;
    }
}
