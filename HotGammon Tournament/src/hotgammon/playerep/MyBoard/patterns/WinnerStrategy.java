package hotgammon.playerep.MyBoard.patterns;

import hotgammon.Color;
import hotgammon.playerep.MyBoard.Game;

public interface WinnerStrategy {
	public Color getWinner(Game g, int turnCount);
}
