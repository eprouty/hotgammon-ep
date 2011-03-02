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
import java.util.Stack;

import hotgammon.Color;
import hotgammon.Location;
import hotgammon.playerep.MyBoard.BackgamFactory;
import hotgammon.playerep.MyBoard.BackgamMoveStrategy;
import hotgammon.playerep.MyBoard.Game;
import hotgammon.playerep.MyBoard.GameImpl;
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
	private int d1,d2;
	private MoveStrategy MS = new BackgamMoveStrategy();
	int[] diceLeft;
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
	public TournamentMove[] move(int die1, int die2, TournamentMove[] opponentsLastMove){
		d1 = die1;
		d2 = die2;
		if (firstTurn){
			if (opponentsLastMove == null){
				g = new GameImpl(new BackgamFactory(md, myColor));
			} else {
				switch(myColor){
				case BLACK:
					g = new GameImpl(new BackgamFactory(md, Color.RED));
					break;
				case RED:
					g = new GameImpl(new BackgamFactory(md, Color.BLACK));	
				}
			}
			
			g.newGame();
		}
		
		makeOpponentsMoves(opponentsLastMove);
		md.setDice(die1, die2);
		int numberOfMoves = 2;
		if (die1 == die2){
			numberOfMoves = 4;
			diceLeft = new int[] {d1, d1, d1, d1};
		} else {
			diceLeft = new int[] {d1, d2};
		}
		
		return makePlayerMoves(numberOfMoves);
	}
	
	private void makeOpponentsMoves(TournamentMove[] lastMoves){
		if (lastMoves == null){
			return;
		}
		
		g.nextTurn();
		
		for (TournamentMove move : lastMoves){
			g.move(move.getFromLocation(), move.getToLocation());
		}
	}
	
	private TournamentMove[] makePlayerMoves(int numOfMoves){
		TournamentMove[] moves = new TournamentMove[numOfMoves];
		
		g.nextTurn();
		for (int i = 0; i < moves.length; i++){
			moves[i] = determineNextMove();
		}
		
		return moves;
	}
	
	private TournamentMove determineNextMove(){
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
		}
		Location bestDest = null;
		Location start = null;
		for (Location l : (Location[])myPieces.toArray()){
			Location temp = checkBestDestination(l, bestDest);
			if (temp != null){
				bestDest = temp;
				start = l;
			}
		}
		
		g.move(start, bestDest);
		return new TournamentMove(start, bestDest);
	}
	
	private Location checkBestDestination(Location start, Location curBest){
		Stack<Location> possibleDests = new Stack<Location>();
		for (int i : diceLeft){
			Location dest = Location.findLocation(myColor, start, i);
			if (MS.isMoveValid(g, start, dest)){
				possibleDests.push(dest);
			}
		}
		
		boolean capture = false;
		boolean unsafe = false;
		
		Location best = null;
		while (!possibleDests.isEmpty()){
			Location dest = possibleDests.pop();
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
		
		return best;
	}
	
	private TournamentMove moveFromBar(ArrayList<Location> myPieces, ArrayList<Location> opponentsPieces){
		Location locD1, locD2;
		if (myColor == Color.RED){
			locD1 = Location.findLocation(myColor, Location.R_BAR, d1);
			locD2 = Location.findLocation(myColor, Location.R_BAR, d2);
			if (g.getColor(locD1) == Color.RED){
				return new TournamentMove(Location.R_BAR, locD1);
			} else {
				return new TournamentMove(Location.R_BAR, locD2);
			}
		} else {
			locD1 = Location.findLocation(myColor, Location.B_BAR, d1);
			locD2 = Location.findLocation(myColor, Location.B_BAR, d2);
			if (g.getColor(locD1) == Color.BLACK){
				return new TournamentMove(Location.B_BAR, locD1);
			} else {
				return new TournamentMove(Location.B_BAR, locD2);
			}
		} 
	}

	public Game accessMyBoard(){
		return g;
	}
}
