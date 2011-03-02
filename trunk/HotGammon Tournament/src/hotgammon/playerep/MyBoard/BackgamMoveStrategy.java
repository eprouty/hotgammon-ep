package hotgammon.playerep.MyBoard;

import hotgammon.Color;
import hotgammon.Location;
import hotgammon.playerep.MyBoard.patterns.MoveStrategy;

public class BackgamMoveStrategy implements MoveStrategy {

	@Override
	public boolean isMoveValid(Game g, Location from, Location to) {
		//allow the game to move a piece to the bar
		if (to == Location.B_BAR || to == Location.R_BAR){
			return true;
		}
		
		boolean performCapture = false;
		switch (g.getColor(from)){
		case BLACK:
			if (g.getCount(Location.B_BAR) > 0 && from != Location.B_BAR){
				return false;
			}
			//Black pieces move from low # locations to high #, so the to cannot be smaller than from
			if (Location.distance(from, to) <= 0){
				return false;
			}
			//Black pieces cannot move to a RED controlled location
			if (g.getColor(to) == Color.RED){
				if (g.getCount(to) > 1){
					return false;
				}else if(g.getCount(to) == 1){
					performCapture = true;
				}
			}
			break;
		case RED:
			if (g.getCount(Location.R_BAR) > 0 && from != Location.R_BAR){
				return false;
			}
			//Red pieces move from high # locations to low #, so the to cannot be larger than from
			if (Location.distance(from, to) >= 0){
				return false;
			}
			//Red pieces cannot move to a BLACK controlled location
			if (g.getColor(to) == Color.BLACK){
				if (g.getCount(to) > 1){
					return false;
				}else if(g.getCount(to) == 1){
					performCapture = true;
				}
			}
			break;
		case NONE:
			return false;
		}
		int distTravelled = Math.abs(Location.distance(from, to));
		boolean performBearOff = (Location.B_BEAR_OFF == to);
		//Only movements relating to an available dice roll can be used, unless a proper bear off is being performed
		boolean valid = false;
		for (int d : g.diceValuesLeft()){
			if (d == distTravelled){
				valid = true;
				break;
			}
		}
		if (!valid && !performBearOff){
			return false;
		}

		boolean possibleBear = false;
		for (int d : g.diceValuesLeft()){
			if (d >= distTravelled){
				possibleBear = true;
			}
		}
		for (Location l : Location.values()){
			switch (g.getColor(from)){
			case RED:
				if (g.getColor(l) == Color.RED){
					if (Location.findLocation(g.getColor(from), l, 6) != Location.R_BEAR_OFF){
						possibleBear = false;
					}
				}
				break;
			case BLACK:
				if (g.getColor(l) == Color.BLACK){
					if (Location.findLocation(g.getColor(from), l, 6) != Location.B_BEAR_OFF){
						possibleBear = false;
					}
				}
				break;
			}
		}
		performBearOff = false;
		if (possibleBear){
			if (to == Location.B_BEAR_OFF && g.getColor(from) == Color.BLACK){
				for (int d : g.diceValuesLeft()){
					if (d == distTravelled){
						performBearOff = true;
						break;
					}
				}
				if (!performBearOff){
					for (Location l : Location.values()){
						if (Location.distance(from, l) < 0){
							if (g.getColor(l) == Color.BLACK){
								return false;
							}
						}
					}
				}
			} else if (to == Location.R_BEAR_OFF && g.getColor(from) == Color.RED){
				for (int d : g.diceValuesLeft()){
					if (d == distTravelled){
						performBearOff = true;
						break;
					}
				}
				if (!performBearOff){
					for (Location l : Location.values()){
						if (Location.distance(from, l) > 0){
							if (g.getColor(l) == Color.RED){
								return false;
							}
						}
					}
				} 
			}
		}
				
		/**Everything below this point affects the state of the game**/
		if (performCapture){
			switch(g.getColor(to)){
			case BLACK:
				g.move(to, Location.B_BAR);
				break;
			case RED:
				g.move(to, Location.R_BAR);
				break;
			}
			
		}
		
		return true;
	}

}
