package hotgammon.playerep.MyBoard;

import hotgammon.playerep.MyBoard.patterns.DiceStrategy;

public class MockDice implements DiceStrategy{
	int die1, die2;
	
	public int[] rollDice() {
		return new int[] {die1, die2};
	}
	
	public void setDice(int die1, int die2){
		this.die1 = die1;
		this.die2 = die2;
	}
}
