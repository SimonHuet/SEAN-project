package fr.epsi.seanProject.dao.mockImpl;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
		if(email != null ) {
		for (Utilisateur u : getListOfUtilisateur()) {
			if (u.getEmail().equals(email)) {
				return u;
			}
		}
		}
		return null;
	}

	@Override
	public void createUtilisateur(Utilisateur utilisateur) throws SQLException {
		Boolean booooolET1 = true;
		for (Utilisateur u : getListOfUtilisateur()) {
			if(u.getEmail().equals(utilisateur.getEmail())) {
				booooolET1 = false;
			}
		}
		if(booooolET1) {
			Connection connection = DBConnection.getConnection();
			PreparedStatement con = connection.prepareStatement("INSERT INTO USERS ( EMAIL, NOM, DATE_CREATION, PASSWORD, IS_ADMIN ) VALUES ( ?,?,?,?,?)");

			con.setString(1, utilisateur.getEmail());
			con.setString(2, utilisateur.getNom());
			con.setString(3, utilisateur.getDateCreation().toString());
			con.setString(4, utilisateur.getPassord());
			con.setString(5, utilisateur.getAdmin().toString());
			con.executeUpdate();
		}
		else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void updateUtilisateur(Utilisateur utilisateur) throws SQLException {
		
		Connection connection = DBConnection.getConnection();
    	PreparedStatement st = connection.prepareStatement(
    			"UPDATE USERS SET email=? , nom=? , date_creation=? , password=? , is_admin=? WHERE email=?");
    	
    	st.setString( 1, utilisateur.getEmail() );
		st.setString( 2, utilisateur.getNom());
		st.setDate( 3, utilisateur.getDateCreation());
		st.setString( 4, utilisateur.getPassord() );
		st.setBoolean( 5, utilisateur.getAdmin() );
    	st.setString( 6, utilisateur.getEmail() );
		st.executeUpdate();
	}

		@Override
		public void deleteUtilisateur(Utilisateur utilisateur) throws SQLException {
			if(utilisateur != null) {
				if(!utilisateur.getAdmin()) {
					try {
						Connection connection = DBConnection.getConnection();
						PreparedStatement con = connection.prepareStatement("DELETE from users where email=?");
						con.setString(1, utilisateur.getEmail());
						con.executeUpdate();
					}
					catch(Exception e) {
						e.printStackTrace();
					}
				}
				else {
					throw new IllegalArgumentException();
				}
			}
			else {
				throw new IllegalArgumentException();
			}
		}

	private List<Utilisateur> getListOfUtilisateur() {
		listOfUtilisateurs = new ArrayList<Utilisateur>();
		
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
					listOfUtilisateurs.add(utilisateur);
				}
	        	
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
		
		return listOfUtilisateurs;
	}
}
