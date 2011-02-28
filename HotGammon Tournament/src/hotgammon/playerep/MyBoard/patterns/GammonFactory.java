package baseMon.factories;

import baseMon.strategies.DiceStrategy;
import baseMon.strategies.MoveStrategy;
import baseMon.strategies.SetupStrategy;
import baseMon.strategies.TurnStrategy;
import baseMon.strategies.WinnerStrategy;

public interface GammonFactory {
	public MoveStrategy createMoveStrategy();
	public TurnStrategy createTurnStrategy();
	public SetupStrategy createSetupStrategy();
	public DiceStrategy createDiceStrategy();
	public WinnerStrategy createWinnerStrategy();
}
