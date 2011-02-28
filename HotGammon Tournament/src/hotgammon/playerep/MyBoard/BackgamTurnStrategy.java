package backgamMon;

import java.util.Random;

import baseMon.Color;
import baseMon.Game;
import baseMon.strategies.TurnStrategy;

public class BackgamTurnStrategy implements TurnStrategy {

	@Override
	public Color nextPlayerInTurn(Game g) {
		if (g.getPlayerInTurn() == Color.NONE){
			Random rg = new Random(System.currentTimeMillis());
			while(true){
				int redRoll = rg.nextInt(6) + 1;
				int blackRoll = rg.nextInt(6) + 1;
				if (redRoll > blackRoll){
					return Color.RED;
				} else if (redRoll < blackRoll){
					return Color.BLACK;
				}
			}
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
