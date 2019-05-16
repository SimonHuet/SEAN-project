package fr.epsi.seanProject.test;

import fr.epsi.seanProject.beans.Blog;
import fr.epsi.seanProject.beans.Utilisateur;
import fr.epsi.seanProject.dao.IBlogDao;
import fr.epsi.seanProject.dao.IUtilisateurDao;
import fr.epsi.seanProject.dao.mockImpl.MockBlogDao;
import fr.epsi.seanProject.dao.mockImpl.MockUtilisateurDao;
import junit.framework.*;


public class TestClass extends TestCase {
	IUtilisateurDao utilisateurDao = new MockUtilisateurDao();
	IBlogDao blogDao = new MockBlogDao();
	
	
	public void testSupressAdministrateur() {
        Utilisateur user = utilisateurDao.getUtilisateur("contact@aquasys.fr");
        Exception ex = null;
        try {
        	utilisateurDao.deleteUtilisateur(user);
        }
        catch(Exception e) {
        	ex = e;
        }
        assertEquals(ex.getClass(),IllegalArgumentException.class);
	}
	public void testAjoutEmailExistant() throws IllegalArgumentException{
        Utilisateur user = utilisateurDao.getUtilisateur("contact@aquasys.fr");
        Exception ex = null;
        try {
        	utilisateurDao.createUtilisateur(user);
        }
        catch(Exception e) {
        	ex = e;
        }
        assertEquals(ex.getClass(), IllegalArgumentException.class);
		
	}
	public void testSuppressionBlog() throws IllegalArgumentException{
        Utilisateur user = utilisateurDao.getUtilisateur("contact@aquasys.fr");
        Blog blog = blogDao.getBlog(6);
        Exception ex = null;
        try {
        	blogDao.deleteBlog(blog, user);
        }
        catch(Exception e) {
        	ex = e;
        }
        assertEquals(ex.getClass(), IllegalArgumentException.class);
		
	}
	public void testGetBlog() throws Exception {
        Blog blog = blogDao.getBlog(4);
        assertEquals(blog, null);
	}
}
