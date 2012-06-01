package com.diycomputerscience.minesweepercore;

import java.io.IOException;


public class Board {

	public static final int MAX_COLS = 6;
	public static final int MAX_ROWS = 6;
	
	private PersistenceStrategy persistenceStrategy;
	private IInitializer boardInitializer;
	private BoardState boardState;
	
	public Board() {		
		
	}
	
	public Board(IInitializer boardInitializer) {
		this.boardInitializer = boardInitializer;
		
		this.boardState = new BoardState(MAX_ROWS, MAX_COLS);
		this.boardState.init();
		this.boardState.markMines(this.boardInitializer.mines());						
		this.boardState.computeCounts();
	}
	
	public void setPersistenceStrategy(PersistenceStrategy persistenceStrategy) {
		this.persistenceStrategy = persistenceStrategy;
	}
	
	public PersistenceStrategy getPersistenceStrategy() {
		return this.persistenceStrategy;
	}

	public void setSquare(Point point, Square square) {
		this.boardState.setSquare(point, square);
	}
	
	public Square getSquare(Point point) {
		return this.boardState.getSquare(point);		
	}

	/**
	 * Save the current state of the board to some persistent storage 
	 */
	public void save() throws IOException, PersistenceException {
		this.persistenceStrategy.save(this.boardState.getSquares());
	}
	
	public void load() throws PersistenceException {
		this.boardState = new BoardState(MAX_ROWS, MAX_COLS);
		boardState.setSquares(this.persistenceStrategy.load());
		this.boardState.computeCounts();
	}
	
	//all ops that can be done on this board
	
	public int fetchSquareCount(Point point) {
		return this.boardState.getSquare(point).getCount();
	}
	
	public boolean isSquareMine(Point point) {
		return this.boardState.getSquare(point).isMine();
	}
	
	public Square.STATUS fetchSquareStatus(Point point) {
		return this.boardState.getSquare(point).getStatus();
	}

	public void markSquareAsMine(Point point) {
		this.boardState.getSquare(point).markAsMine();		
	}

	public void uncoverSquare(Point point) throws UncoveredMineException  {
		this.boardState.getSquare(point).uncover();
	}
	
	//end ops
		
}
