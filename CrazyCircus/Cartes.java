package CrazyCircus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

public class Cartes {

    //contient la carte du podium bleu
    private static LinkedList<String[]> JeuCarteTourBleu;

    //contient la carte du podium rouge
    private static LinkedList<String[]> JeuCarteTourRouge;

    //génère un nombre alétoire pour tirer une carte/configuration
    private static Random rand = new Random();

    /**
     * @return la carte tirée pour le podium bleu
     */
    public static LinkedList<String[]> getJeuCarteTourBleu() {
        return JeuCarteTourBleu;
    }

    /**
     * @return la carte tirée pour le podium rouge
     */
    public static LinkedList<String[]> getJeuCarteTourRouge() {
        return JeuCarteTourRouge;
    }

    /**
     * @param fichier Créer les instances de CrazyCircus.Cartes en fonction du fichier texte
     */
    public Cartes(String fichier) {
        //initialisation des cartes , créations de tableaux
        JeuCarteTourBleu = new LinkedList<>();
        JeuCarteTourRouge = new LinkedList<>();

        try {
            BufferedReader lecteur = new BufferedReader(new FileReader(fichier));
            String ligne;
            boolean premiereListe = true;
            while ((ligne = lecteur.readLine()) != null) {
                if (ligne.equals("----")) {
                    premiereListe = true;
                } else {
                    String[] elements = ligne.split(",");
                    if (premiereListe) {
                        JeuCarteTourBleu.add(elements);
                        premiereListe = false;
                    } else {
                        JeuCarteTourRouge.add(elements);
                    }
                }
            }
            lecteur.close();
        } catch (IOException e) {
            System.err.println("Erreur de lecture du fichier : " + e.getMessage());
        }

    }

    /**
     * @param index supprime la carte tirée aléatoirement grâce à son index
     */
    public static void deleteCarte(int index) {
        JeuCarteTourBleu.remove(index);
        JeuCarteTourRouge.remove(index);
    }

    /**
     * @return si les cartes sont vides
     */
    public static boolean isEmpty() {
        return JeuCarteTourBleu.size() == 0 && JeuCarteTourRouge.size() == 0;
    }

    /**
     * génère un indice aléatoire
     * @return chiffre au hasard entre 0 et inférieur au nombre de carte
     */
    public static int aleatoire() {
        int taille = JeuCarteTourBleu.size();
        return rand.nextInt(taille);
    }
}

