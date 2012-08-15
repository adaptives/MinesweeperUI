package com.diycomputerscience.minesweepercore;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 */

/**
 * @author pshah
 * 
 */
public class FilePersistenceStrategy implements PersistenceStrategy {

	public static final String SQUARE_FORMAT = "%d,%d:%b-%s";
	public static final String SQUARE_LOAD_REGEX = "(\\d*),(\\d*):(true|false)-(COVERED|UNCOVERED|FLAGGED)";

	private FileConnectionFactory fileConnectionFactory;

	public FilePersistenceStrategy(FileConnectionFactory fileConnectionfacory) {
		this.fileConnectionFactory = fileConnectionfacory;
	}

	@Override
	public void save(Square squares[][]) throws PersistenceException {
		try {
			PrintWriter writer = getWriter();
			for (int row = 0; row < Board.MAX_ROWS; row++) {
				for (int col = 0; col < Board.MAX_COLS; col++) {
					Square square = squares[row][col];
					String squareRep = String.format(SQUARE_FORMAT, row, col,
							square.isMine(), square.getStatus());
					writer.println(squareRep);
				}
			}
		} catch (Exception e) {
			String msg = "Could not save the board";
			throw new PersistenceException(msg, e);
		}
	}

	private PrintWriter getWriter() throws Exception {
		return fileConnectionFactory.getWriter();
	}

	@Override
	public Square[][] load() throws PersistenceException {

		Square squares[][] = new Square[Board.MAX_ROWS][Board.MAX_COLS];

		try {
			BufferedReader reader = getReader();
			Pattern regexPattern = Pattern.compile(SQUARE_LOAD_REGEX);
			String line = "";
			while ((line = reader.readLine()) != null) {
				Matcher matcher = regexPattern.matcher(line);
				matcher.find();
				String sRow = matcher.group(1);
				int row = Integer.parseInt(sRow);
				String sCol = matcher.group(2);
				int col = Integer.parseInt(sCol);
				String sIsMine = matcher.group(3);
				boolean mine = Boolean.parseBoolean(sIsMine);
				String sStatus = matcher.group(4);
				Square.STATUS status = Square.STATUS.valueOf(sStatus);

				Square square = new Square();
				square.setMine(mine);

				square.setStatus(status);
				squares[row][col] = square;
			}
		} catch (Exception ioe) {
			String msg = "Could not load persisted state of the board";
			throw new PersistenceException(msg, ioe);
		}

		return squares;
	}

	private BufferedReader getReader() throws Exception {
		return this.fileConnectionFactory.getReader();
	}

}
