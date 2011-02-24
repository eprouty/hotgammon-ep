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

import hotgammon.*;

/**
 * This interface describes the services that students' final game player can
 * access during the final backgammon tournament.
 * 
 * This interface is a subset of the original Game interface provided by Henrik
 * Christensen.
 * 
 * @author gpollice
 * @version Feb 20, 2011
 */
public interface TournamentBoard
{
	  // == ACCESSORS to the board 

	  /** get the colour of the checkers on a given location.
	   * @param location the location on the board to access
	   * @return the color of the checkers on this location
	   */
	  public Color getColor(Location location);
	  
	  /** get the count of checkers of this location.
	   * @param location the location to inspect
	   * @return a integer value showing the number of checkers on this location.
	   */
	  public int getCount(Location location);
	  
}
