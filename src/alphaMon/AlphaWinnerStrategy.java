package alphaMon;

import baseMon.Color;
import baseMon.Game;
import baseMon.strategies.WinnerStrategy;

public class AlphaWinnerStrategy implements WinnerStrategy {

	@Override
	public Color getWinner(Game g, int turnCount) {
		if (turnCount == 6){
			return Color.RED;
		} else {
			return Color.NONE;
		}
	}

}
