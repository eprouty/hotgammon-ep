package deltaMon;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import baseMon.Color;
import baseMon.Game;
import baseMon.GameImpl;


public class TestDeltaMon {
private Game game;
	
	@Before public void setup() {
	    game = new GameImpl(new DeltaFactory());
	    game.newGame();
	}
	
	@Test public void testExtraTurnIf1and2Rolled(){
		game.nextTurn();
		assertEquals(Color.BLACK, game.getPlayerInTurn());
		game.nextTurn();
		assertEquals(Color.BLACK, game.getPlayerInTurn());
	}
	
	@Test public void testRestLikeAlpha(){
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		assertEquals(Color.RED, game.getPlayerInTurn());
		game.nextTurn();
		assertEquals(Color.BLACK, game.getPlayerInTurn());
	}
}
