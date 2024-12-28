package org.example;

import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ProduitTableView extends TableView<ProduitView> {
  public ProduitTableView(ObservableList<ProduitView> produits) {
    super(produits);

    TableColumn<ProduitView, String> nomCol = new TableColumn<>("Nom");
    nomCol.setCellValueFactory(p -> p.getValue().getNom());

    TableColumn<ProduitView, String> categorieCol = new TableColumn<>("Catégorie");
    categorieCol.setCellValueFactory(p -> p.getValue().getCategorie());

    TableColumn<ProduitView, Number> prixCol = new TableColumn<>("Prix");
    prixCol.setCellValueFactory(p -> p.getValue().getPrix());

    TableColumn<ProduitView, Number> quantiteCol = new TableColumn<>("Quantité");
    quantiteCol.setCellValueFactory(p -> p.getValue().getQuantite());

    this.getColumns().addAll(List.of(nomCol, categorieCol, prixCol, quantiteCol));
    this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
  }
}
