package backgamMonTest;

import epsilonMon.EpsilonTestDiceStrategy;
import alphaMon.AlphaSetupStrategy;
import backgamMon.BackgamMoveStrategy;
import baseMon.factories.GammonFactory;
import baseMon.strategies.DiceStrategy;
import baseMon.strategies.MoveStrategy;
import baseMon.strategies.SetupStrategy;
import baseMon.strategies.TurnStrategy;

public class TestBackgamFactory implements GammonFactory{

	@Override
	public MoveStrategy createMoveStrategy() {
		return new BackgamMoveStrategy();
	}

	@Override
	public TurnStrategy createTurnStrategy() {
		return new TestBackgamTurnStrategy();
	}

	@Override
	public SetupStrategy createSetupStrategy() {
		return new AlphaSetupStrategy();
	}

	@Override
	public DiceStrategy createDiceStrategy() {
		return new EpsilonTestDiceStrategy();
	}

}
