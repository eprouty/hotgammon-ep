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
		if (firstTurn){
			if (opponentsLastMoves == null){
				g = new GameImpl(new BackgamFactory(md, myColor));
				g.newGame();
			} else {
				switch(myColor){
				case BLACK:
					g = new GameImpl(new BackgamFactory(md, Color.RED));
					break;
				case RED:
					g = new GameImpl(new BackgamFactory(md, Color.BLACK));	
				}
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
			try {
				makeOpponentsMoves(opponentsLastMoves);
			} catch (InvalidMoveException e) {
				System.out.println("Breaking trying to make opponent moves");
				System.out.println("My color is: " + myColor);
				System.exit(-1);
			}
		}
		
		md.setDice(die1, die2);
		int numberOfMoves = 2;
		if (die1 == die2){
			numberOfMoves = 4;
		}
		
		TournamentMove[] moves = null;
		try {
			moves = makePlayerMoves(numberOfMoves);
		} catch (InvalidMoveException e) {
			System.out.println("Breaking trying to make player moves");
			System.out.println("My color is: " + myColor);
			System.out.println("The dice are: " + die1 + " " + die2);
			System.exit(-1);
		}
		
		return moves;
	}
	
	private void makeOpponentsMoves(TournamentMove[] lastMoves) throws InvalidMoveException{
		if (lastMoves == null || lastMoves.length == 0){
			return;
		}
		
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
				/**System.out.println("The move from " + move.getFromLocation() + " to " + move.getToLocation() + " is an illegal move");
				System.out.println("The count from is " + g.getCount(move.getFromLocation()));
				System.out.println("The count is " + g.getCount(Location.B1));
				System.out.println("The count is " + g.getCount(Location.B2));
				System.out.println("The count is " + g.getCount(Location.B3));
				System.out.println("The count is " + g.getCount(Location.B4));
				System.out.println("The count is " + g.getCount(Location.B5));
				System.out.println("The count is " + g.getCount(Location.B6));
				System.out.println("The count is " + g.getCount(Location.B7));
				System.out.println("The count is " + g.getCount(Location.B8));
				System.out.println("The count is " + g.getCount(Location.B9));
				throw new InvalidMoveException();
			}**/
		}
	}
	
	private TournamentMove[] makePlayerMoves(int numOfMoves) throws InvalidMoveException{
		TournamentMove[] moves = new TournamentMove[numOfMoves];
		int invalidMoves = 0;
		
		g.nextTurn();
		for (int i = 0; i < numOfMoves; i++){
			moves[i] = simpleMove();
			if (moves[i] != null){
				if (!g.move(moves[i].getFromLocation(), moves[i].getToLocation())){
					throw new InvalidMoveException();
				}
			} else {
				invalidMoves++;
			}
		}
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
	
	private TournamentMove simpleSelectMove(ArrayList<Location> myPieces, ArrayList<Location> opponentsPieces){
		if (myPieces.contains(Location.R_BAR) || myPieces.contains(Location.B_BAR)){
			TournamentMove temp = moveFromBar(myPieces, opponentsPieces);
			return temp;
		}
		Location array[] = new Location[myPieces.size()];
		myPieces.toArray(array);
		for (Location l : array){
			if (l == Location.R_BEAR_OFF || l == Location.B_BEAR_OFF){
			} else {
				for (int i : g.diceValuesLeft()){
					if (i > 0 && i <= 6){
						Location dest = Location.findLocation(myColor, l, i);
						if (MS.isMoveValid(g, l, dest)){
							return new TournamentMove(l, dest);
						}
					}
				}
			}
		}
		
		return null;
	}
	
	/**private TournamentMove determineNextMove(){
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
			return selectMove(redPieces, blackPieces);
		} else {
			return selectMove(blackPieces, redPieces);
		}
	}
	
	private TournamentMove selectMove(ArrayList<Location> myPieces, ArrayList<Location> opponentsPieces){
		if (myPieces.contains(Location.R_BAR) || myPieces.contains(Location.B_BAR)){
			TournamentMove temp = moveFromBar(myPieces, opponentsPieces);
			g.move(temp.getFromLocation(), temp.getToLocation());
			return temp;
		}
		Location bestDest = null;
		Location start = null;
		Location array[] = new Location[myPieces.size()];
		myPieces.toArray(array);
		for (Location l : array){
			Location temp = checkBestDestination(l, bestDest);
			if (temp != null){
				bestDest = temp;
				start = l;
			}
		}
	
		return new TournamentMove(start, bestDest);
	}
	
	private Location checkBestDestination(Location start, Location curBest){
		Stack<Location> possibleDests = new Stack<Location>();
		for (int i : g.diceValuesLeft()){
			if (i != -1){
				Location dest = Location.findLocation(myColor, start, i);
				if (MS.isMoveValid(g, start, dest)){
					possibleDests.push(dest);
				}
			}
		}
		
		boolean capture = false;
		boolean unsafe = false;
		boolean valid = false;
		Location best = possibleDests.peek();
		while (!possibleDests.isEmpty()){
			Location dest = possibleDests.pop();
			for(int i : g.diceValuesLeft()){
				if (i == Location.distance(start, dest)){
					valid = true;
					break;
				}
			}
			if (valid){
				if (g.getColor(dest) != myColor){
					capture = true;
				}
				if (g.getCount(start) == 1){
					unsafe = true;
				}
				
				if (capture && unsafe){
					best = dest;
					break;
				} else if (capture) {
					best = dest;
				} else if (unsafe){
					best = dest;
				} 
			}
		}
		return best;
	}**/
	
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
		
		return null;
	}

	public Game accessMyBoard(){
		return g;
	}
}
