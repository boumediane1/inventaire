package org.example;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProduitView {
  private StringProperty nom;
  private StringProperty categorie;
  private DoubleProperty prix;
  private IntegerProperty quantite;

  public ProduitView(String nom, String categorie, double prix, int quantite) {
    this.nom = new SimpleStringProperty(this, "nom", nom);
    this.categorie = new SimpleStringProperty(this, "categorie", categorie);
    this.prix = new SimpleDoubleProperty(this, "prix", prix);
    this.quantite = new SimpleIntegerProperty(this, "quantite", quantite);
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
