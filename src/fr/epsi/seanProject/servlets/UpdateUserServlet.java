package fr.epsi.seanProject.servlets;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.lang.*;

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
import fr.epsi.seanProject.beans.Utilisateur;
import fr.epsi.seanProject.dao.IUtilisateurDao;
import fr.epsi.seanProject.dao.mockImpl.MockUtilisateurDao;

/**
 * Servlet implementation class UpdateUserServlet
 */
@WebServlet("/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IUtilisateurDao utilisateurDao = new MockUtilisateurDao();
	Utilisateur user = new Utilisateur();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateUserServlet() {
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

		String email = request.getParameter("email");
		try {
			user = utilisateurDao.getUtilisateur(email);
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("User", user);
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("UpdateUserPage.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("retour") != null) {
			request.getRequestDispatcher("/ListUserServlet").forward(request, response);
		}else if (request.getParameter("delete") != null) {
			try {
				utilisateurDao.deleteUtilisateur(user);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException ie) {
				response.sendRedirect("Error.jsp");
			}
			request.getRequestDispatcher("/ListUserServlet").forward(request, response);
		} else {
			if (request.getParameter("confirm_password").equals(request.getParameter("password"))) {
				String nom = request.getParameter("nom");
				if (!nom.equals("")) {
					user.setNom(request.getParameter("nom"));
				}

				if (!request.getParameter("mail").equals("")) {
					user.setEmail(request.getParameter("mail"));
				}

				if (!request.getParameter("password").equals("")) {
					user.setPassord(request.getParameter("password"));
				}
				if (!request.getParameter("admin").equals("")) {
					user.setAdmin(Boolean.parseBoolean(request.getParameter("admin")));
				}
				try {
					utilisateurDao.updateUtilisateur(user, request.getParameter("email"));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.getRequestDispatcher("/ListUserServlet").forward(request, response);
			} else {
				request.getRequestDispatcher("PasswordError.jsp").forward(request, response);
			}
		}

	}

}
