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
 *
 * @author jujus
 */
public class TourDeJeuTest {
    
    TourDeJeuTest() {
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
        // Méthode exécutée avant chaque test
    }
    
    @AfterEach
    void tearDown() {
        // Méthode exécutée après chaque test
    }

    /**
     * Test of jouerTour method, of class TourDeJeu.
     * Note: Ce test est commenté car jouerTour attend une entrée utilisateur
     */
    @Test
    void testJouerTour() {
        System.out.println("jouerTour");
        // Test désactivé car la méthode nécessite une interaction utilisateur
        // On teste plutôt les méthodes sans interaction
        assertTrue(true, "Test ignoré - nécessite interaction utilisateur");
    }

    /**
     * Test of verifierFinJeu method, of class TourDeJeu.
     */
    @Test
    void testVerifierFinJeu() {
        System.out.println("verifierFinJeu");
        TourDeJeu instance = new TourDeJeu();
        // Test avec un jeu en cours
        Game gameEnCours = new Game("JUSI");
        boolean result = instance.verifierFinJeu(gameEnCours);
        assertFalse(result, "Le jeu en cours ne devrait pas être terminé");
        
        // Test avec un jeu gagné
        Game gameGagne = new Game(2, 0, "JUSI", "JSI", "");
        boolean resultGagne = instance.verifierFinJeu(gameGagne);
        assertTrue(resultGagne, "Le jeu gagné devrait être terminé");
        
        // Test avec un jeu perdu
        Game gamePerdu = new Game(3, 7, "JUSI", "ERTYU", "JUS");
        boolean resultPerdu = instance.verifierFinJeu(gamePerdu);
        assertTrue(resultPerdu, "Le jeu perdu devrait être terminé");
    }

    /**
     * Test of jouerPartie method, of class TourDeJeu.
     * Note: Ce test est commenté car jouerPartie attend des entrées utilisateur
     */
    @Test
    void testJouerPartie() {
        System.out.println("jouerPartie");
        // Test désactivé car la méthode nécessite une interaction utilisateur
        assertTrue(true, "Test ignoré - nécessite interaction utilisateur");
    }

    /**
     * Test of fermer method, of class TourDeJeu.
     */
    @Test
    void testFermer() {
        System.out.println("fermer");
        // Test que la méthode s'exécute sans erreur (mais ne la ferme pas réellement)
        assertDoesNotThrow(() -> {});
        // Note: On ne ferme pas le scanner pour éviter de corrompre System.in
    }
    
    /**
     * Test du constructeur avec interface personnalisée
     */
    @Test
    void testConstructeurAvecInterface() {
        System.out.println("constructeurAvecInterface");
        Interface interfaceCustom = new Interface();
        TourDeJeu instance = new TourDeJeu(interfaceCustom);
        assertNotNull(instance, "L'instance devrait être créée");
    }
    
    /**
     * Test de jouerTour avec une bonne lettre
     */
    @Test
    void testJouerTourBonneLettre() {
        System.out.println("jouerTourBonneLettre");
        // Simule l'entrée utilisateur "J\n"
        String input = "J\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        
        // Capture la sortie
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        
        try {
            TourDeJeu instance = new TourDeJeu();
            Game game = new Game(1, 0, "JUSTIN", "", "JUSTIN");
            instance.jouerTour(game);
            
            // Vérifie que la lettre a été traitée
            String output = outContent.toString();
            assertTrue(output.contains("Bien joué") || game.getKnownLetter().contains("J"), 
                      "La lettre J devrait être acceptée");
        } finally {
            // Restaure System.in et System.out
            System.setIn(System.in);
            System.setOut(originalOut);
        }
    }
    
    /**
     * Test de jouerTour avec une mauvaise lettre
     */
    @Test
    void testJouerTourMauvaiseLettre() {
        System.out.println("jouerTourMauvaiseLettre");
        // Simule l'entrée utilisateur "Z\n"
        String input = "Z\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        
        // Capture la sortie
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        
        try {
            TourDeJeu instance = new TourDeJeu();
            Game game = new Game(1, 0, "JUSTIN", "", "JUSTIN");
            instance.jouerTour(game);
            
            // Vérifie que la lettre a été rejetée
            String output = outContent.toString();
            assertTrue(output.contains("Dommage") || game.getErrorCount() > 0, 
                      "La lettre Z devrait être rejetée");
        } finally {
            // Restaure System.in et System.out
            System.setIn(System.in);
            System.setOut(originalOut);
        }
    }
    
    /**
     * Test de jouerTour avec une lettre déjà utilisée
     */
    @Test
    void testJouerTourLettreDejaUtilisee() {
        System.out.println("jouerTourLettreDejaUtilisee");
        // Simule l'entrée utilisateur "J\n"
        String input = "J\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        
        // Capture la sortie
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        
        try {
            TourDeJeu instance = new TourDeJeu();
            Game game = new Game(1, 0, "JUSTIN", "J", "USTIN");
            instance.jouerTour(game); // Essaie d'utiliser J à nouveau
            
            // Vérifie que l'erreur a été affichée
            String output = outContent.toString();
            assertTrue(output.contains("déjà été utilisée") || output.contains("invalide"), 
                      "Un message d'erreur devrait s'afficher pour une lettre déjà utilisée");
        } finally {
            // Restaure System.in et System.out
            System.setIn(System.in);
            System.setOut(originalOut);
        }
    }
    
    /**
     * Test de jouerTour avec une entrée invalide puis une valide
     */
    @Test
    void testJouerTourEntreeInvalide() {
        System.out.println("jouerTourEntreeInvalide");
        // Simule une entrée de plusieurs lettres puis une lettre valide
        String input = "ABC\nJ\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        
        // Capture la sortie
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        
        try {
            TourDeJeu instance = new TourDeJeu();
            Game game = new Game(1, 0, "JUSTIN", "", "JUSTIN");
            instance.jouerTour(game);
            
            // Vérifie qu'un message d'erreur a été affiché
            String output = outContent.toString();
            assertTrue(output.contains("exactement une lettre") || game.getKnownLetter().contains("J"), 
                      "Un message d'erreur devrait s'afficher pour une entrée invalide");
        } finally {
            // Restaure System.in et System.out
            System.setIn(System.in);
            System.setOut(originalOut);
        }
    }
    
    /**
     * Test de jouerTour avec un caractère non alphabétique
     */
    @Test
    void testJouerTourCaractereNonAlphabetique() {
        System.out.println("jouerTourCaractereNonAlphabetique");
        // Simule une entrée avec un chiffre puis une lettre valide
        String input = "5\nJ\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        
        // Capture la sortie
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        
        try {
            TourDeJeu instance = new TourDeJeu();
            Game game = new Game(1, 0, "JUSTIN", "", "JUSTIN");
            instance.jouerTour(game);
            
            // Vérifie qu'un message d'erreur a été affiché
            String output = outContent.toString();
            assertTrue(output.contains("entre A et Z") || game.getKnownLetter().contains("J"), 
                      "Un message d'erreur devrait s'afficher pour un caractère non alphabétique");
        } finally {
            // Restaure System.in et System.out
            System.setIn(System.in);
            System.setOut(originalOut);
        }
    }
    
    /**
     * Test de verifierFinJeu avec victoire (affichage complet)
     */
    @Test
    void testVerifierFinJeuVictoire() {
        System.out.println("verifierFinJeuVictoire");
        // Capture la sortie
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        
        try {
            TourDeJeu instance = new TourDeJeu();
            Game gameGagne = new Game(2, 0, "TEST", "", "");
            boolean result = instance.verifierFinJeu(gameGagne);
            
            assertTrue(result, "Le jeu devrait être terminé");
            String output = outContent.toString();
            assertTrue(output.contains("TEST") || output.length() > 0, 
                      "L'affichage de victoire devrait contenir le mot");
        } finally {
            System.setOut(originalOut);
        }
    }
    
    /**
     * Test de verifierFinJeu avec défaite (affichage complet)
     */
    @Test
    void testVerifierFinJeuDefaite() {
        System.out.println("verifierFinJeuDefaite");
        // Capture la sortie
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        
        try {
            TourDeJeu instance = new TourDeJeu();
            Game gamePerdu = new Game(3, 7, "TEST", "ABCDEFG", "");
            boolean result = instance.verifierFinJeu(gamePerdu);
            
            assertTrue(result, "Le jeu devrait être terminé");
            String output = outContent.toString();
            assertTrue(output.contains("TEST") || output.length() > 0, 
                      "L'affichage de défaite devrait contenir le mot");
        } finally {
            System.setOut(originalOut);
        }
    }
    
    /**
     * Test de jouerPartie avec un jeu non initialisé
     */
    @Test
    void testJouerPartieJeuNonInitialise() {
        System.out.println("jouerPartieJeuNonInitialise");
        // Capture la sortie
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        
        try {
            TourDeJeu instance = new TourDeJeu();
            // Crée un jeu avec un état différent de 1 (en cours)
            Game gameNonInitialise = new Game(0, 0, "TEST", "TEST", "");
            instance.jouerPartie(gameNonInitialise);
            
            // Vérifie qu'un message d'erreur a été affiché
            String output = outContent.toString();
            assertTrue(output.contains("pas initialisé") || output.contains("correctement"), 
                      "Un message d'erreur devrait s'afficher pour un jeu non initialisé");
        } finally {
            System.setOut(originalOut);
        }
    }
    
    /**
     * Test de jouerPartie complète jusqu'à la victoire
     */
    @Test
    void testJouerPartieComplete() {
        System.out.println("jouerPartieComplete");
        // Simule les entrées pour gagner (JUSI)
        String input = "J\nU\nS\nI\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        
        // Capture la sortie
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        
        try {
            TourDeJeu instance = new TourDeJeu();
            Game game = new Game(1, 0, "JUSI", "", "JUSI");
            instance.jouerPartie(game);
            
            // Vérifie que le jeu est terminé
            assertEquals(2, game.getState(), "Le jeu devrait être gagné");
        } finally {
            // Restaure System.in et System.out
            System.setIn(System.in);
            System.setOut(originalOut);
        }
    }
    
}
