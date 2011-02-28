package backgamMon;

import epsilonMon.EpsilonDiceStrategy;
import alphaMon.AlphaSetupStrategy;
import baseMon.factories.GammonFactory;
import baseMon.strategies.DiceStrategy;
import baseMon.strategies.MoveStrategy;
import baseMon.strategies.SetupStrategy;
import baseMon.strategies.TurnStrategy;
import baseMon.strategies.WinnerStrategy;

public class BackgamFactory implements GammonFactory {

	@Override
	public MoveStrategy createMoveStrategy() {
		return new BackgamMoveStrategy();
	}

	@Override
	public TurnStrategy createTurnStrategy() {
		return new BackgamTurnStrategy();
	}

	@Override
	public SetupStrategy createSetupStrategy() {
		return new AlphaSetupStrategy();
	}

	@Override
	public DiceStrategy createDiceStrategy() {
		return new EpsilonDiceStrategy();
	}

	@Override
	public WinnerStrategy createWinnerStrategy() {
		return new backgamWinnerStrategy();
	}

}
