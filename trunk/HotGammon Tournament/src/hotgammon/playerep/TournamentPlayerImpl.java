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

import hotgammon.Color;
import hotgammon.playerep.MyBoard.BackgamFactory;
import hotgammon.playerep.MyBoard.Game;
import hotgammon.playerep.MyBoard.GameImpl;
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
	/**
	 * Constructor.
	 * 
	 * @see hotgammon.tournament.TournamentPlayer(TournamentBoard, Color)
	 */
	public TournamentPlayerImpl(TournamentBoard board, Color myColor)
	{
		super(board, myColor);
		
		Game g = new GameImpl(new BackgamFactory());
	}

	/* (non-Javadoc)
	 * @see hotgammon.tournament.TournamentPlayer#move(int, int)
	 */
	@Override
	public TournamentMove[] move(int die1, int die2, TournamentMove[] opponentsLastMove)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
