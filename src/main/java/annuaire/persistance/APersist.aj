package annuaire.persistance;

import annuaire.persistance.*;
import annuaire.metier.Annuaire;
import annuaire.metier.Site;

import java.util.ArrayList;

public aspect APersist {
    SiteDAO dao;
    
    /* Inter-types */
    public ArrayList<Site> Annuaire.sites = new ArrayList<Site>();
    public ArrayList<Site> Annuaire.temp = new ArrayList<Site>();
    public Site Annuaire.s;

    pointcut PcAnnuaire(Object d): args(d) && call (Annuaire.new(String));
    after (Object d): PcAnnuaire(d) {
        try {
            System.out.println("[New]");
            dao = new SiteXMLDAO(d.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    pointcut PcAddSite(Annuaire annu, String desc, String url): args(desc, url) && call(void Annuaire.addSite(String, String)) && target(annu);
    before (Annuaire annu, String desc, String url): PcAddSite(annu, desc, url) {
        annu.s = new Site(desc, url);

        // ajout dans la liste
        try {
            System.out.println("[Add]");
            dao.addSite(annu.s);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    pointcut PcRemoveSite(Annuaire annu, String desc, String url): args(desc, url) && call(void Annuaire.removeSite(String, String)) && target(annu);
    before (Annuaire annu, String desc, String url) : PcRemoveSite(annu, desc, url) {
        annu.s = new Site(desc, url);

        try {
            System.out.println("[Remove]");
            dao.deleteSite(annu.s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    pointcut PcInitSites(Annuaire annu) : call(void Annuaire.initSites()) && target(annu);
    before (Annuaire annu) : PcInitSites(annu) {
        System.out.println("[Init]");
        annu.temp = dao.getAllSites();
    }

}