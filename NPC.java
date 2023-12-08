/**
 * Die Klasse NPC kann andere Charaktere erstellen, mit denen der Spieler kommunizieren kann. 
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class NPC
{
    // Varibalben für NPC's
    protected String name;
    
    // Konstruktor der NPC-Klasse
    public NPC(String parsename)
    {
        name = parsename;
    }
    
    // Methode zum Begrüßen des Spielers
    public void speak() {
        System.out.println("Hallo! Ich bin " + name);
    }
    
    // Methode zum Ausgeben des Names des NPC's
    public String getNPCname() {
        return name;
    }
}
