package fr.humanbooster.fx.englishbattle.service.impl;

import java.sql.SQLException;
import java.util.List;

import fr.humanbooster.fx.englishbattle.business.Ville;
import fr.humanbooster.fx.englishbattle.dao.VilleDao;
import fr.humanbooster.fx.englishbattle.dao.impl.VilleDaoImpl;
import fr.humanbooster.fx.englishbattle.service.VilleService;


public class VilleServiceImpl implements VilleService {

	private VilleDao villeServiceDao = new VilleDaoImpl();

	@Override
	public Ville ajouterVille(String nom) {
		Ville Ville = new Ville(nom);
		try {
			villeServiceDao.create(Ville);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Ville;
	}

	@Override
	public Ville recupererVille(Long id) {
		try {
			return villeServiceDao.findOne(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Ville> recupererVilles() {
		try {
			return villeServiceDao.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean supprimerVille(Long id) {
		
		try {
			return villeServiceDao.delete(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	
}
