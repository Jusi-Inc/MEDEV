/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package infosi.partielmedev;

/**
 *
 * @author Catherine
 */
public class Game {
	final int maxError = 7;
	private int state; // 0: pas de jeu, 1: en cours, 2: gagnée, 3: perdue
	private int errorCount;
	private String secretWord;
	private String word;
	private String knownLetter;
	
	/**
	 * Constructeur pour créer une partie
	 * @param word2find Le mot à trouver
	 */
	public Game(String word2find) {
		state = 1;
		errorCount = 0;
		secretWord = word2find.toUpperCase();
		
		// Initialisation du mot vide
		word = secretWord;
		int len = secretWord.length();
		for (int i = 0; i < len; i++) {
			if (Character.isLetter(word.charAt(i))) {
				word[i] = '_';
			}
		}
	}
	
	/**
	 * Constructeur de copie
	 * @param g le jeu à copier
	 */
	public Game(Game g) {
		state = g.getState();
		errorCount = g.getErrorCount();
		secretWord = g.getSecretWord();
		word = g.getWord();
		knownLetter = g.getKnownLetter();
	}
	
	
	
	/**
	 * Essaie une lettre sur le pendue
	 * @param c Une lettre à essayer
	 * @return 0 si aucun changement, 1 si la lettre est correcte, 2 si la lettre est fausse
	 */
	public int putCharacter(String c) {
		int res = 0;
		
		
		return res;
	}

	/**
	 *
	 * @return
	 */
	public int getState() {
		return state;
	}

	/**
	 *
	 * @param state
	 */
	public void setState(int state) {
		this.state = state;
	}

	/**
	 *
	 * @return
	 */
	public int getErrorCount() {
		return errorCount;
	}

	/**
	 *
	 * @param errorCount
	 */
	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}

	/**
	 *
	 * @return
	 */
	public String getSecretWord() {
		return secretWord;
	}

	/**
	 *
	 * @param secretWord
	 */
	public void setSecretWord(String secretWord) {
		this.secretWord = secretWord;
	}

	/**
	 *
	 * @return
	 */
	public String getWord() {
		return word;
	}

	/**
	 *
	 * @param word
	 */
	public void setWord(String word) {
		this.word = word;
	}

	/**
	 *
	 * @return
	 */
	public String getKnownLetter() {
		return knownLetter;
	}

	/**
	 *
	 * @param knownLetter
	 */
	public void setKnownLetter(String knownLetter) {
		this.knownLetter = knownLetter;
	}
	
	
	
	
	
	
}
