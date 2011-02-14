package baseMon.factories;

import baseMon.Game;
import baseMon.strategies.MoveStrategy;
import baseMon.strategies.SetupStrategy;
import baseMon.strategies.TurnStrategy;

public interface GammonFactory {
	public MoveStrategy createMoveStrategy();
	public TurnStrategy createTurnStrategy();
	public SetupStrategy createSetupStrategy();
}
