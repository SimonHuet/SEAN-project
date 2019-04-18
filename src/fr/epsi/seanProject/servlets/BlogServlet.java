package fr.epsi.seanProject.servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.epsi.seanProject.beans.Blog;
import fr.epsi.seanProject.beans.Reponse;
import fr.epsi.seanProject.beans.Utilisateur;

/**
 * Servlet implementation class BlogServlet
 */
@WebServlet("/BlogServlet")
public class BlogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = LogManager.getLogger(BlogServlet.class);

	Blog TomcatCdelamerde = new Blog();
	Utilisateur createur = new Utilisateur();
	List<Reponse> reps = new ArrayList();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlogServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		logger.info("Entering BlogServlet");
		String param = request.getParameter("post");
		Blog newBlog = (Blog) request.getAttribute("Blog");
		if(newBlog != null) {
			TomcatCdelamerde = newBlog;
		} else {
			createur.setEmail("blyat@gmail.com");
			TomcatCdelamerde.setDateCreation(new Date(2018,06,13)); 
			TomcatCdelamerde.setDateModification(new Date(2018,07,23));
			TomcatCdelamerde.setTitre("C de la merde");
			TomcatCdelamerde.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum");
	        TomcatCdelamerde.setCreateur(createur);
	        TomcatCdelamerde.setListOfReponses(reps);
		}
		response.setContentType("text/plain");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.getWriter().append("Param ").append(param);
		request.setAttribute("postName", param);
		request.setAttribute("Blog", TomcatCdelamerde);
		request.getRequestDispatcher("BlogPage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String description = request.getParameter("commentaire" );
		Reponse e = new Reponse();
		e.setBlog(TomcatCdelamerde);
		e.setBlogger(createur);
		e.setCommentaire(description);
		e.setPublication(new Date(new java.util.Date().getTime()));
		reps.add(e);
		doGet(request, response);
	}

}
