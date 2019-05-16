package fr.epsi.seanProject.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.epsi.seanProject.beans.Utilisateur;
import fr.epsi.seanProject.dao.IUtilisateurDao;
import fr.epsi.seanProject.dao.mockImpl.MockUtilisateurDao;

/**
 * Servlet implementation class ConnectServlet
 */
@WebServlet("/")
public class ConnectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = LogManager.getLogger(ConnectServlet.class);
	IUtilisateurDao utilisateurDao = new MockUtilisateurDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		logger.info("Entering ConnectServlet");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("connectionPage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        String password= request.getParameter("password" );
        String name = request.getParameter("name");
		
        //doGet(request, response);
        Utilisateur user  = utilisateurDao.getUtilisateur(name);
        if(user.getPassord().equals(password)) {
            
    		HttpSession session = request.getSession();
    		session.setAttribute("utilisateur", user);
    		request.getRequestDispatcher("ListPostServlet").forward(request, response);
        }
        else {
        	response.sendRedirect("Error.jsp");
        }
     
    }

    private Boolean isPasswordValid( String password) {
    	return (password == "TEST");
    }
    private Boolean isNameValid( String name ) {
    	return (name == "TEST");
    }

}
