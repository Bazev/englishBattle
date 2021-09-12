package fr.humanbooster.fx.englishbattle.servlets;

import java.util.Date;

import fr.humanbooster.fx.englishbattle.service.NiveauService;
import fr.humanbooster.fx.englishbattle.service.VerbeService;
import fr.humanbooster.fx.englishbattle.service.VilleService;
import fr.humanbooster.fx.englishbattle.service.impl.NiveauServiceImpl;
import fr.humanbooster.fx.englishbattle.service.impl.VerbeServiceImpl;
import fr.humanbooster.fx.englishbattle.service.impl.VilleServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet(urlPatterns = "/init", loadOnStartup = 1)
public class InitServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private VerbeService verbeService = new VerbeServiceImpl();
	private NiveauService niveauService = new NiveauServiceImpl();
	private VilleService villeService = new VilleServiceImpl();

	public InitServlet() {
		System.out.println(new Date() + " constructeur InitServlet");
	}
	
	@Override
	public void init() throws ServletException {

		verbeService.importerVerbes();
		
		if (villeService.recupererVilles().isEmpty()) {
			villeService.ajouterVille("Lyon");
			villeService.ajouterVille("Saint-Galmier");
			villeService.ajouterVille("Clermont-Ferrand");
		}

		if (niveauService.recupererNiveaux().isEmpty()) {
			niveauService.ajouterNiveau("Débutant");
			niveauService.ajouterNiveau("Intermédiaire");
			niveauService.ajouterNiveau("Expert");
		}

	}

}