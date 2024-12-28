package org.example;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
  private Inventaire inventaire;

  public static void main(String[] args) {
    launch();
  }

  @Override
  public void start(Stage primaryStage) throws RemoteException, NotBoundException {
    this.inventaire = inventaire();

    HBox rechercherVue = new RechercherVue();

    TableView<ProduitVM> produitsVue = new ProduitsVue(inventaire);

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
}
