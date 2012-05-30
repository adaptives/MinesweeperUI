import java.io.BufferedReader;
import java.io.PrintWriter;

/**
 * 
 */

/**
 * @author pshah
 *
 */
public class BoardPersistenceUtility {
	
	private String SQUARE_FORMAT = "%d,%d:%b-%s";
	private String LOAD_REGEX = "";
	
	public void save(PrintWriter writer, Board board) {
		for(int row=0; row<Board.MAX_ROWS; row++) {
			for(int col=0; col<board.MAX_COLS; col++) {
				Square square = board.getSquare(new Point(row, col));
				String squareRep = String.format(SQUARE_FORMAT, row, col, square.isMine(), square.getStatus());
				writer.println(squareRep);
			}
		}
	}
	
	public Board load(BufferedReader reader) {
		return null;
	}
}
