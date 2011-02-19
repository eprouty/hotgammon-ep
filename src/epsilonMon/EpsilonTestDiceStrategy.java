package epsilonMon;

import java.util.Random;

import baseMon.strategies.DiceStrategy;

public class EpsilonTestDiceStrategy implements DiceStrategy {
private Random rg = new Random(2);
	@Override
	public int[] rollDice() {
		int[] dice = new int[2];
		dice[0] = rg.nextInt(6) + 1;
		dice[1] = rg.nextInt(6) + 1;
		return dice;
	}
}
