package fr.epsi.seanProject.dao;

import java.sql.SQLException;
import java.util.List;

import fr.epsi.seanProject.beans.Blog;
import fr.epsi.seanProject.beans.Reponse;
import fr.epsi.seanProject.beans.Utilisateur;

public interface IBlogDao {

	Blog getBlog(Integer id);
	List<Blog> getBlogs(Utilisateur utilisateur);
	Integer createBlog(Blog blog) throws SQLException;
	void updateBlog(Blog blog) throws SQLException;
	void deleteBlog(Blog blog) throws SQLException;
	void addReponse(Blog blog, Reponse reponse) throws SQLException;
	
}
