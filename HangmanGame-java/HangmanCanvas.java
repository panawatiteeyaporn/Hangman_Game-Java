/*
 * File: HangmanCanvas.java
 * ------------------------
 * Name: Panawat Iteeyaporn
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

import java.awt.Color;

public class HangmanCanvas extends GCanvas {

	/* Resets the display so that only the scaffold appears */
	public void reset() {

		removeAll();
		addRope();
		addBeam();
		addScaffold();

		wrongLetters = "";
		guessCount = 0;

	}

	/* Add rope to the canvas. */
	private void addRope() {

		double x = ((getWidth() - (HEAD_RADIUS * 2)) / 2) + HEAD_RADIUS;
		double y = (getHeight() - (HEAD_RADIUS * 2)) / CANVAS_Y_OFFSET;

		GLine rope = new GLine(x, y, x, y - ROPE_LENGTH);

		add(rope);

	}

	/* Add beam to the canvas. */
	private void addBeam() {

		double x = ((getWidth() - (HEAD_RADIUS * 2)) / 2) + HEAD_RADIUS;
		double y = ((getHeight() - (HEAD_RADIUS * 2)) / CANVAS_Y_OFFSET)
				- ROPE_LENGTH;

		GLine beam = new GLine(x, y, x - BEAM_LENGTH, y);

		add(beam);

	}

	/* Add scaffold to the canvas. */
	private void addScaffold() {

		double x = (((getWidth() - (HEAD_RADIUS * 2)) / 2) + HEAD_RADIUS)
				- BEAM_LENGTH;
		double y = ((getHeight() - (HEAD_RADIUS * 2)) / CANVAS_Y_OFFSET)
				- ROPE_LENGTH;

		GLine scaffold = new GLine(x, y, x, y + SCAFFOLD_HEIGHT);

		add(scaffold);

	}

	/*
	 * Updates the word on the screen to correspond to the current state of the
	 * game. The argument string shows what letters have been guessed so far;
	 * unguessed letters are indicated by hyphens.
	 */
	public void displayWord(String word) {

		remove(wordShow);
		wordShow = new GLabel(word);
		wordShow.setFont("Times New Roman-24");

		double x = (getWidth() - wordShow.getWidth()) / DISPLAY_X_OFFSET;
		double y = (getHeight() - wordShow.getHeight()) - DISPLAY_Y_OFFSET;

		add(wordShow, x, y);

	}

	/*
	 * Shows the correct word at the bottom of the canvas screen when the player
	 * lost the game.
	 */
	public void displayAnswer(String answer) {

		GLabel a = new GLabel("The word is: " + answer);
		a.setFont("Times New Roman-20");
		a.setColor(Color.red);

		double x = (getWidth() - wordShow.getWidth()) / DISPLAY_X_OFFSET;
		double y = (getHeight() - wordShow.getHeight()) - DISPLAY_Y_OFFSET
				+ (guessedLetters.getHeight() * 2) + 30;

		add(a, x, y);

	}

	/*
	 * Updates the display to correspond to an incorrect guess by the user.
	 * Calling this method causes the next body part to appear on the scaffold
	 * and adds the letter to the list of incorrect guesses that appears at the
	 * bottom of the window.
	 */
	public void noteIncorrectGuess(char letter) {

		wrongLetters = wrongLetters + letter;
		guessCount++;

		addPastGuesses();

		if (guessCount == 1) {
			head();
		} else if (guessCount == 2) {
			body();
		} else if (guessCount == 3) {
			leftArm();
		} else if (guessCount == 4) {
			rightArm();
		} else if (guessCount == 5) {
			leftLeg();
		} else if (guessCount == 6) {
			rightLeg();
		} else if (guessCount == 7) {
			leftFoot();
		} else if (guessCount == 8) {
			rightFoot();
		}

	}

	/* Add past guesses on the canvas as a GLabel object. */
	private void addPastGuesses() {

		remove(guessedLetters);
		guessedLetters = new GLabel(wrongLetters);
		guessedLetters.setFont("Times New Roman-24");

		double x = (getWidth() - guessedLetters.getWidth()) / DISPLAY_X_OFFSET;
		double y = ((getHeight() - guessedLetters.getHeight()) - DISPLAY_Y_OFFSET)
				+ (guessedLetters.getHeight() * 2);

		add(guessedLetters, x, y);

	}

	/* Add head to the canvas. */
	private void head() {

		GOval head = new GOval(HEAD_RADIUS * 2, HEAD_RADIUS * 2);

		double x = (getWidth() - (HEAD_RADIUS * 2)) / 2;
		double y = (getHeight() - (HEAD_RADIUS * 2)) / CANVAS_Y_OFFSET;

		add(head, x, y);
	}

	/* Add body to the canvas. */
	private void body() {

		double x = ((getWidth() - (HEAD_RADIUS * 2)) / 2) + HEAD_RADIUS;
		double y = ((getHeight() - (HEAD_RADIUS * 2)) / CANVAS_Y_OFFSET)
				+ (HEAD_RADIUS * 2);

		GLine body = new GLine(x, y, x, y + BODY_LENGTH);

		add(body);
	}

	/* Add left arm to the canvas. */
	private void leftArm() {

		double x = ((getWidth() - (HEAD_RADIUS * 2)) / 2) + HEAD_RADIUS;
		double y = ((getHeight() - (HEAD_RADIUS * 2)) / CANVAS_Y_OFFSET)
				+ (HEAD_RADIUS * 2) + ARM_OFFSET_FROM_HEAD;

		GLine upperArm = new GLine(x, y, x - UPPER_ARM_LENGTH, y);

		GLine lowerArm = new GLine(x - UPPER_ARM_LENGTH, y, x
				- UPPER_ARM_LENGTH, y + LOWER_ARM_LENGTH);

		add(upperArm);
		add(lowerArm);

	}

	/* Add right arm to the canvas. */
	private void rightArm() {

		double x = ((getWidth() - (HEAD_RADIUS * 2)) / 2) + HEAD_RADIUS;
		double y = ((getHeight() - (HEAD_RADIUS * 2)) / CANVAS_Y_OFFSET)
				+ (HEAD_RADIUS * 2) + ARM_OFFSET_FROM_HEAD;

		GLine upperArm = new GLine(x, y, x + UPPER_ARM_LENGTH, y);

		GLine lowerArm = new GLine(x + UPPER_ARM_LENGTH, y, x
				+ UPPER_ARM_LENGTH, y + LOWER_ARM_LENGTH);

		add(upperArm);
		add(lowerArm);

	}

	/* Add left leg to the canvas. */
	private void leftLeg() {

		double x = ((getWidth() - (HEAD_RADIUS * 2)) / 2) + HEAD_RADIUS;
		double y = ((getHeight() - (HEAD_RADIUS * 2)) / CANVAS_Y_OFFSET)
				+ (HEAD_RADIUS * 2) + BODY_LENGTH;

		GLine hip = new GLine(x, y, x - HIP_WIDTH, y);
		GLine leg = new GLine(x - HIP_WIDTH, y, x - HIP_WIDTH, y + LEG_LENGTH);

		add(hip);
		add(leg);

	}

	/* Add right leg to the canvas. */
	private void rightLeg() {

		double x = ((getWidth() - (HEAD_RADIUS * 2)) / 2) + HEAD_RADIUS;
		double y = ((getHeight() - (HEAD_RADIUS * 2)) / CANVAS_Y_OFFSET)
				+ (HEAD_RADIUS * 2) + BODY_LENGTH;

		GLine hip = new GLine(x, y, x + HIP_WIDTH, y);
		GLine leg = new GLine(x + HIP_WIDTH, y, x + HIP_WIDTH, y + LEG_LENGTH);

		add(hip);
		add(leg);
	}

	/* Add left foot to the canvas. */
	private void leftFoot() {

		double x = ((getWidth() - (HEAD_RADIUS * 2)) / 2) + HEAD_RADIUS
				- HIP_WIDTH;
		double y = ((getHeight() - (HEAD_RADIUS * 2)) / CANVAS_Y_OFFSET)
				+ (HEAD_RADIUS * 2) + BODY_LENGTH + LEG_LENGTH;

		GLine foot = new GLine(x, y, x - FOOT_LENGTH, y);

		add(foot);

	}

	/* Add right foot to the canvas. */
	private void rightFoot() {

		double x = ((getWidth() - (HEAD_RADIUS * 2)) / 2) + HEAD_RADIUS
				+ HIP_WIDTH;
		double y = ((getHeight() - (HEAD_RADIUS * 2)) / CANVAS_Y_OFFSET)
				+ (HEAD_RADIUS * 2) + BODY_LENGTH + LEG_LENGTH;

		GLine foot = new GLine(x, y, x + FOOT_LENGTH, y);

		add(foot);
	}

	/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;

	private static final double CANVAS_Y_OFFSET = 8;

	private static final double DISPLAY_X_OFFSET = 8;
	private static final double DISPLAY_Y_OFFSET = 80;

	private GLabel wordShow = new GLabel("");
	private GLabel guessedLetters = new GLabel("");

	private String wrongLetters = "";

	private int guessCount;

}
