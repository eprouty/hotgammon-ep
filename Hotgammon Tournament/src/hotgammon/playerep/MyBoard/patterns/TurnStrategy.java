package hotgammon.playerep.MyBoard.patterns;

import hotgammon.Color;
import hotgammon.playerep.MyBoard.Game;

public interface TurnStrategy {
	public Color nextPlayerInTurn(Game g);
}
