
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
    private String gender;
    
    /**
     * Konstruktor für Objekte der Klasse Lock
     */
    public Lock(String parsename, Room parseroom, String parsedirection, Key parsekey, String parsegender)
    {
        name = parsename;
        direction = parsedirection;
        key = parsekey;
        gender = parsegender;
        parseroom.setLock(this);
    }
    
    
    public String getname() {
        return name;
    }
    
    public void openLock(Room parseroom, Key parsekey) {
        if(key == parsekey) {
            parseroom.setLock(null);
            System.out.println("Der Schlüssel passt! Das Schloss öffnet sich und gibt den Weg frei.");
        }
        else {
            System.out.println("Der Schlüssek scheint hier nicht zu passen.");
        }
    }
    
    public String getDirection() {
        return direction;
    }
    
    /**
     * Gibt zu einem Item und einem Kasus einen bestimmten oder unbestimmten Artikel aus.
     */
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
