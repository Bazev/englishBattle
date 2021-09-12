package fr.humanbooster.fx.englishbattle.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.humanbooster.fx.englishbattle.business.Joueur;
import fr.humanbooster.fx.englishbattle.service.JoueurService;
import fr.humanbooster.fx.englishbattle.service.VilleService;
import fr.humanbooster.fx.englishbattle.service.impl.JoueurServiceImpl;
import fr.humanbooster.fx.englishbattle.service.impl.VilleServiceImpl;
import fr.humanbooster.fx.englishbattle.util.ComparateurDeJoueurSurScore;
import fr.humanbooster.fx.englishbattle.util.ComparateurDeJoueurSurVille;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JoueursServlet
 */
@WebServlet("/joueurs")
public class JoueursServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private JoueurService joueurService;
    private VilleService villeService;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoueursServlet() {
        super();
        joueurService = new JoueurServiceImpl();
        villeService = new VilleServiceImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long idVilleRecherchee = null;
		String prenomRecherche = null;	
		List<Joueur> joueursCorrespondants = new ArrayList<>();
		List<Joueur> joueurs = joueurService.recupererJoueurs();
		int idTri = 1; // Par défaut on tri sur le meilleur score
		
		if (request.getParameter("PRENOM")!=null) {
			prenomRecherche = request.getParameter("PRENOM");
		}

		if (request.getParameter("ID_VILLE")!=null && !request.getParameter("ID_VILLE").equals("")) {
			idVilleRecherchee = Long.parseLong(request.getParameter("ID_VILLE"));
		}

		if (request.getParameter("ID_TRI")!=null) {
			idTri = Integer.parseInt(request.getParameter("ID_TRI"));
		}
		
		for (Joueur joueur : joueurs) {
			if ((idVilleRecherchee==null || joueur.getVille().getId().equals(idVilleRecherchee))
				&&
			    (prenomRecherche==null || joueur.getPrenom().toLowerCase().contains(prenomRecherche.toLowerCase()))) {
				joueursCorrespondants.add(joueur);
			}
		}
		
		// On tri soit sur la ville soit sur le meilleur score
		if (idTri==1) {
			Collections.sort(joueursCorrespondants, new ComparateurDeJoueurSurScore());
		}
		else {
			Collections.sort(joueursCorrespondants, new ComparateurDeJoueurSurVille());
		}

		request.setAttribute("joueurs", joueursCorrespondants);
		request.setAttribute("villes", villeService.recupererVilles());
		request.setAttribute("idVilleRecherchee", idVilleRecherchee);
		request.setAttribute("prenomRecherche", prenomRecherche);
		request.setAttribute("idTri", idTri);
		request.getRequestDispatcher("WEB-INF/joueurs.jsp").include(request, response);    
	}

}