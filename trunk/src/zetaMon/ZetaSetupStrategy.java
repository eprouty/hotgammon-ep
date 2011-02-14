package zetaMon;

import java.util.Hashtable;

import baseMon.Color;
import baseMon.Location;
import baseMon.strategies.SetupStrategy;

public class ZetaSetupStrategy implements SetupStrategy {

	@Override
	public Hashtable<Location, Color> setupLocationColor() {
		Hashtable<Location, Color> board = new Hashtable<Location, Color>();
		for (Location l : Location.values()){
			switch(l){
			case R1:
				board.put(l, Color.BLACK);
				break;
			case R2:
				board.put(l, Color.BLACK);
				break;
			case R3:
				board.put(l, Color.BLACK);
				break;
			case B1:
				board.put(l, Color.RED);
				break;
			case B2:
				board.put(l, Color.RED);
				break;
			case B3:
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
				board.put(l, 3);
				break;
			case R2:
				board.put(l, 3);
				break;
			case R3:
				board.put(l, 3);
				break;
			case B1:
				board.put(l, 3);
				break;
			case B2:
				board.put(l, 3);
				break;
			case B3:
				board.put(l, 3);
				break;
			//setup the blank areas with the default information
			default:
				board.put(l, 0);
			}
		}
		return board;
	}

}
