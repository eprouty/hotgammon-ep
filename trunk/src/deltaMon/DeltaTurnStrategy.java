package deltaMon;

import baseMon.Color;
import baseMon.Game;
import baseMon.strategies.TurnStrategy;

public class DeltaTurnStrategy implements TurnStrategy{

	@Override
	public Color nextPlayerInTurn(Game g) {
		int dice[] = g.diceThrown();
		if (dice[0] == 1 || dice [0] == 2){
			if (dice [0] != dice[1] && (dice[1] == 1 || dice[1] == 2)){
				return g.getPlayerInTurn();
			}
		}
		//sets the turn based on who currently has a turn
		switch(g.getPlayerInTurn()){
		case BLACK:
			return Color.RED;
		case RED:
			return Color.BLACK;
		//none -> black, since black goes first
		case NONE:
			return Color.BLACK;
		}
		//final return if anything goes wrong with switch... just here to make java happy
		return null;
	}

}
