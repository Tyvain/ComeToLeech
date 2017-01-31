package ComeToLeech.sites;


public class GenericSite extends AbstractSite {

    @Override
    protected String getImageSelector() {
        return "#gallery > ul > li > a > img";
    }

    @Override
    protected String getLinkSelector() {
        return "#div_centre_0 > span > table";
    }

    @Override
    protected String getTitreSelector() {
        return "#div_centre_0 > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(1) > td > table > tbody > tr > td:nth-child(3) > b";
    }

    @Override
    protected String getTexteSelector() {
        return "#div_centre_0 > table > tbody > tr > td:nth-child(2) > table > tbody > tr:nth-child(3) > td > table > tbody > tr > td.andmm > div";
    }

}
