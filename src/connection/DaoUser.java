package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Utilisateur.User;

public class DaoUser {
	
	public static void create(User user) throws Exception {
		try (Connection connection = DBManager.getConnection()) {
			String query = "Insert into user (prenom, nom, login, password) values (?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, user.getPrenom());
			ps.setString(2, user.getNom());
			ps.setString(3, user.getLogin());
			ps.setString(4, user.getPassword());
			ps.execute();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public static boolean check(int id) throws Exception {
		try (Connection connection = DBManager.getConnection()) {
			String query = "Select * From user where id=?";
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
	
	public static boolean checkUser(User user) throws Exception {
		try (Connection connection = DBManager.getConnection()) {
			String query = "Select * From user where prenom=? AND nom=? AND login=? AND password=?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, user.getPrenom());
			ps.setString(2, user.getNom());
			ps.setString(3, user.getLogin());
			ps.setString(4, user.getPassword());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
			return false;
	}
	
	public static int getDBId(User usr) throws Exception {
		try (Connection connection = DBManager.getConnection()) {
			String query = "Select * From user where login=? AND password=?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, usr.getLogin());
			ps.setString(2, usr.getPassword());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt("id");
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
			return 0;
	}
	
	public static User read(int id) throws Exception {
		try (Connection connection = DBManager.getConnection()) {
			String query = "Select * From user where id=?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String prenom = rs.getString("prenom");
				String nom = rs.getString("nom");
				String login = rs.getString("login");
				String password = rs.getString("password");
				User user = new User (prenom, nom, login, password);
				return user;
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
			return null;
	}
	
	public static List<User> list() throws Exception {
		List<User> users = new ArrayList<>();
		try (Connection connection = DBManager.getConnection()) {
			String query = "Select * From user";
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String prenom = rs.getString("prenom");
				String nom = rs.getString("nom");
				String login = rs.getString("login");
				String password = rs.getString("password");
				User user = new User (prenom, nom, login, password);
				users.add(user);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return users;
	}
	
	public static void update(User user,int id) throws Exception {
		try (Connection connection = DBManager.getConnection()) {
			String query = "Update user Set prenom=?, nom=?, login=?, password=? Where id=?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, user.getPrenom());
			ps.setString(2, user.getNom());
			ps.setString(3, user.getLogin());
			ps.setString(4, user.getPassword());
			ps.setInt(5, id);
			ps.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public static void delete(int id) throws Exception {
		try (Connection connection = DBManager.getConnection()) {
			String query = "Delete From user Where id=?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public static void truncate() throws Exception {
		try (Connection connection = DBManager.getConnection()) {
			String query = "truncate table user;";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
}