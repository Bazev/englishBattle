package fr.humanbooster.fx.englishbattle.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.humanbooster.fx.englishbattle.business.Verbe;
import fr.humanbooster.fx.englishbattle.dao.ConnexionBdd;
import fr.humanbooster.fx.englishbattle.dao.Requetes;
import fr.humanbooster.fx.englishbattle.dao.VerbeDao;

public class VerbeDaoImpl implements VerbeDao {

	private static Connection connection;

	public VerbeDaoImpl() {
		try {
			connection = ConnexionBdd.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Verbe create(Verbe verbe) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Requetes.AJOUT_VERBE, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, verbe.getBaseVerbale());
		ps.setString(2, verbe.getParticipePasse());
		ps.setString(3, verbe.getPreterit());
		ps.setString(4, verbe.getTraduction());
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		Verbe newVerbe = new Verbe(verbe.getBaseVerbale(), verbe.getPreterit(), verbe.getParticipePasse(), verbe.getTraduction());
		if (rs.next()) {
			newVerbe.setId(rs.getLong(1));
		}
		return newVerbe;
	}

	@Override
	public List<Verbe> findAll() throws SQLException {
		List<Verbe> verbes = new ArrayList<>();
		PreparedStatement ps = connection.prepareStatement(Requetes.TOUS_LES_VERBES);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Verbe verbe = new Verbe(rs.getString("baseVerbale"), rs.getString("preterit"), rs.getString("participePasse"), rs.getString("traduction"));
			verbe.setId(rs.getLong("id"));
			verbes.add(verbe);
		}
		return verbes;
	}

	@Override
	public Verbe findOne(Long id) throws SQLException {
		Verbe Verbe2 = null;
		PreparedStatement ps = connection.prepareStatement(Requetes.VERBE_PAR_ID);
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			Verbe2 = new Verbe(rs.getString("baseVerbale"), rs.getString("preterit"), rs.getString("participePasse"), rs.getString("traduction"));
			Verbe2.setId(rs.getLong("id"));
		}
		return Verbe2;
	}

	@Override
	public Verbe findAleatoire() throws SQLException {
		Verbe Verbe2 = null;
		PreparedStatement ps = connection.prepareStatement(Requetes.VERBE_ALEATOIRE);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			Verbe2 = new Verbe(rs.getString("baseVerbale"), rs.getString("preterit"), rs.getString("participePasse"), rs.getString("traduction"));
			Verbe2.setId(rs.getLong("id"));
		}
		return Verbe2;
	}

	@Override
	public Verbe update(Verbe verbe) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Requetes.UPDATE_VERBE);
		ps.setString(1,verbe.getBaseVerbale());
		ps.setString(2, verbe.getParticipePasse());
		ps.setString(3, verbe.getPreterit());
		ps.setString(4, verbe.getTraduction());
		ps.setLong(5, verbe.getId());
		ps.executeUpdate();		
		return verbe;
	}

	@Override
	public boolean delete(Long id) throws SQLException {
		Verbe verbe = findOne(id);
		if (verbe==null) {
			return false;
		}
		PreparedStatement ps = connection.prepareStatement(Requetes.SUPPRESSION_VERBE);
		ps.setLong(1, id);
		ps.executeUpdate();
		return true;
	}

	@Override
	public int count() throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Requetes.VERBE_COMPTER);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return rs.getInt("compteur");
		}
		return 0;
	}

}
