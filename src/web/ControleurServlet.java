package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import metier.CatalogueMetierImpl;
import metier.ICatalogueMetier;
import metier.Produit;

public class ControleurServlet extends HttpServlet{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ICatalogueMetier metier;

    @Override
    public void init() throws ServletException{
    	metier = new CatalogueMetierImpl();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	//req.getRequestDispatcher("VueProduits.jsp").forward(req, resp);
    	doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	ProduitModel model = new ProduitModel();
    	String action = req.getParameter("action");
    	if(action != null){
    		if(action.equals("Chercher")){
    			System.out.println("Controller : we seek for your product from database !");
    			model.setMotCle(req.getParameter("motcle"));
    	    	List<Produit> produits = metier.produitsParMC(model.getMotCle());
    	    	model.setProduits(produits);
    	    	
    		}else if(action.equals("edit")){
    			System.out.println("Controller : we edit your product from database !");		
    			model.setProduit(metier.getProduit(req.getParameter("ref")));
    			model.setMode("edit");
    			model.setProduits(metier.listProduits());
    		}else if(action.equals("Supprimer")){
    			System.out.println("Controller : we delete your product from database !");
    			metier.deleteProduit(req.getParameter("ref"));
    			model.setProduits(metier.listProduits());
    		}
    		else if(action.equals("save")){
    			System.out.println("Controller : we save your product into database !");
    			try{
    				model.getProduit().setReference(req.getParameter("reference"));
        			model.getProduit().setDesignation(req.getParameter("designation"));
        			model.getProduit().setPrix(Double.parseDouble(req.getParameter("prix")));
        			model.getProduit().setQuantite(Integer.parseInt(req.getParameter("quantite")));
        			if(req.getParameter("mode").equals("edit")){
        				metier.updateProduit(model.getProduit());
        			}else{
        				metier.addProduit(model.getProduit());
        			}
    			}catch (Exception e){
    				model.setErrors(e.getMessage());
    			}    			
    			/*Produit p = new Produit(req.getParameter("reference"), req.getParameter("designation"),
    					Double.parseDouble(req.getParameter("prix")), Integer.parseInt(req.getParameter("quantite")));*/
    			model.setMode("save");
    			model.setProduits(metier.listProduits());
    		}
    	}
    	req.setAttribute("model1", model);
    	req.getRequestDispatcher("VueProduits.jsp").forward(req, resp);
    }
}
