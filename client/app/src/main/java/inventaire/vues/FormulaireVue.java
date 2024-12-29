package inventaire.vues;

import java.rmi.RemoteException;

import inventaire.rmi.Inventaire;
import inventaire.rmi.Produit;
import inventaire.models.ProduitVM;
import inventaire.util.Util;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class FormulaireVue extends VBox {
  // Champs pour saisir les informations du produit
  private TextField tfNom;
  private TextField tfCategorie;
  private TextField tfPrix;
  private TextField tfQuantite;

  // Bouton pour ajouter un produit
  private Button btnAjouterProduit;

  /**
   * Constructeur pour créer le formulaire de saisie des produits.
   */
  public FormulaireVue(Inventaire inventaire, ObservableList<ProduitVM> produits) {
    // Initialisation des champs
    tfNom = new TextField();
    tfCategorie = new TextField();
    tfPrix = new TextField();
    tfQuantite = new TextField();
    btnAjouterProduit = new Button("Ajouter produit");

    // Configuration de la mise en page
    this.setPrefWidth(300);
    this.setPadding(new Insets(24));
    this.setSpacing(8);

    // Ajout des éléments graphiques au formulaire
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

    // Action à effectuer lors du clic sur le bouton "Ajouter produit"
    this.btnAjouterProduit.setOnAction(
            event -> {
              try {
                // Création d'un produit à partir des informations saisies
                Produit produit =
                        new Produit(
                                tfNom.getText(),
                                tfCategorie.getText(),
                                Double.parseDouble(tfPrix.getText()),
                                Integer.parseInt(tfQuantite.getText()));

                // Ajout du produit dans l'inventaire RMI
                inventaire.ajouterProduit(produit);

                // Mise à jour de la liste observable pour rafraîchir l'affichage
                produits.add(Util.from(produit));
              } catch (RemoteException e) {
                e.printStackTrace(); // Affichage des erreurs éventuelles
              }
            });
  }

  // Getters pour accéder aux champs depuis d'autres parties du code
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
