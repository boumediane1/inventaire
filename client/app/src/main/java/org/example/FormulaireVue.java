package org.example;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class FormulaireVue extends VBox {
  private TextField tfNom;
  private TextField tfCategorie;
  private TextField tfPrix;
  private TextField tfQuantite;
  private Button btnAjouterProduit;

  public FormulaireVue() {
    tfNom = new TextField();
    tfCategorie = new TextField();
    tfPrix = new TextField();
    tfQuantite = new TextField();
    btnAjouterProduit = new Button("Ajouter produit");

    this.setPrefWidth(300);
    this.setPadding(new Insets(24));
    this.setSpacing(8);

    this.getChildren()
        .addAll(
            new Label("Nom"),
            tfNom,
            new Label("Catégorie"),
            tfCategorie,
            new Label("Prix"),
            tfPrix,
            new Label("Quantité"),
            tfQuantite,
            btnAjouterProduit);

    this.btnAjouterProduit.setOnAction(event -> {});
  }

  public TextField getTfNom() {
    return tfNom;
  }

  public TextField getTfCategorie() {
    return tfCategorie;
  }

  public TextField getTfPrix() {
    return tfPrix;
  }

  public TextField getTfQuantite() {
    return tfQuantite;
  }

  public Button getBtnAjouterProduit() {
    return btnAjouterProduit;
  }
}
