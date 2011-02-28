package baseMon.strategies;

import baseMon.Game;
import baseMon.Location;

public interface MoveStrategy {
	public boolean isMoveValid(Game g, Location from, Location to);
}
