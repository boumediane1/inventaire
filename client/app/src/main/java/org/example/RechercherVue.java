package org.example;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class RechercherVue extends HBox {
  private TextField tfNom;
  private TextField tfCategorie;
  private TextField tfPrix;
  private TextField tfQuantite;
  private Button btnRechercher;

  public RechercherVue() {
    super(10);
    this.setStyle("-fx-background-color: #336699; -fx-padding: 10");

    tfNom = new TextField();
    tfNom.setPromptText("Nom");

    tfCategorie = new TextField();
    tfCategorie.setPromptText("Catégorie");

    tfPrix = new TextField();
    tfPrix.setPromptText("Prix");

    tfQuantite = new TextField();
    tfQuantite.setPromptText("Quantité");

    btnRechercher = new Button("Rechercher");
    this.getChildren().addAll(tfNom, tfCategorie, tfPrix, tfQuantite, btnRechercher);

    HBox.setHgrow(tfNom, Priority.ALWAYS);
    HBox.setHgrow(tfCategorie, Priority.ALWAYS);
    HBox.setHgrow(tfPrix, Priority.ALWAYS);
    HBox.setHgrow(tfQuantite, Priority.ALWAYS);
  }
}
