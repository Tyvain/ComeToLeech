package ComeToLeech;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import ComeToLeech.model.Annonce;
import ComeToLeech.model.Source;

import com.esotericsoftware.yamlbeans.YamlReader;

public class App {
    public static void main(String[] args) throws IOException {
        System.out.println("Start...");
        
        System.out.println("- create file");
        String csvFile = "importAds.csv";
        FileWriter writer = new FileWriter(csvFile);
        CSVUtils.writeLine(writer, Arrays.asList("user_name", "user_email", "title", "description", "date", "category", "location", "price", "address", "phone", "website", "image_1", "image_2", "image_3", "image_4"));

        System.out.println("- get ads");
        App.getSourceStream().flatMap(s -> getAnnonceFromSource(s)).map(a -> new AnnonceCleaner().cleanAnnonce(a)).forEach(a -> CSVUtils.writeAnnonce(writer, a));
                
        System.out.println("close file");
        writer.flush();
        writer.close();
        System.out.println("...finished!");
		
    }

    public static Stream<Annonce> getAnnonceFromSource(Source source) {
        return source.rubriques.stream().flatMap(r -> r.subUrls.stream().flatMap(u -> App.getAnnonce(source.rootUrl, u, r.category.libelle)));
    }

    public static Stream<Annonce> getAnnonce(String rootUrl, String url, String rub) {
        return new GenericSite().getAnnonces(rootUrl, url, rub);
    }

    public static Stream<Source> getSourceStream() {
        Stream<Source> ret = null;
        try {
            YamlReader reader = new YamlReader(new FileReader("sources.yml"));
            @SuppressWarnings("unchecked")
            ArrayList<Source> contact = (ArrayList<Source>)reader.read();
            ret = contact.stream();
        } catch (Exception e) {
            System.err.println("Ooops!! " + e);
        }
        return ret;
    }
}
