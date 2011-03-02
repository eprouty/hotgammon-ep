package hotgammon.playerep.MyBoard;

import hotgammon.Color;
import hotgammon.playerep.MyBoard.patterns.TurnStrategy;

public class BackgamTurnStrategy implements TurnStrategy {
	Color first;
	public BackgamTurnStrategy(Color firstToAct){
		this.first = firstToAct;
	}
	
	@Override
	public Color nextPlayerInTurn(Game g) {
		if (g.getPlayerInTurn() == Color.NONE){
			return first;
		}
		//sets the turn based on who currently has a turn
		switch(g.getPlayerInTurn()){
		case BLACK:
			return Color.RED;
		case RED:
			return Color.BLACK;
		}
		//final return if anything goes wrong with switch... just here to make java happy
		return null;
	}

}
