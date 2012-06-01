import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 
 */

/**
 * @author pshah
 *
 */
public class MockFileSystemConnectionFactory implements IFileSystemConnectionFactory {

	public static String expectedSquaresAsText[] = {
			"0,0:true-COVERED",
			"0,1:false-COVERED",
			"0,2:false-COVERED",
			"0,3:false-COVERED",
			"0,4:false-COVERED",
			"0,5:false-COVERED",
			"1,0:false-COVERED",
			"1,1:false-COVERED",
			"1,2:true-COVERED",
			"1,3:false-COVERED",
			"1,4:false-COVERED",
			"1,5:false-COVERED",
			"2,0:false-COVERED",
			"2,1:true-COVERED",
			"2,2:false-COVERED",
			"2,3:false-COVERED",
			"2,4:false-COVERED",
			"2,5:false-COVERED",
			"3,0:false-COVERED",
			"3,1:false-COVERED",
			"3,2:false-COVERED",
			"3,3:false-COVERED",
			"3,4:true-COVERED",
			"3,5:false-COVERED",
			"4,0:false-COVERED",
			"4,1:false-COVERED",
			"4,2:true-COVERED",
			"4,3:false-COVERED",
			"4,4:false-COVERED",
			"4,5:true-COVERED",
			"5,0:false-COVERED",
			"5,1:false-COVERED",
			"5,2:true-COVERED",
			"5,3:true-COVERED",
			"5,4:false-COVERED",
			"5,5:false-COVERED"
	};
	
	/**
	 *     X  0  0  0  0  0
	 *     0  0  X  0  0  0
	 *     0  X  0  0  0  0
	 *     0  0  0  0  X  0
	 *     0  0  X  0  0  X
	 *     0  0  X  X  0  0 
	 */
	public static Square[][] expectedSquares = {
			{cSq(Square.STATUS.COVERED,true), cSq(Square.STATUS.COVERED,false), cSq(Square.STATUS.COVERED,false), cSq(Square.STATUS.COVERED,false), cSq(Square.STATUS.COVERED,false), cSq(Square.STATUS.COVERED,false)},
			{cSq(Square.STATUS.COVERED,false), cSq(Square.STATUS.COVERED,false), cSq(Square.STATUS.COVERED,true), cSq(Square.STATUS.COVERED,false), cSq(Square.STATUS.COVERED,false), cSq(Square.STATUS.COVERED,false)},
			{cSq(Square.STATUS.COVERED,false), cSq(Square.STATUS.COVERED,true), cSq(Square.STATUS.COVERED,false), cSq(Square.STATUS.COVERED,false), cSq(Square.STATUS.COVERED,false), cSq(Square.STATUS.COVERED,false)},
			{cSq(Square.STATUS.COVERED,false), cSq(Square.STATUS.COVERED,false), cSq(Square.STATUS.COVERED,false), cSq(Square.STATUS.COVERED,false), cSq(Square.STATUS.COVERED,true), cSq(Square.STATUS.COVERED,false)},
			{cSq(Square.STATUS.COVERED,false), cSq(Square.STATUS.COVERED,false), cSq(Square.STATUS.COVERED,true), cSq(Square.STATUS.COVERED,false), cSq(Square.STATUS.COVERED,false), cSq(Square.STATUS.COVERED,true)},
			{cSq(Square.STATUS.COVERED,false), cSq(Square.STATUS.COVERED,false), cSq(Square.STATUS.COVERED,true), cSq(Square.STATUS.COVERED,true), cSq(Square.STATUS.COVERED,false), cSq(Square.STATUS.COVERED,false)},
	};
	
	@Override
	public PrintWriter getWriter() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BufferedReader getReader() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	private static Square cSq(Square.STATUS status, boolean mine) {
		Square square = new Square();
		square.setStatus(status);
		square.setMine(mine);
		return square;
	}
}
