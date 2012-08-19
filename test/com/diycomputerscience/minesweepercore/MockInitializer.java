package com.diycomputerscience.minesweepercore;
import com.diycomputerscience.minesweepercore.Initializer;
import com.diycomputerscience.minesweepercore.Point;


public class MockInitializer implements Initializer {
	/**
	 *     X  0  0  0  0  0
	 *     0  0  X  0  0  0
	 *     0  X  0  0  0  0
	 *     0  0  0  0  X  0
	 *     0  0  X  0  0  X
	 *     0  0  X  X  0  0 
	 */
	@Override
	public Point[] mines() {
		return new Point[] {
			new Point(0,0),
			new Point(1,2),
			new Point(2,1),
			new Point(3,4),
			new Point(4,2),
			new Point(4,5),
			new Point(5,2),
			new Point(5,3)
		};
	}

}
