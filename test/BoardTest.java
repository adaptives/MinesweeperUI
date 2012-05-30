import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static org.easymock.EasyMock.*;

import java.io.PrintWriter;

import org.easymock.Capture;
import org.easymock.CaptureType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


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
			Square square = board.getSquare(point);
			assertTrue(square.isMine());
		}
	}
	
	@Test
	public void testSquareCount() throws Exception {
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
				Square square = this.board.getSquare(new Point(row,col));
				int expectedCount = expectedCounts[row][col];
				if(expectedCount != -1) {
					String msg = "Failed for cell [" + row + "][" + col + "]";
					assertEquals(msg , expectedCount, square.getCount());
				}
			}
		}
	}
	
	@Test
	public void testSave() throws Exception {
		
		PrintWriter writer = createMock(PrintWriter.class);
		Capture<String> captureOfString = new Capture<String>(CaptureType.ALL);
		writer.println(capture(captureOfString));
		//Note: It is last call 36 times, and NOT last call + 36 times
		expectLastCall().times(36);
		replay(writer);
		this.board.save(writer);
		
		verify(writer);
//		captureOfString.
	}

}
