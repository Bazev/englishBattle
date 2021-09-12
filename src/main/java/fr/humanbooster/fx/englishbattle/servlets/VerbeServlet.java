package fr.humanbooster.fx.englishbattle.servlets;

import java.io.IOException;
import java.util.Map;

import fr.humanbooster.fx.englishbattle.business.Verbe;
import fr.humanbooster.fx.englishbattle.service.VerbeService;
import fr.humanbooster.fx.englishbattle.service.impl.VerbeServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VerbeServlet
 */
@WebServlet("/verbe")
public class VerbeServlet extends HttpServlet {
	
    private static final long serialVersionUID = 1L;
    private VerbeService verbeService = new VerbeServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerbeServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Long idVerbe = Long.parseLong(request.getParameter("ID"));
    	Verbe verbe = verbeService.recupererVerbe(idVerbe);
        request.setAttribute("verbe", verbe);
        request.getRequestDispatcher("WEB-INF/verbe.jsp").include(request, response);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();

        // On parcourt l'ensemble des clés de la map
        for (String cle : map.keySet()) {
            String[] tableauDeValeursPourCeParametre = (String[]) map.get(cle);
            for (String valeur : tableauDeValeursPourCeParametre) {
                System.out.println("Clé=" + cle + ", valeur=" + valeur);
            }
        }
        
        Long idVerbe = Long.parseLong(request.getParameter("ID"));
        String baseVerbale = map.get("BASE_VERBALE")[0];
        String preterit = map.get("PRETERIT")[0];
        String participePasse = map.get("PARTICIPE_PASSE")[0];
        String traduction = map.get("TRADUCTION")[0];
        
        verbeService.modifierVerbe(idVerbe, baseVerbale, preterit, participePasse, traduction);
        response.sendRedirect("verbes");
    }

}