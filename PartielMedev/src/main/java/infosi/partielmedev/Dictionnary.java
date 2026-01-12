/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package infosi.partielmedev;

import java.util.ArrayList;
import java.util.Random;
import java.io.*;
import java.util.List;

/**
 * Classe permettant de choisir un mot au hasard
 * @author Catherine
 */
public class Dictionnary {
	private String filename;
	private ArrayList<String> wordList;
	private Random rand;

	/**
	 * Contructeur pour avoir un mot depuis un fichier
	 * @param file Le nom du fichier à lire
	 */
	public Dictionnary(String file) {
		rand = new Random();
		filename = file;
		wordList = new ArrayList<>();
	}

	/**
	 * Contructeur pour avoir un mot depuis une liste
	 * @param list La liste à copier
	 */
	public Dictionnary(List<String> list) {
		rand = new Random();
		filename = "";
		wordList = new ArrayList<>(list);
	}

	/**
	 * Contructeur de copie
	 * @param dico L'instance à copier
	 */
	public Dictionnary(Dictionnary dico) {
		rand = new Random();
		filename = dico.getFilename();
		wordList = new ArrayList<>(dico.getWordList());
	}

	/**
	 * Choisit un mot au hasard
	 * @return Un mot
	 */
	public String pickWord() {
		// Affiche une erreur si la liste est vide
		if (wordList.isEmpty()) {
			throw new ArrayIndexOutOfBoundsException();
		}

		// Trouve un index
		int index = rand.nextInt(wordList.size());

		return wordList.get(index);
	}

	/**
	 * Choix d'un mot en particulier
	 * @param i Un index
	 * @return
	 */
	public String chooseWord(int i) {
		// Erreur si l'index est trop grand
		if (i >= wordList.size()) {
			throw new ArrayIndexOutOfBoundsException();
		}
		return wordList.get(i);
	}

	/**
	 * Retourne le nombre de mot dans le dictionnaire
	 * @return La taille de la liste
	 */
	public int listSize() {
		return wordList.size();
	}


	/**
	 * Lecture du fichier
	 * @return Vrai si la lecture à été un succès
	 * @throws java.lang.Exception
	 */
	public boolean readFile() throws Exception { // NOSONAR
		boolean res = false;
		
		BufferedReader file = null;
		
		try {
			String line;
			file = new BufferedReader(new FileReader(filename)); // NOSONAR
			
			wordList.clear();
			
			// Constitution de la liste
			line = file.readLine();
			while (line != null) {
				String word = traitementDuMot(line);
				if (!word.isEmpty()) {
					wordList.add(word);
				}
			
			}
			file.close();
			res = true;
		} catch(IOException e) {
			throw new Exception("Erreur lors de la lecture de la sauvegarde"); // NOSONAR
		}

		return res;
	}

	private String traitementDuMot(String mot) {
		mot = mot.trim();
		mot = mot.toUpperCase();
		return mot;
	}

	/**
	 *
	 * @return
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 *
	 * @param filename
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 *
	 * @return
	 */
	public List<String> getWordList() {
		return wordList;
	}

	/**
	 *
	 * @param wordList
	 */
	public void setWordList(List<String> wordList) {
		this.wordList = new ArrayList<>(wordList);
	}

	/**
	 *
	 * @return
	 */
	public Random getRand() {
		return rand;
	}

	/**
	 *
	 * @param rand
	 */
	public void setRand(Random rand) {
		this.rand = rand;
	}
	
	
}
