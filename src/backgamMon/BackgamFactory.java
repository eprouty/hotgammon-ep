package backgamMon;

import baseMon.factories.GammonFactory;
import baseMon.strategies.DiceStrategy;
import baseMon.strategies.MoveStrategy;
import baseMon.strategies.SetupStrategy;
import baseMon.strategies.TurnStrategy;

public class BackgamFactory implements GammonFactory {

	@Override
	public MoveStrategy createMoveStrategy() {
		return null;
	}

	@Override
	public TurnStrategy createTurnStrategy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SetupStrategy createSetupStrategy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DiceStrategy createDiceStrategy() {
		// TODO Auto-generated method stub
		return null;
	}

}
