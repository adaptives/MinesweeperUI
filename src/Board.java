import java.util.ArrayList;
import java.util.List;


public class Board {

	public static final int WIDTH = 6;
	public static final int HEIGHT = 6;
	
	private IInitializer boardInitializer;
	private Square sqaures[][];
	
	public Board(IInitializer boardInitializer) {
		this.boardInitializer = boardInitializer;
		this.sqaures = new Square[WIDTH][HEIGHT];
		//instantiate all square
		for(int i=0; i<WIDTH; i++) {
			for(int j=0; j<HEIGHT; j++) {
				this.sqaures[i][j] = new Square();
			}
		}
		//mark mines
		Point mines[] = this.boardInitializer.mines();
		for(Point point : mines) {
			this.sqaures[point.x][point.y].setMine(true);
		}
		//compute count
		for(int i=0; i<WIDTH;i++) {
			for(int j=0; j<HEIGHT; j++) {
				Square square = this.sqaures[i][j];
				if(!square.isMine()) {
					List<Point> validNeighbours = computeValidNeighbours(new Point(i,j));
					int mineCount = 0;
					for(Point neighbour : validNeighbours) {
						if(this.sqaures[neighbour.x][neighbour.y].isMine()) {
							mineCount++;
						}
					}
					square.setCount(mineCount);
				}
			}
		}
	}

	private List<Point> computeValidNeighbours(Point p) {
		List<Point> validNeighbours = new ArrayList<Point>();
		
		Point topLeft = new Point(p.x-1, p.y-1);
		if(pointValid(topLeft)) {
			validNeighbours.add(topLeft);
		}
		
		Point top = new Point(p.x, p.y-1);
		if(pointValid(top)) {
			validNeighbours.add(top);
		}
		
		Point topRight = new Point(p.x+1, p.y-1);
		if(pointValid(topRight)) {
			validNeighbours.add(topRight);
		}
		
		Point right = new Point(p.x+1, p.y);
		if(pointValid(right)) {
			validNeighbours.add(right);
		}
		
		Point bottomRight = new Point(p.x+1, p.y+1);
		if(pointValid(bottomRight)) {
			validNeighbours.add(bottomRight);
		}
		
		Point bottom = new Point(p.x, p.y+1);
		if(pointValid(bottomRight)) {
			validNeighbours.add(bottomRight);
		}
		
		Point bottomLeft = new Point(p.x-1, p.y+1);
		if(pointValid(bottomLeft)) {
			validNeighbours.add(bottomLeft);
		}
		
		Point left = new Point(p.x-1, p.y);
		if(pointValid(left)) {
			validNeighbours.add(left);
		}
		
		return validNeighbours;
		
	}
	
	private boolean pointValid(Point p) {
		if(p.x >=0 && p.x < 6 && p.y >= 0 && p.y < 6) {
			return true;
		}
		return false;
	}

	public Square getSquare(Point point) {
		return this.sqaures[point.x][point.y];
	}
}
