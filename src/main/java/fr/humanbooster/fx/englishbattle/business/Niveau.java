package fr.humanbooster.fx.englishbattle.business;
 
public class Niveau {

	// ----------------------------- Attributs ----------------------------------
    private Long id;
    private String nom;
    private static Long compteur = 0L;

    
	// ----------------------------- Constructeurs ------------------------------
    public Niveau(String nom) {
        super();
        this.id = ++compteur;
        this.nom = nom;
    }
    

	// ----------------------------- Set - Get ----------------------------------
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	
	// ----------------------------- hashCode -----------------------------------	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		return result;
	}


	// ------------------------------ equals ------------------------------------
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Niveau other = (Niveau) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}


	// ----------------------------- toString -----------------------------------
	@Override
	public String toString() {
		return "Niveau [id=" + id + ", nom=" + nom + "]";
	}

	
	
}