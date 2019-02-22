package fr.epsi.seanProject.listener;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import fr.epsi.seanProject.beans.Blog;
import fr.epsi.seanProject.beans.Utilisateur;
import fr.epsi.seanProject.dao.*;
import fr.epsi.seanProject.dao.mockImpl.MockBlogDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebListener
public class StartupListener implements ServletContextListener{
    private static final Logger Logger = LogManager.getLogger(StartupListener.class);
	public StartupListener() {
        
    }
    
    public void contextInitialized(ServletContextEvent sce) {
        Logger.error("Démarrage application");
        IBlogDao blogDao = new MockBlogDao();
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("contact@aquasys.fr)");
        List<Blog> listOfBlogs = blogDao.getBlogs(utilisateur);
        System.out.println("nb blogs=" + listOfBlogs.size());
    }
}