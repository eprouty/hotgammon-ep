package betaMon;

import alphaMon.AlphaSetupStrategy;
import alphaMon.AlphaTurnStrategy;
import baseMon.factories.GammonFactory;
import baseMon.strategies.MoveStrategy;
import baseMon.strategies.SetupStrategy;
import baseMon.strategies.TurnStrategy;

public class BetaFactory implements GammonFactory {
	@Override
	public MoveStrategy createMoveStrategy() {
		return new BetaMoveStrategy();
	}

	@Override
	public TurnStrategy createTurnStrategy() {
		return new AlphaTurnStrategy();
	}

	@Override
	public SetupStrategy createSetupStrategy() {
		return new AlphaSetupStrategy();
	}
}
