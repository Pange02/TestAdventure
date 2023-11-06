
/**
 * Beschreiben Sie hier die Klasse Key.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Key extends Item
{
    
    /**
     * Konstruktor für Objekte der Klasse Key
     */
    public Key(String parsename, String parsedescription, String parserarity, String parsegender)
    {
        super(parsename, parsedescription, parserarity, parsegender);
    }
    
    public String getname() {
        return name;
    }
}
