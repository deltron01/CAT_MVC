package metier;

import java.util.List;

public class TestMetier {

	public static void main(String[] args) {
		ICatalogueMetier metier = new CatalogueMetierImpl();
		/*metier.addProduit(new Produit("REF05","AA",870,3));
		metier.addProduit(new Produit("REF06","BB",970,4));
		metier.addProduit(new Produit("REF07","CC",1070,5));*/
		List<Produit> prods = metier.listProduits();
		System.out.println("=========== consultation de tous les produits ==========");
		for(Produit p : prods){
			System.out.println("Ref : "+p.getReference()+" Designation : "+p.getDesignation()+" Prix : "
		        +p.getPrix()+" Quantité: "+p.getQuantite());
		}
		List<Produit> prods2 = metier.produitsParMC("HP");
		System.out.println("=========== consultation des produits par motClé ==========");
		for(Produit p : prods2){
			System.out.println("Ref : "+p.getReference()+" Designation : "+p.getDesignation()+" Prix : "
		        +p.getPrix()+" Quantité: "+p.getQuantite());
		}
		System.out.println("=========== consultation d'un produit via sa ref ==========");
		try{
			Produit p = metier.getProduit("PR09");
			System.out.println(p.getDesignation());
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		System.out.println("=========== Suppression d'un produit ==========");
		metier.deleteProduit("REF05");
	}

}
