import java.util.HashMap;
/**
 * Dient zur korrekten Anwendung von Grammatik
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Grammar
{
    // Erstellt für bestimmte und unbestimmte Artikel und für den Kasus je eine HashMap
    private static HashMap<String, String> nArtikelMap = new HashMap<>();
    private static HashMap<String, String> gArtikelMap = new HashMap<>();
    private static HashMap<String, String> dArtikelMap = new HashMap<>();
    private static HashMap<String, String> aArtikelMap = new HashMap<>();
    private static HashMap<String, String> uArtikelMap = new HashMap<>();
    private static HashMap<String, String> kasusMap = new HashMap<>();
    /**
     * Hinterlegt zu den Geschlechtern bestimmte/unbestimmte Artikel.
     */
    public Grammar()
    {
        //bestimmte Artikel
        nArtikelMap.put("maskulin", "der");
        nArtikelMap.put("feminin","die");
        nArtikelMap.put("neutrum", "das");
        nArtikelMap.put("plural", "die");
        
        gArtikelMap.put("maskulin", "des");
        gArtikelMap.put("feminin","der");
        gArtikelMap.put("neutrum", "des");
        gArtikelMap.put("plural", "der");
        
        dArtikelMap.put("maskulin", "dem");
        dArtikelMap.put("feminin","der");
        dArtikelMap.put("neutrum", "dem");
        dArtikelMap.put("plural", "den");
        
        aArtikelMap.put("maskulin", "den");
        aArtikelMap.put("feminin","die");
        aArtikelMap.put("neutrum", "das");
        aArtikelMap.put("plural", "die");

        
        //unbestimmte Artikel
        uArtikelMap.put("feminin", "eine");
        uArtikelMap.put("maskulin", "ein");
        uArtikelMap.put("neutrum", "ein");
    }
    
    /**
     * Gibt zugehörig zu einem Geschlecht ein bestimmten Artikel zurück.
     */
    public String getNominativArtikel(String key){
        return nArtikelMap.get(key);
    }
    
    /**
     * Gibt zugehörig zu einem Geschlecht ein unbestimmten Artikel zurück.
     */
    public String getUArtikel(String key){
        return uArtikelMap.get(key);
    }
}
