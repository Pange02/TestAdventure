import java.util.ArrayList;
import java.util.HashMap;
/**
 * Beschreiben Sie hier die Klasse Stage.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Stage
{
    protected Room playerstartroom;
    protected ArrayList<Room> roomlist = new ArrayList<>(); 
    /**
     * Konstruktor für Objekte der Klasse Stage
     */
    public Stage()
    {
    }

    /**
     * Ein Beispiel einer Methode - ersetzen Sie diesen Kommentar mit Ihrem eigenen
     * 
     * @param  y    ein Beispielparameter für eine Methode
     * @return        die Summe aus x und y
     */
    public Room getstartroom()
    {
        // tragen Sie hier den Code ein
        return playerstartroom;
    }
    
    public ArrayList<Room> getroomlist() {
        return roomlist;
    }
}
