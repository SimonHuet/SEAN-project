package fr.epsi.seanProject.dao.mockImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.epsi.seanProject.beans.Statut;
import fr.epsi.seanProject.dao.DBConnection;
import fr.epsi.seanProject.dao.IStatutDao;

public class MockStatutDao implements IStatutDao {

	private static List<Statut> listOfStatuts;
	
	@Override
	public Statut getStatut(Integer id) {
		for (Statut s: getListOfStatuts()) {
			if (s.getId().intValue() == id.intValue()) {
				return s;
			}
		}
		return null;
	}
	@Override
	public List<Statut> getListOfStatuts() {
		return getPrivateListOfStatuts();
	}

	private List<Statut> getPrivateListOfStatuts() {
		if (listOfStatuts == null) {
			listOfStatuts = new ArrayList<Statut>();
			
		}
		try {
		Connection connection = DBConnection.getConnection();
    	Statement con = connection.createStatement();
    	
    	ResultSet rs = con.executeQuery("select * from statut");
    	while(rs.next()) {
		
			Statut statut = new Statut();
			statut.setId(rs.getInt("id"));
			statut.setDescription(rs.getString("title"));
			listOfStatuts.add(statut);
    	}
		}catch(SQLException e) {
			
		}
			
		return listOfStatuts;
	}
}
