package fr.humanbooster.fx.englishbattle.servlets;

import java.io.IOException;
import java.util.List;

import fr.humanbooster.fx.englishbattle.business.Joueur;
import fr.humanbooster.fx.englishbattle.service.JoueurService;
import fr.humanbooster.fx.englishbattle.service.VerbeService;
import fr.humanbooster.fx.englishbattle.service.impl.JoueurServiceImpl;
import fr.humanbooster.fx.englishbattle.service.impl.VerbeServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private JoueurService joueurService = null;
	private VerbeService verbeService = null;

	/**
	 * Default constructor.
	 */
	public IndexServlet() {
		joueurService = new JoueurServiceImpl();
		verbeService = new VerbeServiceImpl();
		verbeService.importerVerbes();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("nbVerbes", verbeService.recupererNbVerbes());
		// Recupere toutes les joueurs et les envoies à la JSP
		List<Joueur> joueurs = joueurService.recupererJoueursDuHallOfFame();
		request.setAttribute("joueurs", joueurs);
		request.getRequestDispatcher("WEB-INF/index.jsp").include(request, response);

	}

}