package CrazyCircus;

import java.util.LinkedList;

/**
 *
 */
public class Podium {

    // podium de configuration de départ
    private static LinkedList<String> podiumsBleu;
    private static LinkedList<String> podiumsRouge;

    // podium de configuration d'arrivée
    private static LinkedList<String> podiumsFinalBleu;
    private static LinkedList<String> podiumsFinalRouge;

    // copie des podiums initiaux podiums modifiés
    private static LinkedList<String> podiumsTestBleu;
    private static LinkedList<String> podiumsTestRouge;

    /**
     * @return le podium de départ bleu
     */
    public static LinkedList<String> getPodiumsBleu() {
        return podiumsBleu;
    }

    /**
     * @return le podium de départ rouge
     */
    public static LinkedList<String> getPodiumsRouge() {
        return podiumsRouge;
    }

    /**
     * @return le podium final bleu
     */
    public static LinkedList<String> getPodiumsFinalBleu() {
        return podiumsFinalBleu;
    }
    /**
     * @return le podium final rouge
     */
    public static LinkedList<String> getPodiumsFinalRouge() {
        return podiumsFinalRouge;
    }

    /**
     * @return une copie podium bleu qui va servir de test
     */
    public static LinkedList<String> getPodiumsTestBleu() {
        return podiumsTestBleu;
    }
    /**
     * @return une copie podium bleu qui va servir de test
     */
    public static LinkedList<String> getPodiumsTestRouge() {
        return podiumsTestRouge;
    }
    /**
     * Constructeur des podiums
     */
    public Podium() {
        podiumsBleu = new LinkedList<>();
        podiumsRouge = new LinkedList<>();
        podiumsFinalBleu = new LinkedList<>();
        podiumsFinalRouge = new LinkedList<>();
        podiumsTestBleu = new LinkedList<>();
        podiumsTestRouge = new LinkedList<>();
    }

    /**
     * @param index indice de la carte à ajouter dans la configuration de départ
     * @param cartes cartes à ajouter dans la configuration de départ
     */
    public static void ajouterDepart(int index, Cartes cartes) {
        String[] podiumBleu = cartes.getJeuCarteTourBleu().get(index)[0].split(" ");
        String[] podiumRouge = cartes.getJeuCarteTourRouge().get(index)[0].split(" ");
        for (int i = 0; i < podiumBleu.length; i++) {
            if (podiumBleu[i].equals("") == false) {
                podiumsBleu.add(podiumBleu[i]);
                podiumsTestBleu.add(podiumBleu[i]);
            }
        }
        for (int i = 0; i < podiumRouge.length; i++) {
            if (podiumRouge[i].equals("") == false) {
                podiumsRouge.add(podiumRouge[i]);
                podiumsTestRouge.add(podiumRouge[i]);
            }
        }
        Cartes.deleteCarte(index);
    }

    /**
     * @param index indice de la carte à ajouter dans la configuration souhaitée
     * @param cartes cartes à ajouter dans la configuration d'arrivée souhaitée
     */
    public static void ajouterArrivee(int index, Cartes cartes) {
        String[] podiumBleu = cartes.getJeuCarteTourBleu().get(index)[0].split(" ");
        String[] podiumRouge = cartes.getJeuCarteTourRouge().get(index)[0].split(" ");
        for (int i = 0; i < podiumBleu.length; i++) {
            if (podiumBleu[i].equals("") == false) {
                podiumsFinalBleu.add(podiumBleu[i]);
            }
        }
        for (int i = 0; i < podiumRouge.length; i++) {
            if (podiumRouge[i].equals("") == false) {
                podiumsFinalRouge.add(podiumRouge[i]);
            }
        }
        Cartes.deleteCarte(index);
    }

    /**
     * @param original le podium de départ (bleu ou rouge)
     * @param copie le podium qui va subir les déplacements/tests (bleu ou rouge)
     */
    public static void clone(LinkedList<String> original, LinkedList<String> copie) {
        copie.clear();
        copie.addAll(original);
    }

    /**
     * déplace l'animal qui se trouve en haut du podium bleu au sommet du podium rouge
     */
    public static void KI() {
        if (podiumsTestBleu.size() > 0) {
            podiumsTestRouge.addFirst(podiumsTestBleu.removeFirst());
        }
    }
    /**
     * déplace l'animal qui se trouve en haut du podium rouge au sommet du podium bleu
     */
    public static void LO() {
        if (podiumsTestRouge.size() > 0) {
            podiumsTestBleu.addFirst(podiumsTestRouge.removeFirst());
        }
    }


    /**
     * échange les animaux qui se trouve au sommet des deux podiums
     */
    public static void SO() {
        if (podiumsTestRouge.size() > 0 && podiumsTestBleu.size() > 0) {
            String tempSommetRouge = podiumsTestRouge.removeFirst();
            String tempSommetBleu = podiumsTestBleu.removeFirst();
            podiumsTestBleu.addFirst(tempSommetRouge);
            podiumsTestRouge.addFirst(tempSommetBleu);
        }
    }
    /**
     * déplace l'animal qui se trouve en bas du podium bleu au sommet de cette dernière
     */
    public static void NI() {
        if (podiumsTestBleu.size() > 1) {
            podiumsTestBleu.addFirst(podiumsTestBleu.removeLast());
        }
    }
    /**
     * déplace l'animal qui se trouve en bas du podium rouge au sommet de cette dernière
     */
    public static void MA() {
        if (podiumsTestRouge.size() > 1) {
            podiumsTestRouge.addFirst(podiumsTestRouge.removeLast());
        }
    }

    /**
     * @return compare la taille des podiums qui ont subis
     * des ordres avec la configuration d'arrivée souhaité
     */
    public static boolean verifierPodium() {
        if (podiumsFinalRouge.size() != podiumsTestRouge.size() || podiumsFinalBleu.size() != podiumsTestBleu.size()) {
            return false;
        }

        for (int i = 0; i < podiumsFinalRouge.size(); i++) {
            if (podiumsFinalRouge.get(i).equals(podiumsTestRouge.get(i)) == false) {
                return false;
            }
        }

        for (int i = 0; i < podiumsFinalBleu.size(); i++) {
            if (podiumsFinalBleu.get(i).equals(podiumsTestBleu.get(i)) == false) {
                return false;
            }
        }

        return true;
    }

    /**
     * @return la longueur maximum entre les deux podiums de la meme configuration
     */
    public static int longueurMax() {
        int max1 = Math.max(podiumsBleu.size(),podiumsRouge.size());
        int max2 = Math.max(podiumsFinalBleu.size(),podiumsFinalRouge.size());
        return Math.max(max1,max2);
    }

    /**
     * affiche les podiums ainsi qu'un rappel des ordres
     */
    public static void affichage() {
        int maximum = longueurMax();
        for (int i = 0; i < maximum; i++) {
            if (i < podiumsBleu.size()) {
                if (podiumsBleu.get(i).equals("OURS")) {
                    System.out.print("  " + podiumsBleu.get(i) + "  ");
                }
                if (podiumsBleu.get(i).equals("LION")) {
                    System.out.print("  " + podiumsBleu.get(i) + "  ");
                }
                if (podiumsBleu.get(i).equals("ELEPHANT")) {
                    System.out.print(podiumsBleu.get(i));
                }
            } else {
                System.out.print("        ");
            }

            if (i < podiumsRouge.size()) {
                if (podiumsRouge.get(i).equals("OURS")) {
                    System.out.print("   " + podiumsRouge.get(i) + "         ");
                } else if (podiumsRouge.get(i).equals("LION")) {
                    System.out.print("   " + podiumsRouge.get(i) + "         ");
                } else if (podiumsRouge.get(i).equals("ELEPHANT")) {
                    System.out.print(" " + podiumsRouge.get(i) + "       ");
                }
            } else {
                System.out.print("                ");
            }

            if (i < podiumsFinalBleu.size()) {
                if (podiumsFinalBleu.get(i).equals("OURS")) {
                    System.out.print("  " + podiumsFinalBleu.get(i) + "  ");
                } else if (podiumsFinalBleu.get(i).equals("LION")) {
                    System.out.print("  " + podiumsFinalBleu.get(i) + "  ");
                } else if (podiumsFinalBleu.get(i).equals("ELEPHANT")) {
                    System.out.print(podiumsFinalBleu.get(i));
                }
            } else {
                System.out.print("        ");
            }

            if (i < podiumsFinalRouge.size()) {
                if (podiumsFinalRouge.get(i).equals("OURS")) {
                    System.out.println("   " + podiumsFinalRouge.get(i));
                } else if (podiumsFinalRouge.get(i).equals("LION")) {
                    System.out.println("   " + podiumsFinalRouge.get(i));
                } else if (podiumsFinalRouge.get(i).equals("ELEPHANT")) {
                    System.out.println(" " + podiumsFinalRouge.get(i));
                }
            } else {
                System.out.println("        ");
            }
        }
        System.out.println("  ----     ----    ==>    ----     ----");
        System.out.println("  BLEU     ROUGE          BLEU     ROUGE");
        System.out.println("------------------------------------------");
        System.out.println("KI : BLEU --> ROUGE    NI : BLEU  ^");
        System.out.println("LO : BLEU <-- ROUGE    MA : ROUGE ^");
        System.out.println("SO : BLEU <-> ROUGE");
    }
}

