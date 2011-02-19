package epsilonMon;

import alphaMon.AlphaMoveStrategy;
import alphaMon.AlphaSetupStrategy;
import alphaMon.AlphaTurnStrategy;
import baseMon.factories.GammonFactory;
import baseMon.strategies.DiceStrategy;
import baseMon.strategies.MoveStrategy;
import baseMon.strategies.SetupStrategy;
import baseMon.strategies.TurnStrategy;

public class EpsilonFactory implements GammonFactory {

	@Override
	public MoveStrategy createMoveStrategy() {
		return new AlphaMoveStrategy();
	}

	@Override
	public TurnStrategy createTurnStrategy() {
		return new AlphaTurnStrategy();
	}

	@Override
	public SetupStrategy createSetupStrategy() {
		return new AlphaSetupStrategy();
	}

	@Override
	public DiceStrategy createDiceStrategy() {
		return new EpsilonDiceStrategy();
	}
}
