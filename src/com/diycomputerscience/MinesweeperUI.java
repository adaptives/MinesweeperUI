/**
 * 
 */
package com.diycomputerscience;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author pshah
 *
 */
public class MinesweeperUI  extends JFrame implements MouseListener{
	
	final int row = 6;
	final int col = 6;
	private JButton gridArr[][] = new JButton[row][col];
	private JPanel panel;
	
	public MinesweeperUI()
	{
		this.setTitle("Minesweeper");
		this.setMineLayout();
		this.addWindowListener();
	}
	
	/**
	 * This method sets the layoutManager to Panel and 
	 * creates the Buttons for the grid 
	 */	
	private void setMineLayout()
	{
		this.setSize(300, 300);
		this.setVisible(true);
		panel = new JPanel();
		panel.setLayout(new GridLayout(row,col));
		
		/**In this for loop create the Buttons and populate the Button Array*/
		for(int i=0;i<row;i++)
		{
			for(int j=0;j<col;j++)
			{
				JButton gridButton = new JButton(""+i+j);
				gridArr[i][j] = gridButton;
			}
		}
		this.addGridButtons();
		this.addGridMouseListener();
		this.add(panel);
		this.validate();
		
	}//setMineLayout()
	
	/**This method sets the Text on Button equivalent to its cell number*/
	private void layGrid()
	{
		for(int i=0;i<row;i++)
		{
			for(int j=0;j<col;j++)
				((JButton)gridArr[i][j]).setText(""+i+j);
		}
	}
	
	/**This method adds the JButtons array to the Panel on the JFrame*/
	private void addGridButtons()
	{
		for(int i=0;i<row;i++)
		{
			for(int j=0;j<col;j++)
				panel.add(gridArr[i][j]);
		}
	}
	
	/**
	 * This method circulates through the Buttons Array and adds 
	 * MouseListener to each Button on the grid
	 */
	private void addGridMouseListener()
	{
		for(int i=0;i<row;i++)
		{
			for(int j=0;j<col;j++)
				gridArr[i][j].addMouseListener(this);
		}
	}//addGridMouseListener()
	
	public void mouseEntered(MouseEvent me)
	{
		for(int i=0;i<gridArr.length;i++)
			for(int j=0;j<gridArr[0].length;j++)
			{
				if((me.getComponent()).equals((Object)gridArr[i][j]))
				{
					
					gridArr[i][j].setText("Mouse Entered");
					System.out.println("The Mouse Entered on grid ( "+i+","+j+")");
				}
								
			}
		this.addGridButtons();
				
	}
	
	public void mouseClicked(MouseEvent me)
	{
		
		
	}
	
	public void mouseExited(MouseEvent me)
	{
		this.layGrid();
	}
	
	public void mousePressed(MouseEvent me)
	{
	}
	
	public void mouseReleased(MouseEvent me)
	{
		this.layGrid();
	}
	
	/**T
	 * his method adds Window closing listener
	 */
	private void addWindowListener()
	{
		this.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent we)
			{
				System.exit(DISPOSE_ON_CLOSE);
			}});
		
	}//addWindowListener()
	
	
	public static void main(String args[])
	{
		//Create a JFrame and init it here
		MinesweeperUI MUI = new MinesweeperUI();
		
	}
}
