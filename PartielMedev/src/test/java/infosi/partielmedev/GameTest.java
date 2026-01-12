/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package infosi.partielmedev;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Catherine
 */
public class GameTest {
	
	public GameTest() {
	}
	
	@BeforeAll
	public static void setUpClass() {
	}
	
	@AfterAll
	public static void tearDownClass() {
	}
	
	@BeforeEach
	public void setUp() {
	}
	
	@AfterEach
	public void tearDown() {
	}

	/**
	 * Test of putCharacter method, of class Game.
	 */
	@Test
	public void testPutCharacter() {
		System.out.println("putCharacter");
		Game instance = new Game(1, 0, "TEST", "", "TES");
		
		// Test d'un succès
		int expResult = 1;
		int result = instance.putCharacter('T');
		assertEquals(expResult, result);
		
		// Test d'aucun changement
		expResult = 0;
		result = instance.putCharacter('t');
		assertEquals(expResult, result);
		
		result = instance.putCharacter(' ');
		assertEquals(expResult, result);
		
		expResult = 2;
		result = instance.putCharacter('A');
		assertEquals(expResult, result);
	}
}
