package infosi.partielmedev;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Interface graphique console pour le jeu du pendu
 * Gère l'affichage de la potence, du mot, et des lettres utilisées
 */
public class Interface {
    
    private static final int ERREURS_MAX = 7;
    
    // Ensemble des lettres de l'alphabet en majuscules
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    /**
     * Affiche l'état complet du jeu du pendu
     * @param motSecret Le mot à deviner (sera masqué)
     * @param lettresDecouvertes Les lettres correctement devinées
     * @param lettresErronees Les lettres incorrectes
     * @param nombreErreurs Le nombre d'erreurs commises
     */
    public void afficherJeu(String motSecret, Set<Character> lettresDecouvertes, 
                           Set<Character> lettresErronees, int nombreErreurs) {
        System.out.println("\n".repeat(2)); //NOSONAR
        System.out.println("═══════════════════════════════════════════════════════════"); //NOSONAR
        System.out.println("                    JEU DU PENDU                           "); //NOSONAR
        System.out.println("═══════════════════════════════════════════════════════════"); //NOSONAR
        System.out.println(); //NOSONAR
        
        // Affiche la potence et le bonhomme
        afficherPotence(nombreErreurs);
        
        System.out.println(); //NOSONAR
        System.out.println("───────────────────────────────────────────────────────────"); //NOSONAR
        
        // Affiche le mot avec underscores et lettres découvertes
        afficherMot(motSecret, lettresDecouvertes);
        
        System.out.println(); //NOSONAR
        System.out.println("───────────────────────────────────────────────────────────"); //NOSONAR
        
        // Affiche les lettres utilisées correctes
        afficherLettresCorrectes(lettresDecouvertes);
        
        // Affiche les lettres utilisées erronées
        afficherLettresErronees(lettresErronees, nombreErreurs);
        
        // Affiche les lettres restantes
        afficherLettresRestantes(lettresDecouvertes, lettresErronees);
        
        System.out.println("═══════════════════════════════════════════════════════════"); //NOSONAR
    }
    
    /**
     * Affiche la potence et le bonhomme selon le nombre d'erreurs
     * @param nombreErreurs Nombre d'erreurs (0 à 7)
     */
    private void afficherPotence(int nombreErreurs) {
        String[] etapes = {
            // 0 erreur
            """
                
                    
                    
                    
                    
                    
            ═════╩═════
            """,
            // 1 erreur - mat de la potence
            """
                
                ║    
                ║    
                ║    
                ║    
                ║    
            ═════╩═════
            """,
            // 2 erreurs - baume de la potence
            """
                ╔════╗
                ║    
                ║    
                ║    
                ║    
                ║    
            ═════╩═════
            """,
            // 3 erreurs - tete
            """
                ╔════╗
                ║   │
                ║   O
                ║   
                ║   
                ║    
            ═════╩═════
            """,
            // 4 erreurs - corps
            """
                ╔════╗
                ║   │
                ║   O
                ║   │
                ║   │
                ║    
            ═════╩═════
            """,
            // 5 erreurs - bras
            """
                ╔════╗
                ║   │
                ║   O
                ║  ╱│╲
                ║   │
                ║    
            ═════╩═════
            """,
            // 6 erreurs - jambes
            """
                ╔════╗
                ║   │
                ║   O
                ║  ╱│╲
                ║   │
                ║  ╱ ╲
            ═════╩═════
            """,
            // 7 erreurs - game over
            """
                ╔════╗
                ║   │
                ║   X
                ║  ╱│╲
                ║   │
                ║  ╱ ╲
            ═════╩═════  PERDU!
            """
        };
        
        int index = Math.min(nombreErreurs, ERREURS_MAX);
        System.out.print(etapes[index]); //NOSONAR
    }
    
    /**
     * Affiche le mot avec des underscores pour les lettres non découvertes
     * @param motSecret Le mot à deviner
     * @param lettresDecouvertes Les lettres déjà découvertes
     */
    private void afficherMot(String motSecret, Set<Character> lettresDecouvertes) {
        System.out.print("   MOT À DEVINER: "); //NOSONAR
        
        StringBuilder affichage = new StringBuilder();
        for (char lettre : motSecret.toUpperCase().toCharArray()) {
            if (lettresDecouvertes.contains(lettre)) {
                affichage.append(lettre).append(" ");
            } else {
                affichage.append("_ ");
            }
        }
        
        System.out.println(affichage.toString().trim()); //NOSONAR
        System.out.println("   (" + motSecret.length() + " lettres)"); //NOSONAR
    }
    
    /**
     * Affiche les lettres correctement devinées
     * @param lettresDecouvertes Les lettres correctes
     */
    private void afficherLettresCorrectes(Set<Character> lettresDecouvertes) {
        System.out.print("   ✓ LETTRES CORRECTES: "); //NOSONAR
        
        if (lettresDecouvertes.isEmpty()) {
            System.out.println("(aucune)"); //NOSONAR
        } else {
            TreeSet<Character> triees = new TreeSet<>(lettresDecouvertes);
            System.out.println(String.join(" ",triees.stream().map(String::valueOf).toArray(String[]::new))); //NOSONAR
        }
    }
    
    /**
     * Affiche les lettres erronées
     * @param lettresErronees Les lettres incorrectes
     * @param nombreErreurs Le nombre d'erreurs
     */
    private void afficherLettresErronees(Set<Character> lettresErronees, int nombreErreurs) {
        System.out.print("   ✗ LETTRES ERRONÉES: "); //NOSONAR
        
        if (lettresErronees.isEmpty()) {
            System.out.println("(aucune)"); //NOSONAR
        } else {
            TreeSet<Character> triees = new TreeSet<>(lettresErronees);
            System.out.println(String.join(" ", triees.stream().map(String::valueOf).toArray(String[]::new))); //NOSONAR
        }
        
        System.out.println("   Erreurs: " + nombreErreurs + "/" + ERREURS_MAX); //NOSONAR
    }
    
    /**
     * Affiche les lettres non encore utilisées
     * @param lettresDecouvertes Les lettres correctes
     * @param lettresErronees Les lettres erronées
     */
    private void afficherLettresRestantes(Set<Character> lettresDecouvertes, 
                                         Set<Character> lettresErronees) {
        System.out.print("   LETTRES RESTANTES: "); //NOSONAR
        
        Set<Character> lettresUtilisees = new HashSet<>();
        lettresUtilisees.addAll(lettresDecouvertes);
        lettresUtilisees.addAll(lettresErronees);
        
        StringBuilder restantes = new StringBuilder();
        for (char lettre : ALPHABET.toCharArray()) {
            if (!lettresUtilisees.contains(lettre)) {
                restantes.append(lettre).append(" ");
            }
        }
        
        System.out.println(restantes.toString().trim()); //NOSONAR
    }
    
    /**
     * Affiche un message de victoire
     * @param motSecret Le mot qui a été trouvé
     */
    public void afficherVictoire(String motSecret) {
        System.out.println("\n");//NOSONAR
        System.out.println("╔═══════════════════════════════════════════════════════════╗");//NOSONAR
        System.out.println("║                                                           ║");//NOSONAR
        System.out.println("║               🎉  FÉLICITATIONS ! 🎉                      ║");//NOSONAR
        System.out.println("║                                                           ║");//NOSONAR
        System.out.println("║          Vous avez trouvé le mot: " +String.format("%-20s", motSecret.toUpperCase()) + "║");//NOSONAR
        System.out.println("║                                                           ║");//NOSONAR
        System.out.println("╚═══════════════════════════════════════════════════════════╝");//NOSONAR
        System.out.println();//NOSONAR
    }
    
    /**
     * Affiche un message de défaite
     * @param motSecret Le mot qui devait être trouvé
     */
    public void afficherDefaite(String motSecret) {
        System.out.println("\n");//NOSONAR
        System.out.println("╔═══════════════════════════════════════════════════════════╗");//NOSONAR
        System.out.println("║                                                           ║");//NOSONAR
        System.out.println("║                   GAME OVER 😢                            ║");//NOSONAR
        System.out.println("║                                                           ║");//NOSONAR
        System.out.println("║          Le mot était: " + String.format("%-28s", motSecret.toUpperCase()) + "║");//NOSONAR
        System.out.println("║                                                           ║");//NOSONAR
        System.out.println("╚═══════════════════════════════════════════════════════════╝");//NOSONAR
        System.out.println();//NOSONAR
    }
    
    /**
     * Affiche un message d'invite pour entrer une lettre
     */
    public void afficherInvite() {
        System.out.println();//NOSONAR
        System.out.print("   Entrez une lettre (A-Z): ");//NOSONAR
    }
    
    /**
     * Affiche un message d'erreur
     * @param message Le message d'erreur
     */
    public void afficherErreur(String message) {
        System.out.println("\n   ⚠ ERREUR: " + message);//NOSONAR
    }
}
