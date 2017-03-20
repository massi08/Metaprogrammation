package annuaire.metier;

public class Site {

	String description = null;
	String url = null;

	public Site (String d, String u) {
		this.description = new String (d);
		this.url = new String (u);
 	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String d) {
		this.description = d;
	}

	public String getURL() {
		return this.url;
	}

	public void setURL(String u) {
		this.url = u;
	}

	//Pour la comparaison des champs
    public boolean equals(Site other) {
        return (this.description.equals(other.getDescription())) && (this.url.equals(other.getURL()));
    }
}