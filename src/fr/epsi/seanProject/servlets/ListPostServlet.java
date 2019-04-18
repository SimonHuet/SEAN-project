package fr.epsi.seanProject.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import fr.epsi.seanProject.beans.Blog;
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
		int i = 0;
		List<Blog> list = new ArrayList<>();
		for(i=0;i<20;i++) {
			Blog arg0 = new Blog();
			arg0.setId(i);
			arg0.setDateCreation(new Date(10,1,18));
			arg0.setDateModification(new Date(11,1,18));
			arg0.setTitre("Cyka Blyat");
			arg0.setDescription("Western spy blyat. Cyka Blyat !!");
			list.add(arg0);
		}
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
