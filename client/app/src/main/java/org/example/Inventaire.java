package org.example;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Inventaire extends Remote {
  void ajouterProduit(Produit product) throws RemoteException;

  void modifierProduit(Produit product) throws RemoteException;

  void supprimerProduit(int id) throws RemoteException;

  List<Produit> listerProduits();

  List<Produit> rechercherProduitsParNom(String name) throws RemoteException;

  List<Produit> rechercherProduitsParCategorie(String name) throws RemoteException;

  List<Produit> rechercherProduitParQuantite(int min, int max) throws RemoteException;
}
