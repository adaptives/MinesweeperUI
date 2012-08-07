package com.diycomputerscience.minesweepercore;

public class Square {

	public static enum STATUS implements SquareState {
		
		COVERED() {
			
			@Override
			public SquareState uncover() throws UncoveredMineException {
				return UNCOVERED;
			}

			@Override
			public SquareState mark() {
				return FLAGGED;
			}
			
		},
		
		UNCOVERED() {
			
			@Override
			public SquareState uncover() throws UncoveredMineException {
				return UNCOVERED;
			}

			@Override
			public SquareState mark() {
				return UNCOVERED;
			}
			
		},
		
		FLAGGED() {
			
			@Override
			public SquareState uncover() throws UncoveredMineException {
				return FLAGGED;
			}

			@Override
			public SquareState mark() {
				return COVERED;
			}
			
		};

		private static String notRedifined = "This method should have been redefined in the square state";
		
		@Override
		public SquareState uncover() throws UncoveredMineException {
			throw new RuntimeException(notRedifined);
		}

		@Override
		public SquareState mark() {
			throw new RuntimeException(notRedifined);
		}
	};
	
	private int count;
	private boolean mine;
	private SquareState currentState;
	
	public Square() {
		this.currentState = STATUS.COVERED;
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
		this.currentState = status;
	}
	
	public STATUS getStatus() {
		//TODO: Is this a good idea ?
		return (STATUS)this.currentState;
	}
	
	public void markAsMine() {
		this.currentState = this.currentState.mark();
	}

	public void uncover() throws UncoveredMineException {
		this.currentState = this.currentState.uncover();
		//TODO: Should this logic be here or in the state machine ?
		if(isMine()) {
			throw new UncoveredMineException();
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (mine ? 1231 : 1237);
		result = prime * result + ((currentState == null) ? 0 : currentState.hashCode());
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
		if (currentState != other.currentState)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Square [count=" + count + ", mine=" + mine + ", status=" + currentState + "]";
	}

}
