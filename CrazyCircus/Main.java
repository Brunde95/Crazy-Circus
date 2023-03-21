package CrazyCircus;

import java.util.Scanner;

public class Main {
    /**
     * @param args Les paramètres reçus en ligne de commande sont le nom des joueurs
     *             avec le quel on peut fixer le nombre de joueurs
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Le nombre de joueurs est incorrect");
            return;
        }
        //contient le nombres de joueurs en fonction du nombre d'arguments entrés dans le main
        Joueur tousJoueurs = new Joueur(args);

        Scanner scanner = new Scanner(System.in);

        Cartes EnsembleCarte = new Cartes("C:\\Users\\brunt\\Documents\\Projet IntelliJ IDEA\\" +
                "Projet Crazy Circus\\src\\CrazyCircus\\JeuDeCarte.txt");
        //crée une instance tour du type podium
        Podium Tour = new Podium();
        //stocke l'indice tiré au hasard
        int index = Cartes.aleatoire();
        //ajoute la carte dans la configuration de départ
        Podium.ajouterDepart(index, EnsembleCarte);

        while (Cartes.isEmpty() == false) {
            //stocke le nouvel indice tiré au hasard
            index = Cartes.aleatoire();
            // ajoute la nouvelle carte dans la configuration d'arrivée souhaitée
            Podium.ajouterArrivee(index, EnsembleCarte);
            boolean mancheTerminee = false;

            Podium.affichage();

            while (mancheTerminee == false) {
                if (Joueur.getDejaJoue().size() == Joueur.getNbrJoueurs() - 1) {
                    for (String key : Joueur.getScores().keySet()) {
                        if (!tousJoueurs.getDejaJoue().contains(key)) {
                            mancheTerminee = true;

                            Podium.clone(Podium.getPodiumsFinalBleu(), Podium.getPodiumsBleu());
                            Podium.clone(Podium.getPodiumsBleu(), Podium.getPodiumsTestBleu());
                            Podium.getPodiumsFinalBleu().clear();

                            Podium.clone(Podium.getPodiumsFinalRouge(), Podium.getPodiumsRouge());
                            Podium.clone(Podium.getPodiumsRouge(), Podium.getPodiumsTestRouge());
                            Podium.getPodiumsFinalRouge().clear();

                            Joueur.getDejaJoue().clear();
                            Joueur.ajouterScores(key);
                            System.out.println("Le joueur " + key + " étant le seul joueur à ne pas avoir joué, il remporte la manche");
                            break;
                        }
                    }
                } else {
                    String ligne = scanner.nextLine();
                    String[] mancheEnCours = ligne.split(" ");
                    String nomJoueurEnCours = mancheEnCours[0];
                    String commandeEnCours = mancheEnCours[1];

                    if (Joueur.getScores().containsKey(nomJoueurEnCours)) {
                        if (Joueur.getDejaJoue().contains(nomJoueurEnCours)) {
                            System.out.println("Le joueur " + nomJoueurEnCours + " a déjà joué");
                        } else {
                            String[] commandesTableau = new String[(commandeEnCours.length() / 2)];

                            for (int i = 0; i < commandesTableau.length; i++) {
                                commandesTableau[i] = commandeEnCours.substring(i * 2, i * 2 + 2).toUpperCase();
                            }

                            boolean commandesValides = true;
                            for (String commande : commandesTableau) {
                                if (!(commande.equals("KI") || commande.equals("LO") || commande.equals("SO") ||
                                        commande.equals("NI") || commande.equals("MA"))) {
                                    commandesValides = false;
                                    break;
                                }
                            }

                            if (commandesValides) {
                                for (String commande : commandesTableau) {
                                    //permet d'identier les différents ordres KI,LO,SO,NI,MA
                                    switch (commande) {
                                        case "KI":
                                            Podium.KI();
                                            break;
                                        case "LO":
                                            Podium.LO();
                                            break;
                                        case "SO":
                                            Podium.SO();
                                            break;
                                        case "NI":
                                            Podium.NI();
                                            break;
                                        case "MA":
                                            Podium.MA();
                                            break;
                                    }
                                }

                                if (Podium.verifierPodium()) {
                                    mancheTerminee = true;

                                    Podium.clone(Podium.getPodiumsFinalBleu(), Podium.getPodiumsBleu());
                                    Podium.clone(Podium.getPodiumsBleu(), Podium.getPodiumsTestBleu());
                                    Podium.getPodiumsFinalBleu().clear();

                                    Podium.clone(Podium.getPodiumsFinalRouge(), Podium.getPodiumsRouge());
                                    Podium.clone(Podium.getPodiumsRouge(), Podium.getPodiumsTestRouge());
                                    Podium.getPodiumsFinalRouge().clear();

                                    Joueur.getDejaJoue().clear();
                                    Joueur.ajouterScores(nomJoueurEnCours);
                                    //le joueur a entré la bonne combinaison
                                    System.out.println("Le joueur " + nomJoueurEnCours + " a trouvé la bonne combinaison");
                                    break;
                                } else {
                                    Podium.clone(Podium.getPodiumsBleu(), Podium.getPodiumsTestBleu());
                                    Podium.clone(Podium.getPodiumsRouge(), Podium.getPodiumsTestRouge());

                                    if (!tousJoueurs.getDejaJoue().contains(nomJoueurEnCours)) {
                                        Joueur.setDejaJoue(nomJoueurEnCours);
                                    }
                                    // le joueur s'est trompé de combinaison
                                    System.out.println("Le joueur " + nomJoueurEnCours + " n'a pas fourni la bonne combinaison ");
                                }

                            } else {
                                System.out.println("Au moins une commande saisie n'est pas valide.");
                                if (!tousJoueurs.getDejaJoue().contains(nomJoueurEnCours)) {
                                    Joueur.setDejaJoue(nomJoueurEnCours);
                                }
                            }
                        }
                    } else {
                        // le joueur qui n'existe pas (n'a pas entré son nom dans les arguments)
                        System.out.println("Le joueur " + nomJoueurEnCours + " n'existe pas.");
                    }
                }
            }
        }
        tousJoueurs.afficherGagnant();
    }
}
