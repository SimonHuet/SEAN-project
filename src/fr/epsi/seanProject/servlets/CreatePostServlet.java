package fr.epsi.seanProject.servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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
 * Servlet implementation class CreatePostServlet
 */
@WebServlet("/CreatePostServlet")
public class CreatePostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = LogManager.getLogger(CreatePostServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreatePostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		logger.info("Entering CreatePostServlet");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("CreatePostPage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Blog newBlog = new Blog();
		newBlog.setTitre(request.getParameter("title" ));
		newBlog.setDescription(request.getParameter("textarea"));
		newBlog.setDateCreation(new Date(new java.util.Date().getTime()));
		newBlog.setId(123456789);
		Utilisateur createur = new Utilisateur();
		createur.setEmail("blyat@gmail.com");
		newBlog.setCreateur(createur);
		List<Reponse> reps = new ArrayList();
		newBlog.setListOfReponses(reps);
		request.setAttribute("Blog", newBlog);
		request.getRequestDispatcher("/BlogServlet?post="+newBlog.getId()).forward(request, response);
	}

}
