package fr.humanbooster.fx.englishbattle.business;
 
public class Ville {
 
	// ----------------------------- Attributs ----------------------------------
    private Long id;
    private String nom;
    
    
    // ----------------------------- Constructeurs ------------------------------
    public Ville(String nom) {
        super();
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
 
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ville other = (Ville) obj;
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
        return "Ville [id=" + id + ", nom=" + nom + "]";
    }
 
     
}