package deltaMon;

import alphaMon.AlphaDiceStrategy;
import alphaMon.AlphaMoveStrategy;
import alphaMon.AlphaSetupStrategy;
import baseMon.factories.GammonFactory;
import baseMon.strategies.DiceStrategy;
import baseMon.strategies.MoveStrategy;
import baseMon.strategies.SetupStrategy;
import baseMon.strategies.TurnStrategy;

public class DeltaFactory implements GammonFactory {
	@Override
	public MoveStrategy createMoveStrategy() {
		return new AlphaMoveStrategy();
	}

	@Override
	public TurnStrategy createTurnStrategy() {
		return new DeltaTurnStrategy();
	}

	@Override
	public SetupStrategy createSetupStrategy() {
		return new AlphaSetupStrategy();
	}

	@Override
	public DiceStrategy createDiceStrategy() {
		return new AlphaDiceStrategy();
	}
}
