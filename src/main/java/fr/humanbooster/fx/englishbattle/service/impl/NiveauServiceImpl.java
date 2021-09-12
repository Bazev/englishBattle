package fr.humanbooster.fx.englishbattle.service.impl;

import java.sql.SQLException;
import java.util.List;

import fr.humanbooster.fx.englishbattle.business.Niveau;
import fr.humanbooster.fx.englishbattle.dao.NiveauDao;
import fr.humanbooster.fx.englishbattle.dao.impl.NiveauDaoImpl;
import fr.humanbooster.fx.englishbattle.service.NiveauService;

public class NiveauServiceImpl implements NiveauService {

	private NiveauDao niveauDao = new NiveauDaoImpl();

	@Override
	public Niveau ajouterNiveau(String nom) {
		Niveau niveau = new Niveau(nom);
		try {
			niveauDao.create(niveau);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return niveau;
	}

	@Override
	public Niveau recupererNiveau(Long id) {
		try {
			return niveauDao.findOne(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Niveau> recupererNiveaux() {
		try {
			return niveauDao.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
