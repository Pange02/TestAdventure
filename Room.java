public class Room {
    boolean haschest;
    boolean hasplayer;
    Chest chest1;
    public Room(boolean parsehaschest) {
        haschest = parsehaschest;
        hasplayer = true;
        if(haschest == true) {
           chest1 = new Chest(); 
        }
    }
    
    public static boolean getplayerinfo(Room parseroom) {
        if(parseroom.hasplayer == true) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public static boolean getchestinfo(Room parseroom) {
        if(parseroom.haschest == true) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public static Chest getchest(Room parseroom) {
        return parseroom.chest1;
    }
}
