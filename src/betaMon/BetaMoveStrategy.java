package betaMon;

import baseMon.Color;
import baseMon.Game;
import baseMon.Location;
import baseMon.MoveStrategy;

public class BetaMoveStrategy implements MoveStrategy{

	@Override
	public boolean isMoveValid(Game g, Location from, Location to) {
		//allow the game to move a piece to the bar
		if (to == Location.B_BAR || to == Location.R_BAR){
			return true;
		}
		
		boolean performCapture = false;
		switch (g.getColor(from)){
		case BLACK:
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
		}
		
		//Only movements relating to an available dice roll can be used
		int distTravelled = Math.abs(Location.distance(from, to));
		boolean valid = false;
		for (int d : g.diceValuesLeft()){
			if (d == distTravelled){
				valid = true;
				break;
			}
		}
		if (!valid){
			return false;
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
