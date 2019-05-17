package fr.epsi.seanProject.dao;

import java.sql.SQLException;
import java.util.List;

import fr.epsi.seanProject.beans.Utilisateur;

public interface IUtilisateurDao {

	Utilisateur getUtilisateur(String email);
	void createUtilisateur(Utilisateur utilisateur) throws SQLException;
	void updateUtilisateur(Utilisateur utilisateur , String mail) throws SQLException;
	void deleteUtilisateur(Utilisateur utilisateur) throws SQLException;
	List<Utilisateur> getListOfUtilisateur();
}
