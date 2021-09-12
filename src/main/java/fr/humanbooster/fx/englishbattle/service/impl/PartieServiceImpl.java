package fr.humanbooster.fx.englishbattle.service.impl;

import java.sql.SQLException;
import java.util.List;


import fr.humanbooster.fx.englishbattle.service.PartieService;
import fr.humanbooster.fx.englishbattle.business.Joueur;
import fr.humanbooster.fx.englishbattle.business.Partie;
import fr.humanbooster.fx.englishbattle.business.Question;
import fr.humanbooster.fx.englishbattle.business.Verbe;
import fr.humanbooster.fx.englishbattle.dao.PartieDao;
import fr.humanbooster.fx.englishbattle.dao.impl.PartieDaoImpl;

public class PartieServiceImpl implements PartieService {

	private PartieDao partieDao = new PartieDaoImpl();
	
	@Override
	public Partie ajouterPartie(Joueur joueur) {
		try {
			return partieDao.create(new Partie(joueur));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Partie recupererPartie(Long id) {
		Partie partie = null;
		try {
			partie = partieDao.findOne(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return partie;
	}

	@Override
	public List<Partie> recupererParties() {
		try {
			return partieDao.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

    @Override
    public boolean estPresent(Partie partie, Verbe verbe) {

            for (Question question : partie.getQuestions()) {
                    if (question.getVerbe().getBaseVerbale().equals(verbe.getBaseVerbale())) {
                            return true;
                    }
            }

            return false;
    }

	@Override
	public boolean supprimerPartie(Long id) {
		try {
			return partieDao.delete(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
