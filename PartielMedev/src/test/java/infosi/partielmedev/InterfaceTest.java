/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package infosi.partielmedev;

import java.util.Set;
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
public class InterfaceTest {
    
    InterfaceTest() {
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
     * Test of afficherJeu method, of class Interface.
     */
    @Test
    void testAfficherJeu() {
        System.out.println("afficherJeu");
        String motSecret = "JUSTIN";
        Set<Character> lettresDecouvertes = Set.of('J', 'A');
        Set<Character> lettresErronees = Set.of('E', 'R');
        int nombreErreurs = 2;
        Interface instance = new Interface();
        // Test que la méthode s'exécute sans erreur
        assertDoesNotThrow(() -> instance.afficherJeu(motSecret, lettresDecouvertes, lettresErronees, nombreErreurs));
    }

    /**
     * Test of afficherVictoire method, of class Interface.
     */
    @Test
    void testAfficherVictoire() {
        System.out.println("afficherVictoire");
        String motSecret = "JUSTIN";
        Interface instance = new Interface();
        // Test que la méthode s'exécute sans erreur
        assertDoesNotThrow(() -> instance.afficherVictoire(motSecret));
    }

    /**
     * Test of afficherDefaite method, of class Interface.
     */
    @Test
    void testAfficherDefaite() {
        System.out.println("afficherDefaite");
        String motSecret = "JUSTIN";
        Interface instance = new Interface();
        // Test que la méthode s'exécute sans erreur
        assertDoesNotThrow(() -> instance.afficherDefaite(motSecret));
    }

    /**
     * Test of afficherInvite method, of class Interface.
     */
    @Test
    void testAfficherInvite() {
        System.out.println("afficherInvite");
        Interface instance = new Interface();
        // Test que la méthode s'exécute sans erreur
        assertDoesNotThrow(instance::afficherInvite);
    }

    /**
     * Test of afficherErreur method, of class Interface.
     */
    @Test
    void testAfficherErreur() {
        System.out.println("afficherErreur");
        String message = "Erreur de test";
        Interface instance = new Interface();
        // Test que la méthode s'exécute sans erreur
        assertDoesNotThrow(() -> instance.afficherErreur(message));
    }
    
}
