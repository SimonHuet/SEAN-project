package fr.epsi.seanProject.dao.mockImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.epsi.seanProject.beans.Blog;
import fr.epsi.seanProject.beans.Reponse;
import fr.epsi.seanProject.beans.Utilisateur;
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
			if (b.getCreateur().getEmail().equals(utilisateur.getEmail())) {
				myBlogs.add(b);
			} else if (b.getStatut().getId().intValue() == IStatutDao.PUBLIE) {
				myBlogs.add(b);
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
		for (Blog b : getBlogs()) {
			if (b.getId().intValue() == blog.getId().intValue()) {
				getBlogs().remove(b);
				return;
			}
		}
	}

	@Override
	public void addReponse(Blog blog, Reponse reponse) throws SQLException {
		for (Blog b : getBlogs()) {
			if (b.getId().intValue() == blog.getId().intValue()) {
				if (b.getListOfReponses() == null) {
					b.setListOfReponses(new ArrayList<Reponse>());
				}
				b.getListOfReponses().add(reponse);
				return;
			}
		}
	}
	
	private List<Blog> getBlogs() {
		if (listOfBlogs == null) {
			listOfBlogs = new ArrayList<Blog>();
			Blog blog = new Blog();
			blog.setId(1);
			blog.setTitre("First Blog");
			blog.setDescription("My first blog");
			blog.setDateCreation(new java.sql.Date(new Date().getTime()));
			blog.setCreateur(utilisateurDao.getUtilisateur("contact@aquasys.fr"));
			blog.setStatut(statutDao.getStatut(1));
			listOfBlogs.add(blog);
		}
		return listOfBlogs;
	}

}
