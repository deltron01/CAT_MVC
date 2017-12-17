package web;

import java.util.ArrayList;
import java.util.List;

import metier.Produit;

public class ProduitModel {
   private String motCle;
   private List<Produit> produits = new ArrayList<Produit>();
   private Produit produit = new Produit();
   private String errors;
   private String mode = "save";
   
   public String getMode() {
	return mode;
}
public void setMode(String mode) {
	this.mode = mode;
}
public String getErrors() {
	return errors;
}
public void setErrors(String errors) {
	this.errors = errors;
}
public Produit getProduit() {
	  return produit;
   }
   public void setProduit(Produit produit) {
	  this.produit = produit;
   }
public String getMotCle() {
	 return motCle;
   }
   public void setMotCle(String motCle) {
	 this.motCle = motCle;
   }
   public List<Produit> getProduits() {
	 return produits;
   }
   public void setProduits(List<Produit> produits) {
	 this.produits = produits;
   }
}
