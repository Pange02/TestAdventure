
/**
 * Beschreiben Sie hier die Klasse Key.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Key extends Item
{
    // Konstruktor der Key-Klasse
    public Key(String parsename, String parsedescription, String parserarity, String parsegender)
    {
        super(parsename, parsedescription, parserarity, parsegender);
    }
    
    // Ausgeben des Namens des Schlüssels
    public String getname() {
        return name;
    }
}
