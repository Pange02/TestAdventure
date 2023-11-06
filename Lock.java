
/**
 * Beschreiben Sie hier die Klasse Lock.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Lock
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private String name;
    private String direction;
    private Key key;
    private boolean locked;
    
    /**
     * Konstruktor für Objekte der Klasse Lock
     */
    public Lock(String parsename, Room parseroom, String parsedirection, Key parsekey)
    {
        direction = parsedirection;
        key = parsekey;
        locked = true;
        parseroom.setLock(this);
    }
    
    public void openLock(Key parsekey) {
        if(key == parsekey) {
            locked = false;
        }
    }
}
