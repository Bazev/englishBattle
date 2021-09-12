package fr.humanbooster.fx.englishbattle.servlets;

import java.io.IOException;
import java.util.Map;

import fr.humanbooster.fx.englishbattle.service.JoueurService;
import fr.humanbooster.fx.englishbattle.service.NiveauService;
import fr.humanbooster.fx.englishbattle.service.VilleService;
import fr.humanbooster.fx.englishbattle.service.impl.JoueurServiceImpl;
import fr.humanbooster.fx.englishbattle.service.impl.NiveauServiceImpl;
import fr.humanbooster.fx.englishbattle.service.impl.VilleServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * urlPatterns : URL(s) sur laquelle/lesquelles la servlet va écouter
 * Servlet implementation class InscriptionServlet
 */
@WebServlet(urlPatterns = {"/inscription", "/signin"}, loadOnStartup = 0)
public class InscriptionServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	// La servlet peut uniquement faire appel à des services
	private JoueurService joueurService = new JoueurServiceImpl();
	private NiveauService niveauService = new NiveauServiceImpl();
	private VilleService villeService = new VilleServiceImpl();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InscriptionServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// On enrichit l'objet request avec la liste des niveaux
		request.setAttribute("niveaux", niveauService.recupererNiveaux());
		
		// On enrichit l'objet request avec la liste des villes
		request.setAttribute("villes", villeService.recupererVilles());

		// On fait suivre à la JSP inscription.jsp
		request.getRequestDispatcher("WEB-INF/inscription.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		String email = request.getParameter("EMAIL");
		String nom = request.getParameter("NOM");
		String prenom = request.getParameter("PRENOM");
		String motDePasse = request.getParameter("MOT_DE_PASSE");
		*/
		// On récupère la map associée à la requête HTTP
		Map<String, String[]> map = request.getParameterMap();

		// On parcourt l'ensemble des clés de la map
		for (String cle : map.keySet()) {
			String[] tableauDeValeursPourCeParametre = (String[]) map.get(cle);
			for (String valeur : tableauDeValeursPourCeParametre) {
				System.out.println("Clé=" + cle + ", valeur=" + valeur);
			}
		}
		
		String nom = map.get("NOM")[0];
		String prenom = map.get("PRENOM")[0];
		String email = map.get("EMAIL")[0];
		String motDePasse = map.get("MOT_DE_PASSE")[0];
		Long idNiveau = null;
		Long idVille = null;
		
		// On vérifie d'abord que la map contient la clé ID_NIVEAU
		// si elle la contient, on vérifie qu'elle n'est pas égale à chaîne vide
		// Condition peu digeste, on la ré-écrit avec une des lois de De Morgan
		//if (!(!map.containsKey("ID_NIVEAU") || map.get("ID_NIVEAU")[0].equals(""))) {
		if (map.containsKey("ID_NIVEAU") && !map.get("ID_NIVEAU")[0].equals("")) {	
			idNiveau = Long.parseLong(map.get("ID_NIVEAU")[0]);
			
			// Est-ce que la ville a été renseignée ?
			//if (!(!map.containsKey("ID_VILLE") || map.get("ID_VILLE")[0].equals(""))) {
			if (map.containsKey("ID_VILLE") && !map.get("ID_VILLE")[0].equals("")) {
				// La ville a été renseignée
				idVille = Long.parseLong(map.get("ID_VILLE")[0]);
				joueurService.ajouterJoueur(email, nom, prenom, motDePasse, idNiveau, idVille);
				response.sendRedirect("index");
				return;
			}
		}
		
		request.setAttribute("nom", nom);
		request.setAttribute("prenom", prenom);
		request.setAttribute("email", email);
		request.setAttribute("motDePasse", motDePasse);
		request.setAttribute("idNiveau", idNiveau);
		request.setAttribute("idVille", idVille);
		request.setAttribute("niveaux", niveauService.recupererNiveaux());
		request.setAttribute("villes", villeService.recupererVilles());
		request.getRequestDispatcher("WEB-INF/inscription.jsp").forward(request, response);				

	}

}
