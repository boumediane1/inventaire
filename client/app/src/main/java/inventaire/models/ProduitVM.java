package inventaire.models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 * Classe ProduitVM pour représenter un produit.
 * Permet de gérer les propriétés du produit et de les lier à l'interface utilisateur JavaFX.
 */

public class ProduitVM {
  private IntegerProperty id;
  private StringProperty nom;
  private StringProperty categorie;
  private DoubleProperty prix;
  private IntegerProperty quantite;

  public ProduitVM(int id, String nom, String categorie, double prix, int quantite) {
    this.id = new SimpleIntegerProperty(this, "id", id);
    this.nom = new SimpleStringProperty(this, "nom", nom);
    this.categorie = new SimpleStringProperty(this, "categorie", categorie);
    this.prix = new SimpleDoubleProperty(this, "prix", prix);
    this.quantite = new SimpleIntegerProperty(this, "quantite", quantite);
  }

  public IntegerProperty getId() {
    return id;
  }

  public void setId(IntegerProperty id) {
    this.id = id;
  }

  public StringProperty getNom() {
    return nom;
  }

  public void setNom(StringProperty nom) {
    this.nom = nom;
  }

  public StringProperty getCategorie() {
    return categorie;
  }

  public void setCategorie(StringProperty categorie) {
    this.categorie = categorie;
  }

  public DoubleProperty getPrix() {
    return prix;
  }

  public void setPrix(DoubleProperty prix) {
    this.prix = prix;
  }

  public IntegerProperty getQuantite() {
    return quantite;
  }

  public void setQuantite(IntegerProperty quantite) {
    this.quantite = quantite;
  }
}
