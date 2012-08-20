package com.diycomputerscience.minesweepercore;

public class UncoveredMineException extends Exception {

	public UncoveredMineException() {

	}

	public UncoveredMineException(String message) {
		super(message);
	}

	public UncoveredMineException(Throwable cause) {
		super(cause);
	}

	public UncoveredMineException(String message, Throwable cause) {
		super(message, cause);
	}
}
