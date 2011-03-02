package hotgammon.playerep.MyBoard.patterns;

public interface GammonFactory {
	public MoveStrategy createMoveStrategy();
	public TurnStrategy createTurnStrategy();
	public SetupStrategy createSetupStrategy();
	public DiceStrategy createDiceStrategy();
	public WinnerStrategy createWinnerStrategy();
}
