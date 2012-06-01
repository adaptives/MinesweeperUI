package com.diycomputerscience.minesweepercore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Board {

	public static final int MAX_COLS = 6;
	public static final int MAX_ROWS = 6;
	
	private PersistenceStrategy persistenceStrategy;
	private IInitializer boardInitializer;
	private Square sqaures[][];
	
	public Board() {		
		
	}
	
	public Board(IInitializer boardInitializer) {
		this.boardInitializer = boardInitializer;
		this.sqaures = new Square[MAX_ROWS][MAX_COLS];
		//instantiate all square
		for(int row=0; row<MAX_COLS; row++) {
			for(int col=0; col<MAX_ROWS; col++) {
				this.sqaures[row][col] = new Square();
			}
		}				
		//mark mines
		Point mines[] = this.boardInitializer.mines();
		for(Point point : mines) {
			this.sqaures[point.row][point.col].setMine(true);
		}
		computeCounts();
	}
	
	public void setPersistenceStrategy(PersistenceStrategy persistenceStrategy) {
		this.persistenceStrategy = persistenceStrategy;
	}
	
	public PersistenceStrategy getPersistenceStrategy() {
		return this.persistenceStrategy;
	}

	private void computeCounts() {
		//compute count
		for(int row=0; row<MAX_ROWS;row++) {
			for(int col=0; col<MAX_COLS; col++) {				
				Square square = this.sqaures[row][col];
				if(!square.isMine()) {
					Point squareLocation = new Point(row, col);
					List<Point> validNeighbours = computeValidNeighbours(squareLocation);					
					int mineCount = 0;
					for(Point neighbour : validNeighbours) {
						if(this.sqaures[neighbour.row][neighbour.col].isMine()) {
							mineCount++;
						}
					}
					square.setCount(mineCount);
				}
			}
		}
	}	

	private List<Point> computeValidNeighbours(Point p) {		
		List<Point> validNeighbours = new ArrayList<Point>();
		Point topLeft = new Point(p.row-1, p.col-1);
		if(pointValid(topLeft)) {
			validNeighbours.add(topLeft);
		}
		
		Point top = new Point(p.row-1, p.col);
		if(pointValid(top)) {
			validNeighbours.add(top);
		}
		
		Point topRight = new Point(p.row-1, p.col+1);
		if(pointValid(topRight)) {
			validNeighbours.add(topRight);
		}
		
		Point right = new Point(p.row, p.col+1);
		if(pointValid(right)) {
			validNeighbours.add(right);
		}
		
		Point bottomRight = new Point(p.row+1, p.col+1);
		if(pointValid(bottomRight)) {
			validNeighbours.add(bottomRight);
		}
		
		Point bottom = new Point(p.row+1, p.col);
		if(pointValid(bottom)) {
			validNeighbours.add(bottom);
		}
		
		Point bottomLeft = new Point(p.row+1, p.col-1);
		if(pointValid(bottomLeft)) {
			validNeighbours.add(bottomLeft);
		}
		
		Point left = new Point(p.row, p.col-1);
		if(pointValid(left)) {
			validNeighbours.add(left);
		}
		
		return validNeighbours;
		
	}
	
	private boolean pointValid(Point p) {		
		if(p.row >=0 && p.row < MAX_ROWS && p.col >= 0 && p.col < MAX_COLS) {
			return true;
		} else {			
			return false;
		}
	}

	public void setSquare(Point point, Square square) {
		this.sqaures[point.row][point.col] = square;
	}
	
	public Square getSquare(Point point) {
		return this.sqaures[point.row][point.col];
	}

	/**
	 * Save the current state of the board to some persistent storage 
	 */
	public void save() throws IOException, PersistenceException {
		this.persistenceStrategy.save(this.sqaures);
	}
	
	public void load() throws PersistenceException {
		this.sqaures = this.persistenceStrategy.load();
		this.computeCounts();
	}
	
	public void printBoard() {
		for(int row = 0; row < MAX_ROWS; row++) {
			for(int col = 0; col < MAX_COLS; col++) {
				Square square = this.sqaures[row][col];
				if(square.isMine()) {
					System.out.print(" X ");
				} else {
					System.out.print(" " + square.getCount() + " ");
				}				
			}
			System.out.println("");
		}
	}
		
}
