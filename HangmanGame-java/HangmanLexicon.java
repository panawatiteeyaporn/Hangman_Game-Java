/*
 * File: HangmanLexicon.java
 * -------------------------
 * Name: Panawat Iteeyaporn
 * This file contains words from HangmanLexicon.txt
 */

import java.io.*;
import java.util.ArrayList;

public class HangmanLexicon {

	ArrayList<String> strList = new ArrayList<String>();

	// This is the HangmanLexicon constructor
	public HangmanLexicon() {

		BufferedReader readWord = null;
		try {
			readWord = new BufferedReader(new FileReader("HangmanLexicon.txt"));
			while (true) {
				String line = readWord.readLine();
				if (line == null)
					break;
				strList.add(line);
			}
		} catch (IOException e) {

		}

		try {
			readWord.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* Returns the number of words in the lexicon. */
	public int getWordCount() {
		return strList.size();
	}

	/* Returns the word at the specified index. */
	public String getWord(int index) {
		return strList.get(index);
	}
}
