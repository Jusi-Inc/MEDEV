/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package infosi.partielmedev;

import java.util.ArrayList;
import java.util.Random;
import java.io.*;
import java.util.StringTokenizer;

/**
 *
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
		wordList = new ArrayList<String>();
	}

	/**
	 * Contructeur pour avoir un mot depuis une liste
	 * @param list La liste à copier
	 */
	public Dictionnary(ArrayList list) {
		rand = new Random();
		filename = "";
		wordList = new ArrayList<String>(list);
	}

	/**
	 * Contructeur de copie
	 * @param dico L'instance à copier
	 */
	public Dictionnary(Dictionnary dico) {
		rand = new Random();
		filename = dico.getFilename();
		wordList = new ArrayList<String>(dico.getWordList());
	}

	/**
	 * Choisit un mot au hasard. Lit le fichier sila liste est vide
	 * @return Un mot
	 */
	public String pickWord() {
		String res = "";

		// Essaie de lire le fichier si la liste est vide		
		if (wordList.size() == 0) {
			readFile();
		}

		// Trouve un index
		int index = rand.nextInt(wordList.size());

		// Copie le mot
		res = wordList.get(index);

		return res;
	}

	public String chooseWord(int i) {
		// Erreur si l'index est trop grand
		if (i >= wordList.size()) {
			// TODO
		}

		return wordList.get(i);
	}


	/**
	 * Lecture du fichier
	 * @return Vrai si la lecture à été un succès
	 */
	public boolean readFile() {
		boolean res = false;

		// TODO

		return res;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public ArrayList<String> getWordList() {
		return wordList;
	}

	public void setWordList(ArrayList<String> wordList) {
		this.wordList = wordList;
	}

	public Random getRand() {
		return rand;
	}

	public void setRand(Random rand) {
		this.rand = rand;
	}
	
	
}
