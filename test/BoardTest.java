import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class BoardTest {

	private Board board;
	
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testMinePlacement() {
		MockInitializer mockInitializer = new MockInitializer();
		this.board = new Board(mockInitializer);
		Point mines[] = mockInitializer.mines();
		for(Point point : mines) {
			Square square = this.board.getSquare(point);
			assertTrue(square.isMine());
		}
	}
	
	@Test
	public void testSquareCount() throws Exception {
		MockInitializer mockInitializer = new MockInitializer();
		this.board = new Board(mockInitializer);
		int expectedCounts[][] = new int[][]{
				{-1, 2, 1, 1, 0, 0},
				{2, 3, -1, 1, 0, 0},
				{1, -1, 2, 2, 1, 1},
				{1, 2, 2, 2, -1, 2},
				{0, 2, -1, 3, 3, -1},
				{0, 2, -1, -1, 2, 1}
		};
		for(int i=0; i<Board.WIDTH;i++) {
			for(int j=0; j<Board.HEIGHT; j++) {
				Square square = this.board.getSquare(new Point(i,j));
				int expectedCount = expectedCounts[i][j];
				if(expectedCount != -1) {
					assertEquals(expectedCount, square.getCount());
				}
			}
		}
	}

}
