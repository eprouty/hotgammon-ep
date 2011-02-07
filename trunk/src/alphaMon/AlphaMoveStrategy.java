package alphaMon;

import baseMon.Color;
import baseMon.Game;
import baseMon.Location;
import baseMon.strategies.MoveStrategy;

public class AlphaMoveStrategy implements MoveStrategy{
	
	@Override
	public boolean isMoveValid(Game g, Location from, Location to){
		//cannot move to a place occupied by the opponent
		Color toColor = g.getColor(to);
		Color fromColor = g.getColor(from);
		switch(toColor){
		case RED:
			if (fromColor == Color.BLACK){
				return false;
			}
			break;
		case BLACK:
			if (fromColor == Color.RED){
				return false;
			}
			break;
		}
		
		//cannot make a movement if the from location is empty
		//cannot move if the piece belongs to the opponent
		switch(g.getPlayerInTurn()){
		case RED:
			if(fromColor != Color.RED){
				return false;
			}
			break;
		case BLACK:
			if(fromColor != Color.BLACK){
				return false;
			}
			break;
		//just return something is wrong anyways...
		default:
			return false;	
		}
		
		return true;
	}
}
