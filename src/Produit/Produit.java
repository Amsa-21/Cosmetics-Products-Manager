package Produit;

public class Produit {
	private String nom;
	private String description;
	private int prix;
	private int quantite;
	private String categorie;
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPrix() {
		return prix;
	}
	public void setPrix(int prix) {
		if(prix >= 0)
			this.prix = prix;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		if(quantite >= 0)
			this.quantite = quantite;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	public Produit(String nom, String description, int prix, int quantite, String categorie) {
		this.nom = nom;
		this.description = description;
		this.setPrix(prix);
		this.setQuantite(quantite);
		this.categorie = categorie;
	}
	public Produit(String nom, String description, String categorie) {
		this.nom = nom;
		this.description = description;
		this.categorie = categorie;
	}
	public int prixTotal() {
		return prix*quantite;
		
	}
}
