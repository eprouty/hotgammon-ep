/*******************************************************************************
 * This file is used in CS4233, Object-oriented Analysis and Design
 *
 * Copyright (c) 2010 Worcester Polytechnic Institute.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Author:
 *    gpollice
 *******************************************************************************/
package hotgammon.tournament;

import hotgammon.Color;

/**
 * This is the abstract class that the student's client module must implement. It
 * contains the methods that the tournament manager will call in order to get the
 * player's moves.
 * 
 * @author gpollice
 * @version Feb 20, 2011
 */
public abstract class TournamentPlayer
{
	/**
	 * The TournamentPlayer class is an abstract class that defines the interface
	 * for the student's client code. The tournament manager that runs the games will
	 * create a TournamentPlayer for each of the players in the game. Once the two
	 * players are created, the tournament manager repeatedly calls the <tt>move()</tt>
	 * methods in the proper order until there is a winner.
	 * 
	 * @param board the board for this game. The player can query this board to get
	 * information about the current state of the game.
	 * @param myColor the color for this player.
	 */
	public TournamentPlayer(TournamentBoard board, Color myColor)
	{
		// TODO
	}
	
	/**
	 * Given the dice roll, this method determines the checker moves that the player
	 * wants to make. The player must make the correct number of moves upon 
	 * returning from this method and return those moves in the result.
	 * 
	 * <b>Note:</b> If the moves returned are not legal or the player does not make
	 * the correct number of moves, the player loses the game.
	 * 
	 * @param die1 the value of the first die.
	 * @param die2 the value of the second die.
	 * @param oponentsLastMove The array of moves that your opponent just made. This
	 * 	is the value returned from calling your opponent's move() method.
	 * @return An array of moves. If no moves can be made, this will be an empty
	 * 	array (not null). The moves are in the order to be executed. That is, the first
	 *	element of the array is the first move to be made.
	 */
	public abstract TournamentMove[] move(int die1, int die2,
			TournamentMove[] opponentsLastMove);
}
