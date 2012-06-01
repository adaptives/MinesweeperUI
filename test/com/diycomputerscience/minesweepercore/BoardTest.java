package com.diycomputerscience.minesweepercore;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.diycomputerscience.minesweepercore.Board;
import com.diycomputerscience.minesweepercore.Point;
import com.diycomputerscience.minesweepercore.Square;


public class BoardTest {
	
	private MockInitializer mockInitializer;
	private Board board;
	
	
	
	@Before
	public void setUp() throws Exception {
		this.mockInitializer = new MockInitializer();
		this.board = new Board(mockInitializer);
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testMinePlacement() {
		Point mines[] = this.mockInitializer.mines();
		for(Point point : mines) {			
			assertTrue(board.isSquareMine(point));
		}
	}
	
	@Test
	public void testFetchSquareCount() throws Exception {
		int expectedCounts[][] = new int[][]{
				{-1, 2, 1, 1, 0, 0},
				{2, 3, -1, 1, 0, 0},
				{1, -1, 2, 2, 1, 1},
				{1, 2, 2, 2, -1, 2},
				{0, 2, -1, 4, 3, -1},
				{0, 2, -1, -1, 2, 1}
		};
		for(int row=0; row<Board.MAX_ROWS;row++) {
			for(int col=0; col<Board.MAX_COLS; col++) {				
				int expectedCount = expectedCounts[row][col];
				if(expectedCount != -1) {
					String msg = "Failed for cell [" + row + "][" + col + "]";
					assertEquals(msg , expectedCount, board.fetchSquareCount(new Point(row, col)));
				}
			}
		}
	}
	
	@Test
	public void testInitialSquareStatus() throws Exception {
		for(int row=0; row<Board.MAX_ROWS;row++) {
			for(int col=0; col<Board.MAX_COLS; col++) {				
				assertEquals(Square.STATUS.COVERED, board.fetchSquareStatus(new Point(row, col)));		
			}
		}
	}
	
	@Test
	public void testMarkAsMineWhenCovered() throws Exception {
		for(int row=0; row<Board.MAX_ROWS;row++) {
			for(int col=0; col<Board.MAX_COLS; col++) {
				Point point = new Point(row, col);
				this.board.markSquareAsMine(point);
				assertEquals(Square.STATUS.FLAGGED, board.fetchSquareStatus(point));		
			}
		}
	}
	
	@Test
	public void testUncoverSquaresThatAreNotMines() throws Exception {
		for(int row=0; row<Board.MAX_ROWS;row++) {
			for(int col=0; col<Board.MAX_COLS; col++) {
				Point point = new Point(row, col);
				if(!this.board.isSquareMine(point)) {
					this.board.uncoverSquare(point);
					assertEquals(Square.STATUS.UNCOVERED, board.fetchSquareStatus(point));
				}					
			}
		}
	}
	
	@Test
	public void testUncoverSquaresThatAreAlreadyUncovereds() throws Exception {
		for(int row=0; row<Board.MAX_ROWS;row++) {
			for(int col=0; col<Board.MAX_COLS; col++) {
				Point point = new Point(row, col);
				if(!this.board.isSquareMine(point)) {
					this.board.uncoverSquare(point);
					this.board.uncoverSquare(point);
					assertEquals(Square.STATUS.UNCOVERED, board.fetchSquareStatus(point));
				}					
			}
		}
	}
	
	@Test
	public void testUncoverSquaresThatAreMines() throws Exception {
		for(int row=0; row<Board.MAX_ROWS;row++) {
			for(int col=0; col<Board.MAX_COLS; col++) {
				Point point = new Point(row, col);
				if(this.board.isSquareMine(point)) {
					try {
						this.board.uncoverSquare(point);
						//Note: Understand why we are not using the expected annotation to expect this Exception
						fail("Square [" + point + "] did not throw UncoveredMineException when uncovered");
					} catch(UncoveredMineException ume) {
						//good
					}
				}					
			}
		}
	}
	
	@Test
	public void testMarkAsMineWhenUncovered() throws Exception {
		for(int row=0; row<Board.MAX_ROWS;row++) {
			for(int col=0; col<Board.MAX_COLS; col++) {
				Point point = new Point(row, col);
				if(!this.board.isSquareMine(point)) {
					this.board.uncoverSquare(point);
					this.board.markSquareAsMine(point);
					assertEquals(Square.STATUS.UNCOVERED, board.fetchSquareStatus(point));
				}					
			}
		}
	}
	
	@Test
	public void testMarkAsMineWhenFlagged() throws Exception {
		for(int row=0; row<Board.MAX_ROWS;row++) {
			for(int col=0; col<Board.MAX_COLS; col++) {
				Point point = new Point(row, col);								
				this.board.markSquareAsMine(point);
				this.board.markSquareAsMine(point);
				assertEquals(Square.STATUS.COVERED, board.fetchSquareStatus(point));									
			}
		}
	}
	
}
