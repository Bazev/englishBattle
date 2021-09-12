package fr.humanbooster.fx.englishbattle.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import fr.humanbooster.fx.englishbattle.business.Partie;
import fr.humanbooster.fx.englishbattle.business.Question;
import fr.humanbooster.fx.englishbattle.business.Verbe;
import fr.humanbooster.fx.englishbattle.dao.ConnexionBdd;
import fr.humanbooster.fx.englishbattle.dao.PartieDao;
import fr.humanbooster.fx.englishbattle.dao.QuestionDao;
import fr.humanbooster.fx.englishbattle.dao.Requetes;
import fr.humanbooster.fx.englishbattle.dao.VerbeDao;

public class QuestionDaoImpl implements QuestionDao {
	
	// ----------------------------- Attributs ----------------------------------
	private static Connection connection;
	private static VerbeDao verbeDao = new VerbeDaoImpl();
	private static PartieDao partieDao = new PartieDaoImpl();
	
	
	// ----------------------------- Constructeurs ------------------------------
	public QuestionDaoImpl() {
		try {
			connection = ConnexionBdd.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Question create(Question question) throws SQLException {
		PreparedStatement ps = initialiseStatement(Requetes.AJOUT_QUESTION, question);
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		if (rs.next()) {
			question.setId(rs.getLong(1));
		}
		return question;
	}

	@Override
	public List<Question> findAll() throws SQLException {
        List<Question> questions = new ArrayList<>();
        PreparedStatement ps = connection.prepareStatement(Requetes.TOUS_LES_QUESTIONS);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            questions.add(findOne(rs.getLong("id")));
        }
        return questions;
	}

	@Override
	public Question findOne(Long id) throws SQLException {
		Question question = null;
        PreparedStatement ps = connection.prepareStatement(Requetes.QUESTION_PAR_ID);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
        	Partie partie = partieDao.findOne(rs.getLong("partie_id"));
        	Verbe verbe = verbeDao.findOne(rs.getLong("verbe_id"));
            question = new Question(partie, verbe);
            question.setId(id);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(rs.getTimestamp("dateEnvoi"));
            question.setDateEnvoi(calendar.getTime());
            if (rs.getDate("dateReponse")!=null) {
            	calendar.setTime(rs.getTimestamp("dateReponse"));
            	question.setDateReponse(calendar.getTime());
            }
            question.setReponsePreterit(rs.getString("reponsePreterit"));
            question.setReponseParticipePasse(rs.getString("reponseParticipePasse"));
        }
        return question;
	}

	@Override
	public List<Question> findByPartie(Partie partie) throws SQLException {
		List<Question> questions = new ArrayList<>();
        PreparedStatement ps = connection.prepareStatement(Requetes.TOUTES_LES_QUESTIONS_DE_LA_PARTIE);
        ps.setLong(1, partie.getId());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
        	Verbe verbe = verbeDao.findOne(rs.getLong("verbe_id"));
            Question question = new Question(partie, verbe);
            question.setId(rs.getLong("id"));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(rs.getTimestamp("dateEnvoi"));
            question.setDateEnvoi(calendar.getTime());
            if (rs.getDate("dateReponse")!=null) {
            	calendar.setTime(rs.getTimestamp("dateReponse"));
            	question.setDateReponse(calendar.getTime());
            }
            question.setReponsePreterit(rs.getString("reponsePreterit"));
            question.setReponseParticipePasse(rs.getString("reponseParticipePasse"));
            questions.add(question);
        }
        return questions;
	}

	@Override
	public Question update(Long id, Question question) throws SQLException {
		PreparedStatement ps = initialiseStatement(Requetes.UPDATE_QUESTION, question);
		ps.setLong(7, id);
		ps.executeUpdate();
		return question;
	}

	@Override
	public boolean delete(Long id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Requetes.SUPPRESSION_QUESTION);
		ps.setLong(1, id);
		try {
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private PreparedStatement initialiseStatement(String requete, Question question) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
		Timestamp dateEnvoie = new java.sql.Timestamp(question.getDateEnvoi().getTime());
		Timestamp dateReponse = null;
		if (question.getDateReponse()!=null) {
			dateReponse = new java.sql.Timestamp(question.getDateReponse().getTime());
		}
		ps.setLong(1, question.getPartie().getId());
		ps.setLong(2, question.getVerbe().getId());
		ps.setString(3, question.getReponsePreterit());
		ps.setString(4, question.getReponseParticipePasse());
		ps.setTimestamp(5, dateEnvoie);
		ps.setTimestamp(6, dateReponse);
		return ps;
	}	

}