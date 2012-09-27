/**
 * 
 */
package com.diycomputerscience;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.diycomputerscience.minesweepercore.Board;
import com.diycomputerscience.minesweepercore.Point;
import com.diycomputerscience.minesweepercore.RandomBoardInitializer;
import com.diycomputerscience.minesweepercore.UncoveredMineException;

/**
 * @author pshah
 * 
 */

public class MinesweeperUI extends JFrame implements MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final int row = 6;
	final int col = 6;
	private JButton gridArr[][] = new JButton[row][col];
	private JPanel panel;
	private Board mineBoard;

	public MinesweeperUI() {
		this.setTitle("Minesweeper");

		this.mineBoard = new Board(new RandomBoardInitializer());
		this.setMineLayout();
		this.addWindowListener();
		
	}

	/**
	 * This method sets the layoutManager to Panel and creates the Buttons for
	 * the grid
	 */
	private void setMineLayout() {
		this.setSize(300, 300);
		this.setVisible(true);
		panel = new JPanel();
		panel.setLayout(new GridLayout(row, col));

		/** In this for loop create the Buttons and populate the Button Array */
		JButton gridButton;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				gridButton = new JButton("");
				gridArr[i][j] = gridButton;
			}
		}
		this.addGridButtons();
		this.addGridMouseListener();
		this.add(panel);
		this.validate();

	}// setMineLayout()

	/** This method adds the JButtons array to the Panel on the JFrame */
	private void addGridButtons() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++)
				panel.add(gridArr[i][j]);
		}
	}

	/**
	 * This method circulates through the Buttons Array and adds MouseListener
	 * to each Button on the grid
	 */
	private void addGridMouseListener() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++)
				gridArr[i][j].addMouseListener(this);
		}
	}// addGridMouseListener()

	@Override
	public void mouseEntered(MouseEvent me) {

	}

	@Override
	public void mouseClicked(MouseEvent me) {
		for (int i = 0; i < gridArr.length; i++)
			for (int j = 0; j < gridArr[0].length; j++) {
				if ((me.getComponent()).equals(gridArr[i][j])) {

					if (SwingUtilities.isLeftMouseButton(me)) {
						try {
							
							mineBoard.uncoverSquare(new Point(i, j));
							
						} catch (UncoveredMineException ue) 
						{
							System.out.println("UnCovered Exception : " + ue);
						}
						if (mineBoard.isSquareMine(new Point(i, j))) {
							// Exit the game
							System.exit(ERROR);
						} else {
							// not a mine so display its count
							((JButton) me.getComponent()).setBackground(new Color(255, 255, 255));
							((JButton) me.getComponent()).setEnabled(false);
							gridArr[i][j].setText(""+ mineBoard.fetchSquareCount(new Point(i, j)));

						}
					}

					else {
						// if rightButton clicked
						if (SwingUtilities.isRightMouseButton(me))
							((JButton) me.getComponent()).setBackground(new Color(255, 0, 0));

					}
					return;
				}// if component matches
			}

	}

	@Override
	public void mouseExited(MouseEvent me) {
		// this.layGrid();
	}

	@Override
	public void mousePressed(MouseEvent me) {

	}

	@Override
	public void mouseReleased(MouseEvent me) {
		// this.layGrid();
	}

	/**
	 * This method adds Window closing listener
	 */
	private void addWindowListener() {
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				System.exit(DISPOSE_ON_CLOSE);
			}
		});

	}// addWindowListener()

	public static void main(String args[]) {
		// Create a JFrame and init it here
		MinesweeperUI MUI = new MinesweeperUI();

	}
}
