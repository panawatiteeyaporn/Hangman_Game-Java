/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {

	private static final int MaxGuessTurn = 8;

	public void run() {
		
		wordGuessSoFar = "";
		turn = 0;
		isGameWon = false;

		/*
		 * Get getNewWord object from HangmanLexicon class and call its getWord
		 * method using random generator to get the word for the game.
		 */
		HangmanLexicon getNewWord = new HangmanLexicon();
		String word = getNewWord.getWord(rgen.nextInt(0, getNewWord.getWordCount()));

		println("Welcome to Hangman!");
		canvas.reset();

		while (turn < MaxGuessTurn) {

			println(checkGameCondition(word));
			if (isGameWon == true)
				break;
			turnLeft();
			getLetter();
			guessCondition = isGuessCorrect(word);
			checkGuess();
		}

		println(lastMessage(word));
		gameRestart();

	}
	
	private void gameRestart() {
		
		println("Would you like to play again?");
		String play = readLine("Enter y or n: ");
		play = play.toUpperCase();
		if (play.charAt(0) == 'Y'){
			run();
		} else if (play.charAt(0) == 'N'){
			println("Thank you for playing!");
		} else {
			println("Option unrecognised, please re-enter.");
			gameRestart();
		}
	}

	public void init() {
		setSize(800, 600);
		canvas = new HangmanCanvas();
		add(canvas);
	}

	/* Print out message about the number of guesses player have left. */
	private void turnLeft() {

		if ((MaxGuessTurn - turn) == 1) {
			println("You have only one guess left.");
		} else {
			println("You have " + (MaxGuessTurn - turn) + " guesses left.");
		}
	}

	/* Return wining or losing message according to the outcome. */
	private String lastMessage(String answer) {

		if (isGameWon == true) {
		return	"You Win!";
		
		} else {
			canvas.displayAnswer(answer);
		return	"You are completely hung! \n" + "You lose.";
		}
	}

	/* The method check for game's condition and return message accordingly. */
	private String checkGameCondition(String str) {

		String guessWord = replaceWord(str);
		canvas.displayWord(guessWord);
		isGameWon = checkGameCondition();

		if (isGameWon == true) {
			return "You guessed the word: " + guessWord;
		}

		return "The word now looks like this: " + guessWord;
	}

	/* Boolean method return true if there are no character "-". */
	private boolean checkGameCondition() {

		for (int i = 0; i < wordGuessSoFar.length(); i++) {
			if (wordGuessSoFar.charAt(i) == '-') {
				return false;
			}
		}
		return true;
	}

	/* This method get the letter from user. */
	private void getLetter() {

		String letter = readLine("Your guess: ");
		letter = letter.toUpperCase();
		guessLetter = letter.charAt(0);

	}

	/*
	 * This method check to see if the guess is correct and print respond
	 * accordingly while keeping track of the turn used.
	 */
	private void checkGuess() {

		if (guessCondition == true) {
			println("That guess is correct!");
		} else {
			turn++;
			println("There are no " + guessLetter + "'s in the word.");
			canvas.noteIncorrectGuess(guessLetter);
		}
	}

	/* Boolean return true or false for the guesses. */
	private boolean isGuessCorrect(String word) {

		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == guessLetter) {
				return true;
			}
		}

		return false;
	}

	/*
	 * The method return a word with the same length as the the answer using "-"
	 * char. if the word is empty. Otherwise, The method return the word with
	 * correctly guessed letter shown
	 */
	private String replaceWord(String word) {

		if (wordGuessSoFar.equals("")) {
			char emptyChar = '-';
			for (int i = 0; i < word.length(); i++) {
				wordGuessSoFar = wordGuessSoFar + emptyChar;
			}
		} else {

			for (int i = 0; i < word.length(); i++) {
				if (word.charAt(i) == guessLetter) {
					wordGuessSoFar = wordGuessSoFar.substring(0, i)
							+ guessLetter + wordGuessSoFar.substring(i + 1);
				}
			}

		}
		return wordGuessSoFar;
	}

	/* Randomly generated instance variables. */
	private RandomGenerator rgen = RandomGenerator.getInstance();
	
	private String wordGuessSoFar;
	private char guessLetter;
	private int turn;
	private boolean guessCondition;
	private boolean isGameWon;
	private HangmanCanvas canvas;

}
