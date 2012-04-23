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

}
