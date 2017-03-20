import annuaire.metier.Annuaire;
import annuaire.AnnuaireUI;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Cr�ation et initialisation de l'annuaire
		Annuaire annuaire = new Annuaire("annuaire.xml");
		annuaire.initSites();

		//Cr�ation et lancement de l'interface
		AnnuaireUI ui = new AnnuaireUI(annuaire);
		ui.run();
	}

}
