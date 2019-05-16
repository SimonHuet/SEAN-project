package fr.epsi.seanProject.dao.mockImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.epsi.seanProject.beans.Blog;
import fr.epsi.seanProject.beans.Reponse;
import fr.epsi.seanProject.beans.Statut;
import fr.epsi.seanProject.beans.Utilisateur;
import fr.epsi.seanProject.dao.DBConnection;
import fr.epsi.seanProject.dao.IBlogDao;
import fr.epsi.seanProject.dao.IStatutDao;
import fr.epsi.seanProject.dao.IUtilisateurDao;

public class MockBlogDao implements IBlogDao {

	private static List<Blog> listOfBlogs;
	private IUtilisateurDao utilisateurDao = new MockUtilisateurDao();
	private IStatutDao statutDao = new MockStatutDao();
	
	@Override
	public Blog getBlog(Integer id) {
		for (Blog b : getBlogs()) {
			if (b.getId().intValue() == id.intValue() && b.getStatut().getId().intValue() == IStatutDao.PUBLIE) {
				return b;
			}
		}
		return null;
	}

	@Override
	public List<Blog> getBlogs(Utilisateur utilisateur) {
		List<Blog> myBlogs = new ArrayList<Blog>();
		for (Blog b : getBlogs()) {
			//myBlogs.add(b);
			if(b.getCreateur() != null) {
				if (b.getCreateur().getEmail().equals(utilisateur.getEmail())) {
					myBlogs.add(b);
				} else if (b.getStatut().getId().intValue() == IStatutDao.PUBLIE) {
					myBlogs.add(b);
				}
			}
		}
		return myBlogs;
	}

	@Override
	public Integer createBlog(Blog blog) throws SQLException {
		int max = 0;
		for (Blog b : getBlogs()) {
			if (b.getId().intValue() > max) {
				max = b.getId();
			}
		}
		max+=1;
		try {
			Connection connection = DBConnection.getConnection();
			PreparedStatement con = connection.prepareStatement("INSERT INTO BLOG ( ID, TITRE, DESCRIPTION, EMAIL,DATE_CREATION, DATE_MODIFICATION, STATUT ) VALUES (?, ?, ?, ?, ?, ?, ?)");
			con.setInt(1, max);
			con.setString(2, blog.getTitre());
			con.setString(3, blog.getDescription());
			con.setString(4, blog.getCreateur().getEmail());
			con.setDate(5, blog.getDateCreation());
			con.setDate(6, blog.getDateModification());
			con.setInt(7, blog.getStatut().getId());
			con.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return max;
	}

	@Override
	public void updateBlog(Blog blog) throws SQLException {
		try {
			Connection connection = DBConnection.getConnection();
	    	PreparedStatement con = connection.prepareStatement(
	    			"UPDATE blog SET email=? , titre=?, description=? , date_creation=? , date_modification=? , statut=? where id=?");
	    	con.setString(2, blog.getTitre());
			con.setString(3, blog.getDescription());
			con.setString(1, blog.getCreateur().getEmail());
			con.setDate(4, blog.getDateCreation());
			con.setDate(5, blog.getDateModification());
			con.setInt(6, blog.getStatut().getId());
			con.setInt(7, blog.getId());
			con.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	
	}

	@Override
	public void deleteBlog(Blog blog, Utilisateur utilisateur) throws SQLException {
		if(blog.getCreateur().getEmail().equals(utilisateur.getEmail())) {
			try {
					Connection connection = DBConnection.getConnection();
					PreparedStatement con = connection.prepareStatement("DELETE from blog where id=?");
					con.setInt(1, blog.getId());
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

	@Override
	public void addReponse(Blog blog, Reponse reponse) throws SQLException {
		
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			Connection connection = DBConnection.getConnection();
	    	PreparedStatement con = connection.prepareStatement(
	    			"INSERT INTO BLOG_COMMENTAIRES ( ID, COMMENTAIRE,EMAIL, DATE_CREATION ) VALUES (?, ?, ?, ?)");
	    	con.setInt(1, blog.getId());
			con.setString(2, reponse.getCommentaire());
			con.setString(3, reponse.getBlogger().getEmail());
			con.setDate(4, reponse.getPublication());
			con.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Blog> getBlogs() {	
		listOfBlogs = new ArrayList<Blog>();
			
		try {
		Connection connection = DBConnection.getConnection();
		PreparedStatement st= connection.prepareStatement("SELECT * FROM BLOG");
		ResultSet rs = st.executeQuery();
		
    	while(rs.next()) {
    		Blog blog = new Blog();
    		blog.setId(rs.getInt("id"));
    		blog.setTitre(rs.getString("titre"));
    		blog.setDescription(rs.getString("description"));
    		blog.setDateCreation(rs.getDate("date_creation"));
    		blog.setDateModification(rs.getDate("date_modification"));
    		Utilisateur utilisateur = utilisateurDao.getUtilisateur(rs.getString("email"));
    		blog.setCreateur(utilisateur);
    		Statut statut = statutDao.getStatut(rs.getInt("statut"));
    		blog.setStatut(statut);
    		List<Reponse> listOfReponses = new ArrayList<Reponse>();

    		PreparedStatement st2= connection.prepareStatement("SELECT * FROM BLOG_COMMENTAIRES where id=?");
    		st2.setInt(1, blog.getId());
    		ResultSet rs2 = st2.executeQuery();
    		while(rs2.next()) {
    			Reponse rep = new Reponse();
    			rep.setBlog(blog);
    			rep.setBlogger(utilisateur);
    			rep.setCommentaire(rs2.getString("commentaire"));
    			rep.setPublication(rs2.getDate("date_creation"));
    			listOfReponses.add(rep);
    		}
    		blog.setListOfReponses(listOfReponses);

    		///System.out.println(utilisateur.getEmail());
    		listOfBlogs.add(blog);
    		
    	}
		}catch(SQLException e) {
			System.out.println(e);

		}
		return listOfBlogs;
		
	}
}
