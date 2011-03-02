//Eric Prouty

package hotgammon.playerep.MyBoard;

import hotgammon.Color;
import hotgammon.Location;
import hotgammon.playerep.MyBoard.patterns.DiceStrategy;
import hotgammon.playerep.MyBoard.patterns.GammonFactory;
import hotgammon.playerep.MyBoard.patterns.MoveStrategy;
import hotgammon.playerep.MyBoard.patterns.SetupStrategy;
import hotgammon.playerep.MyBoard.patterns.TurnStrategy;
import hotgammon.playerep.MyBoard.patterns.WinnerStrategy;

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
	private Color currentPlayer = Color.NONE;
	private Hashtable<Location, Color> locationColor = new Hashtable<Location, Color>();
	private Hashtable<Location, Integer> locationCount = new Hashtable<Location, Integer>();
	private GammonFactory factory;
	private MoveStrategy MS;
	private TurnStrategy TS;
	private SetupStrategy SS;
	private DiceStrategy DS;
	private WinnerStrategy WS;
	private int moveCount = 2;
	private int[] dice = new int[2];
	private int[] remainingDice = new int [4];
	private int turnCount = 0;
	
	public GameImpl(GammonFactory factory){
		this.factory = factory;
		this.MS = factory.createMoveStrategy();
		this.TS = factory.createTurnStrategy();
		this.SS = factory.createSetupStrategy();
		this.DS = factory.createDiceStrategy();
		this.WS = factory.createWinnerStrategy();
	}
	
	public void newGame() {
		//set currentPlayer to NONE in preparation to begin game
		currentPlayer = Color.NONE;
		turnCount = 0;
		
		locationCount = SS.setupLocationCount();
		locationColor = SS.setupLocationColor();
		
		DS = factory.createDiceStrategy();
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
		
		dice = DS.rollDice();
		if (dice[0] == dice[1]){
			for (int i = 0; i < remainingDice.length; i++){
				remainingDice[i] = dice[0];
			}
			moveCount = 4;
		} else {
			remainingDice = new int[4];
			remainingDice[0] = dice[0];
			remainingDice[1] = dice[1];
			moveCount = 2;
		}
		
		turnCount++;
	}
	
	public boolean move(Location from, Location to) {
		//if no moves remain, then the player cannot move
		if (remainingDice.equals(new int[] {-1, -1}) || remainingDice.equals(new int[] {-1,-1,-1,-1})){
			return false;
		}
		
		//if the movement strategy does not return true, then the move is invalid
		if (!MS.isMoveValid(this, from, to)){
			return false;
		}
		/**After this point the move has been deemed valid, everything from here on affects the state of the game **/
		
		if (to == Location.R_BAR || to == Location.B_BAR){
		} else {
			//remove the used dice value from the remaining values array
			int distTravelled = Math.abs(Location.distance(from, to));
			for (int i = 0; i < remainingDice.length; i++){
				if (remainingDice[i] == distTravelled){
					remainingDice[i] = -1;
					break;
				}
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
		return remainingDice;
	}
	
	public Color winner() {
		return WS.getWinner(this, turnCount);
	}
	
	public Color getColor(Location location){
		return locationColor.get(location);
	}
	
	public int getCount(Location location) {
		return locationCount.get(location);
	}
}
