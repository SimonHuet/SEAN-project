package fr.epsi.seanProject.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Servlet implementation class ConnectServlet
 */
@WebServlet("/Connect")
public class ConnectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = LogManager.getLogger(ConnectServlet.class);
       
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        String password= request.getParameter("FORM_PASSWORD" );
        String name = request.getParameter("FORM_NAME");
		
        //doGet(request, response);


            if(isPasswordValid( password )) {
            	System.out.println(password);
            }
            if(isNameValid( name )){
            	
            } 
     
    }

    private Boolean isPasswordValid( String password) {
    	return (password == "TEST");
    }
    private Boolean isNameValid( String name ) {
    	return (name == "TEST");
    }

}
