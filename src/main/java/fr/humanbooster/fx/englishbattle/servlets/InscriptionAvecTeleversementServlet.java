package fr.humanbooster.fx.englishbattle.servlets;

import java.io.IOException;
import java.util.Collection;

import fr.humanbooster.fx.englishbattle.business.Joueur;
import fr.humanbooster.fx.englishbattle.service.JoueurService;
import fr.humanbooster.fx.englishbattle.service.NiveauService;
import fr.humanbooster.fx.englishbattle.service.VilleService;
import fr.humanbooster.fx.englishbattle.service.impl.JoueurServiceImpl;
import fr.humanbooster.fx.englishbattle.service.impl.NiveauServiceImpl;
import fr.humanbooster.fx.englishbattle.service.impl.VilleServiceImpl;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/**
 * Servlet implementation class InscriptionServlet
 */
@WebServlet(urlPatterns = "/inscriptionAvecTeleversement", loadOnStartup=1)
@MultipartConfig
public class InscriptionAvecTeleversementServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	private JoueurService joueurService = new JoueurServiceImpl();
	private NiveauService niveauService = new NiveauServiceImpl();
	private VilleService villeService = new VilleServiceImpl();

	private String cheminDeBase = System.getProperty("catalina.base");
	private String separateur = System.getProperty("file.separator");
	private String nomApplication = "englishbattle20210807";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InscriptionAvecTeleversementServlet() {
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
		request.getRequestDispatcher("WEB-INF/inscriptionAvecTeleversement.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext context = request.getServletContext();
		String path = context.getRealPath("/");
		System.out.println(path);
		String nom = "null";
		String prenom = "null";
		String email = "null";
		String motDePasse = "null";
		Long idNiveau = null;
		Long idVille = null;

		Collection<Part> parts = request.getParts();
		for (Part part : parts) {
			switch (part.getName()) {
			case "NOM":
				nom = request.getParameter("NOM");
				break;
			case "PRENOM":
				prenom = request.getParameter("PRENOM");
				break;
			case "EMAIL":
				email = request.getParameter("EMAIL");
				break;
			case "MOT_DE_PASSE":
				motDePasse = request.getParameter("MOT_DE_PASSE");
				break;
			case "ID_VILLE":
				idVille = Long.parseLong(request.getParameter("ID_VILLE"));
				break;
			case "ID_NIVEAU":
				idNiveau = Long.parseLong(request.getParameter("ID_NIVEAU"));
				break;
			default:
				break;
			}
		}

		Joueur joueur = joueurService.ajouterJoueur(email, nom, prenom, motDePasse, idVille, idNiveau);

		String image = cheminDeBase + separateur + "wtpwebapps" + separateur + nomApplication + separateur
				+ "images" + separateur + joueur.getId() + ".jpg";

		for (Part part : parts) {
			switch (part.getName()) {
			case "FICHIER":
				part.write(image);
				break;

			}
		}
		response.sendRedirect("index");
	}
}