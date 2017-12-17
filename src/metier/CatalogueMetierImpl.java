package metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CatalogueMetierImpl implements ICatalogueMetier {

	@Override
	public void addProduit(Produit p) {
		Connection con = SingletonConnection.getConnection();
        try {
			PreparedStatement ps = con.prepareStatement("insert into produits(ref_prod,designation,prix,quantite) values(?,?,?,?)");
			ps.setString(1, p.getReference());
			ps.setString(2, p.getDesignation());
			ps.setDouble(3, p.getPrix());
			ps.setInt(4, p.getQuantite());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Produit> listProduits() {
		List<Produit> prods = new ArrayList<Produit>();
		Connection con = SingletonConnection.getConnection();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("select * from produits");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Produit p = new Produit();
				p.setReference(rs.getString("ref_prod"));
				p.setDesignation(rs.getString("designation"));
				p.setPrix(rs.getDouble("prix"));
				p.setQuantite(rs.getInt("quantite"));
				prods.add(p);
			}
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prods;
	}

	@Override
	public List<Produit> produitsParMC(String mc) {
		List<Produit> prods = new ArrayList<Produit>();
		Connection con = SingletonConnection.getConnection();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("select * from produits where designation like ?");
			ps.setString(1, "%"+mc+"%");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Produit p = new Produit();
				p.setReference(rs.getString("ref_prod"));
				p.setDesignation(rs.getString("designation"));
				p.setPrix(rs.getDouble("prix"));
				p.setQuantite(rs.getInt("quantite"));
				prods.add(p);
			}
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prods;
	}

	@Override
	public Produit getProduit(String ref) {
		Produit prod = null;
		Connection con = SingletonConnection.getConnection();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("select * from produits where ref_prod = ?");
			ps.setString(1, ref);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				prod = new Produit();
				prod.setReference(rs.getString("ref_prod"));
				prod.setDesignation(rs.getString("designation"));
				prod.setPrix(rs.getDouble("prix"));
				prod.setQuantite(rs.getInt("quantite"));
			}
			ps.close();
		} catch (SQLException e) {// une exception non surveillée est 1 exception qui ne peut être signalée par le compilateur			                      
			e.printStackTrace(); // une exception surveillé est une exception qu'on peut gérer, elle est signalé par le compilateur.
		}
		if (prod==null) throw new RuntimeException("Produit "+ref+" introuvable dans la base de données");
		return prod;
	}

	@Override
	public void updateProduit(Produit p) {
		Connection con = SingletonConnection.getConnection();
        try {
			PreparedStatement ps = con.prepareStatement("update produits set DESIGNATION = ?, PRIX = ?, QUANTITE = ? WHERE REF_PROD = ?");
			ps.setString(1, p.getDesignation());
			ps.setDouble(2, p.getPrix());
			ps.setInt(3, p.getQuantite());
			ps.setString(4, p.getReference());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void deleteProduit(String ref) {
		Connection con = SingletonConnection.getConnection();
        try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM produits WHERE REF_PROD = ?");			
			ps.setString(1, ref);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
