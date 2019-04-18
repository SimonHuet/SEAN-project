package fr.epsi.seanProject.dao.mockImpl;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.epsi.seanProject.beans.Utilisateur;
import fr.epsi.seanProject.dao.DBConnection;
import fr.epsi.seanProject.dao.IUtilisateurDao;

public class MockUtilisateurDao implements IUtilisateurDao {

	private static List<Utilisateur> listOfUtilisateurs;
	
	@Override
	public Utilisateur getUtilisateur(String email) {
		for (Utilisateur u : getListOfUtilisateur()) {
			if (u.getEmail().equals(email))
			return u;
		}
		return null;
	}

	@Override
	public void createUtilisateur(Utilisateur utilisateur) throws SQLException {
		getListOfUtilisateur().add(utilisateur);
	}

	@Override
	public void updateUtilisateur(Utilisateur utilisateur) throws SQLException {
		for (Utilisateur u : getListOfUtilisateur()) {
			if (u.getEmail().equals(utilisateur.getEmail())) {
				u.setAdmin(utilisateur.getAdmin());
				u.setNom(utilisateur.getNom());
			}
		}
	}

	@Override
	public void deleteUtilisateur(Utilisateur utilisateur) throws SQLException {
		for (Utilisateur u : getListOfUtilisateur()) {
			if (u.getEmail().equals(utilisateur.getEmail())) {
				getListOfUtilisateur().remove(u);
				return;
			}
		}
	}

	private List<Utilisateur> getListOfUtilisateur() {
		if (listOfUtilisateurs == null) {
			listOfUtilisateurs = new ArrayList<Utilisateur>();
		}	
			try {
	        	Connection connection = DBConnection.getConnection();
	        	Statement con = connection.createStatement();
	        	
	        	ResultSet rs = con.executeQuery("select * from users");
	        	while(rs.next()) {
	        		Utilisateur utilisateur = new Utilisateur();
	        		utilisateur.setEmail(rs.getString("email"));
	        		utilisateur.setNom(rs.getString("nom"));
	        		utilisateur.setAdmin(rs.getBoolean("is_admin"));
	        		utilisateur.setDateCreation(rs.getDate("date_creation"));
					utilisateur.setPassord(rs.getString("password"));
	        	}
	        	
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
		
		return listOfUtilisateurs;
	}
}
