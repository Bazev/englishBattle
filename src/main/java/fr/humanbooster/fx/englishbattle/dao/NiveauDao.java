package fr.humanbooster.fx.englishbattle.dao;

import java.sql.SQLException;
import java.util.List;

import fr.humanbooster.fx.englishbattle.business.Niveau;


public interface NiveauDao {

	Niveau create(Niveau niveau) throws SQLException;
	List<Niveau> findAll() throws SQLException;
	Niveau findOne(Long id) throws SQLException;

	
	
	
	}
	

