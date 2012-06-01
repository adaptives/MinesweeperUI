package com.diycomputerscience.minesweepercore;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (mine ? 1231 : 1237);
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Square other = (Square) obj;
		if (mine != other.mine)
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Square [count=" + count + ", mine=" + mine + ", status=" + status + "]";
	}

}
