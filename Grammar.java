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
    
    private static HashMap<String, String> nUnArtikelMap = new HashMap<>();
    private static HashMap<String, String> gUnArtikelMap = new HashMap<>();
    private static HashMap<String, String> dUnArtikelMap = new HashMap<>();
    private static HashMap<String, String> aUnArtikelMap = new HashMap<>();
    
    /**
     * Hinterlegt zu den Geschlechtern und zu jedem Kasus einen bestimmte/unbestimmte Artikel.
     */
    public Grammar()
    {
        //bestimmte Artikel
        //Nominativ
        nArtikelMap.put("maskulin", "der");
        nArtikelMap.put("feminin","die");
        nArtikelMap.put("neutrum", "das");
        nArtikelMap.put("plural", "die");
        
        //Genetiv
        gArtikelMap.put("maskulin", "des");
        gArtikelMap.put("feminin","der");
        gArtikelMap.put("neutrum", "des");
        gArtikelMap.put("plural", "der");
        
        //Dativ
        dArtikelMap.put("maskulin", "dem");
        dArtikelMap.put("feminin","der");
        dArtikelMap.put("neutrum", "dem");
        dArtikelMap.put("plural", "den");
        
        //Akkusativ
        aArtikelMap.put("maskulin", "den");
        aArtikelMap.put("feminin","die");
        aArtikelMap.put("neutrum", "das");
        aArtikelMap.put("plural", "die");

        
        //unbestimmte Artikel
        //Nominativ
        nUnArtikelMap.put("maskulin", "ein");
        nUnArtikelMap.put("feminin", "eine");
        nUnArtikelMap.put("neutrum", "ein");
        
        //Genitiv
        gUnArtikelMap.put("maskulin", "eines");
        gUnArtikelMap.put("feminin", "einer");
        gUnArtikelMap.put("neutrum", "eines");
        
        //Dativ
        dUnArtikelMap.put("maskulin", "einem");
        dUnArtikelMap.put("feminin", "einer");
        dUnArtikelMap.put("neutrum", "einem");
        
        //Akkusativ
        aUnArtikelMap.put("maskulin", "einen");
        aUnArtikelMap.put("feminin", "eine");
        aUnArtikelMap.put("neutrum", "ein");
    }
    
    /**
     * Gibt zugehörig zu einem Geschlecht und dem Kasus ein bestimmten Artikel zurück.
     */
    public String getArtikel(String kasus, String gender){
        if (kasus.toLowerCase().equals("nominativ")){
            
            return nArtikelMap.get(gender);
        }
        else if (kasus.toLowerCase().equals("genetiv")){
            
            return gArtikelMap.get(gender);
        }
        else if (kasus.toLowerCase().equals("dativ")){
            
            return dArtikelMap.get(gender);
        }
        else if (kasus.toLowerCase().equals("akkusativ")){
            
            return aArtikelMap.get(gender);
        }
        else {
            return null;
        }
    }
    
    /**
     * Gibt zugehörig zu einem Geschlecht und dem Kasus ein unbestimmten Artikel zurück.
     */
    public String getUnArtikel(String kasus, String gender){
        if (kasus.toLowerCase().equals("nominativ")){
            
            return nUnArtikelMap.get(gender);
        }
        else if (kasus.toLowerCase().equals("genetiv")){
            
            return gUnArtikelMap.get(gender);
        }
        else if (kasus.toLowerCase().equals("dativ")){
            
            return dUnArtikelMap.get(gender);
        }
        else if (kasus.toLowerCase().equals("akkusativ")){
            
            return aUnArtikelMap.get(gender);
        }
        else {
            return null;
        }
    }
}
