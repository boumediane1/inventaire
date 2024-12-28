package org.example;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class App extends Application {
  public static void main(String[] args) {
    launch();
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    Registry registry = LocateRegistry.getRegistry();
    Inventaire inventaire = (Inventaire) registry.lookup("inventaire");;


    ObservableList<ProduitView> produits = from(inventaire.listerProduits());

    TableView<ProduitView> tableView = new ProduitTableView(produits);
    ProduitForm vBox = new ProduitForm();

    GridPane pane = new ProductPane();
    pane.add(tableView, 1, 0);
    pane.add(vBox, 1, 0);

    Scene scene = new Scene(pane, 800, 600);
    produits.add(new ProduitView("produit 2", "categorie 2", 0, 0));
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  ObservableList<ProduitView> from(List<Produit> produtis) {
    List<ProduitView> list = produtis.stream().map(p -> new ProduitView(p.getNom(), p.getCategorie(), p.getPrix(), p.getQuantite())).toList();
    return FXCollections.observableArrayList(list);
  }
}
