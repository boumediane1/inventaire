package inventaire.vues;

import inventaire.models.ProduitVM;
import inventaire.rmi.Inventaire;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RechercherVue extends HBox {
  private TextField tfNom;
  private TextField tfCategorie;
  private TextField tfMinQuantite;
  private TextField tfMaxQuantite;
  private Button btnRechercher;
  private Inventaire inventaire;
  private ObservableList<ProduitVM> produits;

  public RechercherVue(Inventaire inventaire, ObservableList<ProduitVM> produits) {
    super(10);
    this.inventaire = inventaire;
    this.produits = produits;

    this.setStyle("-fx-background-color: #336699; -fx-padding: 10");

    tfNom = new TextField();
    tfNom.setPromptText("Nom");

    tfCategorie = new TextField();
    tfCategorie.setPromptText("Catégorie");

    tfMinQuantite = new TextField();
    tfMinQuantite.setPromptText("Quantité min");

    tfMaxQuantite = new TextField();
    tfMaxQuantite.setPromptText("Quantité max");

    btnRechercher = new Button("Rechercher");
    this.getChildren().addAll(tfNom, tfCategorie, tfMinQuantite, tfMaxQuantite, btnRechercher);

    HBox.setHgrow(tfNom, Priority.ALWAYS);
    HBox.setHgrow(tfCategorie, Priority.ALWAYS);
    HBox.setHgrow(tfMinQuantite, Priority.ALWAYS);
    HBox.setHgrow(tfMaxQuantite, Priority.ALWAYS);

    // Attacher un écouteur d'événement au bouton
    btnRechercher.setOnAction(event -> rechercherProduits());
  }

  private void rechercherProduits() {
    String nom = tfNom.getText().trim();
    String categorie = tfCategorie.getText().trim();
    String minQuantiteText = tfMinQuantite.getText().trim();
    String maxQuantiteText = tfMaxQuantite.getText().trim();

    try {
      int minQuantite = minQuantiteText.isEmpty() ? Integer.MIN_VALUE : Integer.parseInt(minQuantiteText);
      int maxQuantite = maxQuantiteText.isEmpty() ? Integer.MAX_VALUE : Integer.parseInt(maxQuantiteText);

      // Utiliser des ensembles pour combiner les résultats de manière correcte
      Set<ProduitVM> resultSet = new HashSet<>();

      if (!nom.isEmpty()) {
        resultSet.addAll(inventaire.rechercherProduitsParNom(nom).stream()
            .map(produit -> new ProduitVM(produit.getId(), produit.getNom(), produit.getCategorie(), produit.getPrix(), produit.getQuantite()))
            .collect(Collectors.toSet()));
      }

      if (!categorie.isEmpty()) {
        Set<ProduitVM> categorySet = inventaire.rechercherProduitsParCategorie(categorie).stream()
            .map(produit -> new ProduitVM(produit.getId(), produit.getNom(), produit.getCategorie(), produit.getPrix(), produit.getQuantite()))
            .collect(Collectors.toSet());

        if (!resultSet.isEmpty()) {
          resultSet.retainAll(categorySet);
        } else {
          resultSet.addAll(categorySet);
        }
      }

      Set<ProduitVM> quantitySet = inventaire.rechercherProduitsParQuantite(minQuantite, maxQuantite).stream()
          .map(produit -> new ProduitVM(produit.getId(), produit.getNom(), produit.getCategorie(), produit.getPrix(), produit.getQuantite()))
          .collect(Collectors.toSet());

      if (!resultSet.isEmpty()) {
        resultSet.retainAll(quantitySet);
      } else {
        resultSet.addAll(quantitySet);
      }

      produits.setAll(resultSet);

    } catch (RemoteException | NumberFormatException e) {
      e.printStackTrace();
    }
  }
}
