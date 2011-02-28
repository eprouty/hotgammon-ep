package hotgammon.playerep.MyBoard;

import hotgammon.playerep.MyBoard.patterns.DiceStrategy;
import hotgammon.playerep.MyBoard.patterns.GammonFactory;
import hotgammon.playerep.MyBoard.patterns.MoveStrategy;
import hotgammon.playerep.MyBoard.patterns.SetupStrategy;
import hotgammon.playerep.MyBoard.patterns.TurnStrategy;
import hotgammon.playerep.MyBoard.patterns.WinnerStrategy;

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
		return null;
	}

	@Override
	public WinnerStrategy createWinnerStrategy() {
		return new backgamWinnerStrategy();
	}

}
