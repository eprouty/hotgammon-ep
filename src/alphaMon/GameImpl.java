package alphaMon;

import java.util.Hashtable;

/** Skeleton implementation of HotGammon.
 
   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Computer Science Department
     Aarhus University
   
   This source code is provided WITHOUT ANY WARRANTY either 
   expressed or implied. You may study, use, modify, and 
   distribute it for non-commercial purposes. For any 
   commercial use, see http://www.baerbak.com/
*/

public class GameImpl implements Game {
	private Color currentTurn = Color.NONE;
	private Hashtable<Location, Color> locationColor = new Hashtable<Location, Color>();
	
	public void newGame() {
		locationColor.put(Location.R1, Color.BLACK);
		locationColor.put(Location.B1, Color.RED);
	}
	public void nextTurn() {
		currentTurn = Color.BLACK;
	}
	
	public boolean move(Location from, Location to) {
		if (getColor(to) == Color.RED){
			return false;
		}
		return true;
	}
	
	public Color getPlayerInTurn(){ 
		return currentTurn; 
	}
	public int getNumberOfMovesLeft() {
		return 1;
	}
	public int[] diceThrown() { return new int[] {1,1}; }
	public int[] diceValuesLeft() { return new int []{}; }
	public Color winner() { return Color.NONE; }
	
	public Color getColor(Location location){
		return locationColor.get(location);
	}
	
	public int getCount(Location location) {
		return 2;
	}
}
