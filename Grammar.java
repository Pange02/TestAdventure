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
    
    // +++++++++++ Adjektive +++++++++++
    // Maps für die Adjektive der Reforges
    private static HashMap<String,String> starkAdjektivMap = new HashMap<>();
    private static HashMap<String,String> solideAdjektivMap = new HashMap<>();
    private static HashMap<String,String> schnellAdjektivMap = new HashMap<>();
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
        
        // +++++++++++ Adjektive +++++++++++
        
        // Adjektiv: stark
        starkAdjektivMap.put("nominativ, maskulin", "starker");
        starkAdjektivMap.put("genitiv, maskulin", "starken");
        starkAdjektivMap.put("dativ, maskulin", "starken");
        starkAdjektivMap.put("akkusativ, maskulin", "starken");
        
        starkAdjektivMap.put("nominativ, feminin", "starke");
        starkAdjektivMap.put("genitiv, feminin", "starken");
        starkAdjektivMap.put("dativ, feminin", "starken");
        starkAdjektivMap.put("akkusativ, feminin", "starke");
        
        starkAdjektivMap.put("nominativ, neutrum", "starkes");
        starkAdjektivMap.put("genitiv, neutrum", "starken");
        starkAdjektivMap.put("dativ, neutrum", "starken");
        starkAdjektivMap.put("akkusativ, neutrum", "starkes");
        
        starkAdjektivMap.put("nominativ, plural", "starken");
        starkAdjektivMap.put("genitiv, plural", "starken");
        starkAdjektivMap.put("dativ, plural", "starken");
        starkAdjektivMap.put("akkusativ, plural", "starken");
        
        
        // Adjektiv: solide
        solideAdjektivMap.put("nominativ, maskulin", "solider");
        solideAdjektivMap.put("genitiv, maskulin", "soliden");
        solideAdjektivMap.put("dativ, maskulin", "soliden");
        solideAdjektivMap.put("akkusativ, maskulin", "soliden");
        
        solideAdjektivMap.put("nominativ, feminin", "solide");
        solideAdjektivMap.put("genitiv, feminin", "soliden");
        solideAdjektivMap.put("dativ, feminin", "soliden");
        solideAdjektivMap.put("akkusativ, feminin", "solide");
        
        solideAdjektivMap.put("nominativ, neutrum", "solides");
        solideAdjektivMap.put("genitiv, neutrum", "soliden");
        solideAdjektivMap.put("dativ, neutrum", "soliden");
        solideAdjektivMap.put("akkusativ, neutrum", "solides");
        
        solideAdjektivMap.put("nominativ, plural", "soliden");
        solideAdjektivMap.put("genitiv, plural", "soliden");
        solideAdjektivMap.put("dativ, plural", "soliden");
        solideAdjektivMap.put("akkusativ, plural", "soliden");
        
        
        // Adjektiv: schnell
        solideAdjektivMap.put("nominativ, maskulin", "schneller");
        solideAdjektivMap.put("genitiv, maskulin", "schnellen");
        solideAdjektivMap.put("dativ, maskulin", "schnellen");
        solideAdjektivMap.put("akkusativ, maskulin", "schnellen");
        
        solideAdjektivMap.put("nominativ, feminin", "schnelle");
        solideAdjektivMap.put("genitiv, feminin", "schnellen");
        solideAdjektivMap.put("dativ, feminin", "schnellen");
        solideAdjektivMap.put("akkusativ, feminin", "schnelle");
        
        solideAdjektivMap.put("nominativ, neutrum", "schnelles");
        solideAdjektivMap.put("genitiv, neutrum", "schnellen");
        solideAdjektivMap.put("dativ, neutrum", "schenllen");
        solideAdjektivMap.put("akkusativ, neutrum", "schnelles");
        
        solideAdjektivMap.put("nominativ, plural", "schnellen");
        solideAdjektivMap.put("genitiv, plural", "schnellen");
        solideAdjektivMap.put("dativ, plural", "schnellen");
        solideAdjektivMap.put("akkusativ, plural", "schnellen");
    }
    
    /**
     * Gibt zugehörig zu einem Geschlecht und dem Kasus ein bestimmten Artikel zurück.
     */
    public static String getArtikel(String kasus, String gender){
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
            return "kein Artikel in Grammar";
        }
    }
    
    /**
     * Gibt zugehörig zu einem Geschlecht und dem Kasus ein unbestimmten Artikel zurück.
     */
    public static String getUnArtikel(String kasus, String gender){
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
            return "kein Artikel in Grammar";
        }
    }
    
    /**
     * Gibt entsprechend des Geschlecht und des Kasus die richtige Form des Adjektives wieder.
     */
    public static String getAdjektiv(String adjektiv, String key){
        if (adjektiv.toLowerCase().equals("stark")){

            return starkAdjektivMap.get(key);
        }
        else if (adjektiv.toLowerCase().equals("schnell")){
            
            return schnellAdjektivMap.get(key);
        }
        else if (adjektiv.toLowerCase().equals("solide")){
            
            return solideAdjektivMap.get(key);
        }
        else {
            return "kein Adjektiv in Grammar";
        }
    }
}
