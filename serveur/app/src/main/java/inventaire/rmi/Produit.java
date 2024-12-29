package inventaire.rmi;

import java.io.Serializable;

public class Produit implements Serializable {
  private int id;
  private String nom;
  private String categorie;
  private double prix;
  private int quantite;

  public Produit(int id, String nom, String categorie, double prix, int quantite) {
    this(nom, categorie, prix, quantite);
    this.id = id;
  }

  public Produit(String nom, String categorie, double prix, int quantite) {
    this.nom = nom;
    this.categorie = categorie;
    this.prix = prix;
    this.quantite = quantite;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String name) {
    this.nom = name;
  }

  public String getCategorie() {
    return categorie;
  }

  public void setCategorie(String category) {
    this.categorie = category;
  }

  public double getPrix() {
    return prix;
  }

  public void setPrix(double price) {
    this.prix = price;
  }

  public int getQuantite() {
    return quantite;
  }

  public void setQuantite(int quantity) {
    this.quantite = quantity;
  }

  @Override
  public String toString() {
    return "Product{id="
        + id
        + ", name="
        + nom
        + ", category="
        + categorie
        + ", price="
        + prix
        + ", quantity="
        + quantite
        + "}";
  }
}
