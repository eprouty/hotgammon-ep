package hotgammon.playerep;

import static org.junit.Assert.*;

import hotgammon.Color;
import hotgammon.Location;
import hotgammon.playerep.MyBoard.Game;
import org.junit.Before;
import org.junit.Test;


public class TestPlayerEP {
	private TournamentPlayerImpl tp;
	  
	@Before public void setup() {
		tp = new TournamentPlayerImpl(null, Color.BLACK);
	}
	
	@Test public void testDiceAreSetWhenRecievedFromDirector(){
		tp.move(1, 2, null);
		Game g = tp.accessMyBoard();
		assertArrayEquals(new int[] {1,2}, g.diceThrown());
	}
	
	@Test public void movesAreRepresentedOnMyGameBoardWhenMade(){
		tp.move(1, 2, null);
		Game g = tp.accessMyBoard();
		assertEquals(Color.BLACK, g.getColor(Location.R2));
	}
}
