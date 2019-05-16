package fr.epsi.seanProject.listener;
import java.lang.management.ManagementFactory;
import java.util.List;

import javax.management.*;
import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import fr.epsi.seanProject.beans.Blog;
import fr.epsi.seanProject.beans.LogLevel;
import fr.epsi.seanProject.beans.Utilisateur;
import fr.epsi.seanProject.dao.*;
import fr.epsi.seanProject.dao.mockImpl.MockBlogDao;
import fr.epsi.seanProject.dao.mockImpl.MockUtilisateurDao;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;


@WebListener
public class StartupListener implements ServletContextListener{
    private static final Logger Logger = LogManager.getLogger(StartupListener.class);
	public StartupListener() {
        
    }
    
    public void contextInitialized(ServletContextEvent sce) {
    	MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
    	ObjectName name = null;
    	ObjectName name2 = null;

    	try {
    	    name = new ObjectName("fr.epsi.jmx:type=LogLevelMBean");
    	    LogLevel mbean = new LogLevel();

    	    mbs.registerMBean(mbean, name);
    	    name2 = new ObjectName("fr.epsi.jmx:type=BlogMBean");
    	    Blog mbean2 = new Blog();

    	    mbs.registerMBean(mbean2, name2);

    	} catch (MalformedObjectNameException e) {
    	    e.printStackTrace();
    	} catch (NullPointerException e) {
    	    e.printStackTrace();
    	} catch (InstanceAlreadyExistsException e) {
    	    e.printStackTrace();
    	} catch (MBeanRegistrationException e) {
    	    e.printStackTrace();
    	} catch (NotCompliantMBeanException e) {
    	    e.printStackTrace();
    	} 

		Configurator.setLevel("fr.epsi.seanProject",Level.ALL);
       
        Logger.error("Démarrage application");
        IBlogDao blogDao = new MockBlogDao();
        List<Blog> listOfBlogs = blogDao.getBlogs();
        System.out.println("total blogs=" + listOfBlogs.size());
        
        }
    }