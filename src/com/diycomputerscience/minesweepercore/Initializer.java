package com.diycomputerscience.minesweepercore;

public interface Initializer {
	/**
	 * Returns an array of Point obejcts which denote the locations of mines on
	 * the board.
	 * 
	 * @return
	 */
	public Point[] mines();
}
