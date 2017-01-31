package ComeToLeech.sites;

import java.net.URL;
import java.util.stream.Stream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ComeToLeech.model.Annonce;

public abstract class AbstractSite {

    protected abstract String getTitreSelector();

    protected abstract String getTexteSelector();

    protected abstract String getLinkSelector();

    protected abstract String getImageSelector();

    public Stream<Annonce> getAnnonces(String rootUrl, String rubUrl, String rub) {
        // liste des docs (cas des pages contenant les liens)  
        System.out.println("       from " + rootUrl + rubUrl + " into " + rub + "...");
        Document doc = getDocumentFromUrl(rootUrl + rubUrl);

        // liste des elements (cad liens des annonces)
        Elements elemz = doc.select(getLinkSelector());

        // listes des ids des annones mettre map pour tout
        Stream<String> idz = elemz.stream().map(e -> e.attr("href"));

        // liste des urls
        Stream<String> urlz = idz.map(s -> rootUrl + s);

        // liste des annonces        
        Stream<Annonce> ret = urlz.map(u -> getAnnonceFromUrl(u, rootUrl, rub));
        return ret;
    }

    private Annonce getAnnonceFromUrl(String url, String rootUrl, String rub) {
        Document doc = getDocumentFromUrl(url);
        return getAnnonce(doc, url, rootUrl, rub);
    }

    private Annonce getAnnonce(Document doc, String url, String rootUrl, String rub) {
        Annonce ret = new Annonce();
        ret.titre = doc.select(getTitreSelector()).first().text();
        ret.texte = doc.select(getTexteSelector()).first().html();
        ret.category = rub;
        ret.url = url;
        ret.imgs = getImagesFromDoc(doc, rootUrl);
        ret.isCommerciale = ret.texte.contains("Annonce Commerciale");
        return ret;
    }

    private static Document getDocumentFromUrl(String url) {
        try {
            // random pause
            long pause = (long)(Math.random() * 5000);
            System.out.print("       pausing " + pause / 1000 + " s...");
            Thread.sleep(pause);
            System.out.println(" end pause");

            return Jsoup.parse(new URL(url).openStream(), "UTF-8", url);
        } catch (Exception e) {
            System.err.println("getDocumentFromUrl err: " + e);
        }
        return null;
    }

    private String[] getImagesFromDoc(Document doc, String rootUrl) {
        Elements els = doc.select(getImageSelector());
        Element el = els.first();
        Stream<String> imgz = Stream.empty();
        if (el != null) {
            imgz = els.stream().map(e -> rootUrl + e.attr("data-src").replaceAll("_thumbs/", ""));
        }

        String[] stringArray = imgz.toArray(size -> new String[size]);
        return stringArray;
    }
}
