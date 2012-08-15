/**
 * 
 */
package com.diycomputerscience.minesweepercore;

/**
 * @author pshah
 * 
 */
public interface SquareState {

	public SquareState uncover() throws UncoveredMineException;

	public SquareState mark();

}
