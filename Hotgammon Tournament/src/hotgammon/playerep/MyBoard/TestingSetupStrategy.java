package hotgammon.playerep.MyBoard;

import hotgammon.Color;
import hotgammon.Location;
import hotgammon.playerep.MyBoard.patterns.SetupStrategy;

import java.util.Hashtable;

public class TestingSetupStrategy implements SetupStrategy {

	@Override
	public Hashtable<Location, Color> setupLocationColor() {
		Hashtable<Location, Color> board = new Hashtable<Location, Color>();
		for (Location l : Location.values()){
			switch(l){
			case R1:
				board.put(l, Color.RED);
				break;
			//setup the blank areas with the default information
			default:
				board.put(l, Color.NONE);
			}
		}
		return board;
	}

	@Override
	public Hashtable<Location, Integer> setupLocationCount() {
		Hashtable<Location, Integer> board = new Hashtable<Location, Integer>();
		for (Location l : Location.values()){
			switch(l){
			case R1:
				board.put(l, 2);
				break;
			//setup the blank areas with the default information
			default:
				board.put(l, 0);
			}
		}
		return board;
	}
}
