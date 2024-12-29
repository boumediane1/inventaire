package inventaire.util;

import java.util.List;

import inventaire.models.ProduitVM;
import inventaire.rmi.Produit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Util {
  public static ObservableList<ProduitVM> from(List<Produit> produtis) {
    List<ProduitVM> list =
        produtis.stream()
            .map(
                p ->
                    new ProduitVM(
                        p.getId(), p.getNom(), p.getCategorie(), p.getPrix(), p.getQuantite()))
            .toList();
    return FXCollections.observableArrayList(list);
  }

  public static ProduitVM from(Produit produit) {
    return new ProduitVM(
        produit.getId(),
        produit.getNom(),
        produit.getCategorie(),
        produit.getPrix(),
        produit.getQuantite());
  }

  public static Produit from(ProduitVM produitVM) {
    return new Produit(
        produitVM.getId().get(),
        produitVM.getNom().getValue(),
        produitVM.getCategorie().getValue(),
        produitVM.getPrix().getValue(),
        produitVM.getQuantite().getValue());
  }
}
