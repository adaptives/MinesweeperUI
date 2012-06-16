/**
 * 
 */
package com.diycomputerscience;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * @author pshah
 *
 */
public class MinesweeperUI  extends JFrame {
	
	public MinesweeperUI()
	{
		this.setTitle("Minesweeper");
		this.setSize(450, 450);
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent we)
			{
				System.exit(DISPOSE_ON_CLOSE);
			}});
		
	}//constr
	
	public static void main(String args[])
	{
		//Create a JFrame and init it here
		MinesweeperUI MUI = new MinesweeperUI();
		
	}
}
