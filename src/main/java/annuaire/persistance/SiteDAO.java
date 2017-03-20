package annuaire.persistance;

import annuaire.metier.Site;

import java.util.ArrayList;

public interface SiteDAO {
    public void addSite(Site item);
    public void deleteSite(Site item);
    public ArrayList<Site> getAllSites();
    public String getInfos();
}