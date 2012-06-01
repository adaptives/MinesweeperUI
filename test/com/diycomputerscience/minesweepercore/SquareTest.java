package com.diycomputerscience.minesweepercore;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.diycomputerscience.minesweepercore.Square;
import com.diycomputerscience.minesweepercore.UncoveredMineException;

public class SquareTest {

	private Square square;
	
	@Before
	public void setUp() throws Exception {
		this.square = new Square();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInitialStatus() {
		assertEquals(Square.STATUS.COVERED, this.square.getStatus());
	}
	
	@Test
	public void testUncoveredSquareForMarkAsMine() {
		//initialize the square to uncovered
		this.square.setStatus(Square.STATUS.UNCOVERED);
		this.square.markAsMine();
		assertEquals(Square.STATUS.UNCOVERED, this.square.getStatus());
	}
	
	@Test
	public void testCoveredSquareForMarkAsMine() {
		this.square.markAsMine();
		assertEquals(Square.STATUS.FLAGGED, this.square.getStatus());
	}
	
	@Test
	public void testFlaggedSquareForMarkAsMine() {
		this.square.setStatus(Square.STATUS.FLAGGED);
		this.square.markAsMine();
		assertEquals(Square.STATUS.COVERED, this.square.getStatus());
	}
	
	@Test
	public void testUncoveredSquareForUncover() throws Exception {
		this.square.setStatus(Square.STATUS.UNCOVERED);
		this.square.uncover();
		assertEquals(Square.STATUS.UNCOVERED, this.square.getStatus());
	}
	
	@Test
	public void testNonMineCoveredSquareForUncover() throws Exception {
		this.square.setMine(false);
		this.square.setStatus(Square.STATUS.COVERED);
		this.square.uncover();
	}
	
	@Test
	public void testFlaggedSquareForUncover() throws Exception {
		this.square.setStatus(Square.STATUS.FLAGGED);
		this.square.uncover();
		assertEquals(Square.STATUS.FLAGGED, this.square.getStatus());
	}
	
	@Test(expected=UncoveredMineException.class)
	public void testMineCoveredSquareForUncover() throws Exception {
		this.square.setMine(true);
		this.square.setStatus(Square.STATUS.COVERED);
		this.square.uncover();
	}

}
