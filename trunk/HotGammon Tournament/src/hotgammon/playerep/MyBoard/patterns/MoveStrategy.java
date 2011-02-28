package hotgammon.playerep.MyBoard.patterns;

import hotgammon.Location;
import hotgammon.playerep.MyBoard.Game;

public interface MoveStrategy {
	public boolean isMoveValid(Game g, Location from, Location to);
}
