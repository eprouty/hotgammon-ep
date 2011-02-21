package baseMon.strategies;

import baseMon.Color;
import baseMon.Game;

public interface WinnerStrategy {
	public Color getWinner(Game g, int turnCount);
}
