package fr.humanbooster.fx.englishbattle.servlets;

import java.io.IOException;
import java.util.List;

import fr.humanbooster.fx.englishbattle.business.Verbe;
import fr.humanbooster.fx.englishbattle.service.VerbeService;
import fr.humanbooster.fx.englishbattle.service.impl.VerbeServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VerbesServlet
 */
@WebServlet("/verbes")
public class VerbesServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private VerbeService verbeService = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerbesServlet() {
    	verbeService = new VerbeServiceImpl();
    }

	/** Cette méthode est invoquée lorsque quelqu'un se rend sur l'url : 
	 * http://localhost:8080/englishbattle/verbes
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Verbe> verbes = verbeService.recupererVerbes();
		request.setAttribute("verbes", verbes);
		request.getRequestDispatcher("WEB-INF/verbes.jsp").forward(request, response);    
	}

}