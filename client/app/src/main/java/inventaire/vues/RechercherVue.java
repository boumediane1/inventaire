package inventaire.vues;

import inventaire.models.ProduitVM;
import inventaire.rmi.Inventaire;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.rmi.RemoteException;
import java.util.ArrayList;
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

      // Liste pour stocker les ensembles de résultats individuels
      List<Set<ProduitVM>> resultSets = new ArrayList<>();

      // Ajouter les résultats pour chaque critère rempli
      if (!nom.isEmpty()) {
        resultSets.add(inventaire.rechercherProduitsParNom(nom).stream()
          .map(produit -> new ProduitVM(produit.getId(), produit.getNom(), produit.getCategorie(), produit.getPrix(), produit.getQuantite()))
          .collect(Collectors.toSet()));
      }

      if (!categorie.isEmpty()) {
        resultSets.add(inventaire.rechercherProduitsParCategorie(categorie).stream()
          .map(produit -> new ProduitVM(produit.getId(), produit.getNom(), produit.getCategorie(), produit.getPrix(), produit.getQuantite()))
          .collect(Collectors.toSet()));
      }

      if (!minQuantiteText.isEmpty() || !maxQuantiteText.isEmpty()) {
        resultSets.add(inventaire.rechercherProduitsParQuantite(minQuantite, maxQuantite).stream()
          .map(produit -> new ProduitVM(produit.getId(), produit.getNom(), produit.getCategorie(), produit.getPrix(), produit.getQuantite()))
          .collect(Collectors.toSet()));
      }

      // Combiner les résultats : commencer avec tous les résultats du premier ensemble et conserver les correspondances des autres
      Set<ProduitVM> combinedResults;
      if (!resultSets.isEmpty()) {
        combinedResults = new HashSet<>(resultSets.get(0)); // Commencer avec le premier ensemble de résultats
        for (int i = 1; i < resultSets.size(); i++) {
          combinedResults.retainAll(resultSets.get(i)); // Conserver uniquement les correspondances
        }
      } else {
        // Aucun critère fourni ; récupérer tous les produits
        combinedResults = inventaire.listerProduits().stream()
        .map(produit -> new ProduitVM(produit.getId(), produit.getNom(), produit.getCategorie(), produit.getPrix(), produit.getQuantite()))
        .collect(Collectors.toSet());
      }

      produits.setAll(combinedResults);

    } catch (RemoteException | NumberFormatException e) {
      e.printStackTrace();
    }
  }
}
