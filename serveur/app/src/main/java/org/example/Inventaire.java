package org.example;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Inventaire extends Remote {
  void ajouterProduit(Produit product) throws RemoteException;

  void modiferProduit(Produit product) throws RemoteException;

  void supprimerProduit(int id) throws RemoteException;

  List<Produit> listerProduits() throws RemoteException;

  List<Produit> rechercherProduitsParNom(String name) throws RemoteException;

  List<Produit> rechercherProduitsParCategorie(String name) throws RemoteException;

  List<Produit> rechercherProduitsParQuantite(int min, int max) throws RemoteException;
}
