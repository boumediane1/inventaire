package org.example;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class ProduitForm extends VBox {
    private TextField tfNom;
    private TextField tfCategorie;
    private TextField tfPrix;
    private TextField tfQuantite;
    private Button btnAjouterProduit;

  public ProduitForm() {
    tfNom = new TextField(); 
    tfCategorie = new TextField(); 
    tfPrix = new TextField(); 
    tfQuantite = new TextField(); 
    btnAjouterProduit = new Button("Ajouter produit"); 

    this.getChildren().addAll(tfNom, tfCategorie, tfPrix, tfQuantite, btnAjouterProduit);
  }
}
