package infosi.partielmedev;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Gère le déroulement d'un tour de jeu du pendu
 * Coordonne l'interaction entre le joueur, l'interface et la logique du jeu
 */
public class TourDeJeu {
    
    private Interface interfaceJeu;
    private Scanner scanner;
    
    /**
     * Constructeur par défaut
     */
    public TourDeJeu() {
        this.interfaceJeu = new Interface();
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Constructeur avec interface personnalisée
     * @param interfaceJeu L'interface à utiliser
     */
    public TourDeJeu(Interface interfaceJeu) {
        this.interfaceJeu = interfaceJeu;
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Joue un tour complet du jeu
     * @param game L'instance du jeu contenant la logique
     */
    public void jouerTour(Game game) {
        // Affiche l'état actuel du jeu
        afficherEtatJeu(game);
        
        // Demande une lettre au joueur
        char lettre = demanderLettre();
        
        // Envoie la lettre au jeu et traite le résultat
        int resultat = game.putCharacter(lettre);
        
        // Traite le résultat
        traiterResultat(resultat, lettre);
    }
    
    /**
     * Affiche l'état actuel du jeu via l'interface
     * @param game L'instance du jeu
     */
    private void afficherEtatJeu(Game game) {
        // Récupère les lettres découvertes et erronées
        Set<Character> lettresDecouvertes = extraireLettres(game.getKnownLetter());
        Set<Character> lettresErronees = extraireLettresErronees(
            game.getKnownLetter(), 
            game.getRemainingLetter()
        );
        
        // Affiche l'état du jeu
        interfaceJeu.afficherJeu(
            game.getSecretWord(),
            lettresDecouvertes,
            lettresErronees,
            game.getErrorCount()
        );
    }
    
    /**
     * Demande une lettre au joueur
     * @return La lettre saisie en majuscule
     */
    private char demanderLettre() {
        while (true) {
            interfaceJeu.afficherInvite();
            String input = scanner.nextLine().trim().toUpperCase();
            
            char lettre = input.charAt(0);
            
            // Vérifie que l'entrée contient exactement un caractère
            if (input.length() != 1) {
                interfaceJeu.afficherErreur("Vous devez entrer exactement une lettre.");
            }else if (lettre < 'A' || lettre > 'Z') { // Vérifie que c'est une lettre de A à Z
                interfaceJeu.afficherErreur("Vous devez entrer une lettre entre A et Z."); 
                continue;
            }
            
            return lettre;
        }
    }
    
    /**
     * Traite le résultat de PutCharacter
     * @param resultat Le code de retour (0: invalide, 1: bon, 2: faux)
     * @param lettre La lettre qui a été jouée
     * @param game L'instance du jeu
     */
    private void traiterResultat(int resultat, char lettre) {
        switch (resultat) {
            case 0:
                // Lettre déjà utilisée ou invalide
                interfaceJeu.afficherErreur("La lettre " + lettre + " a déjà été utilisée ou est invalide.");//NOSONAR
                break;
            case 1:
                // Bonne lettre
                System.out.println("\n   ✓ Bien joué ! La lettre " + lettre + " est dans le mot !");//NOSONAR
                pauseCourte();
                break;
            case 2:
                // Mauvaise lettre
                System.out.println("\n   ✗ Dommage ! La lettre " + lettre + " n'est pas dans le mot.");//NOSONAR
                pauseCourte();
                break;
            default:
                interfaceJeu.afficherErreur("Résultat inattendu: " + resultat);
        }
    }
    
    /**
     * Extrait les lettres du knownLetter qui ont été découvertes
     * @param knownLetter La chaîne contenant les lettres découvertes et des '_'
     * @return Un ensemble des lettres découvertes
     */
    private Set<Character> extraireLettres(String knownLetter) {
        Set<Character> lettres = new HashSet<>();
        
        if (knownLetter != null) {
            for (char c : knownLetter.toUpperCase().toCharArray()) {
                if (c >= 'A' && c <= 'Z') {
                    lettres.add(c);
                }
            }
        }
        
        return lettres;
    }
    
    /**
     * Détermine les lettres erronées en comparant l'alphabet avec knownLetter et remainingLetter
     * @param knownLetter Les lettres découvertes
     * @param remainingLetter Les lettres restantes
     * @return Un ensemble des lettres erronées
     */
    private Set<Character> extraireLettresErronees(String knownLetter, String remainingLetter) {
        
        // Toutes les lettres de A à Z
        Set<Character> toutesLettres = new HashSet<>();
        for (char c = 'A'; c <= 'Z'; c++) {
            toutesLettres.add(c);
        }
        
        // Retire les lettres connues (découvertes)
        Set<Character> lettresDecouvertes = extraireLettres(knownLetter);
        toutesLettres.removeAll(lettresDecouvertes);
        
        // Retire les lettres restantes (non utilisées)
        Set<Character> lettresRestantes = extraireLettres(remainingLetter);
        toutesLettres.removeAll(lettresRestantes);
        
        // Ce qui reste = les lettres erronées
        return toutesLettres;
    }
    
    /**
     * Vérifie l'état du jeu et affiche le message approprié
     * @param game L'instance du jeu
     * @return true si le jeu est terminé, false sinon
     */
    public boolean verifierFinJeu(Game game) {
        int state = game.getState();
        
        switch (state) {
            case 2:
                // Victoire
                afficherEtatJeu(game);
                interfaceJeu.afficherVictoire(game.getSecretWord());
                return true;
            case 3:
                // Défaite
                afficherEtatJeu(game);
                interfaceJeu.afficherDefaite(game.getSecretWord());
                return true;
            default:
                // Jeu en cours ou pas commencé
                return false;
        }
    }
    
    /**
     * Lance une partie complète
     * @param game L'instance du jeu initialisé
     */
    public void jouerPartie(Game game) {
        // Vérifie que le jeu est bien en cours
        if (game.getState() != 1) {
            interfaceJeu.afficherErreur("Le jeu n'est pas initialisé correctement.");
            return;
        }
        
        // Boucle de jeu
        while (!verifierFinJeu(game)) {
            jouerTour(game);
        }
    }
    
    /**
     * Petite pause pour laisser le temps de lire le message
     */
    private void pauseCourte() {
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            // Restaure le statut d'interruption du thread
            Thread.currentThread().interrupt();
        }
    }
    
    /**
     * Ferme le scanner
     */
    public void fermer() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
