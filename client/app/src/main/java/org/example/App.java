package org.example;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
  public static void main(String[] args) {
    launch();
  }

  @Override
  public void start(Stage primaryStage) throws RemoteException, NotBoundException {
    Inventaire inventaire = inventaire();
    ObservableList<ProduitVM> produits = from(inventaire.listerProduits());

    HBox rechercherVue = new RechercherVue();

    TableView<ProduitVM> produitsVue = new ProduitsVue(FXCollections.observableList(produits));

    VBox formulaireVue = new FormulaireVue();

    HBox hBox = new HBox();
    hBox.getChildren().addAll(produitsVue, formulaireVue);

    VBox vBox = new VBox();
    vBox.getChildren().addAll(rechercherVue, hBox);
    VBox.setVgrow(hBox, Priority.ALWAYS);

    // Create the scene and show the stage
    Scene scene = new Scene(vBox, 1000, 600);
    primaryStage.setScene(scene);
    primaryStage.setTitle("JavaFX Layout Example");
    primaryStage.show();
  }

  public Inventaire inventaire() throws RemoteException, NotBoundException {
    Registry registry = LocateRegistry.getRegistry();
    Inventaire inventaire = (Inventaire) registry.lookup("inventaire");
    return inventaire;
  }

  public ObservableList<ProduitVM> from(List<Produit> produtis) {
    List<ProduitVM> list =
        produtis.stream()
            .map(p -> new ProduitVM(p.getNom(), p.getCategorie(), p.getPrix(), p.getQuantite()))
            .toList();
    return FXCollections.observableArrayList(list);
  }

  public ProduitVM from(Produit produit) {
    return new ProduitVM(
        produit.getNom(), produit.getCategorie(), produit.getPrix(), produit.getQuantite());
  }
}
