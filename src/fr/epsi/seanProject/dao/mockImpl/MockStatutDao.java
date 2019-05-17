package fr.epsi.seanProject.dao.mockImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.epsi.seanProject.beans.Statut;
import fr.epsi.seanProject.dao.DBConnection;
import fr.epsi.seanProject.dao.IStatutDao;

public class MockStatutDao implements IStatutDao {
	private static final Logger Logger = LogManager.getLogger(MockStatutDao.class);
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
    	Logger.debug(con.toString());
    	while(rs.next()) {
		
			Statut statut = new Statut();
			statut.setId(rs.getInt("id"));
			statut.setDescription(rs.getString("title"));
			listOfStatuts.add(statut);
    	}
		}catch(SQLException e) {
			e.printStackTrace();
		}
			
		return listOfStatuts;
	}
}
