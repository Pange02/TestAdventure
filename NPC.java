/**
 * Bildet die Elternklasse der Klassen Merchant und Speaker. Jedem Individuum der Klasse kann ein Name zugeteilt werden, mit welchem er sich vorstellen kann und den er zur�ckgeben kann.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class NPC
{
    // Varibalben f�r NPC's
    protected String name;
    
    // Konstruktor der NPC-Klasse
    public NPC(String parsename)
    {
        name = parsename;
    }
    
    // Methode zum Begr��en des Spielers
    public void speak() {
        System.out.println("Hallo! Ich bin " + name);
    }
    
    // Methode zum Ausgeben des Names des NPC's
    public String getNPCname() {
        return name;
    }
}
