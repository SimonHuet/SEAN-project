package fr.epsi.seanProject.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import fr.epsi.seanProject.beans.Blog;
import fr.epsi.seanProject.beans.Utilisateur;
import fr.epsi.seanProject.dao.IBlogDao;
import fr.epsi.seanProject.dao.IUtilisateurDao;
import fr.epsi.seanProject.dao.mockImpl.MockBlogDao;
import fr.epsi.seanProject.dao.mockImpl.MockUtilisateurDao;
import fr.epsi.seanProject.listener.StartupListener;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

/**
 * Servlet implementation class ListPostServlet
 */
@WebServlet("/ListPostServlet")
public class ListPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = LogManager.getLogger(ListPostServlet.class);
	IBlogDao blogDao = new MockBlogDao();
	IUtilisateurDao utilisateurDao = new MockUtilisateurDao();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListPostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Entering ListPostServlet");
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		Utilisateur user = (Utilisateur)session.getAttribute("utilisateur");
		List<Blog> list = blogDao.getBlogs(user);
		System.out.println(list.size());
		response.setContentType("text/plain");
		request.setAttribute("list", list);
		request.getRequestDispatcher("ListPost.jsp").forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
