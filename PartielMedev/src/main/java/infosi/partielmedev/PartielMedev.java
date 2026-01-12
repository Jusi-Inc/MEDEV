/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package infosi.partielmedev;

import java.util.Scanner;

/**
 * Classe principale du jeu du pendu
 * @author Catherine
 */
public class PartielMedev {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Interface interfaceJeu = new Interface();
        
        afficherBienvenue();
        
        boolean continuerAJouer = true;
        
        while (continuerAJouer) {
            // Choix du mode de jeu
            int modeJeu = choisirModeJeu(scanner, interfaceJeu);
            
            if (modeJeu == 0) {
                // Quitter le jeu
                System.out.println("\n👋 Merci d'avoir joué ! À bientôt !"); //NOSONAR
                break;
            }
            
            // Initialisation du jeu selon le mode choisi
            Game game = null;
            
            if (modeJeu == 1) {
                // Mode 1 joueur - À implémenter avec le dictionnaire
                System.out.println("\n🚧 Mode 1 joueur à venir (dictionnaire externe)..."); //NOSONAR
                continue;
            } else if (modeJeu == 2) {
                // Mode 2 joueurs
                game = mode2Joueurs(scanner, interfaceJeu);
            }
            
            // Si un jeu a été créé, lancer la partie
            if (game != null) {
                TourDeJeu tourDeJeu = new TourDeJeu(interfaceJeu, scanner);
                tourDeJeu.jouerPartie(game);
                // Pas besoin de fermer le scanner car il est partagé avec le main
            }
            
            // Demander si on veut rejouer
            continuerAJouer = demanderRejouer(scanner);
        }
        
        scanner.close();
    }
    
    /**
     * Affiche le message de bienvenue
     */
    private static void afficherBienvenue() {
        System.out.println("\n╔═══════════════════════════════════════╗");//NOSONAR
        System.out.println("║        🎮 JEU DU PENDU 🎮           ║");//NOSONAR
        System.out.println("╚═══════════════════════════════════════╝");//NOSONAR
        System.out.println("\nBienvenue dans le jeu du pendu !");//NOSONAR
        System.out.println("Devinez le mot mystère lettre par lettre...\n");//NOSONAR
    }
    
    /**
     * Permet au joueur de choisir le mode de jeu
     * @param scanner Le scanner pour lire l'entrée
     * @param interfaceJeu L'interface du jeu
     * @return 0 pour quitter, 1 pour 1 joueur, 2 pour 2 joueurs
     */
    private static int choisirModeJeu(Scanner scanner, Interface interfaceJeu) {
        System.out.println("┌─────────────────────────────────────┐");//NOSONAR
        System.out.println("│  Choisissez le mode de jeu :         │");//NOSONAR
        System.out.println("│  [1] 1 joueur                        │");//NOSONAR
        System.out.println("│  [2] 2 joueurs                       │");//NOSONAR
        System.out.println("│  [0] Quitter                         │");//NOSONAR
        System.out.println("└─────────────────────────────────────┘");//NOSONAR
        
        while (true) {
            System.out.print("\nVotre choix : ");//NOSONAR
            String input = scanner.nextLine().trim();
            
            if (input.equals("0") || input.equals("1") || input.equals("2")) {
                return Integer.parseInt(input);
            } else {
                interfaceJeu.afficherErreur("Choix invalide. Veuillez entrer 0, 1 ou 2.");
            }
        }
    }
    
    /**
     * Gère le mode 2 joueurs : un joueur choisit le mot, l'autre devine
     * @param scanner Le scanner pour lire l'entrée
     * @param interfaceJeu L'interface du jeu
     * @return Le jeu initialisé ou null si annulé
     */
    private static Game mode2Joueurs(Scanner scanner, Interface interfaceJeu) {
        System.out.println("\n═════════════════════════════════════");//NOSONAR
        System.out.println("       MODE 2 JOUEURS");//NOSONAR
        System.out.println("═════════════════════════════════════");//NOSONAR
        System.out.println("\n🎯 Joueur 1 : Choisissez un mot secret");//NOSONAR
        System.out.println("   (Le joueur 2 devra le deviner)\n");//NOSONAR
        
        String motSecret = null;
        
        while (motSecret == null) {
            System.out.print("Entrez le mot secret (lettres uniquement) : ");//NOSONAR
            String input = scanner.nextLine().trim().toUpperCase();
            
            if (input.isEmpty()) {
                interfaceJeu.afficherErreur("Le mot ne peut pas être vide.");
                continue;
            }
            
            // Vérifie que le mot contient uniquement des lettres
            boolean valide = true;
            for (char c : input.toCharArray()) {
                if (c < 'A' || c > 'Z') {
                    valide = false;
                    break;
                }
            }
            
            if (!valide) {
                interfaceJeu.afficherErreur("Le mot doit contenir uniquement des lettres (A-Z).");
                continue;
            }
            
            if (input.length() < 3) {
                interfaceJeu.afficherErreur("Le mot doit contenir au moins 3 lettres.");
                continue;
            }
            
            motSecret = input;
        }
        
        // Efface l'écran (simule l'effacement avec des lignes vides)
        for (int i = 0; i < 50; i++) {
            System.out.println();//NOSONAR
        }
        
        System.out.println("\n═════════════════════════════════════");//NOSONAR
        System.out.println("🎮 Joueur 2 : À vous de jouer !");//NOSONAR
        System.out.println("═════════════════════════════════════");//NOSONAR
        System.out.println("Devinez le mot mystère de " + motSecret.length() + " lettres...\n");//NOSONAR
        
        // Crée le jeu avec le mot secret
        return new Game(1, 0, motSecret, "", extraireLetttresUniques(motSecret));
    }
    
    /**
     * Extrait les lettres uniques d'un mot
     * @param mot Le mot
     * @return Une chaîne contenant les lettres uniques du mot
     */
    private static String extraireLetttresUniques(String mot) {
        StringBuilder lettres = new StringBuilder();
        for (char c : mot.toUpperCase().toCharArray()) {
            if (lettres.indexOf(String.valueOf(c)) == -1) {
                lettres.append(c);
            }
        }
        return lettres.toString();
    }
    
    /**
     * Demande au joueur s'il veut rejouer
     * @param scanner Le scanner pour lire l'entrée
     * @return true pour rejouer, false pour quitter
     */
    private static boolean demanderRejouer(Scanner scanner) {
        System.out.println("\n═════════════════════════════════════");//NOSONAR
        System.out.print("Voulez-vous rejouer ? (O/N) : ");//NOSONAR
        String reponse = scanner.nextLine().trim().toUpperCase();//NOSONAR
        
        return reponse.equals("O") || reponse.equals("OUI") || reponse.equals("Y") || reponse.equals("YES");
    }
}
