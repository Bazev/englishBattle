package fr.humanbooster.fx.englishbattle.dao;

public class Requetes {

        // Requetes des verbes
        public static final String AJOUT_VERBE = "INSERT INTO Verbe (baseVerbale, participePasse, preterit, traduction) VALUES (? , ? , ? , ?)";
        public static final String VERBE_PAR_ID = "SELECT id, baseVerbale, participePasse, preterit, traduction FROM Verbe WHERE id= ?";
        public static final String TOUS_LES_VERBES = "SELECT id, baseVerbale, participePasse, preterit, traduction FROM Verbe";
        public static final String UPDATE_VERBE = "UPDATE Verbe SET baseVerbale=?, participePasse=?, preterit=?, traduction=? WHERE id=?";
        public static final String VERBE_ALEATOIRE = "SELECT id, baseVerbale, participePasse, preterit, traduction FROM Verbe ORDER BY rand() LIMIT 1";
        public static final String SUPPRESSION_VERBE = "DELETE FROM Verbe WHERE id = ?";
        public static final String VERBE_COMPTER = "SELECT COUNT(*) AS compteur FROM Verbe";

        // Requetes des questions
        public static final String AJOUT_QUESTION = "INSERT INTO Question (partie_id, verbe_id, reponsePreterit, reponseParticipePasse, dateEnvoi, dateReponse) VALUES (?,?,?,?,?,?)";
        public static final String UPDATE_QUESTION = "UPDATE Question SET partie_id = ?, verbe_id = ?, reponsePreterit = ?, reponseParticipePasse = ?, dateEnvoi = ?, dateReponse = ? WHERE id = ?";
        public static final String QUESTION_PAR_ID = "SELECT id, partie_id, verbe_id, reponsePreterit, reponseParticipePasse, dateEnvoi, dateReponse FROM Question WHERE id = ?";
		public static final String TOUTES_LES_QUESTIONS_DE_LA_PARTIE = "SELECT id, partie_id, verbe_id, reponsePreterit, reponseParticipePasse, dateEnvoi, dateReponse FROM Question WHERE partie_id = ?";
        public static final String TOUS_LES_QUESTIONS = "SELECT id, partie_id, verbe_id, reponsePreterit, reponseParticipePasse, dateEnvoi, dateReponse FROM Question";
        public static final String SUPPRESSION_QUESTION = "DELETE FROM Question WHERE id = ?";

        // Requetes des niveaux
        public static final String AJOUT_NIVEAU = "INSERT INTO Niveau (nom) VALUES (?)";
        public static final String NIVEAU_PAR_ID = "SELECT id, nom FROM Niveau WHERE id=?";
        public static final String TOUS_LES_NIVEAUX = "SELECT id, nom FROM Niveau";

        // Requetes des joueurs
        public static final String AJOUT_JOUEUR = "INSERT INTO Joueur (email, motDePasse, nom, prenom, niveau_id, ville_id) VALUES (?,?,?,?,?,?)";
        public static final String UPDATE_JOUEUR = "UPDATE Joueur SET email = ?, motDePasse = ?, nom = ?, prenom = ?, niveau_id = ?, ville_id = ?, estEnLigne=? WHERE id = ?";
        public static final String JOUEUR_PAR_ID = "SELECT id, email, motDePasse, nom, prenom, niveau_id, ville_id, estEnLigne FROM Joueur WHERE id=?";
        public static final String TOUS_LES_JOUEURS = "SELECT id, email, motDePasse, nom, prenom, niveau_id, ville_id FROM Joueur";
        public static final String SUPPRESSION_JOUEUR = "DELETE FROM Joueur WHERE id = ?";

        // Requetes des villes
        public static final String AJOUT_VILLE = "INSERT INTO Ville (nom) VALUES (?)";
        public static final String VILLE_PAR_ID = "SELECT id, nom FROM Ville WHERE id=?";
        public static final String VILLE_PAR_NOM = "SELECT id, nom FROM Ville WHERE nom=?";
        public static final String TOUTES_LES_VILLES = "SELECT id, nom FROM Ville";
        public static final String SUPPRESSION_VILLE = "DELETE FROM Ville WHERE id=?";

        // Requetes des parties
        public static final String AJOUT_PARTIE = "INSERT INTO Partie (joueur_id) VALUES (?)";
        public static final String PARTIE_PAR_ID = "SELECT id, joueur_id FROM Partie WHERE id=?";
        public static final String TOUTES_LES_PARTIES = "SELECT id, joueur_id FROM Partie";
		public static final String TOUTES_LES_PARTIES_DU_JOUEUR = "SELECT id, joueur_id FROM Partie WHERE joueur_id=?";
        public static final String UPDATE_PARTIE = "UPDATE Partie SET joueur_id=? WHERE id =?";
        public static final String SUPPRESSION_PARTIE = "DELETE FROM Partie WHERE id=?";

}
