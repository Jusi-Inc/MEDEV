/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package infosi.partielmedev;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
public class DictionnaryTest {
	
	public DictionnaryTest() {
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
	 * Test of pickWord method, of class Dictionnary.
	 */
	@Test
	public void testPickWord() {
		System.out.println("pickWord");
		
		ArrayList<String> list = new ArrayList<>();
		list.add("HI");
		Dictionnary instance = Dictionnary(list);
		String expResult = "HI";
		String result = instance.pickWord();
		assertEquals(expResult, result);
		
	}

	/**
	 * Test of chooseWord method, of class Dictionnary.
	 */
	@Test
	public void testChooseWord() {
		System.out.println("chooseWord");
		int i = 1;
		
		ArrayList<String> list = new ArrayList<>();
		list.add("HI");
		list.add("HELLO");
		list.add("BRIDGE");
		Dictionnary instance = Dictionnary(list);
		
		String expResult = "HELLO";
		String result = instance.chooseWord(i);
		assertEquals(expResult, result);
	}

	/**
	 * Test of listSize method, of class Dictionnary.
	 */
	@Test
	public void testListSize() {
		System.out.println("listSize");
		
		List<String> list = new ArrayList<>();
		list.add("HI");
		list.add("HELLO");
		list.add("BRIDGE");
		Dictionnary instance = Dictionnary(new List<String>(list));
		
		int expResult = 3;
		int result = instance.listSize();
		assertEquals(expResult, result);
	}

	/**
	 * Test of readFile method, of class Dictionnary.
	 */
	@Test
	public void testReadFile() throws Exception {
		System.out.println("readFile");
		Dictionnary instance = null;
		boolean expResult = false;
		boolean result = instance.readFile();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}	
}
