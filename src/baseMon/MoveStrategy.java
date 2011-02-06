package baseMon;

public interface MoveStrategy {
	public boolean isMoveValid(Game g, Location from, Location to);
}
