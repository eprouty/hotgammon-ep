package zetaMon;

import alphaMon.AlphaDiceStrategy;
import alphaMon.AlphaMoveStrategy;
import alphaMon.AlphaTurnStrategy;
import alphaMon.AlphaWinnerStrategy;
import baseMon.factories.GammonFactory;
import baseMon.strategies.DiceStrategy;
import baseMon.strategies.MoveStrategy;
import baseMon.strategies.SetupStrategy;
import baseMon.strategies.TurnStrategy;
import baseMon.strategies.WinnerStrategy;

public class ZetaFactory implements GammonFactory{
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
		return new ZetaSetupStrategy();
	}

	@Override
	public DiceStrategy createDiceStrategy() {
		return new AlphaDiceStrategy();
	}

	@Override
	public WinnerStrategy createWinnerStrategy() {
		return new AlphaWinnerStrategy();
	}

}
