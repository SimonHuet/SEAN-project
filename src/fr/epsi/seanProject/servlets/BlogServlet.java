package fr.epsi.seanProject.servlets;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.epsi.seanProject.beans.Blog;
import fr.epsi.seanProject.beans.Reponse;
import fr.epsi.seanProject.beans.Statut;
import fr.epsi.seanProject.beans.Utilisateur;
import fr.epsi.seanProject.dao.IBlogDao;
import fr.epsi.seanProject.dao.mockImpl.MockBlogDao;

/**
 * Servlet implementation class BlogServlet
 */
@WebServlet("/BlogServlet")
public class BlogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = LogManager.getLogger(BlogServlet.class);

	Blog TcClm = null;
	List<Reponse> reps = new ArrayList();
	HttpSession session = null;
	IBlogDao blogDao = new MockBlogDao();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BlogServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		logger.info("Entering BlogServlet");
		String param = request.getParameter("post");
		Blog newBlog = (Blog) request.getAttribute("Blog");
		if (newBlog != null) {
			TcClm = newBlog;
			session = request.getSession();
			session.setAttribute("Blog", TcClm);

		} else {
			try {
				TcClm = blogDao.getBlog(Integer.parseInt(param));
			} catch (NumberFormatException e) {
				TcClm = null;
			}
		}
		response.setContentType("text/plain");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.getWriter().append("Param ").append(param);
		request.setAttribute("postName", param);
		request.setAttribute("Blog", TcClm);
		request.getRequestDispatcher("BlogPage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("delete") != null) {
			HttpSession s = request.getSession();
			Utilisateur user = (Utilisateur) s.getAttribute("utilisateur");
			try {
				blogDao.deleteBlog(TcClm, user);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException ie) {
				response.sendRedirect("Error.jsp");
			}
			request.getRequestDispatcher("/ListPostServlet").forward(request, response);
		}else if (request.getParameter("updateStatut") != null) {
			if (request.getParameter("statut") != null) {
				int s = Integer.parseInt(request.getParameter("statut"));
				Statut statut = new Statut();
				statut.setId(s);
				TcClm.setStatut(statut);
				try {
					blogDao.updateBlog(TcClm);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException ie) {
					response.sendRedirect("Error.jsp");
				}
			}
			doGet(request, response);
		} else if (request.getParameter("update") != null) {
			String des = request.getParameter("Description");
			String titre = request.getParameter("Titre");
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

			TcClm.setDescription(des);
			TcClm.setTitre(titre);
			TcClm.setDateModification(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
			try {
				blogDao.updateBlog(TcClm);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException ie) {
				response.sendRedirect("Error.jsp");
			}
			doGet(request, response);
		} else {
			if (TcClm != null) {
				String description = request.getParameter("commentaire");
				Reponse e = new Reponse();
				e.setBlog(TcClm);
				e.setBlogger(TcClm.getCreateur()); // A CHANGER
				e.setCommentaire(description);
				e.setPublication(new Date(new java.util.Date().getTime()));
				reps.add(e);
				try {
					blogDao.addReponse(TcClm, e);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			doGet(request, response);
		}
		
	}
}
