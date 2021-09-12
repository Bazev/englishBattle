package fr.humanbooster.fx.englishbattle.servlets;

import java.io.IOException;

import fr.humanbooster.fx.englishbattle.business.Joueur;
import fr.humanbooster.fx.englishbattle.service.JoueurService;
import fr.humanbooster.fx.englishbattle.service.PartieService;
import fr.humanbooster.fx.englishbattle.service.VerbeService;
import fr.humanbooster.fx.englishbattle.service.impl.JoueurServiceImpl;
import fr.humanbooster.fx.englishbattle.service.impl.PartieServiceImpl;
import fr.humanbooster.fx.englishbattle.service.impl.VerbeServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/connexion")
public class ConnexionServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JoueurService joueurService = null;
	private PartieService partieService = null;
	private VerbeService verbeService = null;

	/**
	 * Default constructor.
	 */
	public ConnexionServlet() {
		joueurService = new JoueurServiceImpl();
		partieService = new PartieServiceImpl();
		verbeService = new VerbeServiceImpl();
		verbeService.importerVerbes();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (verbeService.recupererNbVerbes()==0)
		{
			request.setAttribute("erreur", "il n'est pas possible de jouer, la base de verbes irr&eacute;guliers est vide");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
		// On récupère les données saisies sur le formulaire de connexion
		String email = request.getParameter("EMAIL");
		String motDePasse = request.getParameter("MOT_DE_PASSE");
		
		// On essaie de récupérer un joueur avec ces données
		Joueur joueur = joueurService.recupererJoueurParEmailEtMotDePasse(email, motDePasse);
		
		if (joueur != null) {
			// Le joueur a saisi le bon email + mdp
			joueur.setEstEnLigne(true);
			System.out.println("Connexion de (nom) : " + joueur.getNom());
			System.out.println("Connexion de (prenom) : " + joueur.getPrenom());
			joueurService.mettreAJourJoueur(joueur.getId(), joueur.getEmail(), joueur.getNom(), joueur.getPrenom(), joueur.getMotDePasse(), joueur.getVille(), joueur.getNiveau(), true);
			// Ajout d'une partie et mise en session de cette partie
			request.getSession().setAttribute("partie", partieService.ajouterPartie(joueur));
			// On redirige vers l'URL
			// URL qui est pris en charge par la servlet QuestionServlet
			response.sendRedirect("jeu");
		} else {
			request.setAttribute("erreur", "email et/ou mot de passe incorrect");
			request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
		}
	}

}