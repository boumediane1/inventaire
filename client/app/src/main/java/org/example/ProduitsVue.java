package org.example;

import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class ProduitsVue extends TableView<ProduitVM> {
  public ProduitsVue(ObservableList<ProduitVM> produits) {
    super(produits);

    TableColumn<ProduitVM, String> nomCol = new TableColumn<>("Nom");
    nomCol.setCellValueFactory(p -> p.getValue().getNom());

    TableColumn<ProduitVM, String> categorieCol = new TableColumn<>("Catégorie");
    categorieCol.setCellValueFactory(p -> p.getValue().getCategorie());

    TableColumn<ProduitVM, Number> prixCol = new TableColumn<>("Prix");
    prixCol.setCellValueFactory(p -> p.getValue().getPrix());

    TableColumn<ProduitVM, Number> quantiteCol = new TableColumn<>("Quantité");
    quantiteCol.setCellValueFactory(p -> p.getValue().getQuantite());
    HBox.setHgrow(this, Priority.ALWAYS);

    this.getColumns().addAll(List.of(nomCol, categorieCol, prixCol, quantiteCol));
    this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

    this.setPrefWidth(700);
  }
}
