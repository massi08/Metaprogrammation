package annuaire.metier;

import java.util.ArrayList;
import java.util.Iterator;

import annuaire.metier.Site;
import annuaire.persistance.SiteXMLDAO;

public class Annuaire {

    public Annuaire(String d) {
    }

    public void addSite(String desc, String url) {
        // ajout dans le support de persistance
        sites.add(s);
    }

    public void removeSite(String desc, String url) {
        // suppression dans la liste
        for (Iterator<Site> i = sites.iterator(); i.hasNext(); ) {
            Site temp = (Site) i.next();
            if (temp.equals(s)) {
                sites.remove(temp);
                removeSite(desc, url);
                return;
            }
        }
    }

    public String listSites() {
        String ls = new String();
        for (Iterator<Site> i = sites.iterator(); i.hasNext(); ) {
            Site s = (Site) i.next();
            ls += "Description :\t" + s.getDescription() + "\n";
            ls += "URL :\t" + s.getURL() + "\n";
        }
        return ls;
    }

    public void initSites() {
        sites.clear();
    	for(Site s:temp) {
    		sites.add(new Site(s.getDescription(), s.getURL()));
    	}
    }
}