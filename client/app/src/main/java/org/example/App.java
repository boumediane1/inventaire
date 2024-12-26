/*
 * This source file was generated by the Gradle 'init' task
 */
package org.example;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class App extends Application {
  public static void main(String[] args) {
    launch();
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    ObservableList<Produit> produits = FXCollections.observableArrayList(new Produit("produit 1", "categorie 1", 0, 0));

    Scene scene = new Scene(initTableView(produits), 800, 600);
    produits.add(new Produit("produit 2", "categorie 2", 0, 0));
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public TableView<Produit> initTableView(ObservableList<Produit> produits) {
    TableView<Produit> tableView = new TableView<>(produits);

    TableColumn<Produit, String> nomCol = new TableColumn<>("Nom");
    nomCol.setCellValueFactory(p -> p.getValue().getNom());

    TableColumn<Produit, String> categorieCol = new TableColumn<>("Catégorie");
    categorieCol.setCellValueFactory(p -> p.getValue().getCategorie());

    TableColumn<Produit, Number> prixCol = new TableColumn<>("Prix");
    prixCol.setCellValueFactory(p -> p.getValue().getPrix());

    TableColumn<Produit, Number> quantiteCol = new TableColumn<>("Quantité");
    quantiteCol.setCellValueFactory(p -> p.getValue().getQuantite());


    tableView.getColumns().addAll(List.of(nomCol, categorieCol, prixCol, quantiteCol));

    return tableView;
  }
}
