package inventaire.vues;

import java.rmi.RemoteException;
import java.util.List;

import inventaire.rmi.Inventaire;
import inventaire.rmi.Produit;
import inventaire.models.ProduitVM;
import inventaire.util.Util;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.util.Callback;
import javafx.util.converter.NumberStringConverter;

public class ProduitsVue extends TableView<ProduitVM> {
    private Inventaire inventaire;

    public ProduitsVue(Inventaire inventaire, ObservableList<ProduitVM> produits) {
        this.inventaire = inventaire; // Stockage de l'instance RMI
        this.setItems(produits); // Définition des éléments affichés dans le tableau

        // Colonne pour le nom du produit
        TableColumn<ProduitVM, String> nomCol = new TableColumn<>("Nom");
        nomCol.setCellValueFactory(p -> p.getValue().getNom());
        nomCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nomCol.setOnEditCommit(
                event -> {
                    try {
                        ProduitVM row = event.getRowValue();
                        String newValue = event.getNewValue();

                        // Mise à jour côté RMI
                        Produit produit = Util.from(row);
                        produit.setNom(newValue);
                        inventaire.modifierProduit(produit);

                        // Mise à jour locale
                        row.getNom().set(newValue);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                });

        // Colonne pour la catégorie
        TableColumn<ProduitVM, String> categorieCol = new TableColumn<>("Catégorie");
        categorieCol.setCellValueFactory(p -> p.getValue().getCategorie());
        categorieCol.setCellFactory(TextFieldTableCell.forTableColumn());
        categorieCol.setOnEditCommit(
                event -> {
                    try {
                        ProduitVM row = event.getRowValue();
                        String newValue = event.getNewValue();

                        Produit produit = Util.from(row);
                        produit.setCategorie(newValue);
                        inventaire.modifierProduit(produit);
                        row.getCategorie().set(newValue);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                });

        // Colonne pour le prix
        TableColumn<ProduitVM, Number> prixCol = new TableColumn<>("Prix");
        prixCol.setCellValueFactory(p -> p.getValue().getPrix());
        prixCol.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
        prixCol.setOnEditCommit(
                event -> {
                    try {
                        ProduitVM row = event.getRowValue();
                        double newValue = event.getNewValue().doubleValue();

                        Produit produit = Util.from(row);
                        produit.setPrix(newValue);
                        inventaire.modifierProduit(produit);
                        row.getPrix().set(newValue);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                });

        // Colonne pour la quantité
        TableColumn<ProduitVM, Number> quantiteCol = new TableColumn<>("Quantité");
        quantiteCol.setCellValueFactory(p -> p.getValue().getQuantite());
        quantiteCol.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
        quantiteCol.setOnEditCommit(
                event -> {
                    try {
                        ProduitVM row = event.getRowValue();
                        int newValue = event.getNewValue().intValue();

                        Produit produit = Util.from(row);
                        produit.setQuantite(newValue);
                        inventaire.modifierProduit(produit);
                        row.getQuantite().set(newValue);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                });

        // Colonne pour les actions (supprimer)
        TableColumn<ProduitVM, Void> actionCol = new TableColumn<>("Actions");
        actionCol.setCellFactory(getActionCellFactory());

        // Activation de l'édition
        this.setEditable(true);

        // Ajout des colonnes au tableau
        this.getColumns().addAll(List.of(nomCol, categorieCol, prixCol, quantiteCol, actionCol));

        // Politique de redimensionnement des colonnes
        this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

        // Configuration de la largeur préférée
        this.setPrefWidth(800);
        HBox.setHgrow(this, Priority.ALWAYS);
    }

    /**
     * Crée une fabrique de cellules pour les actions (bouton supprimer).
     * @return Callback pour générer une cellule avec un bouton supprimer.
     */
    private Callback<TableColumn<ProduitVM, Void>, TableCell<ProduitVM, Void>>
    getActionCellFactory() {
        return param ->
                new TableCell<>() {
                    private final Button deleteButton = new Button("Delete");
                    private final HBox buttonsBox = new HBox(deleteButton);

                    {
                        buttonsBox.setSpacing(5);

                        // Action pour le bouton supprimer
                        deleteButton.setOnAction(
                                event -> {
                                    try {
                                        ProduitVM row = getTableView().getItems().get(getIndex());
                                        inventaire.supprimerProduit(row.getId().get());
                                        getTableView().getItems().remove(row); // Mise à jour locale
                                    } catch (RemoteException e) {
                                        e.printStackTrace();
                                    }
                                });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null); // Pas de contenu pour les lignes vides
                        } else {
                            setGraphic(buttonsBox); // Ajout des boutons pour les lignes valides
                        }
                    }
                };
    }
}
