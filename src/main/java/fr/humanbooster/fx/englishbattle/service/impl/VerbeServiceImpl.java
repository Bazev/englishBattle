package fr.humanbooster.fx.englishbattle.service.impl;

import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import fr.humanbooster.fx.englishbattle.business.Partie;
import fr.humanbooster.fx.englishbattle.business.Verbe;
import fr.humanbooster.fx.englishbattle.dao.VerbeDao;
import fr.humanbooster.fx.englishbattle.dao.impl.VerbeDaoImpl;
import fr.humanbooster.fx.englishbattle.exceptions.AucunVerbeException;
import fr.humanbooster.fx.englishbattle.service.PartieService;
import fr.humanbooster.fx.englishbattle.service.VerbeService;

public class VerbeServiceImpl implements VerbeService {

	private VerbeDao verbeDao = new VerbeDaoImpl();
	private PartieService partieService = new PartieServiceImpl();

	@Override
	public Verbe ajouterVerbe(String baseVerbale, String preterit, String participePasse, String traduction) {
		Verbe verbe = new Verbe(baseVerbale, preterit, participePasse, traduction);
		try {
			verbeDao.create(verbe);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return verbe;
	}

	@Override
	public Verbe ajouterVerbe(Verbe verbe) {
		try {
			return verbeDao.create(verbe);
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}

	@Override
	public List<Verbe> recupererVerbes() {
		try {
			return verbeDao.findAll();
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}

	@Override
	public Verbe recupererVerbe(Long id) {
		try {
			return verbeDao.findOne(id);
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}

	@Override
	public Verbe recupererVerbeAleatoire() {
		try {
			return verbeDao.findAleatoire();
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}

	@Override
	public Verbe modifierVerbe(Long idVerbe, String baseVerbale, String preterit, String participePasse,
			String traduction) {
		Verbe verbe = recupererVerbe(idVerbe);
		if (verbe==null) {
			return null;
		}
		verbe.setBaseVerbale(baseVerbale);
		verbe.setPreterit(preterit);
		verbe.setParticipePasse(participePasse);
		verbe.setTraduction(traduction);
		try {
			return verbeDao.update(verbe);
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public boolean importerVerbes() {

		System.out.println("Nb de verbes en base=" + recupererNbVerbes());
		if (recupererNbVerbes() == 0) {
			try {
				// On crée un objet de type URL
				URL url = new URL("https://www.clelia.fr/Battle/englishbattle161.csv");

				// On crée un lecteur à partir du flux d'entrée générée par l'URL
				// == méthode GET du protocole HTTP
				Reader reader = new InputStreamReader(new BufferedInputStream(url.openStream()), "UTF-8");

				// On déclare un format de CSV
				CSVFormat csvFormat = CSVFormat.DEFAULT.withDelimiter(',').withFirstRecordAsHeader();

				// On crée un parser en donnant en paramètre le reader et le format CSV
				CSVParser csvParser = new CSVParser(reader, csvFormat);

				// On parcourt le csvParser, on ajoute un objet de type Verbe à liste villes
				for (CSVRecord record : csvParser) {
					Verbe verbe = new Verbe(record.get(1), record.get(3), record.get(2), record.get(4));
					ajouterVerbe(verbe);
				}
				csvParser.close();
				reader.close();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	@Override
	public int recupererNbVerbes() {
		try {
			return verbeDao.count();
		} catch (SQLException e) {
			System.out.println(e);
			return 0;
		}
	}

	@Override
	public Verbe recupererVerbeAleatoire(Partie partie) throws AucunVerbeException {
		// Si la partie contient un nombre de verbes égale à la taille de la liste
		// verbes
		// lever l'exception AucunVerbeException: le joueur sait tout !!
		if (partie.getNbQuestions() == recupererVerbes().size())
			throw new AucunVerbeException("Perfect !");

		Verbe verbe = null;

		// un verbe, pas encore demandé dans la partie, est choisi de manière aléatoire
		do {
			verbe = recupererVerbeAleatoire();
		} while (partieService.estPresent(partie, verbe));

		return verbe;
	}

	@Override
	public boolean supprimerVerbe(Long id) {
		try {
			return verbeDao.delete(id);
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
	}

}
