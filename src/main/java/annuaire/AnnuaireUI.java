package annuaire;

import annuaire.metier.Annuaire;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AnnuaireUI {
	Annuaire annuaire;

	public AnnuaireUI(Annuaire annuaire) {
		this.annuaire = annuaire;
	}

	public void run () {
		String description = null;
		String url = null;

		for (;;) {
			menu();
			int c = new String (readLine()).charAt(0) - '0';
			switch (c){
			case 1:
				try {
					System.out.print("Description : ");
					description = readLine();
					System.out.print("URL : ");
					url = readLine();
				} catch (Exception e) {
					e.printStackTrace();
				}

				annuaire.addSite(description, url);
				break;

			case 2:
				try {
					System.out.print("Description : ");
					description = readLine();
					System.out.print("URL : ");
					url = readLine();
				} catch (Exception e) {
					e.printStackTrace();
				}

				annuaire.removeSite(description, url);
				break;

			case 3:
				System.out.println("Sites actuellement connus :\n\n");
				System.out.println(annuaire.listSites());
				break;

			case 4:
				annuaire.initSites();
				System.out.println("Liste des sites reinitialisee par rapport au DAO.\n\n");
				break;

			case 5:
				return;
			}
		}
	}

	private void menu() {
		System.out.println("Menu\n\n");
		System.out.println("1)\tRajouter un site\n");
		System.out.println("2)\tSupprimer un site un site\n");
		System.out.println("3)\tLister les sites\n");
        System.out.println("4)\tReinitialiser les sites\n");
        System.out.println("5)\tQuitter\n");
	}

//	 ---------------------------------------------
//   Code trouve a l'URL : http://www.wellho.net/resources/ex.php4?item=j703/WellHouseInput.java
	public static String readLine() {
		BufferedReader standard = new BufferedReader(new InputStreamReader(System.in));
		try {
			String inline = standard.readLine();
			return inline;
		} catch (Exception e) {
			return ("data entry error");
		}
	}
}