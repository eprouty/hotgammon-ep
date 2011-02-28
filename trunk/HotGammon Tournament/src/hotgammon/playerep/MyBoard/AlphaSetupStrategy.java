package hotgammon.playerep.MyBoard;

import hotgammon.Color;
import hotgammon.Location;
import hotgammon.playerep.MyBoard.patterns.SetupStrategy;

import java.util.Hashtable;

public class AlphaSetupStrategy implements SetupStrategy {

	@Override
	public Hashtable<Location, Color> setupLocationColor() {
		Hashtable<Location, Color> board = new Hashtable<Location, Color>();
		for (Location l : Location.values()){
			switch(l){
			case R1:
				board.put(l, Color.BLACK);
				break;
			case B1:
				board.put(l, Color.RED);
				break;
			case B6:
				board.put(l, Color.BLACK);
				break;
			case R6:
				board.put(l, Color.RED);
				break;
			case B8:
				board.put(l, Color.BLACK);
				break;
			case R8:
				board.put(l, Color.RED);
				break;
			case B12:
				board.put(l, Color.RED);
				break;
			case R12:
				board.put(l, Color.BLACK);
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
			case B1:
				board.put(l, 2);
				break;
			case B6:
				board.put(l, 5);
				break;
			case R6:
				board.put(l, 5);
				break;
			case B8:
				board.put(l, 3);
				break;
			case R8:
				board.put(l, 3);
				break;
			case B12:
				board.put(l, 5);
				break;
			case R12:
				board.put(l, 5);
				break;
			//setup the blank areas with the default information
			default:
				board.put(l, 0);
			}
		}
		return board;
	}
}
