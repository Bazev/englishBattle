package fr.humanbooster.fx.englishbattle.util;
 
import java.util.Comparator;
 
import fr.humanbooster.fx.englishbattle.business.Joueur;
 
public class ComparateurDeJoueurSurScore implements Comparator<Joueur> {
 
    @Override
    public int compare(Joueur j1, Joueur j2) {
        if (j1.getMeilleurScore()==j2.getMeilleurScore()) { return 0; }
        else if (j1.getMeilleurScore()>j2.getMeilleurScore()) {return -1;}
        else return 1;
    }
 
}