package alphaMon;

import baseMon.strategies.DiceStrategy;

public class AlphaDiceStrategy implements DiceStrategy {
int turnCount = 0;

	@Override
	public int[] rollDice() {
		int[] dice = new int[2];
		//sets the dice roll at the beginning of the turn
		switch(turnCount % 3){
		case 0:
			dice[0] = 1;
			dice[1] = 2;
			break;
		case 1:
			dice[0] = 3;
			dice[1] = 4;
			break;
		case 2:
			dice[0] = 5;
			dice[1] = 6;
			break;
		}
		turnCount++;
		
		return dice;
	}

}
