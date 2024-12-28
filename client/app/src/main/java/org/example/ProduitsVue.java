package org.example;

import java.rmi.RemoteException;
import java.util.List;
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
    this.inventaire = inventaire;
    this.setItems(produits);

    TableColumn<ProduitVM, String> nomCol = new TableColumn<>("Nom");
    nomCol.setCellValueFactory(p -> p.getValue().getNom());
    nomCol.setCellFactory(TextFieldTableCell.forTableColumn());
    nomCol.setOnEditCommit(
        event -> {
          try {
            ProduitVM row = event.getRowValue();
            String newValue = event.getNewValue();

            Produit produit = Util.from(row);
            produit.setNom(newValue);

            inventaire.updateProduct(produit);
            row.getNom().set(newValue);
          } catch (RemoteException e) {
            e.printStackTrace();
          }
        });

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

            inventaire.updateProduct(produit);
            row.getCategorie().set(newValue);
          } catch (RemoteException e) {
            e.printStackTrace();
          }
        });

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

            inventaire.updateProduct(produit);
            row.getPrix().set(newValue);
          } catch (RemoteException e) {
            e.printStackTrace();
          }
        });

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

            inventaire.updateProduct(produit);
            row.getQuantite().set(newValue);
          } catch (RemoteException e) {
            e.printStackTrace();
          }
        });

    TableColumn<ProduitVM, Void> actionCol = new TableColumn<>("Actions");
    actionCol.setCellFactory(getActionCellFactory());

    this.setEditable(true);

    this.getColumns().addAll(List.of(nomCol, categorieCol, prixCol, quantiteCol, actionCol));
    this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

    this.setPrefWidth(800);
    HBox.setHgrow(this, Priority.ALWAYS);
  }

  private Callback<TableColumn<ProduitVM, Void>, TableCell<ProduitVM, Void>>
      getActionCellFactory() {
    return param ->
        new TableCell<>() {
          private final Button deleteButton = new Button("Delete");
          private final HBox buttonsBox = new HBox(deleteButton);

          {
            buttonsBox.setSpacing(5);

            deleteButton.setOnAction(
                event -> {
                  try {
                    ProduitVM row = getTableView().getItems().get(getIndex());
                    inventaire.deleteProduct(row.getId().get());
                    getTableView().getItems().remove(row);
                  } catch (RemoteException e) {
                    e.printStackTrace();
                  }
                });
          }

          @Override
          protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
              setGraphic(null);
            } else {
              setGraphic(buttonsBox);
            }
          }
        };
  }
}
