package fr.epsi.seanProject.dao.mockImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
			if (b.getId().intValue() == id.intValue()) {
				return b;
			}
		}
		return null;
	}

	@Override
	public List<Blog> getBlogs(Utilisateur utilisateur) {
		List<Blog> myBlogs = new ArrayList<Blog>();
		for (Blog b : getBlogs()) {
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
		blog.setId(max);
		return max;
	}

	@Override
	public void updateBlog(Blog blog) throws SQLException {
		for (Blog b : getBlogs()) {
			if (b.getId().intValue() == blog.getId().intValue()) {
				b.setTitre(blog.getTitre());
				b.setDescription(blog.getDescription());
				blog.setDateModification(new java.sql.Date(new Date().getTime()));
			}
		}
	
	}

	@Override
	public void deleteBlog(Blog blog) throws SQLException {
		try {
			Connection connection = DBConnection.getConnection();
			PreparedStatement st = connection.prepareStatement("DELETE FROM BLOG_COMMENTAIRES WHERE id_blog=?");
			PreparedStatement stBlog= connection.prepareStatement("DELETE FROM BLOG WHERE id=?");
			st.setInt(1, blog.getId());
			stBlog.setInt(1, blog.getId());
			st.executeQuery();
			stBlog.executeQuery();
		}catch(Exception e ) {
			e.printStackTrace();
		}
	}
	

	@Override
	public void addReponse(Blog blog, Reponse reponse) throws SQLException {
		try {
			Connection connection = DBConnection.getConnection();
			PreparedStatement st = connection.prepareStatement("INSERT INTO BLOG(TITRE,DESCRIPTION,EMAIL,DATE_CREATION,DATE_MODIFICATION, STATUT)" +
					"VALUES ( ?, ?, ?, ?, ?,? )");
			st.setString(1, blog.getTitre());
			st.setString(2, blog.getDescription() );
			st.setString(3, blog.getCreateur().getEmail());
			st.executeQuery();
		}catch(Exception e ) {
			e.printStackTrace();
		}
	}
	
	private List<Blog> getBlogs() {	
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
				listOfReponses = getBlogPosts(blog);
				blog.setListOfReponses(listOfReponses);
				listOfBlogs.add(blog);
			}
		}catch(SQLException e) {
			System.out.println(e);
		}
		return listOfBlogs;
	}
	
	public List<Reponse> getBlogPosts(Blog blog) {
		List<Reponse> listOfReponses = new ArrayList<Reponse>();
		try {
			Connection connection = DBConnection.getConnection();
			PreparedStatement st= connection.prepareStatement("SELECT * FROM BLOG_COMMENTAIRES WHERE id_blog=?");
			st.setInt(1, blog.getId() );
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Reponse reponse = new Reponse();
				reponse.setBlog(blog);
				Utilisateur utilisateur = utilisateurDao.getUtilisateur(rs.getString("email"));
	    		reponse.setBlogger(utilisateur);
	    		reponse.setCommentaire(rs.getString("commentaire"));
	    		reponse.setPublication(rs.getDate("date_creation"));
	    		listOfReponses.add(reponse);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return listOfReponses;
	}
}
