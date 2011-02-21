package backgamMon;

import baseMon.Color;
import baseMon.Game;
import baseMon.Location;
import baseMon.strategies.WinnerStrategy;

public class backgamWinnerStrategy implements WinnerStrategy {

	@Override
	public Color getWinner(Game g, int turnCount) {
		boolean blackWinner = true, redWinner = true;
		for (Location l : Location.values()){
			if (l != Location.B_BEAR_OFF && l != Location.R_BEAR_OFF){
				switch (g.getColor(l)){
				case BLACK:
					blackWinner = false;
					break;
				case RED:
					redWinner = false;
					break;	
				}
			}
		}
		if (blackWinner){
			return Color.BLACK;
		} else if (redWinner){
			return Color.RED;
		} else {
			return Color.NONE;
		}
	}

}
