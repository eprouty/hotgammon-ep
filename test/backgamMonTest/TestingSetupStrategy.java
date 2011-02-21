package backgamMonTest;

import java.util.Hashtable;

import baseMon.Color;
import baseMon.Location;
import baseMon.strategies.SetupStrategy;

public class TestingSetupStrategy implements SetupStrategy{

	@Override
	public Hashtable<Location, Color> setupLocationColor() {
		Hashtable<Location, Color> board = new Hashtable<Location, Color>();
		for (Location l : Location.values()){
			board.put(l, Color.NONE);
		}
		board.put(Location.B1, Color.BLACK);
		board.put(Location.B4, Color.BLACK);
		board.put(Location.R2, Color.RED);
		board.put(Location.R1, Color.RED);
		board.put(Location.R4, Color.RED);
		return board;
	}

	@Override
	public Hashtable<Location, Integer> setupLocationCount() {
		Hashtable<Location, Integer> board = new Hashtable<Location, Integer>();
		for (Location l : Location.values()){
			board.put(l, 0);
		}
		board.put(Location.B1, 1);
		board.put(Location.B4, 1);
		board.put(Location.R2, 1);
		board.put(Location.R1, 1);
		board.put(Location.R4, 1);
		return board;
	}

}
