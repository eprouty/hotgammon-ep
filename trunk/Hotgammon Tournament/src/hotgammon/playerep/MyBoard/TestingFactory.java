package hotgammon.playerep.MyBoard;

import hotgammon.Color;
import hotgammon.playerep.MyBoard.patterns.DiceStrategy;
import hotgammon.playerep.MyBoard.patterns.GammonFactory;
import hotgammon.playerep.MyBoard.patterns.MoveStrategy;
import hotgammon.playerep.MyBoard.patterns.SetupStrategy;
import hotgammon.playerep.MyBoard.patterns.TurnStrategy;
import hotgammon.playerep.MyBoard.patterns.WinnerStrategy;

public class TestingFactory implements GammonFactory {
	MockDice md;
	Color first;
	public TestingFactory(MockDice md, Color firstToAct){
		this.md = md;
		this.first = firstToAct;
	}
	@Override
	public MoveStrategy createMoveStrategy() {
		return new BackgamMoveStrategy();
	}

	@Override
	public TurnStrategy createTurnStrategy() {
		return new BackgamTurnStrategy(first);
	}

	@Override
	public SetupStrategy createSetupStrategy() {
		return new TestingSetupStrategy();
	}

	@Override
	public DiceStrategy createDiceStrategy() {
		return md;
	}

	@Override
	public WinnerStrategy createWinnerStrategy() {
		return new backgamWinnerStrategy();
	}

}
