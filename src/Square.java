
public class Square {

	public static enum STATUS {COVERED, UNCOVERED, FLAGGED};
	
	private int count;
	private boolean mine;
	private STATUS status;
	
	public Square() {
		this.status = STATUS.COVERED;
	}
	
	public int getCount() {
		return this.count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public void setMine(boolean mine) {
		this.mine = mine;
	}
	
	public boolean isMine() {
		return this.mine;
	}
	
	public void setStatus(STATUS status) {
		this.status = status;
	}
	
	public STATUS getStatus() {
		return this.status;
	}
	
	public void markAsMine() {
		if(this.getStatus().equals(STATUS.UNCOVERED)) {
			return;
		}
		if(this.getStatus().equals(STATUS.FLAGGED)) {
			this.setStatus(STATUS.COVERED);
		} else if(this.getStatus().equals(STATUS.COVERED)) {
			this.setStatus(STATUS.FLAGGED);
		}
	}

	public void uncover() throws UncoveredMineException {
		if(this.getStatus().equals(STATUS.UNCOVERED)) {
			return;
		}
		if(this.getStatus().equals(STATUS.FLAGGED)) {
			return;
		}
		if(this.getStatus().equals(STATUS.COVERED)) {
			if(!this.isMine()) {
				this.setStatus(STATUS.UNCOVERED);
			} else {
				throw new UncoveredMineException();
			}
		}
		
	}

}
