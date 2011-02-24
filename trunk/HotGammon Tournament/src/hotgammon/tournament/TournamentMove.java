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

import hotgammon.Location;

/**
 * The TournamentMove class is a simple data structure that identifies one move
 * on the board.
 * 
 * @author gpollice
 * @version Feb 20, 2011
 */
public class TournamentMove
{
	final private Location fromLocation;
	final private Location toLocation;
	
	public TournamentMove(Location fromLocation, Location toLocation)
	{
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
	}

	/**
	 * @return the fromLocation
	 */
	final public Location getFromLocation()
	{
		return fromLocation;
	}

	/**
	 * @return the toLocation
	 */
	final public Location getToLocation()
	{
		return toLocation;
	}
}
