package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Produit.Produit;

public class DaoProduit {
	
	public static void create(Produit produit) throws Exception {
		try (Connection connection = DBManager.getConnection()) {
			String query = "Insert into produit (nom, description, prix, quantite, categorie) values (?,?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, produit.getNom());
			ps.setString(2, produit.getDescription());
			ps.setInt(3, produit.getPrix());
			ps.setInt(4, produit.getQuantite());
			ps.setString(5, produit.getCategorie());
			ps.execute();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public static boolean check(int id) throws Exception {
		try (Connection connection = DBManager.getConnection()) {
			String query = "Select * From produit where id=?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
			return false;
	}
	
	public static boolean checkProduit(Produit produit) throws Exception {
		try (Connection connection = DBManager.getConnection()) {
			String query = "Select * From produit where nom=? AND description=? AND prix=? AND quantite=? AND categorie=?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, produit.getNom());
			ps.setString(2, produit.getDescription());
			ps.setInt(3, produit.getPrix());
			ps.setInt(4, produit.getQuantite());
			ps.setString(5, produit.getCategorie());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
			return false;
	}
	
	public static int getDBId(Produit produit) throws Exception {
		try (Connection connection = DBManager.getConnection()) {
			String query = "Select * From produit where nom=? AND description=? AND prix=? AND quantite=? AND categorie=?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, produit.getNom());
			ps.setString(2, produit.getDescription());
			ps.setInt(3, produit.getPrix());
			ps.setInt(4, produit.getQuantite());
			ps.setString(5, produit.getCategorie());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt("id");
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
			return 0;
	}
	
	public Produit read(int id) throws Exception {
		try (Connection connection = DBManager.getConnection()) {
			String query = "Select * From produit where id=?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String nom = rs.getString("nom");
				String description = rs.getString("description");
				int prix = rs.getInt("prix");
				int quantite = rs.getInt("quantite");
				String categorie = rs.getString("categorie");
				Produit produit = new Produit (nom, description, prix, quantite, categorie);
				return produit;
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
			return null;
	}
	
	public static List<Produit> list() throws Exception {
		List<Produit> produits = new ArrayList<>();
		try (Connection connection = DBManager.getConnection()) {
			String query = "Select * From produit";
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String nom = rs.getString("nom");
				String description = rs.getString("description");
				int prix = rs.getInt("prix");
				int quantite = rs.getInt("quantite");
				String categorie = rs.getString("categorie");
				Produit produit = new Produit (nom, description, prix, quantite, categorie);
				produits.add(produit);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return produits;
	}
	
	public static void update(Produit produit,int id) throws Exception {
		try (Connection connection = DBManager.getConnection()) {
			String query = "Update produit Set nom=?, description=?, prix=?, quantite=?, categorie=? Where id=?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, produit.getNom());
			ps.setString(2, produit.getDescription());
			ps.setInt(3, produit.getPrix());
			ps.setInt(4, produit.getQuantite());
			ps.setString(5, produit.getCategorie());
			ps.setInt(6, id);
			ps.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public static void delete(int id) throws Exception {
		try (Connection connection = DBManager.getConnection()) {
			String query = "Delete From produit Where id=?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
}