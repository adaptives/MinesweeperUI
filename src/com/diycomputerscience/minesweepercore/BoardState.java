/**
 * 
 */
package com.diycomputerscience.minesweepercore;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pshah
 * 
 */
public class BoardState {
	private final int MAX_ROWS;
	private final int MAX_COLS;
	private Square squares[][];

	public BoardState(int max_rows, int max_cols) {
		this.MAX_ROWS = max_rows;
		this.MAX_COLS = max_cols;
		this.squares = new Square[MAX_ROWS][MAX_COLS];
	}

	public void init() {
		// instantiate all square
		for (int row = 0; row < MAX_COLS; row++) {
			for (int col = 0; col < MAX_ROWS; col++) {
				this.squares[row][col] = new Square();
			}
		}
	}

	public void markMines(Point mines[]) {
		for (Point point : mines) {
			this.squares[point.row][point.col].setMine(true);
		}
	}

	public void computeCounts() {
		// compute count
		for (int row = 0; row < MAX_ROWS; row++) {
			for (int col = 0; col < MAX_COLS; col++) {
				Square square = this.squares[row][col];
				if (!square.isMine()) {
					Point squareLocation = new Point(row, col);
					List<Point> validNeighbours = computeValidNeighbours(squareLocation);
					int mineCount = 0;
					for (Point neighbour : validNeighbours) {
						if (this.squares[neighbour.row][neighbour.col].isMine()) {
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
		Point topLeft = new Point(p.row - 1, p.col - 1);
		if (pointValid(topLeft)) {
			validNeighbours.add(topLeft);
		}

		Point top = new Point(p.row - 1, p.col);
		if (pointValid(top)) {
			validNeighbours.add(top);
		}

		Point topRight = new Point(p.row - 1, p.col + 1);
		if (pointValid(topRight)) {
			validNeighbours.add(topRight);
		}

		Point right = new Point(p.row, p.col + 1);
		if (pointValid(right)) {
			validNeighbours.add(right);
		}

		Point bottomRight = new Point(p.row + 1, p.col + 1);
		if (pointValid(bottomRight)) {
			validNeighbours.add(bottomRight);
		}

		Point bottom = new Point(p.row + 1, p.col);
		if (pointValid(bottom)) {
			validNeighbours.add(bottom);
		}

		Point bottomLeft = new Point(p.row + 1, p.col - 1);
		if (pointValid(bottomLeft)) {
			validNeighbours.add(bottomLeft);
		}

		Point left = new Point(p.row, p.col - 1);
		if (pointValid(left)) {
			validNeighbours.add(left);
		}

		return validNeighbours;

	}

	public void setSquare(Point point, Square square) {
		this.squares[point.row][point.col] = square;
	}

	public Square getSquare(Point point) {
		return this.squares[point.row][point.col];
	}

	private boolean pointValid(Point p) {
		if (p.row >= 0 && p.row < MAX_ROWS && p.col >= 0 && p.col < MAX_COLS) {
			return true;
		} else {
			return false;
		}
	}

	public Square[][] getSquares() {
		return squares;
	}

	public void setSquares(Square squares[][]) {
		this.squares = squares;
	}

	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer();
		for (int row = 0; row < MAX_ROWS; row++) {
			for (int col = 0; col < MAX_COLS; col++) {
				Square square = this.squares[row][col];
				if (square.isMine()) {
					buff.append(" X " + "\n");
				} else {
					buff.append(" " + square.getCount() + " ");
				}
			}
			buff.append("\n");
		}
		return buff.toString();
	}

}
