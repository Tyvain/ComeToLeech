package ComeToLeech;

import ComeToLeech.model.Annonce;

public class AnnonceCleaner {
    
    public Annonce cleanAnnonce (Annonce a){
        a.titre = cleanAll(a.titre);
        a.texte = cleanAll(a.texte);
        return a;
    }
        
    private String cleanAll (String s) {
        s = s.replaceAll("[\r\n]+", "\n");
        s = s.replaceAll("\\s+", " ");
        s = s.replaceAll("<br> <br>", "<br>");
        s = s.replaceAll("<br> <br>", "<br>");
        s = s.replaceAll("<i>AM-.* </i>","");
        s = s.replace(",","");
       return s;
     }  
}
