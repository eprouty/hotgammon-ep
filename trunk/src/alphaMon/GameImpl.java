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
	private Hashtable<Location, Integer> locationCount = new Hashtable<Location, Integer>();
	private int moveCount = 2;
	
	public void newGame() {
		for (Location l : Location.values()){
			switch(l){
			case R1:
				setupLocation(l, Color.BLACK, 2);
				break;
			case B1:
				setupLocation(l, Color.RED, 2);
				break;
			default:
				setupLocation(l, Color.NONE, 0);
			}
		}
	}
	
	public void setupLocation(Location l, Color c, int count){
		locationColor.put(l, c);
		locationCount.put(l, count);
	}
	
	public void nextTurn() {
		switch(currentTurn){
		case BLACK:
			currentTurn = Color.RED;
			break;
		case RED:
			currentTurn = Color.BLACK;
			break;
		case NONE:
			currentTurn = Color.BLACK;
			break;
		}
	}
	
	public boolean move(Location from, Location to) {
		if (getColor(to) == Color.RED){
			return false;
		}
		
		int toCount = (int)locationCount.get(to) + 1;
		locationCount.put(to, toCount);
		moveCount--;
		return true;
	}
	
	public Color getPlayerInTurn(){ 
		return currentTurn; 
	}
	public int getNumberOfMovesLeft() {
		return moveCount;
	}
	public int[] diceThrown() {
		return new int[] {3,4};
	}
	
	public int[] diceValuesLeft() { return new int []{}; }
	public Color winner() { return Color.NONE; }
	
	public Color getColor(Location location){
		return locationColor.get(location);
	}
	
	public int getCount(Location location) {
		return locationCount.get(location);
	}
}
