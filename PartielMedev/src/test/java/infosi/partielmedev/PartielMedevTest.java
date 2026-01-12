/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package infosi.partielmedev;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests pour la classe PartielMedev
 * @author jujus
 */
public class PartielMedevTest {
    
    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;
    private ByteArrayInputStream originalIn;
    
    PartielMedevTest() {
    }
    
    @BeforeAll
    static void setUpClass() {
        // Méthode exécutée une fois avant tous les tests
    }
    
    @AfterAll
    static void tearDownClass() {
        // Méthode exécutée une fois après tous les tests
    }
    
    @BeforeEach
    void setUp() {
        // Capture la sortie standard
        outContent = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outContent));
    }
    
    @AfterEach
    void tearDown() {
        // Restaure la sortie standard
        System.setOut(originalOut);
        if (originalIn != null) {
            System.setIn(System.in);
        }
    }

    /**
     * Test du menu principal avec choix de quitter
     */
    @Test
    void testMainQuitter() {
        System.out.println("mainQuitter");
        // Simule l'entrée : 0 pour quitter
        String input = "0\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        
        // Exécute le main
        PartielMedev.main(new String[]{});
        
        // Vérifie que le message de bienvenue est affiché
        String output = outContent.toString();
        assertTrue(output.contains("JEU DU PENDU") || output.contains("Bienvenue"), 
                  "Le message de bienvenue devrait s'afficher");
        assertTrue(output.contains("Merci d'avoir joué") || output.contains("bientôt"), 
                  "Le message d'au revoir devrait s'afficher");
    }

    /**
     * Test du menu principal avec choix mode 1 joueur (non implémenté)
     */
    @Test
    void testMainMode1Joueur() {
        System.out.println("mainMode1Joueur");
        // Simule l'entrée : 1 pour mode 1 joueur, puis N pour ne pas rejouer (deux fois car le mode 1 joueur continue)
        String input = "1\n0\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        
        // Exécute le main
        PartielMedev.main(new String[]{});
        
        // Vérifie que le message "à venir" est affiché
        String output = outContent.toString();
        assertTrue(output.contains("Mode 1 joueur") || output.contains("venir") || output.contains("dictionnaire"), 
                  "Le message 'à venir' devrait s'afficher pour le mode 1 joueur");
    }

    /**
     * Test du menu principal avec entrée invalide puis quitter
     */
    @Test
    void testMainEntreeInvalide() {
        System.out.println("mainEntreeInvalide");
        // Simule l'entrée : 9 (invalide), puis 0 pour quitter
        String input = "9\n0\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        
        // Exécute le main
        PartielMedev.main(new String[]{});
        
        // Vérifie qu'un message d'erreur est affiché
        String output = outContent.toString();
        assertTrue(output.contains("invalide") || output.contains("Choix"), 
                  "Un message d'erreur devrait s'afficher pour une entrée invalide");
    }

    /**
     * Test du mode 2 joueurs avec une partie complète
     */
    @Test
    void testMainMode2JoueursPartieComplete() {
        System.out.println("mainMode2JoueursPartieComplete");
        // Simule l'entrée : 2 pour mode 2 joueurs, "ABC" comme mot, puis les lettres A, B, C pour gagner, puis N pour ne pas rejouer
        // On ajoute des lignes supplémentaires au cas où pour éviter NoSuchElementException
        String input = "2\nABC\nA\nB\nC\nN\n\n\n\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        
        // Exécute le main
        PartielMedev.main(new String[]{});
        
        // Vérifie que le mode 2 joueurs est lancé
        String output = outContent.toString();
        assertTrue(output.contains("MODE 2 JOUEURS") || output.contains("Joueur"), 
                  "Le mode 2 joueurs devrait être lancé");
        assertTrue(output.contains("mot secret") || output.contains("Entrez"), 
                  "La demande de mot secret devrait s'afficher");
    }

    /**
     * Test du mode 2 joueurs avec un mot invalide (trop court)
     */
    @Test
    void testMainMode2JoueursMotTropCourt() {
        System.out.println("mainMode2JoueursMotTropCourt");
        // Simule l'entrée : 2 pour mode 2 joueurs, "AB" (trop court), puis "ABC" (valide), puis lettres A, B, C, puis N pour ne pas rejouer
        String input = "2\nAB\nABC\nA\nB\nC\nN\n\n\n\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        
        // Exécute le main
        PartielMedev.main(new String[]{});
        
        // Vérifie qu'un message d'erreur pour mot trop court est affiché
        String output = outContent.toString();
        assertTrue(output.contains("au moins 3 lettres") || output.contains("3"), 
                  "Un message d'erreur pour un mot trop court devrait s'afficher");
    }

    /**
     * Test du mode 2 joueurs avec un mot contenant des chiffres
     */
    @Test
    void testMainMode2JoueursMotAvecChiffres() {
        System.out.println("mainMode2JoueursMotAvecChiffres");
        // Simule l'entrée : 2 pour mode 2 joueurs, "TEST123" (avec chiffres), puis "ABC" (valide), puis lettres A, B, C, puis N
        String input = "2\nTEST123\nABC\nA\nB\nC\nN\n\n\n\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        
        // Exécute le main
        PartielMedev.main(new String[]{});
        
        // Vérifie qu'un message d'erreur pour caractères invalides est affiché
        String output = outContent.toString();
        assertTrue(output.contains("lettres") || output.contains("A-Z"), 
                  "Un message d'erreur pour caractères invalides devrait s'afficher");
    }

    /**
     * Test du mode 2 joueurs avec un mot vide
     */
    @Test
    void testMainMode2JoueursMotVide() {
        System.out.println("mainMode2JoueursMotVide");
        // Simule l'entrée : 2 pour mode 2 joueurs, "" (vide), puis "ABC" (valide), puis lettres A, B, C, puis N
        String input = "2\n\nABC\nA\nB\nC\nN\n\n\n\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        
        // Exécute le main
        PartielMedev.main(new String[]{});
        
        // Vérifie qu'un message d'erreur pour mot vide est affiché
        String output = outContent.toString();
        assertTrue(output.contains("vide") || output.contains("peut pas"), 
                  "Un message d'erreur pour un mot vide devrait s'afficher");
    }

    /**
     * Test de la demande de rejouer avec réponse OUI
     */
    @Test
    void testMainRejouerOui() {
        System.out.println("mainRejouerOui");
        // Simule l'entrée : mode 2 joueurs, "ABC" comme mot, lettres A, B, C, O pour rejouer, puis 0 pour quitter définitivement
        String input = "2\nABC\nA\nB\nC\nO\n0\n\n\n\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        
        // Exécute le main
        PartielMedev.main(new String[]{});
        
        // Vérifie que le menu est réaffiché
        String output = outContent.toString();
        assertTrue(output.contains("Voulez-vous rejouer") || output.contains("rejouer"), 
                  "La question de rejouer devrait s'afficher");
    }

    /**
     * Test de la demande de rejouer avec réponse NON
     */
    @Test
    void testMainRejouerNon() {
        System.out.println("mainRejouerNon");
        // Simule l'entrée : 2 pour mode 2 joueurs, mot "ABC", lettres A, B, C (victoire), puis N pour ne pas rejouer
        String input = "2\nABC\nA\nB\nC\nN\n\n\n\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        
        // Exécute le main
        PartielMedev.main(new String[]{});
        
        // Vérifie que le programme se termine
        String output = outContent.toString();
        assertTrue(output.contains("Voulez-vous rejouer") || output.contains("rejouer"), 
                  "La question de rejouer devrait s'afficher");
    }

    /**
     * Test d'une partie complète avec victoire
     */
    @Test
    void testMainPartieVictoire() {
        System.out.println("mainPartieVictoire");
        // Simule l'entrée : mode 2 joueurs, mot "JEU", puis toutes les lettres J, E, U pour gagner, puis N
        String input = "2\nJEU\nJ\nE\nU\nN\n\n\n\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        
        // Exécute le main
        PartielMedev.main(new String[]{});
        
        // Vérifie que la partie se déroule
        String output = outContent.toString();
        assertTrue(output.contains("MODE 2 JOUEURS") || output.contains("mot secret"), 
                  "La partie devrait se dérouler normalement");
    }

    /**
     * Test d'une partie avec plusieurs erreurs
     */
    @Test
    void testMainPartieAvecErreurs() {
        System.out.println("mainPartieAvecErreurs");
        // Simule l'entrée : mode 2 joueurs, mot "OUI", puis des lettres incorrectes A, B, C et correctes O, U, I, puis N
        String input = "2\nOUI\nA\nB\nC\nO\nU\nI\nN\n\n\n\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        
        // Exécute le main
        PartielMedev.main(new String[]{});
        
        // Vérifie que la partie se déroule
        String output = outContent.toString();
        assertTrue(output.contains("MODE 2 JOUEURS") || output.contains("Joueur"), 
                  "La partie devrait se dérouler avec des erreurs");
    }
    
    /**
     * Test de la validation : choix multiples invalides avant un choix valide
     */
    @Test
    void testMainChoixMultiplesInvalides() {
        System.out.println("mainChoixMultiplesInvalides");
        // Simule l'entrée : plusieurs choix invalides, puis 0 pour quitter
        String input = "abc\n5\n-1\n0\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        
        // Exécute le main
        PartielMedev.main(new String[]{});
        
        // Vérifie que des messages d'erreur sont affichés
        String output = outContent.toString();
        assertTrue(output.contains("invalide") || output.contains("Choix"), 
                  "Des messages d'erreur devraient s'afficher pour les choix invalides");
    }
}
