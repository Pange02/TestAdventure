import java.util.ArrayList;
import java.util.HashMap;
/**
 * Die Klasse Stage wird als Elternklasse f�r die verschiedenen Klassen der Stages eingesetzt, hierbei wird eine Methode �getstartroom()�
 * eingef�hrt, welche den Startraum der entsprechenden Stage wiedergibt. Die Methode �getroomlist()� kann eine ArrayList der R�ume zur�ckgeben, welche in der jeweiligen Stage implementiert wurden.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Stage
{
    protected Room playerstartroom;
    protected ArrayList<Room> roomlist = new ArrayList<>(); 
    /**
     * Konstruktor f�r Objekte der Klasse Stage
     */
    public Stage()
    {
    }

    /**
     * Ein Beispiel einer Methode - ersetzen Sie diesen Kommentar mit Ihrem eigenen
     * 
     * @param  y    ein Beispielparameter f�r eine Methode
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
