package hotgammon.playerep.MyBoard.patterns;

import hotgammon.Color;
import hotgammon.Location;

import java.util.Hashtable;

public interface SetupStrategy {
	public Hashtable<Location, Color> setupLocationColor();
	public Hashtable<Location, Integer> setupLocationCount();
}
