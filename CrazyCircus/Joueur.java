package CrazyCircus;

import java.util.*;

public class Joueur {
    //stocke les scores des joueurs et le nom des joueurs
    private static HashMap<String, Integer> scores;

    //stocke le nom des joueurs qui ont déja joué
    private static ArrayList<String> dejaJoue;

    //stocke le nombre de joueur
    private static int nbrJoueurs;

    /**
     * @param args Construit des instances d'objets joueurs en fonction des arguments saisies dans le programme "CrazyCircus.Main"
     */
    public Joueur(String[] args) {
        nbrJoueurs = args.length;
        scores = new HashMap<String, Integer>();
        dejaJoue = new ArrayList<String>();
        for (int i = 0; i < nbrJoueurs; i++) {
            scores.put(args[i], 0);
        }
    }

    /**
     * @return le score de tout les joueurs
     */
    public static HashMap<String, Integer> getScores() {
        return scores;
    }

    /**
     * ajoute un point au joueur vainqueur de la manche
     * @param nomJoueur nom du joueur vainqueur de la manche
     */
    public static void ajouterScores(String nomJoueur) {
        scores.put(nomJoueur, scores.get(nomJoueur) + 1);
    }

    /**
     *
     * @return le nom des joueurs ayant déja joués
     */
    public static ArrayList<String> getDejaJoue() {
        return dejaJoue;
    }

    /**
     * @param nom du joueur ayant déja joué
     * ajoute le nom du joueur dans la liste des joueurs ayant déja joué
     */
    public static void setDejaJoue(String nom) {
        Joueur.dejaJoue.add(nom);
    }

    /**
     * @return le nombre de joueur
     */
    public static int getNbrJoueurs() {
        return nbrJoueurs;
    }


    /**
     * affiche le gagnant ainsi que la classement de la partie
     */
    public static void afficherGagnant() {
        // trier les entrées de la hashmap par score décroissant, avec un tri alphabétique des noms de joueurs en cas d'égalité
        List<Map.Entry<String, Integer>> list = new ArrayList<>(scores.entrySet());

        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                Map.Entry<String, Integer> entry1 = list.get(i);
                Map.Entry<String, Integer> entry2 = list.get(j);
                int score1 = entry1.getValue();
                int score2 = entry2.getValue();
                if (score2 > score1 || (score2 == score1 && entry2.getKey().compareTo(entry1.getKey()) < 0)) {
                    list.set(i, entry2);
                    list.set(j, entry1);
                }
            }
        }

        System.out.println("Classement:");
        int rank = 1;
        for (Map.Entry<String, Integer> entry : list) {
            System.out.println(rank++ + ". " + entry.getKey() + ": " + entry.getValue() + " point" + (entry.getValue() <= 1 ? "" : "s"));
        }
    }
}
