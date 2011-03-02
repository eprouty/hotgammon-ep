/*******************************************************************************
 * This file is used in CS4233, Object-oriented Analysis and Design
 *
 * Copyright (c) 2010 Worcester Polytechnic Institute.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hotgammon.playerep;

import java.util.ArrayList;
import hotgammon.Color;
import hotgammon.Location;
import hotgammon.playerep.MyBoard.BackgamFactory;
import hotgammon.playerep.MyBoard.BackgamMoveStrategy;
import hotgammon.playerep.MyBoard.Game;
import hotgammon.playerep.MyBoard.GameImpl;
import hotgammon.playerep.MyBoard.InvalidMoveException;
import hotgammon.playerep.MyBoard.MockDice;
import hotgammon.playerep.MyBoard.patterns.MoveStrategy;
import hotgammon.tournament.*;


/**
 * This is the class that must be implement by the student. It is the complete
 * interface for the student's player.
 * 
 * @author gpollice
 * @version Feb 20, 2011
 */
public class TournamentPlayerImpl extends TournamentPlayer
{
	private MockDice md = new MockDice();
	private Game g;
	private boolean firstTurn = true;
	private Color myColor;
	private MoveStrategy MS = new BackgamMoveStrategy();
	/**
	 * Constructor.
	 * 
	 * @see hotgammon.tournament.TournamentPlayer(TournamentBoard, Color)
	 */
	public TournamentPlayerImpl(TournamentBoard board, Color myColor)
	{
		super(board, myColor);
		
		this.myColor = myColor;	
	}

	/* (non-Javadoc)
	 * @see hotgammon.tournament.TournamentPlayer#move(int, int)
	 */
	@Override
	public TournamentMove[] move(int die1, int die2, TournamentMove[] opponentsLastMoves){
		//setup my implementation of the game on the first move
		if (firstTurn){
			//if no opponents moves are passed then my player is the first to act
			if (opponentsLastMoves == null || opponentsLastMoves.length == 0){
				g = new GameImpl(new BackgamFactory(md, myColor));
				g.newGame();
			} else {
				//if moves exist that the first to act was the opposite color as this players... set it accordingly
				switch(myColor){
				case BLACK:
					g = new GameImpl(new BackgamFactory(md, Color.RED));
					break;
				case RED:
					g = new GameImpl(new BackgamFactory(md, Color.BLACK));	
				}
				//start the new game and make the opponents moves
				g.newGame();
				try {
					makeOpponentsMoves(opponentsLastMoves);
				} catch (InvalidMoveException e) {
					e.printStackTrace();
					System.exit(-1);
				}
			}
			firstTurn = false;
		} else {
			//make the opponents moves at the beggining of your turn
			try {
				makeOpponentsMoves(opponentsLastMoves);
			} catch (InvalidMoveException e) {
				System.out.println("Breaking trying to make opponent moves");
				System.out.println("My color is: " + myColor);
				System.exit(-1);
			}
		}
		
		//set the dice so that my implementation knows what dice values to use
		md.setDice(die1, die2);
		int numberOfMoves = 2;
		//double the num of moves if there are doubles
		if (die1 == die2){
			numberOfMoves = 4;
		}
		
		TournamentMove[] moves = null;
		try {
			//determine moves to make
			moves = makePlayerMoves(numberOfMoves);
		} catch (InvalidMoveException e) {
			System.out.println("Breaking trying to make player moves");
			System.out.println("My color is: " + myColor);
			System.out.println("The dice are: " + die1 + " " + die2);
			System.exit(-1);
		}
		
		return moves;
	}
	
	//execute the opponents moves that were passed to you at the beggining of the turn
	private void makeOpponentsMoves(TournamentMove[] lastMoves) throws InvalidMoveException{
		if (lastMoves == null || lastMoves.length == 0){
			return;
		}
		
		//determine what the opponents dice values were to maitain continuity with the game implementation i developed
		int od1 = 0, od2 = 0;
		switch (lastMoves.length){
		case 4:
		case 3:
		case 2:
			od1 = Math.abs(Location.distance(lastMoves[0].getFromLocation(), lastMoves[0].getToLocation()));
			od2 = Math.abs(Location.distance(lastMoves[1].getFromLocation(), lastMoves[1].getToLocation()));
			break;
		case 1:
			od1 = Math.abs(Location.distance(lastMoves[0].getFromLocation(), lastMoves[0].getToLocation()));
			od2 = od1+1;
			break;
		}
		md.setDice(od1, od2);
		g.nextTurn();
		
		for (TournamentMove move : lastMoves){
			g.move(move.getFromLocation(), move.getToLocation());
		}
	}
	
	//make and execute the moves for this turn in my game implementation
	private TournamentMove[] makePlayerMoves(int numOfMoves) throws InvalidMoveException{
		TournamentMove[] moves = new TournamentMove[numOfMoves];
		int invalidMoves = 0;
		
		//start this players turn in my impl
		g.nextTurn();
		//for each available move this turn select a valid move
		for (int i = 0; i < numOfMoves; i++){
			//select the move
			moves[i] = simpleMove();
			if (moves[i] != null){
				//execute the move in my implementation so that the next one can also be properly selected
				if (!g.move(moves[i].getFromLocation(), moves[i].getToLocation())){
					throw new InvalidMoveException();
				}
			} else {
				//this keeps track of however many moves must be discared as there are no possible options
				invalidMoves++;
			}
		}
		//if available moves cannot be used this reformats the array that will be returned in order to represent that fact
		if (invalidMoves > 0){
			TournamentMove[] temp = new TournamentMove[numOfMoves - invalidMoves];
			int i = 0;
			for (TournamentMove m : moves){
				if (m != null){
					temp[i] = m;
					i++;
				}
			}
			moves = temp;
		}
		
		return moves;
	}
	
	//selects the simplest available and successful move
	private TournamentMove simpleMove(){
		ArrayList <Location>redPieces = new ArrayList<Location>();
		ArrayList <Location>blackPieces = new ArrayList<Location>();
		for (Location l : Location.values()){
			switch(g.getColor(l)){
			case BLACK:
				blackPieces.add(l);
				break;
			case RED:
				redPieces.add(l);
				break;
			}
		}
		
		if (myColor == Color.RED){
			return simpleSelectMove(redPieces, blackPieces);
		} else {
			return simpleSelectMove(blackPieces, redPieces);
		}
	}
	
	//determine a viable move
	private TournamentMove simpleSelectMove(ArrayList<Location> myPieces, ArrayList<Location> opponentsPieces){
		//if there are pieces on the bar than those pieces must be moved first
		if (myPieces.contains(Location.R_BAR) || myPieces.contains(Location.B_BAR)){
			TournamentMove temp = moveFromBar(myPieces, opponentsPieces);
			return temp;
		}
		Location array[] = new Location[myPieces.size()];
		myPieces.toArray(array);
		//cycle through all of the locations with this players pieces on it inorder to find one with a possible move
		for (Location l : array){
			//the player cannot move a piece that is in the bear off
			if (l == Location.R_BEAR_OFF || l == Location.B_BEAR_OFF){
			} else {
				for (int i : g.diceValuesLeft()){
					if (i > 0 && i <= 6){
						Location dest = Location.findLocation(myColor, l, i);
						//if a movement to this destination is valid return it
						if (MS.isMoveValid(g, l, dest)){
							return new TournamentMove(l, dest);
						}
					}
				}
			}
		}
		//returns null, this is interpreted by the makePlayerMoves() function as being that there are no possible valid moves, and should be represented as such
		return null;
	}
	
	//makes a movement off of the players respective bar
	private TournamentMove moveFromBar(ArrayList<Location> myPieces, ArrayList<Location> opponentsPieces){
		Location locD1;
		if (myColor == Color.RED){
			for (int i : g.diceValuesLeft()){
				if (i != -1){
					locD1 = Location.findLocation(myColor, Location.R_BAR, i);
					if (MS.isMoveValid(g, Location.R_BAR, locD1)){
						return new TournamentMove(Location.R_BAR, locD1);
					}
				}
			}
		} else {
			for (int i : g.diceValuesLeft()){
				if (i != -1){
					locD1 = Location.findLocation(myColor, Location.B_BAR, i);
					if (MS.isMoveValid(g, Location.B_BAR, locD1)){
						return new TournamentMove(Location.B_BAR, locD1);
					}
				}
			}
		} 
		//returns null, this is interpreted by the makePlayerMoves() function as being that there are no possible valid moves, and should be represented as such
		return null;
	}

	//allows access to the game board for testing purposes
	public Game accessMyBoard(){
		return g;
	}
}
