package baseMon.strategies;

import baseMon.Color;
import baseMon.Game;

public interface TurnStrategy {
	public Color nextPlayerInTurn(Game g);
}
