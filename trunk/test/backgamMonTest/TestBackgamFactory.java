package backgamMonTest;

import epsilonMon.EpsilonTestDiceStrategy;
import backgamMon.BackgamMoveStrategy;
import backgamMon.backgamWinnerStrategy;
import baseMon.factories.GammonFactory;
import baseMon.strategies.DiceStrategy;
import baseMon.strategies.MoveStrategy;
import baseMon.strategies.SetupStrategy;
import baseMon.strategies.TurnStrategy;
import baseMon.strategies.WinnerStrategy;

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
		return new TestingSetupStrategy();
	}

	@Override
	public DiceStrategy createDiceStrategy() {
		return new EpsilonTestDiceStrategy();
	}

	@Override
	public WinnerStrategy createWinnerStrategy() {
		return new backgamWinnerStrategy();
	}

}
