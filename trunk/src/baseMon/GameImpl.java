//Eric Prouty

package baseMon;

import java.util.Hashtable;

import baseMon.strategies.MoveStrategy;
import baseMon.strategies.SetupStrategy;
import baseMon.strategies.TurnStrategy;


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
	private Color currentPlayer = Color.NONE;
	private Hashtable<Location, Color> locationColor = new Hashtable<Location, Color>();
	private Hashtable<Location, Integer> locationCount = new Hashtable<Location, Integer>();
	private MoveStrategy MS;
	private TurnStrategy TS;
	private SetupStrategy SS;
	private int moveCount = 2;
	private int[] dice = new int[2];
	private int[] remainingDice = new int [4];
	private int turnCount = 0;
	
	public GameImpl(MoveStrategy MS, TurnStrategy TS){
		this.MS = MS;
		this.TS = TS;
	}
	public GameImpl(MoveStrategy MS, TurnStrategy TS, SetupStrategy SS){
		this.MS = MS;
		this.TS = TS;
		this.SS = SS;
	}
	
	public void newGame() {
		//set currentPlayer to NONE in preparation to begin game
		currentPlayer = Color.NONE;
		turnCount = 0;
		
		//setup the locations that should have pieces to start
		for (Location l : Location.values()){
			switch(l){
			case R1:
				setupLocation(l, Color.BLACK, 2);
				break;
			case B1:
				setupLocation(l, Color.RED, 2);
				break;
			case B6:
				setupLocation(l, Color.BLACK, 5);
				break;
			case R6:
				setupLocation(l, Color.RED, 5);
				break;
			case B8:
				setupLocation(l, Color.BLACK, 3);
				break;
			case R8:
				setupLocation(l, Color.RED, 3);
				break;
			case B12:
				setupLocation(l, Color.RED, 5);
				break;
			case R12:
				setupLocation(l, Color.BLACK, 5);
				break;
			//setup the blank areas with the default information
			default:
				setupLocation(l, Color.NONE, 0);
			}
		}
	}
	
	/**
	 * Sets up the given location with pieces of the specified color and quantity
	 * @param l The location on the board to setup
	 * @param c	The Color to be placed at this location
	 * @param count	The number of pieces to be placed at this location
	 */
	public void setupLocation(Location l, Color c, int count){
		locationColor.put(l, c);
		locationCount.put(l, count);
	}
	
	public void nextTurn() {
		//determine the next turn based off the selected turn strategy
		currentPlayer = TS.nextPlayerInTurn(this);
		
		//sets the dice roll at the beggining of the turn
		switch(turnCount % 3){
		case 0:
			dice[0] = 1;
			dice[1] = 2;
			remainingDice[0] = 1;
			remainingDice[1] = 2;
			break;
		case 1:
			dice[0] = 3;
			dice[1] = 4;
			remainingDice[0] = 3;
			remainingDice[1] = 4;
			break;
		case 2:
			dice[0] = 5;
			dice[1] = 6;
			remainingDice[0] = 5;
			remainingDice[1] = 6;
			break;
		}
		turnCount++;
		
		//reset the number of possible moves to 2
		moveCount = 2;
	}
	
	public boolean move(Location from, Location to) {
		//if no moves remain, then the player cannot move
		if (moveCount == 0){
			return false;
		}
		
		//if the movement strategy does not return true, then the move is invalid
		if (!MS.isMoveValid(this, from, to)){
			return false;
		}
		/**After this point the move has been deemed valid, everything from here on affects the state of the game **/
		
		//remove the used dice value from the remaining values array
		int distTravelled = to.ordinal() - from.ordinal();
		for (int i = 0; i < dice.length; i++){
			if (remainingDice[i] == distTravelled){
				remainingDice[i] = -1;
			}
		}
		
		//update the color of the to location
		locationColor.put(to, getColor(from));
		
		//update the number of pieces at the location being moved to
		int toCount = (int)locationCount.get(to) + 1;
		locationCount.put(to, toCount);
		
		//update the number of pieces at the from location
		int fromCount = (int)locationCount.get(from) - 1;
		locationCount.put(from, fromCount);
		
		//if fromLocation is now empty change its color to NONE
		if (fromCount == 0){
			locationColor.put(from, Color.NONE);
		}
		
		//made one move, so there is one less
		moveCount--;
		return true;
	}
	
	public Color getPlayerInTurn(){ 
		return currentPlayer; 
	}
	public int getNumberOfMovesLeft() {
		return moveCount;
	}
	public int[] diceThrown() {
		return dice;
	}
	
	public int[] diceValuesLeft() {
		return new int[] {remainingDice[1], remainingDice[0]};
	}
	
	public Color winner() {
		if (turnCount == 6){
			return Color.RED;
		} else {
			return Color.NONE;
		}
	}
	
	public Color getColor(Location location){
		return locationColor.get(location);
	}
	
	public int getCount(Location location) {
		return locationCount.get(location);
	}
}
