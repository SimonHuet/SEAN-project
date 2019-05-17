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
 * Servlet implementation class CreateUserServlet
 */
@WebServlet("/CreateUserServlet")
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    IUtilisateurDao utilisateurDao = new MockUtilisateurDao();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("CreateUserPage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Utilisateur user = new Utilisateur();
		if(request.getParameter("confirm_password").equals(request.getParameter("password"))) {
		user.setNom(request.getParameter("nom"));
		user.setEmail(request.getParameter("email"));
		user.setPassord(request.getParameter("password"));
		user.setDateCreation(new Date(new java.util.Date().getTime()));
		user.setAdmin(Boolean.parseBoolean(request.getParameter("admin")));
		try {
			utilisateurDao.createUtilisateur(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		session.setAttribute("utilisateur", user);
		request.getRequestDispatcher("/ListPostServlet").forward(request, response);
		}else {
			request.getRequestDispatcher("PasswordError.jsp").forward(request, response);
		}
	}

}
