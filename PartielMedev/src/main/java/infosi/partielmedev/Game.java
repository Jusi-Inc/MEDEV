/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package infosi.partielmedev;

/**
 * Classe pour le respect des règles du jeu
 * @author Catherine
 */
public class Game {
	public static final int MAX_ERROR = 7;
	private int state; // 0: pas de jeu, 1: en cours, 2: gagnée, 3: perdue
	private int errorCount;
	private String secretWord;
	private String knownLetter;
	private String remainingLetter;
	
	/**
	 * Constructeur pour créer une partie
	 * @param word2find Le mot à trouver
	 */
	public Game(String word2find) {
		state = 1;
		errorCount = 0;
		secretWord = word2find.toUpperCase();
		
		// On initialise la liste des lettres à deviner
		remainingLetter = "";
		int len = secretWord.length();
		for (int i = 0; i < len; i++) {
			char char2include = secretWord.charAt(i);
			
			// Si le caractère n'est pas présent, on l'ajoute
			if (remainingLetter.indexOf(char2include) == -1) {
				remainingLetter = remainingLetter.concat(Character.toString(char2include));
			}
		}
	}
	
	/**
	 * Constructeur total de la classe
	 * @param state
	 * @param errorCount
	 * @param secretWord
	 * @param knownLetter
	 * @param remainingLetter
	 */
	public Game(int state, int errorCount, String secretWord, String knownLetter, String remainingLetter) {
		this.state = state;
		this.errorCount = errorCount;
		this.secretWord = secretWord;
		this.knownLetter = knownLetter;
		this.remainingLetter = remainingLetter;
	}
	

	/**
	 * Constructeur de copie
	 * @param g le jeu à copier
	 */
	public Game(Game g) {
		state = g.getState();
		errorCount = g.getErrorCount();
		secretWord = g.getSecretWord();
		knownLetter = g.getKnownLetter();
		remainingLetter = g.getRemainingLetter();
	}
	
	/**
	 * Essaie une lettre sur le pendue
	 * @param c Une lettre à essayer
	 * @return 0 si aucun changement, 1 si la lettre est correcte, 2 si la lettre est fausse
	 */
	public int putCharacter(char c) {
		int res = 0;
		
		// On met le caractère en majuscule si ce n'est pas le cas
		c = Character.toUpperCase(c);

		if (Character.isLetter(c) && knownLetter.indexOf(c) == -1 && state == 1) {
			int index = remainingLetter.indexOf(c);

			if (index != -1) {
				res = 1; // On change l'état du test: bonne lettre

				// On enlève la lettre de la liste
				remainingLetter = remainingLetter.replace(Character.toString(c), "");
			} else {
				res = 2; // On change l'état du test: mauvaise lettre
				errorCount++; // On ajoute une erreur
			}
			knownLetter += c; // On ajoute dans la liste des caractères vus
		}
		
		// On met à jour l'état de la partie si besoin
		if (errorCount >= MAX_ERROR) {
			state = 3; // Le joueur a perdu
		} else if (remainingLetter.length() == 0) {
			state = 2; // Le joueur a gagné
		}
		
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

	/**
	 *
	 * @return
	 */
	public String getRemainingLetter() {
		return remainingLetter;
	}

	/**
	 *
	 * @param remainingLetter
	 */
	public void setRemainingLetter(String remainingLetter) {
		this.remainingLetter = remainingLetter;
	}
}
