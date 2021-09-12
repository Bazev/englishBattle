package fr.humanbooster.fx.englishbattle.servlets;

import java.io.IOException;
import java.util.Date;

import fr.humanbooster.fx.englishbattle.business.Partie;
import fr.humanbooster.fx.englishbattle.business.Question;
import fr.humanbooster.fx.englishbattle.business.Verbe;
import fr.humanbooster.fx.englishbattle.exceptions.AucunVerbeException;
import fr.humanbooster.fx.englishbattle.service.QuestionService;
import fr.humanbooster.fx.englishbattle.service.VerbeService;
import fr.humanbooster.fx.englishbattle.service.impl.QuestionServiceImpl;
import fr.humanbooster.fx.englishbattle.service.impl.VerbeServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class QuestionServlet
 */
@WebServlet("/jeu")
public class QuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private QuestionService questionService = new QuestionServiceImpl();
	private VerbeService verbeService = new VerbeServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QuestionServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Partie partie = (Partie) request.getSession().getAttribute("partie");

		if (partie != null) {
			if (request.getSession().getAttribute("question") == null) {
				// En provenance de IndexServlet, on met une question en session
				request.getSession().setAttribute("question", ajouterQuestion(partie, request));
				// on fait suivre à la JSP jeu.jsp
				request.getRequestDispatcher("WEB-INF/question.jsp").forward(request, response);
			}
			else {
				request.getRequestDispatcher("WEB-INF/question.jsp").forward(request, response);
			}
		} else
			response.sendRedirect("index");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// On vérifie que la session contient bien un objet partie
		if (request.getSession().getAttribute("partie") != null) {
			Partie partie = (Partie) request.getSession().getAttribute("partie");

			Question question = (Question) request.getSession().getAttribute("question");

			question = questionService.mettreAJourQuestion(question.getId(), request.getParameter("PRETERIT"),
					request.getParameter("PARTICIPE_PASSE"));

			// On vérifie que la réponse est arrivée en moins de 60 secondes
			long tempsDeReponseEnMilliSecondes = question.getDateReponse().getTime()
					- question.getDateEnvoi().getTime();

			System.out.println(tempsDeReponseEnMilliSecondes);
			System.out.println(question);
			if (tempsDeReponseEnMilliSecondes < 60000 && questionService.verifierReponse(question)) {
				// Bonne réponse, on ajoute un nouveau verbe à la partie
				// et on redirige vers question.jsp
				System.out.println("Bonne réponse");
				Question prochaineQuestion = ajouterQuestion(partie, request);
				if (prochaineQuestion == null) {
					// le joueur a su répondre à tous les verbes !
					response.sendRedirect("WEB-INF/bravo.jsp");
				} else {
					request.getSession().setAttribute("question", prochaineQuestion);
					request.getRequestDispatcher("WEB-INF/question.jsp").forward(request, response);
				}
			} else {
				// Réponse incorrecte ou trop tardive, on met fin à la session
				response.sendRedirect("deconnexion");
			}
		} else {
			response.sendRedirect("index");
		}
	}

	private Question ajouterQuestion(Partie partie, HttpServletRequest request) {
		Verbe verbe = null;
		try {
			verbe = verbeService.recupererVerbeAleatoire(partie);
			request.getSession().setAttribute("random", Math.random());
		} catch (AucunVerbeException e) {
			// le joueur a su répondre à tous les verbes !
			return null;
		}
		System.out.println(new Date() + " " + partie.getJoueur().getPrenom() + " doit répondre à la question #" + (1+partie.getNbQuestions()) + " " + verbe);
		Question question = questionService.ajouterQuestion(partie, verbe);
		return question;
	}
}